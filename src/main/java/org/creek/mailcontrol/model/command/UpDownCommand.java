package org.creek.mailcontrol.model.command;

import static org.creek.mailcontrol.model.command.CommandType.UP_DOWN;

import org.creek.mailcontrol.model.types.UpDownDataType;
import org.json.simple.JSONObject;

/**
 * 
 * @author Andrey Pereverzin
 */
@SuppressWarnings("serial")
public class UpDownCommand extends PrimitiveCommand implements CommandTransformable {
    public UpDownCommand(UpDownDataType data) {
        super(UP_DOWN, data);
    }
    
    public UpDownCommand(JSONObject jsonObject) {
        super(jsonObject);
        data = UpDownDataType.valueOf((String)jsonObject.get(VALUE));
    }

    @SuppressWarnings("unchecked")
    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject = super.toJSON();
        jsonObject.put(VALUE, ((UpDownDataType)data).name());
        return jsonObject;
    }

    @Override
    public String toString() {
        return "UpDownCommand [" + super.toString() + ", " + VALUE + "=" + ((UpDownDataType)data).name() + "]";
    }
}
