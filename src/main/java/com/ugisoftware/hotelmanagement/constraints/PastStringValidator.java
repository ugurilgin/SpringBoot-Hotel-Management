package com.ugisoftware.hotelmanagement.constraints;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ugisoftware.hotelmanagement.utils.DateUtil;

public class PastStringValidator implements ConstraintValidator<PastString, String> {
	  @Override
	  public boolean isValid(final String valueToValidate, final ConstraintValidatorContext context) {
		LocalDate valueDate=DateUtil.setDate(valueToValidate);	
	    LocalDate todayDate=LocalDate.now();
	 
    	 int compare =  todayDate.compareTo(valueDate);
    			 
    	return compare>0;
	  }
	}