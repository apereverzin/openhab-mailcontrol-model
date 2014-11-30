package org.creek.mailcontrol.model.command;

import static org.creek.mailcontrol.model.command.CommandType.OPEN_CLOSED;
import static org.junit.Assert.assertEquals;
import static org.creek.mailcontrol.model.types.OpenClosedDataType.OPEN;
import static org.creek.mailcontrol.model.types.OpenClosedDataType.CLOSED;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.creek.mailcontrol.model.types.OpenClosedDataType;
import org.creek.mailcontrol.model.util.JSONTransformer;

/**
 * 
 * @author Andrey.Pereverzin
 */
public class OpenClosedCommandTest {
    private OpenClosedCommand command;
    
    @Test
    public void shouldTransformOpenCommand() throws ParseException {
        // given
        OpenClosedDataType value = OPEN;
        command = new OpenClosedCommand(value);

        // when
        JSONObject jsonCommand = command.toJSON();
        String s = jsonCommand.toString();
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);

        // {"value":"OPEN","commandType":"OPEN_CLOSED"}
        JSONObject res = (JSONObject) transformer.getResult();
        OpenClosedCommand commandRes = new OpenClosedCommand(res);
        
        // then
        assertEquals(OPEN_CLOSED, commandRes.getCommandType());
        assertEquals(value, commandRes.getData());
    }
    
    @Test
    public void shouldTransformClosedCommand() throws ParseException {
        // given
        OpenClosedDataType value = CLOSED;
        command = new OpenClosedCommand(value);

        // when
        JSONObject jsonCommand = command.toJSON();
        String s = jsonCommand.toString();
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);

        // {"value":"CLOSED","commandType":"OPEN_CLOSED"}
        JSONObject res = (JSONObject) transformer.getResult();
        OpenClosedCommand commandRes = new OpenClosedCommand(res);
        
        // then
        assertEquals(OPEN_CLOSED, commandRes.getCommandType());
        assertEquals(value, commandRes.getData());
    }
    
    @Test(expected=Throwable.class)
    public void shouldThrowExceptionIfWrongValue() throws ParseException {
        // given
        String s = "{\"commandType\":\"OPEN_CLOSED\",\"value\":\"ABC\"}";

        // when
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);
        JSONObject res = (JSONObject) transformer.getResult();
        new OpenClosedCommand(res);

        // then
        // throw exception
    }
    
    @Test(expected=Throwable.class)
    public void shouldThrowExceptionIfWrongElementName() throws ParseException {
        // given
        String s = "{\"commandType\":\"OPEN_CLOSED\",\"value1\":\"OPEN\"}";

        // when
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);
        JSONObject res = (JSONObject) transformer.getResult();
        new OpenClosedCommand(res);

        // then
        // throw exception
    }
}
