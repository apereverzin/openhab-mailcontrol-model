package org.creek.mailcontrol.model.data;

import static org.creek.mailcontrol.model.data.DataType.UP_DOWN;
import static org.junit.Assert.assertEquals;
import static org.creek.mailcontrol.model.types.UpDownDataType.DOWN;
import static org.creek.mailcontrol.model.types.UpDownDataType.UP;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.creek.mailcontrol.model.data.UpDownData;
import org.creek.mailcontrol.model.types.UpDownDataType;
import org.creek.mailcontrol.model.util.JSONTransformer;

/**
 * 
 * @author Andrey.Pereverzin
 */
public class UpDownDataTest {
    private UpDownData data;
    
    @Test
    public void shouldTransformUpData() throws ParseException {
        // given
        UpDownDataType value = UP;
        data = new UpDownData(value);

        // when
        JSONObject jsonData = data.toJSON();
        String s = jsonData.toString();
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);

        // {"value":"UP","type":"UP_DOWN"}
        JSONObject res = (JSONObject) transformer.getResult();
        UpDownData dataRes = new UpDownData(res);
        
        // then
        assertEquals(UP_DOWN, dataRes.getStateType());
        assertEquals(value, dataRes.getData());
    }
    
    @Test
    public void shouldTransformDownData() throws ParseException {
        // given
        UpDownDataType value = DOWN;
        data = new UpDownData(value);

        // when
        JSONObject jsonData = data.toJSON();
        String s = jsonData.toString();
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);

        // {"value":"DOWN","type":"UP_DOWN"}
        JSONObject res = (JSONObject) transformer.getResult();
        UpDownData dataRes = new UpDownData(res);
        
        // then
        assertEquals(UP_DOWN, dataRes.getStateType());
        assertEquals(value, dataRes.getData());
    }
    
    @Test(expected=Throwable.class)
    public void shouldThrowExceptionIfWrongValue() throws ParseException {
        // given
        String s = "{\"type\":\"UP_DOWN\",\"value\":\"ABC\"}";

        // when
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);
        JSONObject res = (JSONObject) transformer.getResult();
        new UpDownData(res);

        // then
        // throw exception
    }
    
    @Test(expected=Throwable.class)
    public void shouldThrowExceptionIfWrongElementName() throws ParseException {
        // given
        String s = "{\"type\":\"UP_DOWN\",\"value1\":\"UP\"}";

        // when
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);
        JSONObject res = (JSONObject) transformer.getResult();
        new UpDownData(res);

        // then
        // throw exception
    }
}
