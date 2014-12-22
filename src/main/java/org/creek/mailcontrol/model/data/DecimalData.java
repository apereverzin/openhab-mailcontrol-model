package org.creek.mailcontrol.model.data;

import static org.creek.mailcontrol.model.data.DataType.DECIMAL;

import org.creek.mailcontrol.model.types.DecimalDataType;
import org.json.simple.JSONObject;

/**
 * 
 * @author Andrey Pereverzin
 */
@SuppressWarnings("serial")
public class DecimalData extends PrimitiveData<DecimalDataType> implements StateTransformable, CommandTransformable {
    public DecimalData(String value) {
        super(new DecimalDataType(value));
    }
    
    public DecimalData(JSONObject jsonObject) {
        super(new DecimalDataType((String)jsonObject.get(VALUE)));
    }

    public DecimalData(DecimalDataType data) {
        super(data);
    }

    @Override
    public DataType getStateType() {
        return DECIMAL;
    }

    @Override
    public DataType getCommandType() {
        return DECIMAL;
    }

    @SuppressWarnings("unchecked")
    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject = super.toJSON();
        jsonObject.put(VALUE, ((DecimalDataType)data).toString());
        return jsonObject;
    }

    @Override
    public String toString() {
        return getClass().getName() + " [" + super.toString() + ", " + VALUE + "=" + ((DecimalDataType)data).toString() + "]";
    }

    @Override
    protected DataType getDataType() {
        return DECIMAL;
    }
}
