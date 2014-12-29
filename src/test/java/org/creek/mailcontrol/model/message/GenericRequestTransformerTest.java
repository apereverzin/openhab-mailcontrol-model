package org.creek.mailcontrol.model.message;

import org.junit.Before;
import org.junit.Test;

import static org.creek.mailcontrol.model.message.MessageType.ITEMS_STATE_REQUEST_MESSAGE;
import static org.creek.mailcontrol.model.message.MessageType.ITEM_COMMAND_REQUEST_MESSAGE;
import static org.creek.mailcontrol.model.message.MessageType.ITEM_STATE_REQUEST_MESSAGE;
import static org.junit.Assert.assertEquals;

/**
 * 
 * @author Andrey Pereverzin
 */
public class GenericRequestTransformerTest {
    private GenericRequestTransformer transformer;
    
    private static final String ITEM_COMMAND_REQUEST_MSG = "{\"messageType\":\"110\",\"messageId\":{\"timestamp\":1418237415197,\"senderEmail\":\"aa@bb.cc\"},\"productVersion\":\"1.0\",\"itemCommand\":{\"command\":{\"type\":\"DECIMAL\",\"value\":\"12\"},\"itemId\":\"LIGHT\",\"timeSent\":\"0\"}}";
    private static final String ITEMS_STATE_REQUEST_MSG = "{\"messageType\":\"114\",\"messageId\":{\"timestamp\":1418237375519,\"senderEmail\":\"aa@bb.cc\"},\"productVersion\":\"1.0\"}";
    private static final String ITEM_STATE_REQUEST_MSG = "{\"itemId\":\"LIGHT\",\"messageType\":\"112\",\"messageId\":{\"timestamp\":1418237338073,\"senderEmail\":\"aa@bb.cc\"},\"productVersion\":\"1.0\"}";
    
    @Before
    public void setUp() {
        transformer = new GenericRequestTransformer();
    }
    
    @Test
    public void shouldTransformItemCommandRequestMessage() throws TransformException {
        // given
        
        // when
        GenericRequest request = transformer.transform(ITEM_COMMAND_REQUEST_MSG);
        
        // then
        assertEquals(ItemCommandRequestMessage.class.getName(), request.getClass().getName());
        assertEquals(ITEM_COMMAND_REQUEST_MESSAGE, request.getMessageType());
    }
    
    @Test
    public void shouldTransformItemsStateRequestMessage() throws TransformException {
        // given
        
        // when
        GenericRequest request = transformer.transform(ITEMS_STATE_REQUEST_MSG);
        
        // then
        assertEquals(ItemsStateRequestMessage.class.getName(), request.getClass().getName());
        assertEquals(ITEMS_STATE_REQUEST_MESSAGE, request.getMessageType());
    }
    
    @Test
    public void shouldTransformItemStateRequestMessage() throws TransformException {
        // given
        
        // when
        GenericRequest request = transformer.transform(ITEM_STATE_REQUEST_MSG);
        
        // then
        assertEquals(ItemStateRequestMessage.class.getName(), request.getClass().getName());
        assertEquals(ITEM_STATE_REQUEST_MESSAGE, request.getMessageType());
    }
}
