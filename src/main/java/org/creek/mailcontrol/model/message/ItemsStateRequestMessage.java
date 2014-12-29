package org.creek.mailcontrol.model.message;

import static org.creek.mailcontrol.model.message.MessageType.ITEMS_STATE_REQUEST_MESSAGE;

import org.json.simple.JSONObject;

/**
 * 
 * @author Andrey Pereverzin
 */
@SuppressWarnings("serial")
public class ItemsStateRequestMessage extends AbstractMessage implements GenericRequest {

    public ItemsStateRequestMessage(String senderEmail) {
        super(senderEmail);
    }
    
    public ItemsStateRequestMessage(JSONObject jsonObject){
        super(jsonObject);
    }

    @Override
    public MessageType getMessageType() {
        return ITEMS_STATE_REQUEST_MESSAGE;
    }

    @Override
    public String toString() {
        return getClass().getName() + " [" + super.toString() + "]";
    }
}
