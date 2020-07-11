package com.cabInvoiceGenerator;

import com.cabInvoiceGenerator.Exceptions.InvoiceException;
import com.cabInvoiceGenerator.Services.InvoiceGenerator;
import com.cabInvoiceGenerator.models.InvoiceSummary;
import com.cabInvoiceGenerator.models.Ride;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.cabInvoiceGenerator.Services.InvoiceGenerator.RideType.NORMAL;
import static com.cabInvoiceGenerator.Services.InvoiceGenerator.RideType.PREMIUM;

public class InvoiceGeneratorTest {
    InvoiceGenerator invoiceGenerator;

    @Before
    public void setUp() {
        invoiceGenerator = new InvoiceGenerator();
    }

    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFare() {
        double distance = 2.0;
        int time = 5;
        InvoiceGenerator.RideType rideType = NORMAL;
        double fare = invoiceGenerator.calculateFare(distance, time, rideType);
        Assert.assertEquals(25, fare, 0.0);
    }

    @Test
    public void givenLessDistanceAndTime_ShouldReturnMinimumFare() {
        double distance = 0.1;
        int time = 1;
        InvoiceGenerator.RideType rideType = NORMAL;
        double fare = invoiceGenerator.calculateFare(distance, time, rideType);
        Assert.assertEquals(5, fare, 0.0);
    }

    @Test
    public void givenMultipleRides_ShouldReturnTotalFare() {
        Ride[] rides = {
                new Ride(2.0, 5, NORMAL),
                new Ride(0.1, 1, NORMAL),
                new Ride(10, 6, NORMAL)
        };
        InvoiceSummary summary = invoiceGenerator.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(3, 136.0);
        Assert.assertEquals(expectedInvoiceSummary, summary);
    }

    @Test
    public void givenUserIdAndRides_shouldReturnInvoiceSummary() throws InvoiceException {
        String userId = "firstUser";
        Ride[] rides = {new Ride(2.0, 5, NORMAL),
                new Ride(0.1, 1, NORMAL)};
        invoiceGenerator.addRides(userId, rides);
        InvoiceSummary summary = invoiceGenerator.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30.0);
        Assert.assertEquals(expectedInvoiceSummary, summary);
    }

    @Test
    public void givenCategories_WhenRideList_ShouldReturnInvoiceSummary() throws InvoiceException {
        String userId = "firstUser";
        Ride rides[] = {new Ride(2.0, 5, PREMIUM),
                new Ride(0.1, 1, PREMIUM)};
        invoiceGenerator.addRides(userId, rides);
        InvoiceSummary summary = invoiceGenerator.getInvoiceSummary(userId);
        InvoiceSummary expectedSumry = new InvoiceSummary(2, 60.0);
        Assert.assertEquals(summary, expectedSumry);
    }

    @Test
    public void givenUserId_WhenNotExists_throwsException() {
        try {
            String userId = "firstUser";
            String wrongId = "ANY";
            Ride rides[] = {new Ride(2.0, 5, PREMIUM),
                    new Ride(0.1, 1, PREMIUM)};
            invoiceGenerator.addRides(userId, rides);
            InvoiceSummary summary = invoiceGenerator.getInvoiceSummary(wrongId);
            InvoiceSummary expectedSumry = new InvoiceSummary(2, 60.0);
        } catch (InvoiceException e) {
            Assert.assertEquals(InvoiceException.ExceptionType.USER_NOT_EXIST, e.type);
        }
    }


    @Test
    public void givenUserId_WhenAlreadyExists_throwsException() {
        try {
            String userId = "firstUser";
            Ride rides[] = {new Ride(2.0, 5, PREMIUM),
                    new Ride(0.1, 1, PREMIUM)};
            invoiceGenerator.addRides(userId, rides);
            InvoiceSummary summary = invoiceGenerator.getInvoiceSummary(userId);
            invoiceGenerator.addRides(userId, rides);
            InvoiceSummary expectedSumry = new InvoiceSummary(2, 60.0);
        } catch (InvoiceException e) {
            Assert.assertEquals(InvoiceException.ExceptionType.USER_ALREADY_EXISTS, e.type);
        }
    }
}