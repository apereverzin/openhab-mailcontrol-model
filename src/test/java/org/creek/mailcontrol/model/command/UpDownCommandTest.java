package org.creek.mailcontrol.model.command;

import static org.creek.mailcontrol.model.command.CommandType.UP_DOWN;
import static org.junit.Assert.assertEquals;
import static org.creek.mailcontrol.model.types.UpDownDataType.DOWN;
import static org.creek.mailcontrol.model.types.UpDownDataType.UP;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.creek.mailcontrol.model.types.UpDownDataType;
import org.creek.mailcontrol.model.util.JSONTransformer;

/**
 * 
 * @author Andrey.Pereverzin
 */
public class UpDownCommandTest {
    private UpDownCommand command;
    
    @Test
    public void shouldTransformUpCommand() throws ParseException {
        // given
        UpDownDataType value = UP;
        command = new UpDownCommand(value);

        // when
        JSONObject jsonCommand = command.toJSON();
        String s = jsonCommand.toString();
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);

        // {"value":"UP","commandType":"UP_DOWN"}
        JSONObject res = (JSONObject) transformer.getResult();
        UpDownCommand commandRes = new UpDownCommand(res);
        
        // then
        assertEquals(UP_DOWN, commandRes.getCommandType());
        assertEquals(value, commandRes.getData());
    }
    
    @Test
    public void shouldTransformDownCommand() throws ParseException {
        // given
        UpDownDataType value = DOWN;
        command = new UpDownCommand(value);

        // when
        JSONObject jsonCommand = command.toJSON();
        String s = jsonCommand.toString();
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);

        // {"value":"DOWN","commandType":"UP_DOWN"}
        JSONObject res = (JSONObject) transformer.getResult();
        UpDownCommand commandRes = new UpDownCommand(res);
        
        // then
        assertEquals(UP_DOWN, commandRes.getCommandType());
        assertEquals(value, commandRes.getData());
    }
    
    @Test(expected=Throwable.class)
    public void shouldThrowExceptionIfWrongValue() throws ParseException {
        // given
        String s = "{\"commandType\":\"UP_DOWN\",\"value\":\"ABC\"}";

        // when
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);
        JSONObject res = (JSONObject) transformer.getResult();
        new UpDownCommand(res);

        // then
        // throw exception
    }
    
    @Test(expected=Throwable.class)
    public void shouldThrowExceptionIfWrongElementName() throws ParseException {
        // given
        String s = "{\"commandType\":\"UP_DOWN\",\"value1\":\"UP\"}";

        // when
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);
        JSONObject res = (JSONObject) transformer.getResult();
        new UpDownCommand(res);

        // then
        // throw exception
    }
}
