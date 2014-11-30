package org.creek.mailcontrol.model.types;

/**
 * 
 * @author Andrey Pereverzin
 */
public enum IncreaseDecreaseDataType implements GenericDataType {
    INCREASE, DECREASE;
    
    public String toString() {
        return name();
    }
}
