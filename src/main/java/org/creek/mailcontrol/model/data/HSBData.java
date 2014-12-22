package org.creek.mailcontrol.model.data;

import static org.creek.mailcontrol.model.data.DataType.HSB;

import org.creek.mailcontrol.model.types.HSBDataType;
import org.json.simple.JSONObject;

/**
 * 
 * @author Andrey Pereverzin
 */
@SuppressWarnings("serial")
public class HSBData extends AbstractData<HSBDataType> implements StateTransformable, CommandTransformable {
    private static final String HUE = "hue";
    private static final String SATURATION = "saturation";
    private static final String BRIGHTNESS = "brightness";

    public HSBData(long hue, long saturation, long brightness) {
        super(new HSBDataType(hue, saturation, brightness));
    }

    public HSBData(HSBDataType data) {
        super(data);
    }

    public HSBData(JSONObject jsonObject) {
        super(new HSBDataType((Long) jsonObject.get(HUE), (Long) jsonObject.get(SATURATION), (Long) jsonObject.get(BRIGHTNESS)));
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject = super.toJSON();
        jsonObject.put(HUE, ((HSBDataType)data).getHue());
        jsonObject.put(SATURATION, ((HSBDataType)data).getSaturation());
        jsonObject.put(BRIGHTNESS, ((HSBDataType)data).getBrightness());
        return jsonObject;
    }

    @Override
    public DataType getStateType() {
        return getDataType();
    }

    @Override
    public DataType getCommandType() {
        return getDataType();
    }

    @Override
    protected DataType getDataType() {
        return HSB;
    }

    @Override
    public String toString() {
        return getClass().getName() + " [" + super.toString() + ", " + HSB + " [" + HUE + "=" + ((HSBDataType)data).getHue() + ", " + 
                SATURATION + "=" + ((HSBDataType)data).getSaturation() + ", " + 
                BRIGHTNESS + "=" + ((HSBDataType)data).getBrightness() + "]]";
    }
}
