package org.creek.mailcontrol.model.data;

import static org.creek.mailcontrol.model.data.DataType.ON_OFF;
import static org.junit.Assert.assertEquals;
import static org.creek.mailcontrol.model.types.OnOffDataType.OFF;
import static org.creek.mailcontrol.model.types.OnOffDataType.ON;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.creek.mailcontrol.model.data.OnOffData;
import org.creek.mailcontrol.model.types.OnOffDataType;
import org.creek.mailcontrol.model.util.JSONTransformer;

/**
 * 
 * @author Andrey.Pereverzin
 */
public class OnOffDataTest {
    private OnOffData command;
    
    @Test
    public void shouldTransformOnCommand() throws ParseException {
        // given
        OnOffDataType value = ON;
        command = new OnOffData(value);

        // when
        JSONObject jsonCommand = command.toJSON();
        String s = jsonCommand.toString();
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);

        // {"commandType":"ON_OFF","value":"ON"}
        JSONObject res = (JSONObject) transformer.getResult();
        OnOffData commandRes = new OnOffData(res);
        
        // then
        assertEquals(ON_OFF, commandRes.getStateType());
        assertEquals(value, commandRes.getData());
    }
    
    @Test
    public void shouldTransformOffCommand() throws ParseException {
        // given
        OnOffDataType value = OFF;
        command = new OnOffData(value);

        // when
        JSONObject jsonCommand = command.toJSON();
        String s = jsonCommand.toString();
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);

        // {"commandType":"ON_OFF","value":"OFF"}
        JSONObject res = (JSONObject) transformer.getResult();
        OnOffData commandRes = new OnOffData(res);
        
        // then
        assertEquals(ON_OFF, commandRes.getStateType());
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
        new OnOffData(res);

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
        new OnOffData(res);

        // then
        // throw exception
    }
}