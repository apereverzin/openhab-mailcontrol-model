package org.creek.mailcontrol.model.message;

import java.io.Serializable;

import org.json.simple.JSONObject;

/**
 * 
 * @author Andrey Pereverzin
 */
public interface JsonTransformable extends Serializable {
    JSONObject toJSON();
}
