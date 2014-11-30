package org.creek.mailcontrol.model.command;

import static org.creek.mailcontrol.model.command.CommandType.STRING;

import org.creek.mailcontrol.model.types.StringDataType;
import org.json.simple.JSONObject;

/**
 * 
 * @author Andrey Pereverzin
 */
@SuppressWarnings("serial")
public class StringCommand extends PrimitiveCommand implements CommandTransformable {
    public StringCommand(StringDataType data) {
        super(STRING, data);
    }
    
    public StringCommand(JSONObject jsonObject) {
        super(jsonObject);
        String value = (String)jsonObject.get(VALUE);
        
        if (value == null) {
            throw new IllegalArgumentException();
        }
        
        this.data = new StringDataType((String)jsonObject.get(VALUE));
    }

    @SuppressWarnings("unchecked")
    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject = super.toJSON();
        jsonObject.put(VALUE, data.toString());
        return jsonObject;
    }

    @Override
    public String toString() {
        return "StringTypeCommand [" + super.toString() + ", " + VALUE + "=" + data.toString() + "]";
    }
}
