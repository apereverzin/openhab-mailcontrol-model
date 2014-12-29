package org.creek.mailcontrol.model.message;

import org.json.simple.JSONObject;

/**
 * 
 * @author Andrey Pereverzin
 */
@SuppressWarnings("serial")
public abstract class ItemData extends AbstractSendableData {
    private final String itemId;

    private static final String ITEM_ID = "itemId";

    public ItemData(long timeSent, String itemId) {
        super(timeSent);
        this.itemId = itemId;
    }

    public ItemData(JSONObject jsonObject) {
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
    public String toString() {
        return super.toString() + ", " + ITEM_ID + "=" + itemId;
    }
}
