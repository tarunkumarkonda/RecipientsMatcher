package com.mycompany.recipientsmatcher.service;

import java.time.OffsetDateTime;
import java.util.Calendar;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.mycompany.recipientsmatcher.model.Pickup;
import com.mycompany.recipientsmatcher.model.Recipient;

@Service
@PropertySource("classpath:/application.properties")
public class TimeCheckService {
    @Value("${recipients_time_zone_id}")
    private String mRecipientsTimeZoneId;
    
    public boolean openCheck(Pickup pickup, Recipient recipient)
    {
        int hour = getPickupAtHour(pickup);
        int dayOfWeek = getDayOfWeek(pickup);
        int operationHours = getOperationHours(dayOfWeek, recipient);
            
        return openCheck(hour, operationHours);
    }
    private boolean openCheck(int hour, int operationHours)
    {
         if(hour < 8) // Bit 0 starts from 8 AM so, 8 is used here.
             return false;
         int bitPosition = hour - 8; // To represent an hour in bit.  ex: 9AM - 8 = 1 (1 Bit)
         for(int i = 0; i < bitPosition; i++)
            operationHours = operationHours >> 1;
         return (operationHours % 2) == 1;
    }
    private int getOperationHours(int dayOfWeek, Recipient recipient)
    {
       if(dayOfWeek == Calendar.MONDAY)
            return recipient.getMonday();
        
        if(dayOfWeek == Calendar.TUESDAY)
            return recipient.getTuesday();
        
        if(dayOfWeek == Calendar.WEDNESDAY)
            return recipient.getWednesday();
        
        if(dayOfWeek == Calendar.THURSDAY)
            return recipient.getThursday();
        
        if(dayOfWeek == Calendar.FRIDAY)
            return recipient.getFriday();
        
        if(dayOfWeek == Calendar.SATURDAY)
            return recipient.getSaturday();
        
         return recipient.getSunday();
         
    }
    public int getPickupAtHour(Pickup pickup)
    {
               
        String pickupTimeZoneId = pickup.getTimeZoneId();
        String recipientTimeZoneId = mRecipientsTimeZoneId;
        
        OffsetDateTime odt = OffsetDateTime.parse(pickup.getPickupAt());
        Calendar calendar_pickup = Calendar.getInstance(TimeZone.getTimeZone(pickupTimeZoneId));
        calendar_pickup.set(odt.getYear(), odt.getMonthValue() - 1, odt.getDayOfMonth(), odt.getHour(), odt.getMinute(), odt.getSecond());
        
        Calendar calendar_recipient = Calendar.getInstance(TimeZone.getTimeZone(recipientTimeZoneId));
        calendar_recipient.setTimeInMillis(calendar_pickup.getTimeInMillis());
        
        
        int year = calendar_recipient.get(Calendar.YEAR);
        int month = calendar_recipient.get(Calendar.MONTH); // Jan = 0, dec = 11
        int dayOfMonth = calendar_recipient.get(Calendar.DAY_OF_MONTH);
        int hour = calendar_recipient.get(Calendar.HOUR); // 12 hour clock
        int hourOfDay = calendar_recipient.get(Calendar.HOUR_OF_DAY); // 24 hour clock
        int dayOfWeek = calendar_recipient.get(Calendar.DAY_OF_WEEK);
        int minute = calendar_recipient.get(Calendar.MINUTE);
        int second = calendar_recipient.get(Calendar.SECOND);
        int ampm = calendar_recipient.get(Calendar.AM_PM); //0 = AM , 1 = PM
        
        
        return hourOfDay;
    }
    public int getDayOfWeek(Pickup pickup)
    {
        String pickupTimeZoneId = pickup.getTimeZoneId();
        String recipientTimeZoneId = mRecipientsTimeZoneId;
        
        OffsetDateTime odt = OffsetDateTime.parse(pickup.getPickupAt());
        Calendar calendar_pickup = Calendar.getInstance(TimeZone.getTimeZone(pickupTimeZoneId));
        calendar_pickup.set(odt.getYear(), odt.getMonthValue() - 1, odt.getDayOfMonth(), odt.getHour(), odt.getMinute(), odt.getSecond());
        
        Calendar calendar_recipient = Calendar.getInstance(TimeZone.getTimeZone(recipientTimeZoneId));
        calendar_recipient.setTimeInMillis(calendar_pickup.getTimeInMillis());
        
        int dayOfWeek = calendar_recipient.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek;
    }
}

