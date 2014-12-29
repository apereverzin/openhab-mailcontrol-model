package org.creek.mailcontrol.model.data;

import static org.creek.mailcontrol.model.data.DataType.HSB;
import static org.creek.mailcontrol.model.data.DataType.INCREASE_DECREASE;
import static org.creek.mailcontrol.model.data.DataType.ON_OFF;
import static org.creek.mailcontrol.model.data.DataType.PERCENT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.creek.mailcontrol.model.data.DecimalData;
import org.creek.mailcontrol.model.types.DateTimeDataType;
import org.creek.mailcontrol.model.types.DecimalDataType;
import org.creek.mailcontrol.model.types.HSBDataType;
import org.creek.mailcontrol.model.types.OnOffDataType;
import org.creek.mailcontrol.model.types.OpenClosedDataType;
import org.creek.mailcontrol.model.types.PercentDataType;
import org.creek.mailcontrol.model.types.StringDataType;
import org.creek.mailcontrol.model.types.UpDownDataType;
import org.creek.mailcontrol.model.util.JSONTransformer;

/**
 * 
 * @author Andrey.Pereverzin
 */
public class ItemStateDataTest {
    private static final String DATE_TIME_VALUE = "2014-12-22 19:59:53";
    private static final double DECIMAL_VALUE = 1.2;
    private static final int PERCENT_VALUE = 22;
    private static final String STRING_VALUE = "abcd";
    private static final long TIMESTAMP = 0L;
    private static final String ITEM_ID = "LIGHT";
    private static final long HUE_VALUE = 360;
    private static final long SATURATION_VALUE = 99;
    private static final long BRIGHTNESS_VALUE = 98;
    
    private ItemStateData itemState;
    private StateTransformable state;
    private List<DataType> acceptedCommands;
    
    @Test
    public void shouldTransformDateTimeState() throws ParseException {
        // given
        state = new DateTimeData(new DateTimeDataType(DATE_TIME_VALUE));
        acceptedCommands = buildAcceptedCommandsList(INCREASE_DECREASE, ON_OFF);
        itemState = new ItemStateData(TIMESTAMP, ITEM_ID, state, acceptedCommands);

        // when
        JSONTransformer transformer = transformData();

        // {"itemId":"LIGHT","acceptedCommands":["INCREASE_DECREASE","ON_OFF"],"timeSent":"0","state":{"value":"2014-12-22 19:59:53","type":"DATE_TIME"}}
        JSONObject res = (JSONObject) transformer.getResult();
        ItemStateData itemStateRes = new ItemStateData(res);
        
        // then
        assertEquals(TIMESTAMP, itemStateRes.getTimeSent());
        assertEquals(ITEM_ID, ((ItemStateData)itemStateRes).getItemId());
        assertEquals(DateTimeData.class.getName(), itemStateRes.getState().getClass().getName());
        DateTimeData dataRes = (DateTimeData)itemStateRes.getState();
        assertEquals(DATE_TIME_VALUE, dataRes.getData().toString());
        List<DataType> acceptedCommandsRes = itemStateRes.getAcceptedCommands();
        assertEquals(2, acceptedCommandsRes.size());
        assertTrue(acceptedCommandsRes.contains(INCREASE_DECREASE));
        assertTrue(acceptedCommandsRes.contains(ON_OFF));
    }
    
    @Test
    public void shouldTransformDecimalState() throws ParseException {
        // given
        state = new DecimalData(new DecimalDataType(Double.toString(DECIMAL_VALUE)));
        acceptedCommands = buildAcceptedCommandsList();
        itemState = new ItemStateData(TIMESTAMP, ITEM_ID, state, acceptedCommands);

        // when
        JSONTransformer transformer = transformData();

        // {"itemId":"LIGHT","acceptedCommands":[],"timeSent":"0","state":{"value":"1.2","type":"DECIMAL"}}
        JSONObject res = (JSONObject) transformer.getResult();
        ItemStateData itemStateRes = new ItemStateData(res);
        
        // then
        assertEquals(TIMESTAMP, itemStateRes.getTimeSent());
        assertEquals(ITEM_ID, ((ItemStateData)itemStateRes).getItemId());
        assertEquals(DecimalData.class.getName(), itemStateRes.getState().getClass().getName());
        DecimalData dataRes = (DecimalData)itemStateRes.getState();
        assertEquals(DECIMAL_VALUE, dataRes.getData().doubleValue());
        List<DataType> acceptedCommandsRes = itemStateRes.getAcceptedCommands();
        assertEquals(0, acceptedCommandsRes.size());
    }
    
