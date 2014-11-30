package org.creek.mailcontrol.model.command;

import static org.creek.mailcontrol.model.command.CommandType.INCREASE_DECREASE;
import static org.junit.Assert.assertEquals;
import static org.creek.mailcontrol.model.types.IncreaseDecreaseDataType.DECREASE;
import static org.creek.mailcontrol.model.types.IncreaseDecreaseDataType.INCREASE;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.creek.mailcontrol.model.types.IncreaseDecreaseDataType;
import org.creek.mailcontrol.model.util.JSONTransformer;

/**
 * 
 * @author Andrey.Pereverzin
 */
public class IncreaseDecreaseCommandTest {
    private IncreaseDecreaseCommand command;
    
    @Test
    public void shouldTransformIncreaseCommand() throws ParseException {
        // given
        IncreaseDecreaseDataType value = INCREASE;
        command = new IncreaseDecreaseCommand(value);

        // when
        JSONObject jsonCommand = command.toJSON();
        String s = jsonCommand.toString();
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);

        // {"commandType":"INCREASE_DECREASE","value":"INCREASE"}
        JSONObject res = (JSONObject) transformer.getResult();
        IncreaseDecreaseCommand commandRes = new IncreaseDecreaseCommand(res);
        
        // then
        assertEquals(INCREASE_DECREASE, commandRes.getCommandType());
        assertEquals(value, commandRes.getData());
    }
    
    @Test
    public void shouldTransformDecreaseCommand() throws ParseException {
        // given
        IncreaseDecreaseDataType value = DECREASE;
        command = new IncreaseDecreaseCommand(value);

        // when
        JSONObject jsonCommand = command.toJSON();
        String s = jsonCommand.toString();
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);

        // {"commandType":"INCREASE_DECREASE","value":"DECREASE"}
        JSONObject res = (JSONObject) transformer.getResult();
        IncreaseDecreaseCommand commandRes = new IncreaseDecreaseCommand(res);
        
        // then
        assertEquals(INCREASE_DECREASE, commandRes.getCommandType());
        assertEquals(value, commandRes.getData());
    }
    
    @Test(expected=Throwable.class)
    public void shouldThrowExceptionIfWrongValue() throws ParseException {
        // given
        String s = "{\"commandType\":\"INCREASE_DECREASE\",\"value\":\"ABC\"}";

        // when
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);
        JSONObject res = (JSONObject) transformer.getResult();
        new IncreaseDecreaseCommand(res);

        // then
        // throw exception
    }
    
    @Test(expected=Throwable.class)
    public void shouldThrowExceptionIfWrongElementName() throws ParseException {
        // given
        String s = "{\"commandType\":\"INCREASE_DECREASE\",\"value1\":\"INCREASE\"}";

        // when
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);
        JSONObject res = (JSONObject) transformer.getResult();
        new IncreaseDecreaseCommand(res);

        // then
        // throw exception
    }
}
