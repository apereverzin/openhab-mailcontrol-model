package org.creek.mailcontrol.model.command;

import static org.creek.mailcontrol.model.command.CommandType.ON_OFF;

import org.creek.mailcontrol.model.types.OnOffDataType;
import org.json.simple.JSONObject;

/**
 * 
 * @author Andrey Pereverzin
 */
@SuppressWarnings("serial")
public class OnOffCommand extends PrimitiveCommand implements CommandTransformable {
    public OnOffCommand(OnOffDataType commandValue) {
        super(ON_OFF, commandValue);
    }
    
    public OnOffCommand(JSONObject jsonObject) {
        super(jsonObject);
        data = OnOffDataType.valueOf((String)jsonObject.get(VALUE));
    }

    @SuppressWarnings("unchecked")
    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject = super.toJSON();
        jsonObject.put(VALUE, ((OnOffDataType)data).name());
        return jsonObject;
    }

    @Override
    public String toString() {
        return "OnOffCommand [" + super.toString() + ", " + VALUE + "=" + ((OnOffDataType)data).name() + "]";
    }
}
