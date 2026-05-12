package com.smart_tech.smart_parking_system.util;

import com.smart_tech.smart_parking_system.enums.VehicleType;

public class ParkingBookingSlotUtil {

	public double calculateAmount(VehicleType type, long hours) {

	    double rate = 0;

	    switch (type) {

	        case BIKE:
	            rate = 20;
	            break;

	        case CAR:
	            rate = 50;
	            break;

	        case AUTO:
	            rate = 40;
	            break;
	    }

	    return rate * hours;
	}
}
