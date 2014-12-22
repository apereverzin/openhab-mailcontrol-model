package org.creek.mailcontrol.model.data;

import org.creek.mailcontrol.model.types.GenericDataType;

/**
 * 
 * @author Andrey Pereverzin
 */
@SuppressWarnings("serial")
public abstract class PrimitiveData <T extends GenericDataType> extends AbstractData<T> {
    protected static final String VALUE = "value";

    protected PrimitiveData(T data) {
        super(data);
    }
}
