package org.creek.mailcontrol.model.message;

import org.junit.Before;
import org.junit.Test;

import static org.creek.mailcontrol.model.message.MessageType.ITEMS_STATE_RESPONSE_MESSAGE;
import static org.creek.mailcontrol.model.message.MessageType.ITEM_STATE_RESPONSE_MESSAGE;
import static org.junit.Assert.assertEquals;

/**
 * 
 * @author Andrey Pereverzin
 */
public class GenericResponseTransformerTest {
    private GenericResponseTransformer transformer;
    
    private static final String ITEMS_STATE_RESPONSE_MSG = "{\"messageType\":\"115\",\"messageId\":{\"senderEmail\":\"dd@ee.ff\",\"timestamp\":1418367657751},\"productVersion\":\"1.0\",\"itemStates\":[{\"state\":{\"type\":\"DECIMAL\",\"value\":\"12\"},\"itemId\":\"LIGHT1\",\"acceptedCommands\":[\"INCREASE_DECREASE\",\"ON_OFF\"],\"timeSent\":\"0\"},{\"state\":{\"type\":\"DECIMAL\",\"value\":\"12\"},\"itemId\":\"LIGHT2\",\"acceptedCommands\":[\"INCREASE_DECREASE\",\"ON_OFF\"],\"timeSent\":\"0\"}],\"requestId\":{\"senderEmail\":\"aa@bb.cc\",\"timestamp\":1418367657749}}";
    private static final String ITEM_STATE_RESPONSE_MSG = "{\"messageType\":\"113\",\"itemState\":{\"itemId\":\"LIGHT\",\"acceptedCommands\":[\"INCREASE_DECREASE\",\"ON_OFF\"],\"state\":{\"type\":\"DECIMAL\",\"value\":\"12\"},\"timeSent\":\"0\"},\"productVersion\":\"1.0\",\"messageId\":{\"timestamp\":1418321714818,\"senderEmail\":\"dd@ee.ff\"},\"requestId\":{\"timestamp\":1418321714816,\"senderEmail\":\"aa@bb.cc\"}}";

    @Before
    public void setUp() {
        transformer = new GenericResponseTransformer();
    }
    
    @Test
    public void shouldTransformItemCommandRequestMessage() throws TransformException {
        // given
        
        // when
        GenericResponse response = transformer.transform(ITEMS_STATE_RESPONSE_MSG);
        
        // then
        assertEquals(ItemsStateResponseMessage.class.getName(), response.getClass().getName());
        assertEquals(ITEMS_STATE_RESPONSE_MESSAGE, response.getMessageType());
    }
    
    @Test
    public void shouldTransformItemsStateRequestMessage() throws TransformException {
        // given
        
        // when
        GenericResponse response = transformer.transform(ITEM_STATE_RESPONSE_MSG);
        
        // then
        assertEquals(ItemStateResponseMessage.class.getName(), response.getClass().getName());
        assertEquals(ITEM_STATE_RESPONSE_MESSAGE, response.getMessageType());
    }
}
