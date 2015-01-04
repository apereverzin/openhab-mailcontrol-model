package org.creek.mailcontrol.model.message;

import static org.creek.mailcontrol.model.data.DataType.INCREASE_DECREASE;
import static org.creek.mailcontrol.model.data.DataType.ON_OFF;
import static org.creek.mailcontrol.model.message.AbstractMessage.CURRENT_PRODUCT_VERSION;
import static org.creek.mailcontrol.model.message.MessageType.ITEM_STATE_RESPONSE_MESSAGE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.creek.mailcontrol.model.data.DataType;
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
        ItemStateResponseMessage message = buildMessage();
        long requestTimestamp = message.getRequestId().getTimestamp();
        
        // when
        JSONObject jsonMessage = message.toJSON();
        String s = jsonMessage.toString();
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);

        // {"requestId":{"timestamp":1420380013436,"senderEmail":"aa@bb.cc"},"productVersion":"1.0","messageType":"113","timeSent":0,"itemState":{"itemId":"LIGHT","timeSent":"0","acceptedCommands":["INCREASE_DECREASE","ON_OFF"],"state":{"type":"DECIMAL","value":"12"}},"messageId":{"timestamp":1420380013436,"senderEmail":"dd@ee.ff"}}
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
    
    @Test
    public void shouldToStringWork() throws ParseException {
        // given
        ItemStateResponseMessage message = buildMessage();
        
        // when
        String s = message.toString();
        
        // then
        assertTrue(s.contains(ItemStateResponseMessage.class.getName()));
    }

    private ItemStateResponseMessage buildMessage() {
        StateTransformable command = new DecimalData(DECIMAL_VALUE);
        List<DataType> acceptedCommands = buildAcceptedCommandsList(INCREASE_DECREASE, ON_OFF);
        ItemStateData itemState = new ItemStateData(TIMESTAMP, ITEM_ID, command, acceptedCommands);
        MessageId requestId = new MessageId(REQUEST_EMAIL);
        ItemStateResponseMessage message = new ItemStateResponseMessage(itemState, requestId, SENDER_EMAIL);
        return message;
    }
    
    private List<DataType> buildAcceptedCommandsList(DataType... dataTypes) {
        List<DataType> acceptedCommands = new ArrayList<DataType>();
        
        for (DataType dataType: dataTypes) {
            acceptedCommands.add(dataType);
        }
        
        return acceptedCommands;
    }
}
