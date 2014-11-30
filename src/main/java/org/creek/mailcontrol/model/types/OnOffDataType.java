package org.creek.mailcontrol.model.types;

/**
 * 
 * @author Andrey Pereverzin
 */
public enum OnOffDataType implements GenericDataType {
    ON, OFF;
    
    @Override
    public String toString() {
        return name();
    }
}
