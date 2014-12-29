package org.creek.mailcontrol.model.data;

import static org.creek.mailcontrol.model.data.DataType.PERCENT;

import org.creek.mailcontrol.model.types.PercentDataType;
import org.json.simple.JSONObject;

/**
 * 
 * @author Andrey Pereverzin
 */
@SuppressWarnings("serial")
public class PercentData extends PrimitiveData<PercentDataType> implements StateTransformable, CommandTransformable {
    public PercentData(String value) {
        super(new PercentDataType(value));
    }
    
    public PercentData(JSONObject jsonObject) {
        super(new PercentDataType((String)jsonObject.get(VALUE)));
    }

    public PercentData(PercentDataType data) {
        super(data);
    }

    @SuppressWarnings("unchecked")
    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject = super.toJSON();
        jsonObject.put(VALUE, ((PercentDataType)data).toString());
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
        return PERCENT;
    }

    @Override
    public String toString() {
        return getClass().getName() + " [" + super.toString() + ", " + VALUE + "=" + ((PercentDataType)data).toString() + "]";
    }
}
