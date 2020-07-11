package com.cabInvoiceGenerator.Repositories;

import com.cabInvoiceGenerator.Exceptions.*;
import com.cabInvoiceGenerator.models.Ride;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RideRepository {
    Map<String, ArrayList<Ride>> userRides;

    public RideRepository() {
        this.userRides = new HashMap<>();
    }

    public void addRides(String userId, Ride[] rides) throws InvoiceException {
        ArrayList<Ride> rideList = this.userRides.get(userId);
        if (!userRides.containsKey(userId))
            this.userRides.put(userId, new ArrayList<>(Arrays.asList(rides)));
        else
            throw new InvoiceException("User Already Exists !", InvoiceException.ExceptionType.USER_PROBLEM);
    }

    public Ride[] getRides(String userId) throws InvoiceException {
        if (userRides.containsKey(userId))
            return this.userRides.get(userId).toArray(new Ride[0]);
        throw new InvoiceException("User not exist !",InvoiceException.ExceptionType.USER_PROBLEM);
    }
}
