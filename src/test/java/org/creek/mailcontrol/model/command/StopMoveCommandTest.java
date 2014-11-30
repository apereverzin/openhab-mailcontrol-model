package org.creek.mailcontrol.model.command;

import static org.creek.mailcontrol.model.command.CommandType.STOP_MOVE;
import static org.junit.Assert.assertEquals;
import static org.creek.mailcontrol.model.types.StopMoveDataType.MOVE;
import static org.creek.mailcontrol.model.types.StopMoveDataType.STOP;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.creek.mailcontrol.model.types.StopMoveDataType;
import org.creek.mailcontrol.model.util.JSONTransformer;

/**
 * 
 * @author Andrey.Pereverzin
 */
public class StopMoveCommandTest {
    private StopMoveCommand command;
    
    @Test
    public void shouldTransformStopCommand() throws ParseException {
        // given
        StopMoveDataType value = STOP;
        command = new StopMoveCommand(value);

        // when
        JSONObject jsonCommand = command.toJSON();
        String s = jsonCommand.toString();
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);

        // {"commandType":"STOP_MOVE","value":"STOP"}
        JSONObject res = (JSONObject) transformer.getResult();
        StopMoveCommand commandRes = new StopMoveCommand(res);
        
        // then
        assertEquals(STOP_MOVE, commandRes.getCommandType());
        assertEquals(value, commandRes.getData());
    }
    
    @Test
    public void shouldTransformMoveCommand() throws ParseException {
        // given
        StopMoveDataType value = MOVE;
        command = new StopMoveCommand(value);

        // when
        JSONObject jsonCommand = command.toJSON();
        String s = jsonCommand.toString();
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);

        // {"commandType":"STOP_MOVE","value":"MOVE"}
        JSONObject res = (JSONObject) transformer.getResult();
        StopMoveCommand commandRes = new StopMoveCommand(res);
        
        // then
        assertEquals(STOP_MOVE, commandRes.getCommandType());
        assertEquals(value, commandRes.getData());
    }
    
    @Test(expected=Throwable.class)
    public void shouldThrowExceptionIfWrongValue() throws ParseException {
        // given
        String s = "{\"commandType\":\"STOP_MOVE\",\"value\":\"ABC\"}";

        // when
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);
        JSONObject res = (JSONObject) transformer.getResult();
        new StopMoveCommand(res);

        // then
        // throw exception
    }
    
    @Test(expected=Throwable.class)
    public void shouldThrowExceptionIfWrongElementName() throws ParseException {
        // given
        String s = "{\"commandType\":\"STOP_MOVE\",\"value1\":\"STOP\"}";

        // when
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);
        JSONObject res = (JSONObject) transformer.getResult();
        new StopMoveCommand(res);

        // then
        // throw exception
    }
}
