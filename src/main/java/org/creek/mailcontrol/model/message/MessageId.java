package org.creek.mailcontrol.model.message;

import org.json.simple.JSONObject;

/**
 * 
 * @author Andrey Pereverzin
 */
@SuppressWarnings("serial")
public class MessageId implements JsonTransformable {
    static final String SENDER_EMAIL = "senderEmail";
    static final String TIMESTAMP = "timestamp";

    private final String senderEmail;
    private final long timestamp;
    
    public MessageId(String senderEmail) {
        this.senderEmail = senderEmail;
        this.timestamp = System.currentTimeMillis();
    }

    public MessageId(JSONObject jsonObject) {
        this.senderEmail = (String)jsonObject.get(SENDER_EMAIL);
        this.timestamp = (Long) jsonObject.get(TIMESTAMP);
    }
    
    public String getSenderEmail() {
        return senderEmail;
    }
    
    public long getTimestamp() {
        return timestamp;
    }

    @Override
    @SuppressWarnings("unchecked")
    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(SENDER_EMAIL, senderEmail);
        jsonObject.put(TIMESTAMP, timestamp);
        return jsonObject;
    }

    @Override
    public String toString() {
        return SENDER_EMAIL + "=" + senderEmail + ", " + TIMESTAMP + "=" + timestamp;
    }
}
