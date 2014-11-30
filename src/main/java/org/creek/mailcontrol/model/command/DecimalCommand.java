package org.creek.mailcontrol.model.command;

import static org.creek.mailcontrol.model.command.CommandType.DECIMAL;

import org.creek.mailcontrol.model.types.DecimalDataType;
import org.json.simple.JSONObject;

/**
 * 
 * @author Andrey Pereverzin
 */
@SuppressWarnings("serial")
public class DecimalCommand extends PrimitiveCommand implements CommandTransformable {
    public DecimalCommand(String value) {
        super(DECIMAL, new DecimalDataType(value));
    }
    
    public DecimalCommand(JSONObject jsonObject) {
        super(jsonObject);
        this.data = new DecimalDataType((String)jsonObject.get(VALUE));
    }

    public DecimalCommand(DecimalDataType data) {
        super(DECIMAL, data);
    }

    @SuppressWarnings("unchecked")
    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject = super.toJSON();
        jsonObject.put(VALUE, ((DecimalDataType)data).toString());
        return jsonObject;
    }

    @Override
    public String toString() {
        return "DecimalCommand [" + super.toString() + ", " + VALUE + "=" + ((DecimalDataType)data).toString() + "]";
    }
}
