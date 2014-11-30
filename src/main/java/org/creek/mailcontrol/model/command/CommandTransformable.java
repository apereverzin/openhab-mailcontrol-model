package org.creek.mailcontrol.model.command;

import org.creek.mailcontrol.model.message.JsonTransformable;

/**
 * 
 * @author Andrey Pereverzin
 */
public interface CommandTransformable extends JsonTransformable {
    CommandType getCommandType();
}
