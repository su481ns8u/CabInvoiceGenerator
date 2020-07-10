package com.cabInvoiceGenerator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
        double fare = invoiceGenerator.calculateFare(distance, time);
        Assert.assertEquals(25, fare,0.0);
    }

    @Test
    public void givenLessDistanceAndTime_ShouldReturnMinimumFare() {
        double distance = 0.1;
        int time = 1;
        double fare = invoiceGenerator.calculateFare(distance, time);
        Assert.assertEquals(5, fare,0.0);
    }
    @Test
    public void givenMultipleRides_ShouldReturnTotalFare() {
        Ride[] rides = {
                new Ride(2.0, 5),
                new Ride(0.1,1),
                new Ride(10,6)  };
        InvoiceSummary summary = invoiceGenerator.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(3, 136.0);
        Assert.assertEquals(expectedInvoiceSummary,summary);
    }

    @Test
    public void givenUserIdAndRides_shouldReturnInvoiceSummary() {
        String userId = "firstUser";
        Ride[] rides = { new Ride(2.0, 5),
                new Ride(0.1, 1)};
        invoiceGenerator.addRides(userId, rides);
        InvoiceSummary summary = invoiceGenerator.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30.0);
        Assert.assertEquals(expectedInvoiceSummary, summary);
    }

    @Test
    public void givenCategories_WhenRideList_ShouldReturnInvoiceSummary() {
        String userId = "firstUser";
        Ride rides[] = {new Ride(2.0, 5),new Ride(0.1, 1)};
        Ride rides1[] = {new Ride(2.0, 5),new Ride(0.1, 1)};
        invoiceGenerator.addRides(userId,rides);
        InvoiceSummary summary = invoiceGenerator.getInvoiceSummary(userId,getInvoiceSummary.RideCategories.PREMIUM_RIDE);
        InvoiceSummary expectedSumry = new InvoiceSummary(2,60.0);
        Assert.assertEquals(summary,expectedSumry);
    }

}
