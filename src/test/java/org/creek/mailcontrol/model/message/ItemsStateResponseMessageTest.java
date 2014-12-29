package org.creek.mailcontrol.model.message;

import static org.creek.mailcontrol.model.data.DataType.DECIMAL;
import static org.creek.mailcontrol.model.data.DataType.INCREASE_DECREASE;
import static org.creek.mailcontrol.model.data.DataType.ON_OFF;
import static org.creek.mailcontrol.model.data.DataType.PERCENT;
import static org.creek.mailcontrol.model.message.AbstractMessage.CURRENT_PRODUCT_VERSION;
import static org.creek.mailcontrol.model.message.MessageType.ITEMS_STATE_RESPONSE_MESSAGE;
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
public class ItemsStateResponseMessageTest {
    private static final String REQUEST_EMAIL = "aa@bb.cc";
    private static final String SENDER_EMAIL = "dd@ee.ff";
    private static final String ITEM_ID_1 = "LIGHT1";
    private static final String ITEM_ID_2 = "LIGHT2";
    private static final String DECIMAL_VALUE_1 = "12";
    private static final String DECIMAL_VALUE_2 = "13";
    private static final long TIMESTAMP_1 = 1234L;
    private static final long TIMESTAMP_2 = 1235L;

    @Test
    public void shouldTransformItemsStateResponseMessage() throws ParseException {
        // given
        ItemsStateResponseMessage message = buildMessage();
        long requestTimestamp = message.getRequestId().getTimestamp();

        // when
        JSONObject jsonMessage = message.toJSON();
        String s = jsonMessage.toString();
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);

        // {"requestId":{"senderEmail":"aa@bb.cc","timestamp":1419359483905},"itemStates":[{"state":{"type":"DECIMAL","value":"12"},"timeSent":"1234","itemId":"LIGHT1"},{"state":{"type":"DECIMAL","value":"13"},"timeSent":"1235","itemId":"LIGHT2"}],"messageType":"115","messageId":{"senderEmail":"dd@ee.ff","timestamp":1419359483907},"productVersion":"1.0"}
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
    
    @Test
    public void shouldToStringWork() throws ParseException {
        // given
        ItemsStateResponseMessage message = buildMessage();
        
        // when
        String s = message.toString();
        
        // then
        assertTrue(s.contains(ItemsStateResponseMessage.class.getName()));
    }

    private ItemsStateResponseMessage buildMessage() {
        StateTransformable command1 = new DecimalData(DECIMAL_VALUE_1);
        StateTransformable command2 = new DecimalData(DECIMAL_VALUE_2);
        List<DataType> acceptedCommands1 = buildAcceptedCommandsList(INCREASE_DECREASE, ON_OFF);
        ItemStateData itemState1 = new ItemStateData(TIMESTAMP_1, ITEM_ID_1, command1, acceptedCommands1);
        List<DataType> acceptedCommands2 = buildAcceptedCommandsList(INCREASE_DECREASE, ON_OFF, PERCENT);
        ItemStateData itemState2 = new ItemStateData(TIMESTAMP_2, ITEM_ID_2, command2, acceptedCommands2);
        MessageId requestId = new MessageId(REQUEST_EMAIL);
        List<ItemStateData> itemStates = new ArrayList<ItemStateData>();
        itemStates.add(itemState1);
        itemStates.add(itemState2);
        ItemsStateResponseMessage message = new ItemsStateResponseMessage(itemStates, requestId, SENDER_EMAIL);
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
