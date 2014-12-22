package org.creek.mailcontrol.model.data;

import static org.creek.mailcontrol.model.data.DataType.PERCENT;
import static org.junit.Assert.assertEquals;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.creek.mailcontrol.model.data.PercentData;
import org.creek.mailcontrol.model.types.PercentDataType;
import org.creek.mailcontrol.model.util.JSONTransformer;

/**
 * 
 * @author Andrey.Pereverzin
 */
public class PercentDataTest {
    private static final String PERCENT_VALUE = "12";
    
    private PercentData data;
    
    @Test
    public void shouldTransformPercentData() throws ParseException {
        // given
        data = new PercentData(PERCENT_VALUE);

        // when
        JSONObject jsonData = data.toJSON();
        String s = jsonData.toString();
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);

        // {"value":"12","type":"PERCENT"}
        JSONObject res = (JSONObject) transformer.getResult();
        PercentData dataRes = new PercentData(res);
        
        // then
        assertEquals(PERCENT, dataRes.getStateType());
        assertEquals(PERCENT_VALUE, ((PercentDataType)dataRes.getData()).toString());
    }
    
    @Test(expected=Throwable.class)
    public void shouldThrowExceptionIfWrongValue() throws ParseException {
        // given
        String s = "{\"type\":\"PERCENT\",\"value\":\"ABC\"}";

        // when
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);
        JSONObject res = (JSONObject) transformer.getResult();
        new PercentData(res);

        // then
        // throw exception
    }
    
    @Test(expected=Throwable.class)
    public void shouldThrowExceptionIfWrongElementName() throws ParseException {
        // given
        String s = "{\"type\":\"PERCENT\",\"value1\":\"12\"}";

        // when
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);
        JSONObject res = (JSONObject) transformer.getResult();
        new PercentData(res);

        // then
        // throw exception
    }
}
