package org.creek.mailcontrol.model.types;

/**
 * 
 * @author Andrey Pereverzin
 */
public class HSBDataType implements GenericDataType {
    private final long hue;
    private final long saturation;
    private final long brightness;

    public HSBDataType(long hue, long saturation, long brightness) {
        this.hue = hue;
        this.saturation = saturation;
        this.brightness = brightness;
    }

    public long getHue() {
        return hue;
    }

    public long getSaturation() {
        return saturation;
    }

    public long getBrightness() {
        return brightness;
    }

    public String toString() {
        return "hue=" + getHue() + ", saturation=" + getSaturation() + ", brightness=" + getBrightness();
    }
}
