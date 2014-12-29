package org.creek.mailcontrol.model.message;

import static org.creek.mailcontrol.model.message.MessageType.ITEMS_STATE_RESPONSE_MESSAGE;

import java.util.ArrayList;
import java.util.List;

import org.creek.mailcontrol.model.data.ItemStateData;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * 
 * @author Andrey Pereverzin
 */
@SuppressWarnings("serial")
public class ItemsStateResponseMessage extends AbstractResponse implements GenericResponse {
    private static final String ITEM_STATES = "itemStates";
    
    private final List<ItemStateData> itemStates;

    public ItemsStateResponseMessage(List<ItemStateData> itemStates, MessageId requestId, String senderEmail) {
        super(senderEmail, requestId);
        this.itemStates = itemStates;
    }
    
    public ItemsStateResponseMessage(JSONObject jsonObject) {
        super(jsonObject);
        this.itemStates = buildItemStatesList(jsonObject);
    }

    public List<ItemStateData> getItemStates() {
        return itemStates;
    }

    @Override
    @SuppressWarnings("unchecked")
    public JSONObject toJSON() {
        JSONObject jsonObject = super.toJSON();
        JSONArray itemStatesArray = new JSONArray();
        for (int i = 0; i < itemStates.size(); i++) {
            ItemStateData itemState = itemStates.get(i);
            itemStatesArray.add(itemState.toJSON());
        }
        jsonObject.put(ITEM_STATES, itemStatesArray);
        return jsonObject;
    }

    @Override
    public MessageType getMessageType() {
        return ITEMS_STATE_RESPONSE_MESSAGE;
    }

    @Override
    public String toString() {
        String s = getClass().getName() + " [" + super.toString() + ", " + ITEM_STATES + " [";
        int n = itemStates.size();
        for (int i = 0; i < n; i++) {
            ItemStateData itemState = itemStates.get(i);
            s = s + itemState.toString();
            if (i < n - 1) {
                s = s + ", ";
            }
        }
        s = s + "]]";
        return s;
    }

    private List<ItemStateData> buildItemStatesList(JSONObject jsonObject) {
        JSONArray itemStatesArray = (JSONArray)jsonObject.get(ITEM_STATES);
        
        List<ItemStateData> itemStates = new ArrayList<ItemStateData>();
        for(Object itemStateObject: itemStatesArray) {
            ItemStateData itemState = new ItemStateData((JSONObject)itemStateObject);
            itemStates.add(itemState);
        }
        
        return itemStates;
    }
}
