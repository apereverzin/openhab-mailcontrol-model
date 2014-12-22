package org.creek.mailcontrol.model.data;

import static org.creek.mailcontrol.model.data.DataType.UP_DOWN;

import org.creek.mailcontrol.model.types.UpDownDataType;
import org.json.simple.JSONObject;

/**
 * 
 * @author Andrey Pereverzin
 */
@SuppressWarnings("serial")
public class UpDownData extends PrimitiveData<UpDownDataType> implements StateTransformable, CommandTransformable {
    public UpDownData(UpDownDataType data) {
        super(data);
    }
    
    public UpDownData(JSONObject jsonObject) {
        super(UpDownDataType.valueOf((String)jsonObject.get(VALUE)));
    }

    @SuppressWarnings("unchecked")
    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject = super.toJSON();
        jsonObject.put(VALUE, ((UpDownDataType)data).name());
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
        return UP_DOWN;
    }

    @Override
    public String toString() {
        return getClass().getName() + " [" + super.toString() + ", " + VALUE + "=" + ((UpDownDataType)data).name() + "]";
    }
}
