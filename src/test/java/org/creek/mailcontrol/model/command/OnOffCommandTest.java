package org.creek.mailcontrol.model.command;

import static org.creek.mailcontrol.model.command.CommandType.ON_OFF;
import static org.junit.Assert.assertEquals;
import static org.creek.mailcontrol.model.types.OnOffDataType.OFF;
import static org.creek.mailcontrol.model.types.OnOffDataType.ON;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.creek.mailcontrol.model.types.OnOffDataType;
import org.creek.mailcontrol.model.util.JSONTransformer;

/**
 * 
 * @author Andrey.Pereverzin
 */
public class OnOffCommandTest {
    private OnOffCommand command;
    
    @Test
    public void shouldTransformOnCommand() throws ParseException {
        // given
        OnOffDataType value = ON;
        command = new OnOffCommand(value);

        // when
        JSONObject jsonCommand = command.toJSON();
        String s = jsonCommand.toString();
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);

        // {"commandType":"ON_OFF","value":"ON"}
        JSONObject res = (JSONObject) transformer.getResult();
        OnOffCommand commandRes = new OnOffCommand(res);
        
        // then
        assertEquals(ON_OFF, commandRes.getCommandType());
        assertEquals(value, commandRes.getData());
    }
    
    @Test
    public void shouldTransformOffCommand() throws ParseException {
        // given
        OnOffDataType value = OFF;
        command = new OnOffCommand(value);

        // when
        JSONObject jsonCommand = command.toJSON();
        String s = jsonCommand.toString();
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);

        // {"commandType":"ON_OFF","value":"OFF"}
        JSONObject res = (JSONObject) transformer.getResult();
        OnOffCommand commandRes = new OnOffCommand(res);
        
        // then
        assertEquals(ON_OFF, commandRes.getCommandType());
        assertEquals(value, commandRes.getData());
    }
    
    @Test(expected=Throwable.class)
    public void shouldThrowExceptionIfWrongValue() throws ParseException {
        // given
        String s = "{\"commandType\":\"ON_OFF\",\"value\":\"ABC\"}";

        // when
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);
        JSONObject res = (JSONObject) transformer.getResult();
        new OnOffCommand(res);

        // then
        // throw exception
    }
    
    @Test(expected=Throwable.class)
    public void shouldThrowExceptionIfWrongElementName() throws ParseException {
        // given
        String s = "{\"commandType\":\"ON_OFF\",\"value1\":\"ON\"}";

        // when
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);
        JSONObject res = (JSONObject) transformer.getResult();
        new OnOffCommand(res);

        // then
        // throw exception
    }
}
