package org.creek.mailcontrol.model.command;

import static org.creek.mailcontrol.model.command.AbstractCommand.COMMAND_TYPE;
import static org.creek.mailcontrol.model.command.CommandType.DECIMAL;
import static org.creek.mailcontrol.model.command.CommandType.HSB;
import static org.creek.mailcontrol.model.command.CommandType.INCREASE_DECREASE;
import static org.creek.mailcontrol.model.command.CommandType.ON_OFF;
import static org.creek.mailcontrol.model.command.CommandType.OPEN_CLOSED;
import static org.creek.mailcontrol.model.command.CommandType.PERCENT;
import static org.creek.mailcontrol.model.command.CommandType.STOP_MOVE;
import static org.creek.mailcontrol.model.command.CommandType.STRING;
import static org.creek.mailcontrol.model.command.CommandType.UP_DOWN;

import org.creek.mailcontrol.model.message.GenericItemCommand;
import org.creek.mailcontrol.model.message.JsonTransformable;
import org.json.simple.JSONObject;

/**
 * 
 * @author Andrey Pereverzin
 */
@SuppressWarnings("serial")
public class ItemCommand extends GenericItemCommand implements JsonTransformable {
    private final CommandTransformable command;
    
    private static final String COMMAND = "command";

    public ItemCommand(long timeSent, String itemId, CommandTransformable command) {
        super(timeSent, itemId);
        this.command = command;
    }

    public ItemCommand(JSONObject jsonObject) {
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
        return "ItemCommand [" + super.toString() + ", command=" + command + "]";
    }

    private CommandTransformable buildCommand(JSONObject jsonObject) {
        JSONObject commandObject = (JSONObject)jsonObject.get(COMMAND);
        CommandType commandType = CommandType.valueOf((String)commandObject.get(COMMAND_TYPE));
        if (commandType == DECIMAL) {
            return new DecimalCommand(commandObject);
        }
        if (commandType == HSB) {
            return new HSBCommand(commandObject);
        }
        if (commandType == INCREASE_DECREASE) {
            return new IncreaseDecreaseCommand(commandObject);
        }
        if (commandType == ON_OFF) {
            return new OnOffCommand(commandObject);
        } 
        if (commandType == OPEN_CLOSED) {
            return new OpenClosedCommand(commandObject);
        } 
        if (commandType == PERCENT) {
            return new PercentCommand(commandObject);
        } 
        if (commandType == STOP_MOVE) {
            return new StopMoveCommand(commandObject);
        } 
        if (commandType == STRING) {
            return new StringCommand(commandObject);
        } 
        if (commandType == UP_DOWN) {
            return new UpDownCommand(commandObject);
        }
        throw new IllegalArgumentException();
    }
}
