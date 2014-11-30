package org.creek.mailcontrol.model.types;

/**
 * 
 * @author Andrey Pereverzin
 */
public enum OpenClosedDataType implements GenericDataType {
    OPEN, CLOSED;
    
    @Override
    public String toString() {
        return name();
    }
}
