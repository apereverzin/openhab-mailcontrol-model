package org.creek.mailcontrol.model.command;

import static org.creek.mailcontrol.model.command.CommandType.OPEN_CLOSED;

import org.creek.mailcontrol.model.types.OpenClosedDataType;
import org.json.simple.JSONObject;

/**
 * 
 * @author Andrey Pereverzin
 */
@SuppressWarnings("serial")
public class OpenClosedCommand extends PrimitiveCommand implements CommandTransformable {
    public OpenClosedCommand(OpenClosedDataType data) {
        super(OPEN_CLOSED, data);
    }
    
    public OpenClosedCommand(JSONObject jsonObject) {
        super(jsonObject);
        data = OpenClosedDataType.valueOf((String)jsonObject.get(VALUE));
    }

    @SuppressWarnings("unchecked")
    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject = super.toJSON();
        jsonObject.put(VALUE, ((OpenClosedDataType)data).name());
        return jsonObject;
    }

    @Override
    public String toString() {
        return "OpenClosedCommand [" + super.toString() + ", " + VALUE + "=" + ((OpenClosedDataType)data).name() + "]";
    }
}
