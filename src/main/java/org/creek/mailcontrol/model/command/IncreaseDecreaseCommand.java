package org.creek.mailcontrol.model.command;

import static org.creek.mailcontrol.model.command.CommandType.INCREASE_DECREASE;

import org.creek.mailcontrol.model.types.IncreaseDecreaseDataType;
import org.json.simple.JSONObject;

/**
 * 
 * @author Andrey Pereverzin
 */
@SuppressWarnings("serial")
public class IncreaseDecreaseCommand extends PrimitiveCommand implements CommandTransformable {
    public IncreaseDecreaseCommand(IncreaseDecreaseDataType commandValue) {
        super(INCREASE_DECREASE, commandValue);
    }
    
    public IncreaseDecreaseCommand(JSONObject jsonObject) {
        super(jsonObject);
        data = IncreaseDecreaseDataType.valueOf((String)jsonObject.get(VALUE));
    }

    @SuppressWarnings("unchecked")
    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject = super.toJSON();
        jsonObject.put(VALUE, ((IncreaseDecreaseDataType)data).name());
        return jsonObject;
    }

    @Override
    public String toString() {
        return "IncreaseDecreaseCommand [" + super.toString() + ", " + VALUE + "=" + ((IncreaseDecreaseDataType)data).name() + "]";
    }
}
