package org.creek.mailcontrol.model.message;

import org.json.simple.JSONObject;

/**
 * 
 * @author Andrey Pereverzin
 */
@SuppressWarnings("serial")
public abstract class AbstractMessage implements GenericMessage {
    public static final String CURRENT_PRODUCT_VERSION = "1.0";
    
    private final String productVersion;
    private final MessageId messageId;
    private long timeSent;

    static final String PRODUCT_VERSION = "productVersion";
    static final String MESSAGE_TYPE = "messageType";
    static final String MESSAGE_ID = "messageId";
    static final String TIME_SENT = "timeSent";

    public AbstractMessage(String senderEmail) {
        this.productVersion = CURRENT_PRODUCT_VERSION;
        this.messageId = new MessageId(senderEmail);
    }

    public AbstractMessage(JSONObject jsonObject) {
        this.productVersion = (String)jsonObject.get(PRODUCT_VERSION);
        this.messageId = new MessageId((JSONObject)jsonObject.get(MESSAGE_ID));
    }

    public abstract MessageType getMessageType();

    @Override
    public String getProductVersion() {
        return productVersion;
    }

    @Override
    public MessageId getMessageId() {
        return messageId;
    }

    @Override
    public long getTimeSent() {
        return timeSent;
    }

    @Override
    public void setTimeSent(long timeSent) {
        this.timeSent = timeSent;
    }

    @Override
    @SuppressWarnings("unchecked")
    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(PRODUCT_VERSION, productVersion);
        jsonObject.put(MESSAGE_TYPE, Integer.toString(getMessageType().getType()));
        jsonObject.put(MESSAGE_ID, messageId.toJSON());
        jsonObject.put(TIME_SENT, timeSent);
        return jsonObject;
    }

    @Override
    public String toString() {
        return PRODUCT_VERSION + "=" + productVersion + ", " + MESSAGE_TYPE + "=" + getMessageType().getType() + 
                ", " + MESSAGE_ID + "=" + messageId.toString() + ", " + TIME_SENT + "=" + timeSent;
    }
}
