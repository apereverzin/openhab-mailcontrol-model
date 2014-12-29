package org.creek.mailcontrol.model.data;

import static org.junit.Assert.assertEquals;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.creek.mailcontrol.model.data.DecimalData;
import org.creek.mailcontrol.model.types.DecimalDataType;
import org.creek.mailcontrol.model.types.HSBDataType;
import org.creek.mailcontrol.model.types.IncreaseDecreaseDataType;
import org.creek.mailcontrol.model.types.OnOffDataType;
import org.creek.mailcontrol.model.types.OpenClosedDataType;
import org.creek.mailcontrol.model.types.PercentDataType;
import org.creek.mailcontrol.model.types.StopMoveDataType;
import org.creek.mailcontrol.model.types.StringDataType;
import org.creek.mailcontrol.model.types.UpDownDataType;
import org.creek.mailcontrol.model.util.JSONTransformer;

/**
 * 
 * @author Andrey.Pereverzin
 */
public class ItemCommandDataTest {
    private static final double DECIMAL_VALUE = 1.2;
    private static final int PERCENT_VALUE = 22;
    private static final String STRING_VALUE = "abcd";
    private static final long TIMESTAMP = 0L;
    private static final String ITEM_ID = "LIGHT";
    private static final long HUE_VALUE = 360;
    private static final long SATURATION_VALUE = 99;
    private static final long BRIGHTNESS_VALUE = 98;
    
    private ItemCommandData itemCommand;
    private CommandTransformable command;
    
    @Test
    public void shouldTransformDecimalCommand() throws ParseException {
        // given
        command = new DecimalData(new DecimalDataType(Double.toString(DECIMAL_VALUE)));
        itemCommand = new ItemCommandData(TIMESTAMP, ITEM_ID, command);

        // when
        JSONTransformer transformer = transformData();

        // {"itemId":"LIGHT","timeSent":"0","state":{"value":"1.2","commandType":"DECIMAL"}}
        JSONObject res = (JSONObject) transformer.getResult();
        ItemCommandData itemCommandRes = new ItemCommandData(res);
        
        // then
        assertEquals(TIMESTAMP, itemCommandRes.getTimeSent());
        assertEquals(ITEM_ID, ((ItemCommandData)itemCommandRes).getItemId());
        assertEquals(DecimalData.class.getName(), itemCommandRes.getCommand().getClass().getName());
        DecimalData dataRes = (DecimalData)itemCommandRes.getCommand();
        assertEquals(DECIMAL_VALUE, dataRes.getData().doubleValue());
    }
    
    @Test
    public void shouldTransformHSBCommand() throws ParseException {
        // given
        command = new HSBData(new HSBDataType(HUE_VALUE, SATURATION_VALUE, BRIGHTNESS_VALUE));
        itemCommand = new ItemCommandData(TIMESTAMP, ITEM_ID, command);

        // when
        JSONTransformer transformer = transformData();

        // {"timeSent":"0","itemId":"LIGHT","command":{"saturation":99,"type":"HSB","hue":360,"brightness":98}}
        JSONObject res = (JSONObject) transformer.getResult();
        ItemCommandData itemCommandRes = new ItemCommandData(res);
        
        // then
        assertEquals(TIMESTAMP, itemCommandRes.getTimeSent());
        assertEquals(ITEM_ID, ((ItemCommandData)itemCommandRes).getItemId());
        assertEquals(HSBData.class.getName(), itemCommandRes.getCommand().getClass().getName());
        HSBData dataRes = (HSBData)itemCommandRes.getCommand();
        assertEquals(HUE_VALUE, dataRes.getData().getHue());
        assertEquals(SATURATION_VALUE, dataRes.getData().getSaturation());
        assertEquals(BRIGHTNESS_VALUE, dataRes.getData().getBrightness());
    }
    
    @Test
    public void shouldTransformIncreaseDecreaseCommand() throws ParseException {
        // given
        command = new IncreaseDecreaseData(IncreaseDecreaseDataType.INCREASE);
        itemCommand = new ItemCommandData(TIMESTAMP, ITEM_ID, command);

        // when
        JSONTransformer transformer = transformData();

        // {"timeSent":"0","command":{"type":"INCREASE_DECREASE","value":"INCREASE"},"itemId":"LIGHT"}
        JSONObject res = (JSONObject) transformer.getResult();
        ItemCommandData itemCommandRes = new ItemCommandData(res);
        
        // then
        assertEquals(TIMESTAMP, itemCommandRes.getTimeSent());
        assertEquals(ITEM_ID, ((ItemCommandData)itemCommandRes).getItemId());
        assertEquals(IncreaseDecreaseData.class.getName(), itemCommandRes.getCommand().getClass().getName());
        IncreaseDecreaseData dataRes = (IncreaseDecreaseData)itemCommandRes.getCommand();
        assertEquals(IncreaseDecreaseDataType.INCREASE, dataRes.getData());
    }
    
    @Test
    public void shouldTransformOnOffCommand() throws ParseException {
        // given
        command = new OnOffData(OnOffDataType.ON);
        itemCommand = new ItemCommandData(TIMESTAMP, ITEM_ID, command);

        // when
        JSONTransformer transformer = transformData();

        // {"timeSent":"0","itemId":"LIGHT","command":{"type":"ON_OFF","value":"ON"}}
        JSONObject res = (JSONObject) transformer.getResult();
        ItemCommandData itemCommandRes = new ItemCommandData(res);
        
        // then
        assertEquals(TIMESTAMP, itemCommandRes.getTimeSent());
        assertEquals(ITEM_ID, ((ItemCommandData)itemCommandRes).getItemId());
        assertEquals(OnOffData.class.getName(), itemCommandRes.getCommand().getClass().getName());
        OnOffData dataRes = (OnOffData)itemCommandRes.getCommand();
        assertEquals(OnOffDataType.ON, dataRes.getData());
    }
    
