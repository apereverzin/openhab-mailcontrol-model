package org.creek.mailcontrol.model.data;

import static org.creek.mailcontrol.model.data.DataType.STOP_MOVE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.creek.mailcontrol.model.types.StopMoveDataType.MOVE;
import static org.creek.mailcontrol.model.types.StopMoveDataType.STOP;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.creek.mailcontrol.model.data.StopMoveData;
import org.creek.mailcontrol.model.types.StopMoveDataType;
import org.creek.mailcontrol.model.util.JSONTransformer;

/**
 * 
 * @author Andrey.Pereverzin
 */
public class StopMoveDataTest {
    private StopMoveData data;
    
    @Test
    public void shouldTransformStopData() throws ParseException {
        // given
        StopMoveDataType value = STOP;
        data = new StopMoveData(value);

        // when
        JSONObject jsonData = data.toJSON();
        String s = jsonData.toString();
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);

        // {"type":"STOP_MOVE","value":"STOP"}
        JSONObject res = (JSONObject) transformer.getResult();
        StopMoveData dataRes = new StopMoveData(res);
        
        // then
        assertEquals(STOP_MOVE, dataRes.getCommandType());
        assertEquals(value, dataRes.getData());
    }
    
    @Test
    public void shouldTransformMoveData() throws ParseException {
        // given
        StopMoveDataType value = MOVE;
        data = new StopMoveData(value);

        // when
        JSONObject jsonData = data.toJSON();
        String s = jsonData.toString();
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);

        // {"type":"STOP_MOVE","value":"MOVE"}
        JSONObject res = (JSONObject) transformer.getResult();
        StopMoveData dataRes = new StopMoveData(res);
        
        // then
        assertEquals(STOP_MOVE, dataRes.getCommandType());
        assertEquals(value, dataRes.getData());
    }
    
    @Test
    public void shouldToStringWork() throws ParseException {
        // given
        data = new StopMoveData(MOVE);

        // when
        String s = data.toString();
        
        // then
        assertTrue(s.contains(StopMoveData.class.getName()));
    }
    
    @Test(expected=Throwable.class)
    public void shouldThrowExceptionIfWrongValue() throws ParseException {
        // given
        String s = "{\"type\":\"STOP_MOVE\",\"value\":\"ABC\"}";

        // when
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);
        JSONObject res = (JSONObject) transformer.getResult();
        new StopMoveData(res);

        // then
        // throw exception
    }
    
    @Test(expected=Throwable.class)
    public void shouldThrowExceptionIfWrongElementName() throws ParseException {
        // given
        String s = "{\"type\":\"STOP_MOVE\",\"value1\":\"STOP\"}";

        // when
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);
        JSONObject res = (JSONObject) transformer.getResult();
        new StopMoveData(res);

        // then
        // throw exception
    }
}
