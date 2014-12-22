package org.creek.mailcontrol.model.message;

import static org.creek.mailcontrol.model.message.AbstractMessage.CURRENT_PRODUCT_VERSION;
import static org.creek.mailcontrol.model.message.MessageType.ITEM_STATE_REQUEST_MESSAGE;
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
public class ItemStateRequestMessageTest {
    private static final String SENDER_EMAIL = "aa@bb.cc";
    private static final String ITEM_ID = "LIGHT";
    
    private ItemStateRequestMessage message;
        
    @Test
    public void shouldTransformItemStateRequestMessage() throws ParseException {
        // given
        message = new ItemStateRequestMessage(SENDER_EMAIL, ITEM_ID);
        
        // when
        JSONObject jsonMessage = message.toJSON();
        String s = jsonMessage.toString();
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);

        // {"itemId":"LIGHT","messageType":"112","messageId":{"timestamp":1418237338073,"senderEmail":"aa@bb.cc"},"productVersion":"1.0"}
        JSONObject res = (JSONObject) transformer.getResult();
        ItemStateRequestMessage messageRes = new ItemStateRequestMessage(res);
        
        // then
        assertEquals(ITEM_STATE_REQUEST_MESSAGE, messageRes.getMessageType());
        assertEquals(CURRENT_PRODUCT_VERSION, messageRes.getProductVersion());
        assertEquals(SENDER_EMAIL, messageRes.getMessageId().getSenderEmail());
        assertTrue(messageRes.getMessageId().getTimestamp() > 0);
    }
}