    @Test
    public void shouldTransformOpenClosedCommand() throws ParseException {
        // given
        command = new OpenClosedData(OpenClosedDataType.OPEN);
        itemCommand = new ItemCommandData(TIMESTAMP, ITEM_ID, command);

        // when
        JSONTransformer transformer = transformData();

        // {"timeSent":"0","command":{"value":"OPEN","type":"OPEN_CLOSED"},"itemId":"LIGHT"}
        JSONObject res = (JSONObject) transformer.getResult();
        ItemCommandData itemCommandRes = new ItemCommandData(res);
        
        // then
        assertEquals(TIMESTAMP, itemCommandRes.getTimeSent());
        assertEquals(ITEM_ID, ((ItemCommandData)itemCommandRes).getItemId());
        assertEquals(OpenClosedData.class.getName(), itemCommandRes.getCommand().getClass().getName());
        OpenClosedData dataRes = (OpenClosedData)itemCommandRes.getCommand();
        assertEquals(OpenClosedDataType.OPEN, dataRes.getData());
    }
    
    @Test
    public void shouldTransformPercentCommand() throws ParseException {
        // given
        command = new PercentData(new PercentDataType(Integer.toString(PERCENT_VALUE)));
        itemCommand = new ItemCommandData(TIMESTAMP, ITEM_ID, command);

        // when
        JSONTransformer transformer = transformData();

        // {"command":{"value":"22","type":"PERCENT"},"itemId":"LIGHT","timeSent":"0"}
        JSONObject res = (JSONObject) transformer.getResult();
        ItemCommandData itemCommandRes = new ItemCommandData(res);
        
        // then
        assertEquals(TIMESTAMP, itemCommandRes.getTimeSent());
        assertEquals(ITEM_ID, ((ItemCommandData)itemCommandRes).getItemId());
        assertEquals(PercentData.class.getName(), itemCommandRes.getCommand().getClass().getName());
        PercentData dataRes = (PercentData)itemCommandRes.getCommand();
        assertEquals(PERCENT_VALUE, dataRes.getData().intValue());
    }
    
    @Test
    public void shouldTransformStopMoveCommand() throws ParseException {
        // given
        command = new StopMoveData(StopMoveDataType.MOVE);
        itemCommand = new ItemCommandData(TIMESTAMP, ITEM_ID, command);

        // when
        JSONTransformer transformer = transformData();

        // {"itemId":"LIGHT","timeSent":"0","command":{"type":"STOP_MOVE","value":"MOVE"}}
        JSONObject res = (JSONObject) transformer.getResult();
        ItemCommandData itemCommandRes = new ItemCommandData(res);
        
        // then
        assertEquals(TIMESTAMP, itemCommandRes.getTimeSent());
        assertEquals(ITEM_ID, ((ItemCommandData)itemCommandRes).getItemId());
        assertEquals(StopMoveData.class.getName(), itemCommandRes.getCommand().getClass().getName());
        StopMoveData dataRes = (StopMoveData)itemCommandRes.getCommand();
        assertEquals(StopMoveDataType.MOVE, dataRes.getData());
    }
    
    @Test
    public void shouldTransformStringCommand() throws ParseException {
        // given
        command = new StringData(new StringDataType(STRING_VALUE));
        itemCommand = new ItemCommandData(TIMESTAMP, ITEM_ID, command);

        // when
        JSONTransformer transformer = transformData();

        // {"timeSent":"0","itemId":"LIGHT","command":{"value":"abcd","type":"STRING"}}
        JSONObject res = (JSONObject) transformer.getResult();
        ItemCommandData itemCommandRes = new ItemCommandData(res);
        
        // then
        assertEquals(TIMESTAMP, itemCommandRes.getTimeSent());
        assertEquals(ITEM_ID, ((ItemCommandData)itemCommandRes).getItemId());
        assertEquals(StringData.class.getName(), itemCommandRes.getCommand().getClass().getName());
        StringData dataRes = (StringData)itemCommandRes.getCommand();
        assertEquals(STRING_VALUE, dataRes.getData().toString());
    }
    
    @Test
    public void shouldTransformUpDownCommand() throws ParseException {
        // given
        command = new UpDownData(UpDownDataType.DOWN);
        itemCommand = new ItemCommandData(TIMESTAMP, ITEM_ID, command);

        // when
        JSONTransformer transformer = transformData();

        // {"command":{"value":"DOWN","type":"UP_DOWN"},"itemId":"LIGHT","timeSent":"0"}
        JSONObject res = (JSONObject) transformer.getResult();
        ItemCommandData itemCommandRes = new ItemCommandData(res);
        
        // then
        assertEquals(TIMESTAMP, itemCommandRes.getTimeSent());
        assertEquals(ITEM_ID, ((ItemCommandData)itemCommandRes).getItemId());
        assertEquals(UpDownData.class.getName(), itemCommandRes.getCommand().getClass().getName());
        UpDownData dataRes = (UpDownData)itemCommandRes.getCommand();
        assertEquals(UpDownDataType.DOWN, dataRes.getData());
    }

    private JSONTransformer transformData() throws ParseException {
        JSONObject jsonCommand = itemCommand.toJSON();
        String s = jsonCommand.toString();
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);
        return transformer;
    }
}
