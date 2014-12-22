package org.creek.mailcontrol.model.data;

import org.creek.mailcontrol.model.message.JsonTransformable;

/**
 * 
 * @author Andrey Pereverzin
 */
public interface StateTransformable extends JsonTransformable {
    DataType getStateType();
}
