package org.xmlcrm.utils.math;

import java.util.Date;
import java.util.Locale;
import java.text.SimpleDateFormat;
/**
 * @author Sebastian Wagner
 * 27.08.2005 - 19:24:25
 *
 */
public class Calender {
	public Calender(){
	}
    private static Calender instance = null;
    public static synchronized Calender getInstance(){
        if(instance == null)
        {
            instance = new Calender();
        }

        return instance;
    }	
    
    public String getDateByMiliSeconds(long msek){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.GERMAN);
        Date dateOld = new Date();
        long timeAdv = msek;
        dateOld.setTime(timeAdv);
        String result = sdf.format(dateOld);
        return result;
    }
    
    public String getDatumMili(long msek){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy 'um' HH-mm-ss", Locale.GERMAN);
        Date dateOld = new Date();
        long timeAdv = msek;
        dateOld.setTime(timeAdv);
        String result = sdf.format(dateOld);
        return result;
    } 
    public String getUhrzeitMili(long msek){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.GERMAN);
        Date dateOld = new Date();
        long timeAdv = msek;
        dateOld.setTime(timeAdv);
        String result = sdf.format(dateOld);
        return result;
    }    
	public String getDatum(int sek){
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy 'um' HH-mm-ss", Locale.GERMAN);
		Date dateOld = new Date();
		long timeAdv = sek;
		timeAdv*=1000;
		dateOld.setTime(timeAdv);
		String result = sdf.format(dateOld);
		return result;
	}
    public long getTimeStampMili (){
        Date date1 = new Date();
        long date = date1.getTime();
        return date;
    }    
	public int getTimeStamp (){
		Date date1 = new Date();
		long date = date1.getTime();
		int datum = Math.round(date/1000);
		return datum;
	}
    public long ParseDatum(int year, int month, int day, int hour, int min){
        long returndate = 0;
        try {
        	String Sday = "";
        	if (day<10){
        		Sday = "0"+day;
        	} else {
        		Sday = ""+day;
        	}
        	String Smonth = "";
        	if (month<10){
        		Smonth = "0"+month;
        	} else {
        		Smonth = ""+month;
        	}
        	String Syear = "";
        	if (year<10){
        		Syear = "0"+year;
        	} else {
        		Syear = ""+year;
        	}
        	String Shour = "";
        	if (hour<10){
        		Shour = "0"+hour;
        	} else {
        		Shour = ""+hour;
        	}
        	String Smin = "";
        	if (min<10){
        		Smin = "0"+min;
        	} else {
        		Smin = ""+min;
        	}
        	String datum = Sday+"-"+Smonth+"-"+Syear+" "+Shour+"-"+Smin+"-00";
        	returndate = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss" ).parse(datum).getTime();
        	System.out.println("returndate: "+returndate);
        } catch(Exception e) {
            System.out.println("Fatal Error @ Date Conversion: "+e);
        }
        return returndate;
    }
    public int getYear(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy", Locale.GERMAN);
        Date dateOld = new Date();
        String result = sdf.format(dateOld);
        return Integer.valueOf(result).intValue();
    }   
    public int getMonth(){
        SimpleDateFormat sdf = new SimpleDateFormat("MM", Locale.GERMAN);
        Date dateOld = new Date();
        String result = sdf.format(dateOld);
        return Integer.valueOf(result).intValue();
    } 
    public int getDay(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd", Locale.GERMAN);
        Date dateOld = new Date();
        String result = sdf.format(dateOld);
        return Integer.valueOf(result).intValue();
    }
    public int getNextDay(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd", Locale.GERMAN);
        Date dateOld = new Date();
        long date = dateOld.getTime();
        date = date+86400000;
        dateOld.setTime(date);
        String result = sdf.format(dateOld);
        return Integer.valueOf(result).intValue();
    }
    public String getKalenderwoche(long msek){
        SimpleDateFormat sdf = new SimpleDateFormat("ww", Locale.GERMAN);
        Date dateOld = new Date();
        long timeAdv = msek;
        dateOld.setTime(timeAdv);
        String result = sdf.format(dateOld);
        return result;
    }
    public int getWeekday(long msek){
        SimpleDateFormat sdf = new SimpleDateFormat("EE", Locale.GERMAN);
        Date dateOld = new Date();
        long timeAdv = msek;
        dateOld.setTime(timeAdv);
        String result = sdf.format(dateOld);

        return getDayNum(result);
    }
    
    private int getDayNum(String Day){
        int val = 0;
        
        String [] daysInMonths = {"Mo","Di","Mi","Do","Fr","Sa","So"};
        for (int i=0;i<daysInMonths.length;i++){
            if (daysInMonths[i].equals(Day)){
                val=(i+1);
                break;
            }
        }

        return val;
    }
    public String getWeekdayDELong(int num){
        String [] daysInMonths = {"Montag","Dienstag","Mittwoch","Donnerstag","Freitag","Samstag","Sonntag"};
        return daysInMonths[num];
    } 
    
    public String getWeekdayStr(long msek){
        SimpleDateFormat sdf = new SimpleDateFormat("EE" );
        Date dateOld = new Date();
        long timeAdv = msek;
        dateOld.setTime(timeAdv);
        String result = sdf.format(dateOld);
        return result;
    } 
    public int getDaysOfMonth(long msek){
        int [] daysInMonths = {31,28,31,30,31,30,31,31,30,31,30,31};
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        Date dateOld = new Date();
        long timeAdv = msek;
        dateOld.setTime(timeAdv);        
        String result = sdf.format(dateOld);
        return daysInMonths[Integer.valueOf(result).intValue()-1];
    }   
    public int getDaysOfMonth(int month){
        int [] daysInMonths = {31,28,31,30,31,30,31,31,30,31,30,31};
        return daysInMonths[month-1];
    }
    public int getDaysOfMonth(){
        int [] daysInMonths = {31,28,31,30,31,30,31,31,30,31,30,31};
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        Date dateOld = new Date();   
        String result = sdf.format(dateOld);        
        return daysInMonths[Integer.valueOf(result).intValue()-1];
    }    
    public String parsehours(long msek){
        SimpleDateFormat sdf = new SimpleDateFormat("HH" );
        Date dateOld = new Date();
        long timeAdv = msek;
        dateOld.setTime(timeAdv);
        String result = sdf.format(dateOld);
        return result;
    }
    public String parseminutes(long msek){
        SimpleDateFormat sdf = new SimpleDateFormat("mm" );
        Date dateOld = new Date();
        long timeAdv = msek;
        dateOld.setTime(timeAdv);
        String result = sdf.format(dateOld);
        return result;
    }
    public String parsedays(long msek){
        SimpleDateFormat sdf = new SimpleDateFormat("dd" );
        Date dateOld = new Date();
        long timeAdv = msek;
        dateOld.setTime(timeAdv);
        String result = sdf.format(dateOld);
        return result;
    }
    public String parsemonths(long msek){
        SimpleDateFormat sdf = new SimpleDateFormat("MM" );
        Date dateOld = new Date();
        long timeAdv = msek;
        dateOld.setTime(timeAdv);
        String result = sdf.format(dateOld);
        return result;
    }
    public String parseyears(long msek){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy" );
        Date dateOld = new Date();
        long timeAdv = msek;
        dateOld.setTime(timeAdv);
        String result = sdf.format(dateOld);
        return result;
    }    
    public String parsehoursduration(long start,long end){
    	long msek = end-start;
        SimpleDateFormat sdf = new SimpleDateFormat("HH" );
        Date dateOld = new Date();
        long timeAdv = msek;
        dateOld.setTime(timeAdv);
        String result = sdf.format(dateOld);
        return result;
    }    
    public String parseminutesduration(long start,long end){
    	long msek = end-start;
        SimpleDateFormat sdf = new SimpleDateFormat("mm" );
        Date dateOld = new Date();
        long timeAdv = msek;
        dateOld.setTime(timeAdv);
        String result = sdf.format(dateOld);
        return result;
    } 
    public int parseduration(long start,long end){
        long msek = end-start;
        long timeAdv = msek;
        int minutes = Math.round(timeAdv/60000);
        
        return minutes;
    }     
}
