package org.creek.mailcontrol.model.message;

import static org.creek.mailcontrol.model.message.MessageType.ITEM_STATE_RESPONSE_MESSAGE;

import org.creek.mailcontrol.model.data.ItemStateData;
import org.json.simple.JSONObject;

/**
 * 
 * @author Andrey Pereverzin
 */
@SuppressWarnings("serial")
public class ItemStateResponseMessage extends AbstractResponse implements GenericResponse {

    private static final String ITEM_STATE = "itemState";
    
    private final ItemStateData itemState;

    public ItemStateResponseMessage(ItemStateData itemState, MessageId requestId, String senderEmail) {
        super(senderEmail, requestId);
        this.itemState = itemState;
    }
    
    public ItemStateResponseMessage(JSONObject jsonObject) {
        super(jsonObject);
        this.itemState = new ItemStateData((JSONObject) jsonObject.get(ITEM_STATE));
    }

    public ItemStateData getItemState() {
        return itemState;
    }

    @Override
    @SuppressWarnings("unchecked")
    public JSONObject toJSON() {
        JSONObject jsonObject = super.toJSON();
        jsonObject.put(ITEM_STATE, itemState.toJSON());
        return jsonObject;
    }

    @Override
    public MessageType getMessageType() {
        return ITEM_STATE_RESPONSE_MESSAGE;
    }

    @Override
    public String toString() {
        return getClass().getName() + " [" + super.toString() + ", " + ITEM_STATE + " [" + itemState.toString() + "]]";
    }
}
