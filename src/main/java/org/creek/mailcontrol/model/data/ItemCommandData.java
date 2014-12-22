package org.creek.mailcontrol.model.data;

import static org.creek.mailcontrol.model.data.AbstractData.TYPE;
import static org.creek.mailcontrol.model.data.DataType.DECIMAL;
import static org.creek.mailcontrol.model.data.DataType.HSB;
import static org.creek.mailcontrol.model.data.DataType.INCREASE_DECREASE;
import static org.creek.mailcontrol.model.data.DataType.ON_OFF;
import static org.creek.mailcontrol.model.data.DataType.OPEN_CLOSED;
import static org.creek.mailcontrol.model.data.DataType.STOP_MOVE;
import static org.creek.mailcontrol.model.data.DataType.STRING;
import static org.creek.mailcontrol.model.data.DataType.UP_DOWN;

import org.creek.mailcontrol.model.message.GenericItemState;
import org.json.simple.JSONObject;

/**
 * 
 * @author Andrey Pereverzin
 */
@SuppressWarnings("serial")
public class ItemCommandData extends GenericItemState {
    private final CommandTransformable command;
    
    private static final String COMMAND = "command";

    public ItemCommandData(long timeSent, String itemId, CommandTransformable command) {
        super(timeSent, itemId);
        this.command = command;
    }

    public ItemCommandData(JSONObject jsonObject) {
        super(jsonObject);
        this.command = buildCommand(jsonObject);
    }

    public CommandTransformable getCommand() {
        return command;
    }

    @Override
    @SuppressWarnings("unchecked")
    public JSONObject toJSON() {
        JSONObject jsonObject = super.toJSON();
        jsonObject.put(COMMAND, command.toJSON());
        return jsonObject;
    }

    @Override
    public String toString() {
        return getClass().getName() + " [" + super.toString() + ", " + COMMAND + "=" + command + "]";
    }

    private CommandTransformable buildCommand(JSONObject jsonObject) {
        JSONObject commandObject = (JSONObject)jsonObject.get(COMMAND);
        DataType stateType = DataType.valueOf((String)commandObject.get(TYPE));
        if (stateType == DECIMAL) {
            return new DecimalData(commandObject);
        }
        if (stateType == HSB) {
            return new HSBData(commandObject);
        }
        if (stateType == ON_OFF) {
            return new OnOffData(commandObject);
        } 
        if (stateType == INCREASE_DECREASE) {
            return new IncreaseDecreaseData(commandObject);
        } 
        if (stateType == OPEN_CLOSED) {
            return new OpenClosedData(commandObject);
        } 
        if (stateType == STRING) {
            return new StringData(commandObject);
        } 
        if (stateType == STOP_MOVE) {
            return new StopMoveData(commandObject);
        } 
        if (stateType == UP_DOWN) {
            return new UpDownData(commandObject);
        }
        throw new IllegalArgumentException();
    }
}
