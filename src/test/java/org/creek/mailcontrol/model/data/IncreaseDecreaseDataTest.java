package org.creek.mailcontrol.model.data;

import static org.creek.mailcontrol.model.data.DataType.INCREASE_DECREASE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.creek.mailcontrol.model.types.IncreaseDecreaseDataType.DECREASE;
import static org.creek.mailcontrol.model.types.IncreaseDecreaseDataType.INCREASE;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.creek.mailcontrol.model.data.IncreaseDecreaseData;
import org.creek.mailcontrol.model.types.IncreaseDecreaseDataType;
import org.creek.mailcontrol.model.util.JSONTransformer;

/**
 * 
 * @author Andrey.Pereverzin
 */
public class IncreaseDecreaseDataTest {
    private IncreaseDecreaseData data;
    
    @Test
    public void shouldTransformIncreaseData() throws ParseException {
        // given
        IncreaseDecreaseDataType value = INCREASE;
        data = new IncreaseDecreaseData(value);

        // when
        JSONObject jsonData = data.toJSON();
        String s = jsonData.toString();
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);

        // {"type":"INCREASE_DECREASE","value":"INCREASE"}
        JSONObject res = (JSONObject) transformer.getResult();
        IncreaseDecreaseData dataRes = new IncreaseDecreaseData(res);
        
        // then
        assertEquals(INCREASE_DECREASE, dataRes.getCommandType());
        assertEquals(value, dataRes.getData());
    }
    
    @Test
    public void shouldTransformDecreaseData() throws ParseException {
        // given
        IncreaseDecreaseDataType value = DECREASE;
        data = new IncreaseDecreaseData(value);

        // when
        JSONObject jsonData = data.toJSON();
        String s = jsonData.toString();
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);

        // {"type":"INCREASE_DECREASE","value":"DECREASE"}
        JSONObject res = (JSONObject) transformer.getResult();
        IncreaseDecreaseData dataRes = new IncreaseDecreaseData(res);
        
        // then
        assertEquals(INCREASE_DECREASE, dataRes.getCommandType());
        assertEquals(value, dataRes.getData());
    }
    
    @Test
    public void shouldToStringWork() throws ParseException {
        // given
        data = new IncreaseDecreaseData(DECREASE);

        // when
        String s = data.toString();
        
        // then
        assertTrue(s.contains(IncreaseDecreaseData.class.getName()));
    }
    
    @Test(expected=Throwable.class)
    public void shouldThrowExceptionIfWrongValue() throws ParseException {
        // given
        String s = "{\"type\":\"INCREASE_DECREASE\",\"value\":\"ABC\"}";

        // when
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);
        JSONObject res = (JSONObject) transformer.getResult();
        new IncreaseDecreaseData(res);

        // then
        // throw exception
    }
    
    @Test(expected=Throwable.class)
    public void shouldThrowExceptionIfWrongElementName() throws ParseException {
        // given
        String s = "{\"type\":\"INCREASE_DECREASE\",\"value1\":\"INCREASE\"}";

        // when
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);
        JSONObject res = (JSONObject) transformer.getResult();
        new IncreaseDecreaseData(res);

        // then
        // throw exception
    }
}
