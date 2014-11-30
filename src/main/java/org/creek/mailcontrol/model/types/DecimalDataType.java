package org.creek.mailcontrol.model.types;

import java.math.BigDecimal;

/**
 * 
 * @author Andrey Pereverzin
 */
@SuppressWarnings("serial")
public class DecimalDataType extends Number implements GenericDataType {

    final static public DecimalDataType ZERO = new DecimalDataType("0");

    protected BigDecimal value;

    public DecimalDataType() {
        this.value = BigDecimal.ZERO;
    }

    public DecimalDataType(String value) {
        this.value = new BigDecimal(value);
    }

    public static DecimalDataType valueOf(String value) {
        return new DecimalDataType(value);
    }

    @Override
    public double doubleValue() {
        return value.doubleValue();
    }

    @Override
    public float floatValue() {
        return value.floatValue();
    }

    @Override
    public int intValue() {
        return value.intValue();
    }

    @Override
    public long longValue() {
        return value.longValue();
    }
    
    @Override
    public String toString() {
        return value.toPlainString();
    }
}
