package org.creek.mailcontrol.model.data;

import static org.creek.mailcontrol.model.data.DataType.STOP_MOVE;

import org.creek.mailcontrol.model.types.StopMoveDataType;
import org.json.simple.JSONObject;

/**
 * 
 * @author Andrey.Pereverzin
 * @since 1.6.0
 */
@SuppressWarnings("serial")
public class StopMoveData extends PrimitiveData<StopMoveDataType> implements CommandTransformable {
    public StopMoveData(StopMoveDataType data) {
        super(data);
    }
    
    public StopMoveData(JSONObject jsonObject) {
        super(StopMoveDataType.valueOf((String)jsonObject.get(VALUE)));
    }

    @SuppressWarnings("unchecked")
    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject = super.toJSON();
        jsonObject.put(VALUE, ((StopMoveDataType)data).name());
        return jsonObject;
    }

    @Override
    public DataType getCommandType() {
        return getDataType();
    }

    @Override
    protected DataType getDataType() {
        return STOP_MOVE;
    }

    @Override
    public String toString() {
        return getClass().getName() + " [" + super.toString() + ", " + VALUE + "=" + ((StopMoveDataType)data).name() + "]";
    }
}
