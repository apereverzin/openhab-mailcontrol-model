package org.creek.mailcontrol.model.command;

import static org.creek.mailcontrol.model.command.CommandType.PERCENT;

import org.creek.mailcontrol.model.types.PercentDataType;
import org.json.simple.JSONObject;

/**
 * 
 * @author Andrey Pereverzin
 */
@SuppressWarnings("serial")
public class PercentCommand extends PrimitiveCommand implements CommandTransformable {
    public PercentCommand(String value) {
        super(PERCENT, new PercentDataType(value));
    }
    
    public PercentCommand(JSONObject jsonObject) {
        super(jsonObject);
        data = new PercentDataType((String)jsonObject.get(VALUE));
    }

    public PercentCommand(PercentDataType data) {
        super(PERCENT, data);
    }

    @SuppressWarnings("unchecked")
    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject = super.toJSON();
        jsonObject.put(VALUE, ((PercentDataType)data).toString());
        return jsonObject;
    }

    @Override
    public String toString() {
        return "PercentCommand [" + super.toString() + ", " + VALUE + "=" + ((PercentDataType)data).toString() + "]";
    }
}
