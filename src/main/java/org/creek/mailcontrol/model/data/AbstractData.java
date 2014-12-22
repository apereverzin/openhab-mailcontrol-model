package org.creek.mailcontrol.model.data;

import org.creek.mailcontrol.model.message.JsonTransformable;
import org.creek.mailcontrol.model.types.GenericDataType;
import org.json.simple.JSONObject;

/**
 * 
 * @author Andrey Pereverzin
 */
@SuppressWarnings("serial")
public abstract class AbstractData<T extends GenericDataType> implements JsonTransformable {
    public final static String TYPE = "type";

    protected T data;
    
    protected AbstractData(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }

        this.data = data;
    }

    @SuppressWarnings("unchecked")
    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(TYPE, getDataType().name());
        return jsonObject;
    }
    
    public T getData() {
        return data;
    }
    
    protected abstract DataType getDataType();

    @Override
    public String toString() {
        return TYPE + "=" + getDataType();
    }
}
