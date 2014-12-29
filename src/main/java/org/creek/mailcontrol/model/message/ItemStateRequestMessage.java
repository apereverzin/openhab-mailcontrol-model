package org.creek.mailcontrol.model.message;

import static org.creek.mailcontrol.model.message.MessageType.ITEM_STATE_REQUEST_MESSAGE;

import org.json.simple.JSONObject;

/**
 * 
 * @author Andrey Pereverzin
 */
@SuppressWarnings("serial")
public class ItemStateRequestMessage extends AbstractMessage implements GenericRequest {

    private static final String ITEM_ID = "itemId";
    
    private final String itemId;

    public ItemStateRequestMessage(String senderEmail, String itemId) {
        super(senderEmail);
        this.itemId = itemId;
    }
    
    public ItemStateRequestMessage(JSONObject jsonObject) {
        super(jsonObject);
        this.itemId = (String) jsonObject.get(ITEM_ID);
    }

    public String getItemId() {
        return itemId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public JSONObject toJSON() {
        JSONObject jsonObject = super.toJSON();
        jsonObject.put(ITEM_ID, itemId);
        return jsonObject;
    }

    @Override
    public MessageType getMessageType() {
        return ITEM_STATE_REQUEST_MESSAGE;
    }

    @Override
    public String toString() {
        return getClass().getName() + " [" + super.toString() + ", " + ITEM_ID + "=" + itemId + "]";
    }
}
