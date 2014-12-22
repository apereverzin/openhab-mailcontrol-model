package org.creek.mailcontrol.model.message;

import static org.creek.mailcontrol.model.data.DataType.DECIMAL;
import static org.creek.mailcontrol.model.message.AbstractMessage.CURRENT_PRODUCT_VERSION;
import static org.creek.mailcontrol.model.message.MessageType.ITEMS_STATE_RESPONSE_MESSAGE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

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
public class ItemsStateResponseMessageTest {
    private static final String REQUEST_EMAIL = "aa@bb.cc";
    private static final String SENDER_EMAIL = "dd@ee.ff";
    private static final String ITEM_ID_1 = "LIGHT1";
    private static final String ITEM_ID_2 = "LIGHT2";
    private static final String DECIMAL_VALUE_1 = "12";
    private static final String DECIMAL_VALUE_2 = "13";
    private static final long TIMESTAMP_1 = 1234L;
    private static final long TIMESTAMP_2 = 1235L;
    
    private ItemsStateResponseMessage message;
        
    @Test
    public void shouldTransformItemsStateResponseMessage() throws ParseException {
        // given
        StateTransformable command1 = new DecimalData(DECIMAL_VALUE_1);
        StateTransformable command2 = new DecimalData(DECIMAL_VALUE_2);
        ItemStateData itemState1 = new ItemStateData(TIMESTAMP_1, ITEM_ID_1, command1);
        ItemStateData itemState2 = new ItemStateData(TIMESTAMP_2, ITEM_ID_2, command2);
        MessageId requestId = new MessageId(REQUEST_EMAIL);
        long requestTimestamp = requestId.getTimestamp();
        List<ItemStateData> itemStates = new ArrayList<ItemStateData>();
        itemStates.add(itemState1);
        itemStates.add(itemState2);
        message = new ItemsStateResponseMessage(itemStates, requestId, SENDER_EMAIL);
        
        // when
        JSONObject jsonMessage = message.toJSON();
        String s = jsonMessage.toString();
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);

        // {"messageType":"115","messageId":{"senderEmail":"dd@ee.ff","timestamp":1418367657751},"productVersion":"1.0","itemStates":[{"state":"State1","itemId":"LIGHT1"},{"state":"State2","itemId":"LIGHT2"}],"requestId":{"senderEmail":"aa@bb.cc","timestamp":1418367657749}}
        JSONObject res = (JSONObject) transformer.getResult();
        ItemsStateResponseMessage messageRes = new ItemsStateResponseMessage(res);
        
        // then
        assertEquals(ITEMS_STATE_RESPONSE_MESSAGE, messageRes.getMessageType());
        assertEquals(CURRENT_PRODUCT_VERSION, messageRes.getProductVersion());
        assertEquals(REQUEST_EMAIL, messageRes.getRequestId().getSenderEmail());
        assertEquals(requestTimestamp, messageRes.getRequestId().getTimestamp());
        assertEquals(SENDER_EMAIL, messageRes.getMessageId().getSenderEmail());
        assertTrue(messageRes.getMessageId().getTimestamp() > 0);
        assertEquals(2, messageRes.getItemStates().size());
        assertEquals(ITEM_ID_1, messageRes.getItemStates().get(0).getItemId());
        assertEquals(DECIMAL, messageRes.getItemStates().get(0).getState().getStateType());
        assertEquals(ITEM_ID_2, messageRes.getItemStates().get(1).getItemId());
        assertEquals(DECIMAL, messageRes.getItemStates().get(1).getState().getStateType());
    }
}
