package org.creek.mailcontrol.model.data;

import static org.creek.mailcontrol.model.data.DataType.OPEN_CLOSED;

import org.creek.mailcontrol.model.types.OpenClosedDataType;
import org.json.simple.JSONObject;

/**
 * 
 * @author Andrey Pereverzin
 */
@SuppressWarnings("serial")
public class OpenClosedData extends PrimitiveData<OpenClosedDataType> implements StateTransformable, CommandTransformable {
    public OpenClosedData(OpenClosedDataType data) {
        super(data);
    }
    
    public OpenClosedData(JSONObject jsonObject) {
        super(OpenClosedDataType.valueOf((String)jsonObject.get(VALUE)));
    }

    @SuppressWarnings("unchecked")
    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject = super.toJSON();
        jsonObject.put(VALUE, ((OpenClosedDataType)data).name());
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
        return OPEN_CLOSED;
    }

    @Override
    public String toString() {
        return getClass().getName() + " [" + super.toString() + ", " + VALUE + "=" + ((OpenClosedDataType)data).name() + "]";
    }
}
