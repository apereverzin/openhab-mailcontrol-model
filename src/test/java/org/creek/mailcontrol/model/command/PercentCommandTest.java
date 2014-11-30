package org.creek.mailcontrol.model.command;

import static org.creek.mailcontrol.model.command.CommandType.PERCENT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.creek.mailcontrol.model.types.PercentDataType;
import org.creek.mailcontrol.model.util.JSONTransformer;

/**
 * 
 * @author Andrey.Pereverzin
 */
public class PercentCommandTest {
    private static final String PERCENT_VALUE = "12";
    
    private PercentCommand command;
    
    @Test
    public void shouldTransformPercentCommand() throws ParseException {
        // given
        command = new PercentCommand(PERCENT_VALUE);

        // when
        JSONObject jsonCommand = command.toJSON();
        String s = jsonCommand.toString();
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);

        // {"value":"12","commandType":"PERCENT"}
        JSONObject res = (JSONObject) transformer.getResult();
        PercentCommand commandRes = new PercentCommand(res);
        
        // then
        assertEquals(PERCENT, commandRes.getCommandType());
        assertEquals(PERCENT_VALUE, ((PercentDataType)commandRes.getData()).toString());
    }
    
    @Test(expected=Throwable.class)
    public void shouldThrowExceptionIfWrongValue() throws ParseException {
        // given
        String s = "{\"commandType\":\"PERCENT\",\"value\":\"ABC\"}";

        // when
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);
        JSONObject res = (JSONObject) transformer.getResult();
        new PercentCommand(res);

        // then
        // throw exception
    }
    
    @Test(expected=Throwable.class)
    public void shouldThrowExceptionIfWrongElementName() throws ParseException {
        // given
        String s = "{\"commandType\":\"PERCENT\",\"value1\":\"12\"}";

        // when
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);
        JSONObject res = (JSONObject) transformer.getResult();
        new PercentCommand(res);

        // then
        // throw exception
    }
}
