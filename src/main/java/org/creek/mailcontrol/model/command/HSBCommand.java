package org.creek.mailcontrol.model.command;

import static org.creek.mailcontrol.model.command.CommandType.HSB;

import org.creek.mailcontrol.model.types.HSBDataType;
import org.json.simple.JSONObject;

/**
 * 
 * @author Andrey Pereverzin
 */
@SuppressWarnings("serial")
public class HSBCommand extends AbstractCommand implements CommandTransformable {
    private static final String HUE = "hue";
    private static final String SATURATION = "saturation";
    private static final String BRIGHTNESS = "brightness";

    public HSBCommand(long hue, long saturation, long brightness) {
        super(HSB, new HSBDataType(hue, saturation, brightness));
    }

    public HSBCommand(HSBDataType data) {
        super(HSB, data);
    }

    public HSBCommand(JSONObject jsonObject) {
        super(jsonObject);
        long hue = (Long) jsonObject.get(HUE);
        long saturation = (Long) jsonObject.get(SATURATION);
        long brightness = (Long) jsonObject.get(BRIGHTNESS);
        data = new HSBDataType(hue, saturation, brightness);
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
    public String toString() {
        return "HSBCommand [" + super.toString() + ", " + HSB + "[" + HUE + "=" + ((HSBDataType)data).getHue() + ", " + 
                SATURATION + "=" + ((HSBDataType)data).getSaturation() + ", " + 
                BRIGHTNESS + "=" + ((HSBDataType)data).getBrightness() + "]]";
    }
}
