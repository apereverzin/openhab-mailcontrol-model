package org.creek.mailcontrol.model.command;

import org.creek.mailcontrol.model.types.GenericDataType;
import org.json.simple.JSONObject;

/**
 * 
 * @author Andrey Pereverzin
 */
@SuppressWarnings("serial")
public abstract class AbstractCommand implements CommandTransformable {
    public final static String COMMAND_TYPE = "commandType";
    
    protected final CommandType commandType;
    protected GenericDataType data;
    
    protected AbstractCommand(CommandType commandType, GenericDataType data) {
        this.commandType = commandType;
        this.data = data;
    }

    public AbstractCommand(JSONObject jsonObject) {
        this.commandType = CommandType.valueOf((String)jsonObject.get(COMMAND_TYPE));
    }

    @Override
    public CommandType getCommandType() {
        return commandType;
    }

    public GenericDataType getData() {
        return data;
    }

    @SuppressWarnings("unchecked")
    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(COMMAND_TYPE, commandType.name());
        return jsonObject;
    }

    @Override
    public String toString() {
        return COMMAND_TYPE + "=" + commandType.name();
    }
}
