package com.cookie.wash.annotations.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValuesValidator implements ConstraintValidator<Values, String>{

	
	private String[] values;
	
	@Override
	public void initialize(Values arg0) {
		values = arg0.values();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext arg1) {
		boolean b = false;
		for(String v : values){
			if(v.equalsIgnoreCase(value)){
				b = true;
				break;
			}
		}
		return b;
	}

}
