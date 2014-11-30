package org.creek.mailcontrol.model.message;

import org.json.simple.JSONObject;

/**
 * 
 * @author Andrey Pereverzin
 */
@SuppressWarnings("serial")
public abstract class AbstractSendableData implements JsonTransformable {
    private final long timeSent;

    private static final String TIME_SENT = "timeSent";

    public AbstractSendableData(long timeSent) {
        this.timeSent = timeSent;
    }

    public AbstractSendableData(JSONObject jsonObject) {
        this.timeSent = Long.parseLong((String) jsonObject.get(TIME_SENT));
    }
    
    public long getTimeSent() {
        return timeSent;
    }

    @Override
    @SuppressWarnings("unchecked")
    public JSONObject toJSON() {
        JSONObject dataObject = new JSONObject();
        dataObject.put(TIME_SENT, Long.toString(getTimeSent()));
        return dataObject;
    }

    @Override
    public String toString() {
        return "timeSent=" + timeSent;
    }
}
