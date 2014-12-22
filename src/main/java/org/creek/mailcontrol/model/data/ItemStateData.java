package org.creek.mailcontrol.model.data;

import static org.creek.mailcontrol.model.data.AbstractData.TYPE;
import static org.creek.mailcontrol.model.data.DataType.DECIMAL;
import static org.creek.mailcontrol.model.data.DataType.HSB;
import static org.creek.mailcontrol.model.data.DataType.ON_OFF;
import static org.creek.mailcontrol.model.data.DataType.OPEN_CLOSED;
import static org.creek.mailcontrol.model.data.DataType.PERCENT;
import static org.creek.mailcontrol.model.data.DataType.STRING;
import static org.creek.mailcontrol.model.data.DataType.UP_DOWN;

import org.creek.mailcontrol.model.message.GenericItemState;
import org.json.simple.JSONObject;

/**
 * 
 * @author Andrey Pereverzin
 */
@SuppressWarnings("serial")
public class ItemStateData extends GenericItemState {
    private final StateTransformable state;
    
    private static final String STATE = "state";

    public ItemStateData(long timeSent, String itemId, StateTransformable state) {
        super(timeSent, itemId);
        this.state = state;
    }

    public ItemStateData(JSONObject jsonObject) {
        super(jsonObject);
        this.state = buildState(jsonObject);
    }

    public StateTransformable getState() {
        return state;
    }

    @Override
    @SuppressWarnings("unchecked")
    public JSONObject toJSON() {
        JSONObject jsonObject = super.toJSON();
        jsonObject.put(STATE, state.toJSON());
        return jsonObject;
    }

    @Override
    public String toString() {
        return getClass().getName() + " [" + super.toString() + ", " + STATE + "=" + state + "]";
    }

    private StateTransformable buildState(JSONObject jsonObject) {
        JSONObject stateObject = (JSONObject)jsonObject.get(STATE);
        DataType stateType = DataType.valueOf((String)stateObject.get(TYPE));
        if (stateType == DECIMAL) {
            return new DecimalData(stateObject);
        }
        if (stateType == HSB) {
            return new HSBData(stateObject);
        }
        if (stateType == ON_OFF) {
            return new OnOffData(stateObject);
        } 
        if (stateType == OPEN_CLOSED) {
            return new OpenClosedData(stateObject);
        } 
        if (stateType == PERCENT) {
            return new PercentData(stateObject);
        } 
        if (stateType == STRING) {
            return new StringData(stateObject);
        } 
        if (stateType == UP_DOWN) {
            return new UpDownData(stateObject);
        }
        throw new IllegalArgumentException();
    }
}
