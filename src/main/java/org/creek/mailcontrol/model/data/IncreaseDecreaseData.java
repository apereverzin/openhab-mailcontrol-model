package org.creek.mailcontrol.model.data;

import static org.creek.mailcontrol.model.data.DataType.INCREASE_DECREASE;

import org.creek.mailcontrol.model.types.IncreaseDecreaseDataType;
import org.json.simple.JSONObject;

/**
 * 
 * @author Andrey Pereverzin
 */
@SuppressWarnings("serial")
public class IncreaseDecreaseData extends PrimitiveData<IncreaseDecreaseDataType> implements StateTransformable, CommandTransformable {
    public IncreaseDecreaseData(IncreaseDecreaseDataType commandValue) {
        super(commandValue);
    }
    
    public IncreaseDecreaseData(JSONObject jsonObject) {
        super(IncreaseDecreaseDataType.valueOf((String)jsonObject.get(VALUE)));
    }

    @SuppressWarnings("unchecked")
    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject = super.toJSON();
        jsonObject.put(VALUE, ((IncreaseDecreaseDataType)data).name());
        return jsonObject;
    }

    @Override
    public DataType getStateType() {
        return getDataType();
    }

    @Override
    public DataType getCommandType() {
        return getDataType();
    }

    @Override
    protected DataType getDataType() {
        return INCREASE_DECREASE;
    }

    @Override
    public String toString() {
        return getClass().getName() + " [" + super.toString() + ", " + VALUE + "=" + ((IncreaseDecreaseDataType)data).name() + "]";
    }
}
