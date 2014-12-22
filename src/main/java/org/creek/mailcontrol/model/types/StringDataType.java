package org.creek.mailcontrol.model.types;

/**
 * 
 * @author Andrey Pereverzin
 */
public class StringDataType implements GenericDataType {
    private final String value;

    public StringDataType(String value) {
        if (value == null) {
            throw new IllegalArgumentException();
        }

        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
