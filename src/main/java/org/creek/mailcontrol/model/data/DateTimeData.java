package org.creek.mailcontrol.model.data;

import static org.creek.mailcontrol.model.data.DataType.DATE_TIME;

import org.creek.mailcontrol.model.types.DateTimeDataType;
import org.json.simple.JSONObject;

/**
 * 
 * @author Andrey Pereverzin
 */
@SuppressWarnings("serial")
public class DateTimeData extends PrimitiveData<DateTimeDataType> implements StateTransformable {
    public DateTimeData(DateTimeDataType data) {
        super(data);
    }
    
    public DateTimeData(JSONObject jsonObject) {
        super(new DateTimeDataType((String)jsonObject.get(VALUE)));
    }

    @Override
    public DataType getStateType() {
        return getDataType();
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
        return getClass().getName() + " [" + super.toString() + ", " + VALUE + "=" + data.toString() + "]";
    }

    @Override
    protected DataType getDataType() {
        return DATE_TIME;
    }
}
