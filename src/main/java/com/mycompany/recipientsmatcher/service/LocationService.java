package com.mycompany.recipientsmatcher.service;

import org.springframework.stereotype.Service;

import com.mycompany.recipientsmatcher.model.Pickup;
import com.mycompany.recipientsmatcher.model.Recipient;

@Service
public class LocationService {
   
    
    public double getDistance(Pickup pickup, Recipient recipient)
    {
        return distance(pickup.getLatitude(), pickup.getLongitude(), recipient.getLatitude(), recipient.getLongitude());
    }
    
    /*returns distance between two locations as mile*/
    private double distance(double lat1, double lon1, double lat2, double lon2) {
    // lat1 = 33.4484; lon1 = -112.074; lat2 = 30.2672; lon2 = -97.7431;
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		return (dist);
	}

	
	/*converts decimal degrees to radians*/
	
	private double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	/*converts radians to decimal degrees*/
        private double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
        }
}

