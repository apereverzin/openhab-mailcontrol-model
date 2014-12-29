package org.creek.mailcontrol.model.message;

import static org.creek.mailcontrol.model.message.AbstractMessage.CURRENT_PRODUCT_VERSION;
import static org.creek.mailcontrol.model.message.MessageType.ITEM_COMMAND_REQUEST_MESSAGE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.creek.mailcontrol.model.data.CommandTransformable;
import org.creek.mailcontrol.model.data.DecimalData;
import org.creek.mailcontrol.model.data.ItemCommandData;
import org.creek.mailcontrol.model.util.JSONTransformer;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

/**
 * 
 * @author Andrey Pereverzin
 */
public class ItemCommandRequestMessageTest {
    private static final long TIMESTAMP = 0L;
    private static final String ITEM_ID = "LIGHT";
    private static final String SENDER_EMAIL = "aa@bb.cc";
    private static final String DECIMAL_VALUE = "12";
    
    @Test
    public void shouldTransformItemCommandRequestMessage() throws ParseException {
        // given
        ItemCommandRequestMessage message = buildMessage();
        
        // when
        JSONObject jsonMessage = message.toJSON();
        String s = jsonMessage.toString();
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);

        // {"messageType":"110","messageId":{"timestamp":1418237415197,"senderEmail":"aa@bb.cc"},"productVersion":"1.0","itemCommand":{"command":{"type":"DECIMAL","value":"12"},"itemId":"LIGHT","timeSent":"0"}}
        JSONObject res = (JSONObject) transformer.getResult();
        ItemCommandRequestMessage messageRes = new ItemCommandRequestMessage(res);
        
        // then
        assertEquals(ITEM_COMMAND_REQUEST_MESSAGE, messageRes.getMessageType());
        assertEquals(CURRENT_PRODUCT_VERSION, messageRes.getProductVersion());
        assertEquals(SENDER_EMAIL, messageRes.getMessageId().getSenderEmail());
        assertEquals(ItemCommandData.class.getName(), messageRes.getItemCommand().getClass().getName());
        assertTrue(messageRes.getMessageId().getTimestamp() > 0);
    }
    
    @Test
    public void shouldToStringWork() throws ParseException {
        // given
        ItemCommandRequestMessage message = buildMessage();
        
        // when
        String s = message.toString();
        
        // then
        assertTrue(s.contains(ItemCommandRequestMessage.class.getName()));
    }

    private ItemCommandRequestMessage buildMessage() {
        CommandTransformable command = new DecimalData(DECIMAL_VALUE);
        ItemCommandData itemCommand = new ItemCommandData(TIMESTAMP, ITEM_ID, command);
        ItemCommandRequestMessage message = new ItemCommandRequestMessage(itemCommand, SENDER_EMAIL);
        return message;
    }
}
