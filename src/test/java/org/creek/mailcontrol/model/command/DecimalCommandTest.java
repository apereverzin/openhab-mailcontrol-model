package org.creek.mailcontrol.model.command;

import static org.creek.mailcontrol.model.command.CommandType.DECIMAL;
import static org.junit.Assert.assertEquals;

import org.creek.mailcontrol.model.util.JSONTransformer;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

/**
 * 
 * @author Andrey.Pereverzin
 */
public class DecimalCommandTest {
    private static final String DECIMAL_VALUE = "1.2";
    private static final String WRONG_DECIMAL_VALUE = "abc";
    
    private DecimalCommand command;
    
    @Test
    public void shouldTransformDecimalCommand() throws ParseException {
        // given
        command = new DecimalCommand(DECIMAL_VALUE);

        // when
        JSONObject jsonCommand = command.toJSON();
        String s = jsonCommand.toString();
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);

        // {"commandType":"DECIMAL","value":"1.2"}
        JSONObject res = (JSONObject) transformer.getResult();
        DecimalCommand commandRes = new DecimalCommand(res);
        
        // then
        assertEquals(DECIMAL, commandRes.getCommandType());
        assertEquals(DECIMAL_VALUE, commandRes.getData().toString());
    }
    
    @Test(expected=Throwable.class)
    public void shouldThrowExceptionIfWrongDecimalValue() throws ParseException {
        // given
        command = new DecimalCommand(WRONG_DECIMAL_VALUE);
    }
    
    @Test(expected=Throwable.class)
    public void shouldThrowExceptionIfWrongValue() throws ParseException {
        // given
        String s = "{\"commandType\":\"DECIMAL\",\"value\":\"ABC\"}";

        // when
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);
        JSONObject res = (JSONObject) transformer.getResult();
        new DecimalCommand(res);

        // then
        // throw exception
    }
    
    @Test(expected=Throwable.class)
    public void shouldThrowExceptionIfWrongElementName() throws ParseException {
        // given
        String s = "{\"commandType\":\"DECIMAL\",\"value1\":\"1.2\"}";

        // when
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);
        JSONObject res = (JSONObject) transformer.getResult();
        new DecimalCommand(res);

        // then
        // throw exception
    }
}
