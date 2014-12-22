package org.creek.mailcontrol.model.data;

import static org.creek.mailcontrol.model.data.DataType.STRING;

import org.creek.mailcontrol.model.types.StringDataType;
import org.json.simple.JSONObject;

/**
 * 
 * @author Andrey Pereverzin
 */
@SuppressWarnings("serial")
public class StringData extends PrimitiveData<StringDataType> implements StateTransformable, CommandTransformable {
    public StringData(StringDataType data) {
        super(data);
    }
    
    public StringData(JSONObject jsonObject) {
        super(new StringDataType((String)jsonObject.get(VALUE)));
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
        return STRING;
    }

    @SuppressWarnings("unchecked")
    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject = super.toJSON();
        jsonObject.put(VALUE, data.toString());
        return jsonObject;
    }

    @Override
    public String toString() {
        return getClass().getName() + " [" + super.toString() + ", " + VALUE + "=" + data.toString() + "]";
    }
}
