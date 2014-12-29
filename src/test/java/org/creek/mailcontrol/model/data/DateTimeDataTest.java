package org.creek.mailcontrol.model.data;

import static org.creek.mailcontrol.model.data.DataType.DATE_TIME;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.creek.mailcontrol.model.data.DateTimeData;
import org.creek.mailcontrol.model.types.DateTimeDataType;
import org.creek.mailcontrol.model.util.JSONTransformer;

/**
 * 
 * @author Andrey.Pereverzin
 */
public class DateTimeDataTest {
    private static final String DATE_TIME_VALUE = "2014-12-22 19:59:53";
    
    private DateTimeData data;
    
    @Test
    public void shouldTransformDateTimeState() throws ParseException {
        // given
        DateTimeDataType value = new DateTimeDataType(DATE_TIME_VALUE);
        data = new DateTimeData(value);

        // when
        JSONObject jsonData = data.toJSON();
        String s = jsonData.toString();
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);

        // {"value":"someValue","type":"DATE_TIME"}
        JSONObject res = (JSONObject) transformer.getResult();
        DateTimeData dataRes = new DateTimeData(res);
        
        // then
        assertEquals(DATE_TIME, dataRes.getStateType());
        assertEquals(DATE_TIME_VALUE, dataRes.getData().toString());
    }
    
    @Test
    public void shouldToStringWork() throws ParseException {
        // given
        DateTimeDataType value = new DateTimeDataType(DATE_TIME_VALUE);
        data = new DateTimeData(value);

        // when
        String s = data.toString();
        
        // then
        assertTrue(s.contains(DateTimeData.class.getName()));
    }
    
    @Test(expected=Throwable.class)
    public void shouldThrowExceptionIfWrongElementName() throws ParseException {
        // given
        String s = "{\"type\":\"DATE_TIME\",\"value1\":\"someValue\"}";

        // when
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);
        JSONObject res = (JSONObject) transformer.getResult();
        new DateTimeData(res);

        // then
        // throw exception
    }
}
