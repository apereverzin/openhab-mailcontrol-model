package org.creek.mailcontrol.model.command;

import org.creek.mailcontrol.model.types.GenericDataType;
import org.json.simple.JSONObject;

/**
 * 
 * @author Andrey Pereverzin
 */
@SuppressWarnings("serial")
public abstract class PrimitiveCommand extends AbstractCommand implements CommandTransformable {
    protected static final String VALUE = "value";

    protected PrimitiveCommand(CommandType commandType, GenericDataType data) {
        super(commandType, data);
    }
    
    protected PrimitiveCommand(JSONObject jsonObject) {
        super(jsonObject);
    }
}
