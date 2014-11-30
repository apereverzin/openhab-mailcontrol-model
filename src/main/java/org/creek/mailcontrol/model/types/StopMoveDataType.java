package org.creek.mailcontrol.model.types;

/**
 * 
 * @author Andrey Pereverzin
 */
public enum StopMoveDataType implements GenericDataType {
    STOP, MOVE;
    
    @Override
    public String toString() {
        return name();
    }
}
