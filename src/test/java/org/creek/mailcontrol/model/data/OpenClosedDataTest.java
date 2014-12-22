package org.creek.mailcontrol.model.data;

import static org.creek.mailcontrol.model.data.DataType.OPEN_CLOSED;
import static org.junit.Assert.assertEquals;
import static org.creek.mailcontrol.model.types.OpenClosedDataType.OPEN;
import static org.creek.mailcontrol.model.types.OpenClosedDataType.CLOSED;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.creek.mailcontrol.model.data.OpenClosedData;
import org.creek.mailcontrol.model.types.OpenClosedDataType;
import org.creek.mailcontrol.model.util.JSONTransformer;

/**
 * 
 * @author Andrey.Pereverzin
 */
public class OpenClosedDataTest {
    private OpenClosedData data;
    
    @Test
    public void shouldTransformOpenData() throws ParseException {
        // given
        OpenClosedDataType value = OPEN;
        data = new OpenClosedData(value);

        // when
        JSONObject jsonData = data.toJSON();
        String s = jsonData.toString();
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);

        // {"value":"OPEN","type":"OPEN_CLOSED"}
        JSONObject res = (JSONObject) transformer.getResult();
        OpenClosedData dataRes = new OpenClosedData(res);
        
        // then
        assertEquals(OPEN_CLOSED, dataRes.getStateType());
        assertEquals(value, dataRes.getData());
    }
    
    @Test
    public void shouldTransformClosedData() throws ParseException {
        // given
        OpenClosedDataType value = CLOSED;
        data = new OpenClosedData(value);

        // when
        JSONObject jsonData = data.toJSON();
        String s = jsonData.toString();
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);

        // {"value":"CLOSED","type":"OPEN_CLOSED"}
        JSONObject res = (JSONObject) transformer.getResult();
        OpenClosedData dataRes = new OpenClosedData(res);
        
        // then
        assertEquals(OPEN_CLOSED, dataRes.getStateType());
        assertEquals(value, dataRes.getData());
    }
    
    @Test(expected=Throwable.class)
    public void shouldThrowExceptionIfWrongValue() throws ParseException {
        // given
        String s = "{\"type\":\"OPEN_CLOSED\",\"value\":\"ABC\"}";

        // when
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);
        JSONObject res = (JSONObject) transformer.getResult();
        new OpenClosedData(res);

        // then
        // throw exception
    }
    
    @Test(expected=Throwable.class)
    public void shouldThrowExceptionIfWrongElementName() throws ParseException {
        // given
        String s = "{\"type\":\"OPEN_CLOSED\",\"value1\":\"OPEN\"}";

        // when
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);
        JSONObject res = (JSONObject) transformer.getResult();
        new OpenClosedData(res);

        // then
        // throw exception
    }
}
