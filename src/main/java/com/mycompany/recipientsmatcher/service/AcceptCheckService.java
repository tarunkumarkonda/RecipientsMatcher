package com.mycompany.recipientsmatcher.service;

import org.springframework.stereotype.Service;

import com.mycompany.recipientsmatcher.model.Pickup;
import com.mycompany.recipientsmatcher.model.Recipient;


@Service
public class AcceptCheckService {
    public boolean accept(Pickup pickup, Recipient recipient)
    {
        return accept(pickup.getCategories(), recipient.getRestrictions());
    }
    private boolean accept(int category, int restriction)
    {
        if(category > restriction)
            return false;
        while(category > 0)
        {
        	/*In order to convert an integer to hexadecimal, we need to perform LCM and get 
        	 the remainders in each step give us the binary digits of hexadecimal. */
        	
            int binary_digit_category = category % 2;
            int binary_digit_restriction = restriction % 2;
            
          // checking whether the pickup is having the food which recipient can accept 
            if(binary_digit_category > binary_digit_restriction)
                return false;
            category = category>>1;
            restriction = restriction>>1;
        }
      return true;
    }
  
            
}