package org.creek.mailcontrol.model.data;

import static org.creek.mailcontrol.model.data.DataType.ON_OFF;

import org.creek.mailcontrol.model.types.OnOffDataType;
import org.json.simple.JSONObject;

/**
 * 
 * @author Andrey Pereverzin
 */
@SuppressWarnings("serial")
public class OnOffData extends PrimitiveData<OnOffDataType> implements StateTransformable, CommandTransformable {
    public OnOffData(OnOffDataType commandValue) {
        super(commandValue);
    }
    
    public OnOffData(JSONObject jsonObject) {
        super(OnOffDataType.valueOf((String)jsonObject.get(VALUE)));
    }

    @SuppressWarnings("unchecked")
    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject = super.toJSON();
        jsonObject.put(VALUE, ((OnOffDataType)data).name());
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
        return ON_OFF;
    }

    @Override
    public String toString() {
        return getClass().getName() + " [" + super.toString() + ", " + VALUE + "=" + ((OnOffDataType)data).name() + "]";
    }
}
