package org.creek.mailcontrol.model.data;

import static org.creek.mailcontrol.model.data.AbstractData.TYPE;
import static org.creek.mailcontrol.model.data.DataType.DECIMAL;
import static org.creek.mailcontrol.model.data.DataType.HSB;
import static org.creek.mailcontrol.model.data.DataType.INCREASE_DECREASE;
import static org.creek.mailcontrol.model.data.DataType.ON_OFF;
import static org.creek.mailcontrol.model.data.DataType.OPEN_CLOSED;
import static org.creek.mailcontrol.model.data.DataType.PERCENT;
import static org.creek.mailcontrol.model.data.DataType.STOP_MOVE;
import static org.creek.mailcontrol.model.data.DataType.STRING;
import static org.creek.mailcontrol.model.data.DataType.UP_DOWN;

import org.creek.mailcontrol.model.message.ItemData;
import org.json.simple.JSONObject;

/**
 * 
 * @author Andrey Pereverzin
 */
@SuppressWarnings("serial")
public class ItemCommandData extends ItemData {
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
        DataType commandType = DataType.valueOf((String)commandObject.get(TYPE));
        if (commandType == DECIMAL) {
            return new DecimalData(commandObject);
        }
        if (commandType == HSB) {
            return new HSBData(commandObject);
        }
        if (commandType == ON_OFF) {
            return new OnOffData(commandObject);
        } 
        if (commandType == INCREASE_DECREASE) {
            return new IncreaseDecreaseData(commandObject);
        } 
        if (commandType == OPEN_CLOSED) {
            return new OpenClosedData(commandObject);
        } 
        if (commandType == PERCENT) {
            return new PercentData(commandObject);
        } 
        if (commandType == STRING) {
            return new StringData(commandObject);
        } 
        if (commandType == STOP_MOVE) {
            return new StopMoveData(commandObject);
        } 
        if (commandType == UP_DOWN) {
            return new UpDownData(commandObject);
        }
        throw new IllegalArgumentException();
    }
}