    @Test
    public void shouldTransformHSBState() throws ParseException {
        // given
        state = new HSBData(new HSBDataType(HUE_VALUE, SATURATION_VALUE, BRIGHTNESS_VALUE));
        acceptedCommands = buildAcceptedCommandsList(HSB, ON_OFF);
        itemState = new ItemStateData(TIMESTAMP, ITEM_ID, state, acceptedCommands);

        // when
        JSONTransformer transformer = transformData();

        // {"timeSent":"0","itemId":"LIGHT","acceptedCommands":["HSB","ON_OFF"],"state":{"saturation":99,"type":"HSB","hue":360,"brightness":98}}
        JSONObject res = (JSONObject) transformer.getResult();
        ItemStateData itemStateRes = new ItemStateData(res);
        
        // then
        assertEquals(TIMESTAMP, itemStateRes.getTimeSent());
        assertEquals(ITEM_ID, ((ItemStateData)itemStateRes).getItemId());
        assertEquals(HSBData.class.getName(), itemStateRes.getState().getClass().getName());
        HSBData dataRes = (HSBData)itemStateRes.getState();
        assertEquals(HUE_VALUE, dataRes.getData().getHue());
        assertEquals(SATURATION_VALUE, dataRes.getData().getSaturation());
        assertEquals(BRIGHTNESS_VALUE, dataRes.getData().getBrightness());
        List<DataType> acceptedCommandsRes = itemStateRes.getAcceptedCommands();
        assertEquals(2, acceptedCommandsRes.size());
        assertTrue(acceptedCommandsRes.contains(ON_OFF));
        assertTrue(acceptedCommandsRes.contains(HSB));
    }
    
    @Test
    public void shouldTransformOnOffState() throws ParseException {
        // given
        state = new OnOffData(OnOffDataType.ON);
        acceptedCommands = buildAcceptedCommandsList(INCREASE_DECREASE, ON_OFF);
        itemState = new ItemStateData(TIMESTAMP, ITEM_ID, state, acceptedCommands);

        // when
        JSONTransformer transformer = transformData();

        // {"itemId":"LIGHT","acceptedCommands":["INCREASE_DECREASE","ON_OFF"],"state":{"type":"ON_OFF","value":"ON"},"timeSent":"0"}
        JSONObject res = (JSONObject) transformer.getResult();
        ItemStateData itemStateRes = new ItemStateData(res);
        
        // then
        assertEquals(TIMESTAMP, itemStateRes.getTimeSent());
        assertEquals(ITEM_ID, ((ItemStateData)itemStateRes).getItemId());
        assertEquals(OnOffData.class.getName(), itemStateRes.getState().getClass().getName());
        OnOffData dataRes = (OnOffData)itemStateRes.getState();
        assertEquals(OnOffDataType.ON, dataRes.getData());
        List<DataType> acceptedCommandsRes = itemStateRes.getAcceptedCommands();
        assertEquals(2, acceptedCommandsRes.size());
        assertTrue(acceptedCommandsRes.contains(INCREASE_DECREASE));
        assertTrue(acceptedCommandsRes.contains(ON_OFF));
    }
    
    @Test
    public void shouldTransformOpenClosedState() throws ParseException {
        // given
        state = new OpenClosedData(OpenClosedDataType.OPEN);
        acceptedCommands = buildAcceptedCommandsList(INCREASE_DECREASE, ON_OFF, PERCENT);
        itemState = new ItemStateData(TIMESTAMP, ITEM_ID, state, acceptedCommands);

        // when
        JSONTransformer transformer = transformData();

        // {"timeSent":"0","state":{"value":"OPEN","type":"OPEN_CLOSED"},"itemId":"LIGHT","acceptedCommands":["INCREASE_DECREASE","ON_OFF","PERCENT"]}
        JSONObject res = (JSONObject) transformer.getResult();
        ItemStateData itemStateRes = new ItemStateData(res);
        
        // then
        assertEquals(TIMESTAMP, itemStateRes.getTimeSent());
        assertEquals(ITEM_ID, ((ItemStateData)itemStateRes).getItemId());
        assertEquals(OpenClosedData.class.getName(), itemStateRes.getState().getClass().getName());
        OpenClosedData dataRes = (OpenClosedData)itemStateRes.getState();
        assertEquals(OpenClosedDataType.OPEN, dataRes.getData());
        List<DataType> acceptedCommandsRes = itemStateRes.getAcceptedCommands();
        assertEquals(3, acceptedCommandsRes.size());
        assertTrue(acceptedCommandsRes.contains(INCREASE_DECREASE));
        assertTrue(acceptedCommandsRes.contains(ON_OFF));
        assertTrue(acceptedCommandsRes.contains(PERCENT));
    }
    
