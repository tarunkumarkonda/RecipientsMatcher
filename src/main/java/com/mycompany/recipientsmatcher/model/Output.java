package com.mycompany.recipientsmatcher.model;

@SuppressWarnings("rawtypes")
public class Output implements Comparable{
    
   
    String pickupFirstName;
    
    String pickupLastName;
    
    String recipientFirstName;
    
    String recipientLastName;
    
    double distance;
    
    String pickupAt;
    
    int categories;
    
    int restrictions;

    public Output(String pickupFirstName, String pickupLastName, String recipientFirstName, String recipientLastName, double distance, String pickupAt, int categories, int restrictions)
    {
        this.pickupFirstName = pickupFirstName;
        this.pickupLastName = pickupLastName;
        this.recipientFirstName = recipientFirstName;
        this.recipientLastName = recipientLastName;
        this.distance = distance;
        this.pickupAt = pickupAt;
        this.categories = categories;
        this.restrictions = restrictions;
    }

    public String getPickupAt() {
        return pickupAt;
    }

    public void setPickupAt(String pickupAt) {
        this.pickupAt = pickupAt;
    }

    @Override
    public int compareTo(Object theOther) {
        double distance_other = ((Output)theOther).getDistance();
        if(distance_other - this.getDistance() > 0)
            return -1;
        else if (distance_other - this.getDistance() < 0)
            return 1;
        return 0;
    }

    public String getPickupFirstName() {
        return pickupFirstName;
    }

    public void setPickupFirstName(String pickupFirstName) {
        this.pickupFirstName = pickupFirstName;
    }

    public String getPickupLastName() {
        return pickupLastName;
    }

    public void setPickupLastName(String pickupLastName) {
        this.pickupLastName = pickupLastName;
    }

    public String getRecipientFirstName() {
        return recipientFirstName;
    }

    public void setRecipientFirstName(String recipientFirstName) {
        this.recipientFirstName = recipientFirstName;
    }

    public String getRecipientLastName() {
        return recipientLastName;
    }

    public void setRecipientLastName(String recipientLastName) {
        this.recipientLastName = recipientLastName;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getCategories() {
        return categories;
    }

    public void setCategories(int categories) {
        this.categories = categories;
    }

    public int getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(int restrictions) {
        this.restrictions = restrictions;
    }

 
}

