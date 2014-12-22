package org.creek.mailcontrol.model.message;

/**
 * 
 * @author Andrey Pereverzin
 */
public enum MessageType {
    ITEM_COMMAND_REQUEST_MESSAGE(110),
    ITEM_COMMAND_RESPONSE_MESSAGE(111),
    ITEM_STATE_REQUEST_MESSAGE(112),
    ITEM_STATE_RESPONSE_MESSAGE(113),
    ITEMS_STATE_REQUEST_MESSAGE(114),
    ITEMS_STATE_RESPONSE_MESSAGE(115),
    ITEM_STATE_NOTIFICATION_MESSAGE(116);

    private final int type;

    private MessageType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
    
    public static MessageType getMessageType(int type) {
        for (MessageType messageType: values()) {
            if (messageType.getType() == type) {
                return messageType;
            }
        }
        
        return null;
    }
}
