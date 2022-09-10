package com.ugisoftware.hotelmanagement.utils;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.ugisoftware.hotelmanagement.exceptions.EntityNotFoundException;

public class DateUtil {

    public static Date removeTime(Date date) {
        try { 
        	 Calendar cal = Calendar.getInstance();
             cal.setTime(date);
             cal.set(Calendar.HOUR_OF_DAY, 0);
             cal.set(Calendar.MINUTE, 0);
             cal.set(Calendar.SECOND, 0);
             cal.set(Calendar.MILLISECOND, 0);
        DateFormat outputFormatter = new SimpleDateFormat("dd/MM/yyyy");
        Date output = outputFormatter.parse(outputFormatter.format(cal.getTime())) ;

        return output;
       
        
        }
        catch (Exception e) {
			return null;
		}
    }
    
    public static void compareDates(Date birthDate1,Date startDate1,Date exitDate1)
	{
		Date birthDate = birthDate1;
		Date startDate = startDate1;
		Date exitDate= exitDate1;
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
   
		dateFormat.format(birthDate);
		dateFormat.format(startDate);
		
		Calendar c = Calendar.getInstance();
        c.setTime(birthDate);
        c.add(Calendar.YEAR, 18);
        Date birthAddDate = c.getTime();
        int compareBirthStart =  birthAddDate.compareTo(startDate);
        
        if (compareBirthStart>0)
			throw new EntityNotFoundException("Start Date cant be earlier than Birth Date and younger than 18 can not work ");

		try {
			dateFormat.format(exitDate);
			int compareStartExit = startDate.compareTo(exitDate);
		 if (compareStartExit<0)
			throw new EntityNotFoundException("Finish Date cant be earlier than Start Date");}
		catch (Exception e) {
			// TODO: handle exception
		}
		 
	}



}