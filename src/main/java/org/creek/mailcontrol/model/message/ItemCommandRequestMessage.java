package org.creek.mailcontrol.model.message;

import static org.creek.mailcontrol.model.message.MessageType.ITEM_COMMAND_REQUEST_MESSAGE;

import org.creek.mailcontrol.model.data.ItemCommandData;
import org.json.simple.JSONObject;

/**
 * 
 * @author Andrey Pereverzin
 */
@SuppressWarnings("serial")
public class ItemCommandRequestMessage extends AbstractMessage {

    private static final String ITEM_COMMAND = "itemCommand";
    
    private ItemCommandData itemCommand;

    public ItemCommandRequestMessage(ItemCommandData itemCommand, String senderEmail) {
        super(senderEmail);
        this.itemCommand = itemCommand;
    }
    
    public ItemCommandRequestMessage(JSONObject jsonObject){
        super(jsonObject);
        this.itemCommand = new ItemCommandData((JSONObject) jsonObject.get(ITEM_COMMAND));
    }

    public GenericItemState getItemCommand() {
        return itemCommand;
    }

    @Override
    @SuppressWarnings("unchecked")
    public JSONObject toJSON() {
        JSONObject jsonObject = super.toJSON();
        jsonObject.put(ITEM_COMMAND, itemCommand.toJSON());
        return jsonObject;
    }

    @Override
    public MessageType getMessageType() {
        return ITEM_COMMAND_REQUEST_MESSAGE;
    }

    @Override
    public String toString() {
        return getClass().getName() + " [" + super.toString() + ", " + ITEM_COMMAND + " [" + itemCommand.toString() + "]]";
    }
}
