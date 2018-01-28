package com.mycompany.recipientsmatcher.modeltest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mycompany.recipientsmatcher.model.Output;
import com.mycompany.recipientsmatcher.model.Pickup;
import com.mycompany.recipientsmatcher.model.Recipient;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ModelTest {
    
    
    @Test
    public void pickupModelTest()
    {
        Pickup pickup = new Pickup();
        pickup.setFirstName("Carlos");
        pickup.setLastName("Marsh");
        
        assertTrue(pickup.getFirstName() == "Carlos");
        assertTrue(pickup.getLastName() == "Marsh");
    }
    
    @Test
    public void recipientModelTest()
    {
        Recipient recipient = new Recipient();
        recipient.setSunday(1);
        recipient.setMonday(2);
        recipient.setTuesday(3);
        recipient.setWednesday(4);
        recipient.setThursday(5);
        recipient.setFriday(6);
        recipient.setSaturday(7);
        
        assertTrue(recipient.getSunday() == 1);
        assertTrue(recipient.getMonday() == 2);
        assertTrue(recipient.getTuesday() == 3);
        assertTrue(recipient.getWednesday() == 4);
        assertTrue(recipient.getThursday() == 5);
        assertTrue(recipient.getFriday() == 6);
        assertTrue(recipient.getSaturday() == 7);
    }
    
    @Test
    public void outputModelTest()
    {
        Output output1 = new Output("Tanya", "Matthews", "Palmer", "Morales", 3.2, "2016-11-27T19:00:00-08:00", 1, 1);
        Output output2 = new Output("Tanya", "Matthews", "Myra", "Donohoe", 3.3, "2016-11-20T14:00:00-08:00", 1, 1);
        assertTrue(output1.compareTo(output2) == -1);
        assertTrue(output2.compareTo(output1) == 1);
        assertTrue(output1.compareTo(output1) == 0);
    }
    
}

