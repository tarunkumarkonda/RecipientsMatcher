package com.mycompany.recipientsmatcher.servicetest;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mycompany.recipientsmatcher.model.Pickup;
import com.mycompany.recipientsmatcher.model.Recipient;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {
    @Autowired
    com.mycompany.recipientsmatcher.service.AcceptCheckService acceptCheckService;
    
    @Autowired
    com.mycompany.recipientsmatcher.service.LocationService locationService;
    
    @Autowired
    com.mycompany.recipientsmatcher.service.TimeCheckService timeCheckService;
    
    @Autowired
    com.mycompany.recipientsmatcher.service.RecipientsService recipientsService;
    
    @Test
    public void acceptCheckServiceTest()
    {
        int categories = 5; //0b 101 
        int restrictions = 13; //0b 1101
        
        Pickup pickup = new Pickup();
        Recipient recipient = new Recipient();
        pickup.setCategories(categories);
        recipient.setRestrictions(restrictions);
        
        assertTrue(acceptCheckService.accept(pickup, recipient) == true);
        
        categories = 7; //0b 111
        restrictions = 13; //0b 1101
        pickup.setCategories(categories);
        recipient.setRestrictions(restrictions);
        
        assertTrue(acceptCheckService.accept(pickup, recipient) == false);
    }
    
    @Test
    public void locationServiceTest()
    {
        Pickup pickup = new Pickup();
        Recipient recipient = new Recipient();
        
        //Phoenix lat 33.4484 lang -112.074
        //Austin lat 30.2672 lang -97.7431
        //Boston lat 42.3584 lang -71.0598

        //phoenix-austin 868 miles
        //Boston-Phoenix 2295 miles


        //set pickup to phoenix, 
        pickup.setLatitude(33.4484);
        pickup.setLongitude(-112.074);
        
        //set recipient to austin
        recipient.setLatitude(30.2672);
        recipient.setLongitude(-97.7431);
        
        double distance = locationService.getDistance(pickup, recipient);
        System.out.println("phoenix-austin" + distance);
        assertTrue(Math.abs(distance - 868) < 1);
        
        //set recipient to boston
        recipient.setLatitude(42.3584);
        recipient.setLongitude(-71.0598);
        
        distance = locationService.getDistance(pickup, recipient);
        System.out.println("phoenix-boston" + distance);
        assertTrue(Math.abs(distance - 2295) < 1);
    }
    
    @Test
    public void timeCheckServiceTest()
    {
        Pickup pickup = new Pickup();
        Recipient recipient = new Recipient();
        
        pickup.setTimeZoneId("America/Los_Angeles");
        pickup.setPickupAt("2018-01-27T08:00:00-08:00"); //Saturday
        recipient.setSaturday(5); //0b 101 open between 8:00-9:00
        
        System.out.println(timeCheckService.getPickupAtHour(pickup));
        System.out.println(timeCheckService.getDayOfWeek(pickup));
        System.out.println(timeCheckService.openCheck(pickup, recipient));
        
        assertTrue(timeCheckService.getPickupAtHour(pickup) == 8);
        assertTrue(timeCheckService.getDayOfWeek(pickup) == Calendar.SATURDAY);
        assertTrue(timeCheckService.openCheck(pickup, recipient) == true);
        
        pickup.setPickupAt("2018-01-28T09:00:00-08:00"); //Sunday
        recipient.setSaturday(6); //0b 110 open between 9:00-10:00
        recipient.setSunday(5); //0b 101 don't open between 9:00-10:00
        
        assertTrue(timeCheckService.openCheck(pickup, recipient) == false);
    }
    
    @Test
    public void recipientsServiceTest() throws IOException
    {
        recipientsService.init();
        int count_pickup = recipientsService.getPickupCount();
        int count_recipient = recipientsService.getRecipientCount();
        System.out.println("pickup count = " + count_pickup);
        System.out.println("Recipient count = " + count_recipient);
        assertTrue(count_pickup == 190);
        assertTrue(count_recipient == 149);
        assertTrue(recipientsService.proc() == true);
    }
}
