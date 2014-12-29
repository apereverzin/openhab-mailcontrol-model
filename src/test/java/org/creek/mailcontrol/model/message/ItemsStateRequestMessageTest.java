package org.creek.mailcontrol.model.message;

import static org.creek.mailcontrol.model.message.AbstractMessage.CURRENT_PRODUCT_VERSION;
import static org.creek.mailcontrol.model.message.MessageType.ITEMS_STATE_REQUEST_MESSAGE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.creek.mailcontrol.model.util.JSONTransformer;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

/**
 * 
 * @author Andrey Pereverzin
 */
public class ItemsStateRequestMessageTest {
    private static final String SENDER_EMAIL = "aa@bb.cc";
    
    @Test
    public void shouldTransformItemsStateRequestMessage() throws ParseException {
        // given
        ItemsStateRequestMessage message = buildMessage();
        
        // when
        JSONObject jsonMessage = message.toJSON();
        String s = jsonMessage.toString();
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);

        // {"messageType":"114","messageId":{"timestamp":1418237375519,"senderEmail":"aa@bb.cc"},"productVersion":"1.0"}
        JSONObject res = (JSONObject) transformer.getResult();
        ItemsStateRequestMessage messageRes = new ItemsStateRequestMessage(res);
        
        // then
        assertEquals(ITEMS_STATE_REQUEST_MESSAGE, messageRes.getMessageType());
        assertEquals(CURRENT_PRODUCT_VERSION, messageRes.getProductVersion());
        assertEquals(SENDER_EMAIL, messageRes.getMessageId().getSenderEmail());
        assertTrue(messageRes.getMessageId().getTimestamp() > 0);
    }
    
    @Test
    public void shouldToStringWork() throws ParseException {
        // given
        ItemsStateRequestMessage message = buildMessage();
        
        // when
        String s = message.toString();
        
        // then
        assertTrue(s.contains(ItemsStateRequestMessage.class.getName()));
    }

    private ItemsStateRequestMessage buildMessage() {
        ItemsStateRequestMessage message = new ItemsStateRequestMessage(SENDER_EMAIL);
        return message;
    }
}