    @Test
    public void shouldTransformPercentState() throws ParseException {
        // given
        state = new PercentData(new PercentDataType(Integer.toString(PERCENT_VALUE)));
        acceptedCommands = buildAcceptedCommandsList(INCREASE_DECREASE, ON_OFF);
        itemState = new ItemStateData(TIMESTAMP, ITEM_ID, state, acceptedCommands);

        // when
        JSONTransformer transformer = transformData();

        // {"state":{"value":"22","type":"PERCENT"},"itemId":"LIGHT","acceptedCommands":["INCREASE_DECREASE","ON_OFF"],"timeSent":"0"}
        JSONObject res = (JSONObject) transformer.getResult();
        ItemStateData itemStateRes = new ItemStateData(res);
        
        // then
        assertEquals(TIMESTAMP, itemStateRes.getTimeSent());
        assertEquals(ITEM_ID, ((ItemStateData)itemStateRes).getItemId());
        assertEquals(PercentData.class.getName(), itemStateRes.getState().getClass().getName());
        PercentData dataRes = (PercentData)itemStateRes.getState();
        assertEquals(PERCENT_VALUE, dataRes.getData().intValue());
        List<DataType> acceptedCommandsRes = itemStateRes.getAcceptedCommands();
        assertEquals(2, acceptedCommandsRes.size());
        assertTrue(acceptedCommandsRes.contains(INCREASE_DECREASE));
        assertTrue(acceptedCommandsRes.contains(ON_OFF));
    }
    
    @Test
    public void shouldTransformStringState() throws ParseException {
        // given
        state = new StringData(new StringDataType(STRING_VALUE));
        acceptedCommands = buildAcceptedCommandsList(INCREASE_DECREASE, ON_OFF);
        itemState = new ItemStateData(TIMESTAMP, ITEM_ID, state, acceptedCommands);

        // when
        JSONTransformer transformer = transformData();

        // {"timeSent":"0","itemId":"LIGHT","acceptedCommands":["INCREASE_DECREASE","ON_OFF"],"state":{"value":"abcd","type":"STRING"}}
        JSONObject res = (JSONObject) transformer.getResult();
        ItemStateData itemStateRes = new ItemStateData(res);
        
        // then
        assertEquals(TIMESTAMP, itemStateRes.getTimeSent());
        assertEquals(ITEM_ID, ((ItemStateData)itemStateRes).getItemId());
        assertEquals(StringData.class.getName(), itemStateRes.getState().getClass().getName());
        StringData dataRes = (StringData)itemStateRes.getState();
        assertEquals(STRING_VALUE, dataRes.getData().toString());
        List<DataType> acceptedCommandsRes = itemStateRes.getAcceptedCommands();
        assertEquals(2, acceptedCommandsRes.size());
        assertTrue(acceptedCommandsRes.contains(INCREASE_DECREASE));
        assertTrue(acceptedCommandsRes.contains(ON_OFF));
    }
    
    @Test
    public void shouldTransformUpDownState() throws ParseException {
        // given
        state = new UpDownData(UpDownDataType.DOWN);
        acceptedCommands = buildAcceptedCommandsList(INCREASE_DECREASE, ON_OFF);
        itemState = new ItemStateData(TIMESTAMP, ITEM_ID, state, acceptedCommands);

        // when
        JSONTransformer transformer = transformData();

        // {"state":{"value":"DOWN","type":"UP_DOWN"},"itemId":"LIGHT","acceptedCommands":["INCREASE_DECREASE","ON_OFF"],"timeSent":"0"}
        JSONObject res = (JSONObject) transformer.getResult();
        ItemStateData itemStateRes = new ItemStateData(res);
        
        // then
        assertEquals(TIMESTAMP, itemStateRes.getTimeSent());
        assertEquals(ITEM_ID, ((ItemStateData)itemStateRes).getItemId());
        assertEquals(UpDownData.class.getName(), itemStateRes.getState().getClass().getName());
        UpDownData dataRes = (UpDownData)itemStateRes.getState();
        assertEquals(UpDownDataType.DOWN, dataRes.getData());
        List<DataType> acceptedCommandsRes = itemStateRes.getAcceptedCommands();
        assertEquals(2, acceptedCommandsRes.size());
        assertTrue(acceptedCommandsRes.contains(INCREASE_DECREASE));
        assertTrue(acceptedCommandsRes.contains(ON_OFF));
    }

    private JSONTransformer transformData() throws ParseException {
        JSONObject jsonState = itemState.toJSON();
        String s = jsonState.toString();
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);
        return transformer;
    }
    
    private List<DataType> buildAcceptedCommandsList(DataType... dataTypes) {
        List<DataType> acceptedCommands = new ArrayList<DataType>();
        
        for (DataType dataType: dataTypes) {
            acceptedCommands.add(dataType);
        }
        
        return acceptedCommands;
    }
}
