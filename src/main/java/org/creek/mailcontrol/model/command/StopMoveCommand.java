package org.creek.mailcontrol.model.command;

import static org.creek.mailcontrol.model.command.CommandType.STOP_MOVE;

import org.creek.mailcontrol.model.types.StopMoveDataType;
import org.json.simple.JSONObject;

/**
 * 
 * @author Andrey.Pereverzin
 * @since 1.6.0
 */
@SuppressWarnings("serial")
public class StopMoveCommand extends PrimitiveCommand implements CommandTransformable {
    public StopMoveCommand(StopMoveDataType data) {
        super(STOP_MOVE, data);
    }
    
    public StopMoveCommand(JSONObject jsonObject) {
        super(jsonObject);
        data = StopMoveDataType.valueOf((String)jsonObject.get(VALUE));
    }

    @SuppressWarnings("unchecked")
    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject = super.toJSON();
        jsonObject.put(VALUE, ((StopMoveDataType)data).name());
        return jsonObject;
    }

    @Override
    public String toString() {
        return "StopMoveCommand [" + super.toString() + ", " + VALUE + "=" + ((StopMoveDataType)data).name() + "]";
    }
}
