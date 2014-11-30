package org.creek.mailcontrol.model.command;

import static org.creek.mailcontrol.model.command.CommandType.HSB;
import static org.junit.Assert.assertEquals;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.creek.mailcontrol.model.types.HSBDataType;
import org.creek.mailcontrol.model.util.JSONTransformer;

/**
 * 
 * @author Andrey.Pereverzin
 */
public class HSBCommandTest {
    private static final long HUE_VALUE = 10;
    private static final long SATURATION_VALUE = 20;
    private static final long BRIGHTNESS_VALUE = 30;
    
    private HSBCommand command;
    
    @Test
    public void shouldTransformColorCommand() throws ParseException {
        // given
        command = new HSBCommand(HUE_VALUE, SATURATION_VALUE, BRIGHTNESS_VALUE);

        // when
        JSONObject jsonCommand = command.toJSON();
        String s = jsonCommand.toString();
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);

        // {"brightness":30,"saturation":20,"commandType":"HSB","hue":10}
        JSONObject res = (JSONObject) transformer.getResult();
        HSBCommand commandRes = new HSBCommand(res);
        
        // then
        assertEquals(HSB, commandRes.getCommandType());
        assertEquals(((HSBDataType)commandRes.getData()).getHue(), HUE_VALUE);
        assertEquals(((HSBDataType)commandRes.getData()).getSaturation(), SATURATION_VALUE);
        assertEquals(((HSBDataType)commandRes.getData()).getBrightness(), BRIGHTNESS_VALUE);
    }
    
    @Test(expected=Throwable.class)
    public void shouldThrowExceptionIfWrongHueElementName() throws ParseException {
        // given
        String s = "{\"brightness\":30,\"saturation\":20,\"commandType\":\"HSB\",\"hue1\":10}";

        // when
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);
        JSONObject res = (JSONObject) transformer.getResult();
        new HSBCommand(res);

        // then
        // throw exception
    }
    
    @Test(expected=Throwable.class)
    public void shouldThrowExceptionIfWrongHueValue() throws ParseException {
        // given
        String s = "{\"brightness\":30,\"saturation\":20,\"commandType\":\"HSB\",\"hue\":\"ABC\"}";

        // when
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);
        JSONObject res = (JSONObject) transformer.getResult();
        new HSBCommand(res);

        // then
        // throw exception
    }
    
    @Test(expected=Throwable.class)
    public void shouldThrowExceptionIfWrongSaturationElementName() throws ParseException {
        // given
        String s = "{\"brightness\":30,\"saturation1\":20,\"commandType\":\"HSB\",\"hue\":10}";

        // when
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);
        JSONObject res = (JSONObject) transformer.getResult();
        new HSBCommand(res);

        // then
        // throw exception
    }
    
    @Test(expected=Throwable.class)
    public void shouldThrowExceptionIfWrongSaturationValue() throws ParseException {
        // given
        String s = "{\"brightness\":30,\"saturation\":ABC,\"commandType\":\"HSB\",\"hue\":\"10\"}";

        // when
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);
        JSONObject res = (JSONObject) transformer.getResult();
        new HSBCommand(res);

        // then
        // throw exception
    }
    
    @Test(expected=Throwable.class)
    public void shouldThrowExceptionIfWrongBrightnessElementName() throws ParseException {
        // given
        String s = "{\"brightness1\":30,\"saturation\":20,\"commandType\":\"HSB\",\"hue\":10}";

        // when
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);
        JSONObject res = (JSONObject) transformer.getResult();
        new HSBCommand(res);

        // then
        // throw exception
    }
    
    @Test(expected=Throwable.class)
    public void shouldThrowExceptionIfWrongBrightnessValue() throws ParseException {
        // given
        String s = "{\"brightness\":ABC,\"saturation\":20,\"commandType\":\"HSB\",\"hue\":\"10\"}";

        // when
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);
        JSONObject res = (JSONObject) transformer.getResult();
        new HSBCommand(res);

        // then
        // throw exception
    }
}
