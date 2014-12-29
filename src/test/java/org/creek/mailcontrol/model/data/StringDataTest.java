package org.creek.mailcontrol.model.data;

import static org.creek.mailcontrol.model.data.DataType.STRING;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.creek.mailcontrol.model.data.StringData;
import org.creek.mailcontrol.model.types.StringDataType;
import org.creek.mailcontrol.model.util.JSONTransformer;

/**
 * 
 * @author Andrey.Pereverzin
 */
public class StringDataTest {
    private static final String STRING_VALUE = "someValue";
    
    private StringData data;
    
    @Test
    public void shouldTransformStringData() throws ParseException {
        // given
        StringDataType value = new StringDataType(STRING_VALUE);
        data = new StringData(value);

        // when
        JSONObject jsonData = data.toJSON();
        String s = jsonData.toString();
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);

        // {"value":"someValue","type":"STRING"}
        JSONObject res = (JSONObject) transformer.getResult();
        StringData dataRes = new StringData(res);
        
        // then
        assertEquals(STRING, dataRes.getStateType());
        assertEquals(STRING, dataRes.getCommandType());
        assertEquals(STRING_VALUE, dataRes.getData().toString());
    }
    
    @Test
    public void shouldToStringWork() throws ParseException {
        // given
        StringDataType value = new StringDataType(STRING_VALUE);
        data = new StringData(value);

        // when
        String s = data.toString();
        
        // then
        assertTrue(s.contains(StringData.class.getName()));
    }
    
    @Test(expected=Throwable.class)
    public void shouldThrowExceptionIfWrongElementName() throws ParseException {
        // given
        String s = "{\"type\":\"STRING\",\"value1\":\"someValue\"}";

        // when
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);
        JSONObject res = (JSONObject) transformer.getResult();
        new StringData(res);

        // then
        // throw exception
    }
}
