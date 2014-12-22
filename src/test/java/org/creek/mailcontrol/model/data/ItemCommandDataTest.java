package org.creek.mailcontrol.model.data;

import static org.junit.Assert.assertEquals;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.creek.mailcontrol.model.data.DecimalData;
import org.creek.mailcontrol.model.util.JSONTransformer;

/**
 * 
 * @author Andrey.Pereverzin
 */
public class ItemCommandDataTest {
    private static final String DECIMAL_VALUE = "1.2";
    private static final long TIMESTAMP = 0L;
    private static final String ITEM_ID = "LIGHT";
    
    private ItemCommandData itemCommand;
    private CommandTransformable command;
    
    @Test
    public void shouldTransformItemState() throws ParseException {
        // given
        command = new DecimalData(DECIMAL_VALUE);
        itemCommand = new ItemCommandData(TIMESTAMP, ITEM_ID, command);

        // when
        JSONObject jsonCommand = itemCommand.toJSON();
        String s = jsonCommand.toString();
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);

        // {"itemId":"LIGHT","timeSent":"0","state":{"value":"1.2","commandType":"DECIMAL"}}
        JSONObject res = (JSONObject) transformer.getResult();
        ItemCommandData itemCommandRes = new ItemCommandData(res);
        
        // then
        assertEquals(TIMESTAMP, itemCommandRes.getTimeSent());
        assertEquals(ITEM_ID, ((ItemCommandData)itemCommandRes).getItemId());
        assertEquals(DecimalData.class.getName(), itemCommandRes.getCommand().getClass().getName());
    }
}
