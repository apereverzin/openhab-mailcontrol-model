package org.creek.mailcontrol.model.command;

import static org.junit.Assert.assertEquals;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.creek.mailcontrol.model.util.JSONTransformer;

/**
 * 
 * @author Andrey.Pereverzin
 */
public class ItemCommandTest {
    private static final String DECIMAL_VALUE = "1.2";
    private static final long TIMESTAMP = 0L;
    private static final String ITEM_ID = "LIGHT";
    
    private ItemCommand itemCommand;
    private CommandTransformable command;
    
    @Test
    public void shouldTransformItemCommand() throws ParseException {
        // given
        command = new DecimalCommand(DECIMAL_VALUE);
        itemCommand = new ItemCommand(TIMESTAMP, ITEM_ID, command);

        // when
        JSONObject jsonCommand = itemCommand.toJSON();
        String s = jsonCommand.toString();
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);

        // {"item_id":"LIGHT","timeSent":"0","command":{"value":"1.2","commandType":"DECIMAL"}}
        JSONObject res = (JSONObject) transformer.getResult();
        ItemCommand itemCommandRes = new ItemCommand(res);
        
        // then
        assertEquals(TIMESTAMP, itemCommandRes.getTimeSent());
        assertEquals(ITEM_ID, ((ItemCommand)itemCommandRes).getItemId());
        assertEquals(DecimalCommand.class.getName(), itemCommandRes.getCommand().getClass().getName());
    }
}
