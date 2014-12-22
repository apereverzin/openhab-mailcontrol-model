package org.creek.mailcontrol.model.message;

import org.json.simple.JSONObject;

/**
 * 
 * @author Andrey Pereverzin
 */
@SuppressWarnings("serial")
public abstract class AbstractResponse extends AbstractMessage {
    private final MessageId requestId;
    
    private final static String REQUEST_ID = "requestId";
    
    public AbstractResponse(String senderEmail, MessageId requestId) {
        super(senderEmail);
        this.requestId = requestId;
    }

    public AbstractResponse(JSONObject jsonObject){
        super(jsonObject);
        this.requestId = new MessageId((JSONObject) jsonObject.get(REQUEST_ID));
    }

    public MessageId getRequestId() {
        return requestId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public JSONObject toJSON() {
        JSONObject jsonObject = super.toJSON();
        jsonObject.put(REQUEST_ID, requestId.toJSON());
        return jsonObject;
    }

    @Override
    public String toString() {
        return super.toString() + ", " + REQUEST_ID + " [" + requestId.toString() + "]";
    }
}
