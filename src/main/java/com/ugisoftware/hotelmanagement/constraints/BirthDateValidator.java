package com.ugisoftware.hotelmanagement.constraints;

import java.time.LocalDate;
import java.time.Period;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ugisoftware.hotelmanagement.utils.DateUtil;

public class BirthDateValidator implements ConstraintValidator<BirthDate, String> {
  @Override
  public boolean isValid(final String valueToValidate, final ConstraintValidatorContext context) {
	LocalDate valueDate=DateUtil.setDate(valueToValidate);	
    LocalDate todayDate=LocalDate.now();
    Period period = Period.between(valueDate, todayDate);
    return period.getYears() >= 18;
  }
}
