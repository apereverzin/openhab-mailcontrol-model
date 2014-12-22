package org.creek.mailcontrol.model.data;

import org.creek.mailcontrol.model.message.GenericItemState;
import org.creek.mailcontrol.model.message.JsonTransformable;
import org.json.simple.JSONObject;

/**
 * 
 * @author Andrey Pereverzin
 */
@SuppressWarnings("serial")
public abstract class ItemData extends GenericItemState implements JsonTransformable {
    private final StateTransformable state;
    
    private static final String STATE = "state";

    public ItemData(long timeSent, String itemId, StateTransformable state) {
        super(timeSent, itemId);
        this.state = state;
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

}
