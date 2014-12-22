package org.creek.mailcontrol.model.message;

import static org.creek.mailcontrol.model.message.AbstractMessage.CURRENT_PRODUCT_VERSION;
import static org.creek.mailcontrol.model.message.MessageType.ITEM_STATE_RESPONSE_MESSAGE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.creek.mailcontrol.model.data.DecimalData;
import org.creek.mailcontrol.model.data.ItemStateData;
import org.creek.mailcontrol.model.data.StateTransformable;
import org.creek.mailcontrol.model.util.JSONTransformer;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

/**
 * 
 * @author Andrey Pereverzin
 */
public class ItemStateResponseMessageTest {
    private static final String REQUEST_EMAIL = "aa@bb.cc";
    private static final String SENDER_EMAIL = "dd@ee.ff";
    private static final String ITEM_ID = "LIGHT";
    private static final String DECIMAL_VALUE = "12";
    private static final long TIMESTAMP = 0L;
    
    @Test
    public void shouldTransformItemStateResponseMessage() throws ParseException {
        // given
        StateTransformable command = new DecimalData(DECIMAL_VALUE);
        ItemStateData itemState = new ItemStateData(TIMESTAMP, ITEM_ID, command);
        MessageId requestId = new MessageId(REQUEST_EMAIL);
        long requestTimestamp = requestId.getTimestamp();
        ItemStateResponseMessage message = new ItemStateResponseMessage(itemState, requestId, SENDER_EMAIL);
        
        // when
        JSONObject jsonMessage = message.toJSON();
        String s = jsonMessage.toString();
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);

        // {"messageType":"113","itemState":{"itemId":"LIGHT","state":"Undefined"},"productVersion":"1.0","messageId":{"timestamp":1418321714818,"senderEmail":"dd@ee.ff"},"requestId":{"timestamp":1418321714816,"senderEmail":"aa@bb.cc"}}
        JSONObject res = (JSONObject) transformer.getResult();
        ItemStateResponseMessage messageRes = new ItemStateResponseMessage(res);
        
        // then
        assertEquals(ITEM_STATE_RESPONSE_MESSAGE, messageRes.getMessageType());
        assertEquals(CURRENT_PRODUCT_VERSION, messageRes.getProductVersion());
        assertEquals(REQUEST_EMAIL, messageRes.getRequestId().getSenderEmail());
        assertEquals(requestTimestamp, messageRes.getRequestId().getTimestamp());
        assertEquals(SENDER_EMAIL, messageRes.getMessageId().getSenderEmail());
        assertTrue(messageRes.getMessageId().getTimestamp() > 0);
        assertEquals(ITEM_ID, messageRes.getItemState().getItemId());
    }
}
