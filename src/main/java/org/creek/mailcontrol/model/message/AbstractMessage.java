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
    private final String senderEmail;

    static final String MESSAGE_TYPE = "messageType";
    static final String PRODUCT_VERSION = "productVersion";
    static final String SENDER_EMAIL = "senderEmail";

    public AbstractMessage(String senderEmail) {
        this.productVersion = CURRENT_PRODUCT_VERSION;
        this.senderEmail = senderEmail;
    }

    public AbstractMessage(JSONObject jsonObject) {
        this.productVersion = (String)jsonObject.get(PRODUCT_VERSION);
        this.senderEmail = (String)jsonObject.get(SENDER_EMAIL);
    }

    public abstract MessageType getMessageType();

    @Override
    public String getProductVersion() {
        return productVersion;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    @Override
    @SuppressWarnings("unchecked")
    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(MESSAGE_TYPE, Integer.toString(getMessageType().getType()));
        jsonObject.put(PRODUCT_VERSION, productVersion);
        jsonObject.put(SENDER_EMAIL, senderEmail);
        return jsonObject;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("productVersion=").append(productVersion).append(", senderEmail=").append(senderEmail);
        return builder.toString();
    }
}
