package org.creek.mailcontrol.model.types;

/**
 * 
 * @author Andrey Pereverzin
 */
public enum UpDownDataType implements GenericDataType {
    UP, DOWN;
    
    @Override
    public String toString() {
        return name();
    }
}
