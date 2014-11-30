package org.creek.mailcontrol.model.types;

import java.math.BigDecimal;

/**
 * 
 * @author Andrey Pereverzin
 */
@SuppressWarnings("serial")
public class PercentDataType extends DecimalDataType implements GenericDataType {
	final static public PercentDataType ZERO = new PercentDataType("0"); 
	final static public PercentDataType HUNDRED = new PercentDataType("100"); 
	
	public PercentDataType() {
		super();
	}
	
	public PercentDataType(String value) {
		super(value);
		validateValue(this.value);
	}
	
	private void validateValue(BigDecimal value) {
		if(BigDecimal.ZERO.compareTo(value) > 0 || new BigDecimal(100).compareTo(value) < 0) {
			throw new IllegalArgumentException("Value must be between 0 and 100");
		}
	}
    
    @Override
    public String toString() {
        return value.toString();
    }
}
