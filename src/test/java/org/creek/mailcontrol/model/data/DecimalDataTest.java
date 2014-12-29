package org.creek.mailcontrol.model.data;

import static org.creek.mailcontrol.model.data.DataType.DECIMAL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.creek.mailcontrol.model.data.DecimalData;
import org.creek.mailcontrol.model.util.JSONTransformer;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

/**
 * 
 * @author Andrey.Pereverzin
 */
public class DecimalDataTest {
    private static final String DECIMAL_VALUE = "1.2";
    private static final String WRONG_DECIMAL_VALUE = "abc";
    
    private DecimalData data;
    
    @Test
    public void shouldTransformDecimalData() throws ParseException {
        // given
        data = new DecimalData(DECIMAL_VALUE);

        // when
        JSONObject jsonData = data.toJSON();
        String s = jsonData.toString();
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);

        // {"type":"DECIMAL","value":"1.2"}
        JSONObject res = (JSONObject) transformer.getResult();
        DecimalData dataRes = new DecimalData(res);
        
        // then
        assertEquals(DECIMAL, dataRes.getStateType());
        assertEquals(DECIMAL, dataRes.getCommandType());
        assertEquals(DECIMAL_VALUE, dataRes.getData().toString());
    }
    
    @Test
    public void shouldToStringWork() throws ParseException {
        // given
        data = new DecimalData(DECIMAL_VALUE);

        // when
        String s = data.toString();
        
        // then
        assertTrue(s.contains(DecimalData.class.getName()));
    }
    
    @Test(expected=Throwable.class)
    public void shouldThrowExceptionIfWrongDecimalValue() throws ParseException {
        // given
        data = new DecimalData(WRONG_DECIMAL_VALUE);
    }
    
    @Test(expected=Throwable.class)
    public void shouldThrowExceptionIfWrongValue() throws ParseException {
        // given
        String s = "{\"type\":\"DECIMAL\",\"value\":\"ABC\"}";

        // when
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);
        JSONObject res = (JSONObject) transformer.getResult();
        new DecimalData(res);

        // then
        // throw exception
    }
    
    @Test(expected=Throwable.class)
    public void shouldThrowExceptionIfWrongElementName() throws ParseException {
        // given
        String s = "{\"type\":\"DECIMAL\",\"value1\":\"1.2\"}";

        // when
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);
        JSONObject res = (JSONObject) transformer.getResult();
        new DecimalData(res);

        // then
        // throw exception
    }
}
