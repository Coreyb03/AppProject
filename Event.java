import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.spi.CalendarNameProvider;
import java.util.TimeZone;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.EventQueue;
import java.util.ArrayList;



public class Event {

    private int startDay;
    private int firstInstance;
    private int interval; 

//Array startdate holds day of year out of 365 and year in AD
//interval counts how often an event happens, for yearly event put 364
//first instance is the first time it happend, if its a repetative event that happened before the start date
//put it before the start date 
    public Event(int startDate, int interval, int firstInstance) {
        this.startDay = startDate;
        this.firstInstance = firstInstance;
        this.interval = interval;
    }

    public Event(int interval) {
        this.startDay = 0;
        this.firstInstance = 0;
        this.interval = interval;
    }

    public Event() {
        this.startDay = 0;
        this.firstInstance = 0;
        this.interval = 0;
    }

    public int getStartDay() {
        return this.startDay;
    }


    public int getFirstInstance() {
        return this.firstInstance;
    }
    
    public int getInterval() {
        return this.interval;
    }

    
    public void setStartDay(int startDate) {
        this.startDay = startDate;
    }

    public void setFirstInstance(int firstInstance) {
        this.firstInstance = firstInstance;
    }
    
    public void setInterval(int newInterval) {
        this.interval = newInterval;
    }

    public int eventCount() {
        // int day = theCal.get(Calendar.DAY_OF_YEAR);
        int day = 90;
        int count = 0;
        int reducted = 0;
        
        if (this.firstInstance < this.startDay) {
            reducted = (this.startDay - this.firstInstance) / this.interval;
        }
        count = ((day - this.firstInstance) / this.interval) - reducted;
        return count;
    }
}