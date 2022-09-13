package com.ugisoftware.hotelmanagement.utils;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.ugisoftware.hotelmanagement.exceptions.EntityNotFoundException;

public class DateUtil {

  public static String  getDate(LocalDate date)
  {
	    DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	  return  date.format(formatters);

	  
  }
  
  public static LocalDate setDate(String text)
  {
	    DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	    return    LocalDate.parse(text, formatters);

  }
    public static long getDifferenceDays(String d1, String d2) {
    	
    	LocalDate dd1=setDate(d1);
    	LocalDate dd2=setDate(d2);

        Period period = Period.between(dd1, dd2);

        return period.getDays();
    }
    public static void compareDates(String birthDate1,String startDate1,String exitDate1)
	{
    	LocalDate birthDate=setDate(birthDate1);
    	LocalDate startDate=setDate(startDate1);
    	

   
        LocalDate birthAddDate = birthDate.plusYears(18);
        int compareBirthStart =  birthAddDate.compareTo(startDate);
        
        if (compareBirthStart>0)
			throw new EntityNotFoundException("Start Date cant be earlier than Birth Date and younger than 18 can not work ");

		if(exitDate1 !=null)
			compareTwoDate(startDate1, exitDate1,"Finish Date cant be earlier than Start Date");
		
		 
		
		
	}
    public static void compareTwoDate(String Date1,String Date2,String message)
    {
		
    	LocalDate dd1=setDate(Date1);
    	LocalDate dd2=setDate(Date2);
    	 int compare =  dd2.compareTo(dd1);
    			 if (compare<0)
    					throw new EntityNotFoundException(message);
    }


}