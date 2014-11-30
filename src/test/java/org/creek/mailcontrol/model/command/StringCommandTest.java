package org.creek.mailcontrol.model.command;

import static org.creek.mailcontrol.model.command.CommandType.STRING;
import static org.junit.Assert.assertEquals;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.creek.mailcontrol.model.types.StringDataType;
import org.creek.mailcontrol.model.util.JSONTransformer;

/**
 * 
 * @author Andrey.Pereverzin
 */
public class StringCommandTest {
    private static final String STRING_VALUE = "someValue";
    
    private StringCommand command;
    
    @Test
    public void shouldTransformStringCommand() throws ParseException {
        // given
        StringDataType value = new StringDataType(STRING_VALUE);
        command = new StringCommand(value);

        // when
        JSONObject jsonCommand = command.toJSON();
        String s = jsonCommand.toString();
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);

        // {"value":"someValue","commandType":"STRING"}
        JSONObject res = (JSONObject) transformer.getResult();
        StringCommand commandRes = new StringCommand(res);
        
        // then
        assertEquals(STRING, commandRes.getCommandType());
        assertEquals(STRING_VALUE, commandRes.getData().toString());
    }
    
    @Test(expected=Throwable.class)
    public void shouldThrowExceptionIfWrongElementName() throws ParseException {
        // given
        String s = "{\"commandType\":\"STRING\",\"value1\":\"someValue\"}";

        // when
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);
        JSONObject res = (JSONObject) transformer.getResult();
        new StringCommand(res);

        // then
        // throw exception
    }
}
