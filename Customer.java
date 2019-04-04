package drivingInterface;

import java.util.*;

public class Customer extends Person {

    Date firstTransaction;
    Date LastTransaction;
    double individualTip;
    int deliveryCount;
    Address CustAddress;
    private Stack<Double> tipHistory;

    public Customer(String name, String Address, String City, String State, int Zip) {
        super(name);
        firstTransaction = new Date();
        LastTransaction = new Date();
        individualTip = 0;
        deliveryCount = 0;
        CustAddress = new Address(Address, City, State, Zip);

    }

    public Date getFirstTransaction() {
        return firstTransaction;
    }

    public void setFirstTransaction(Date firstTransaction) {
        this.firstTransaction = firstTransaction;
    }

    public Date getLastTransaction() {
        return LastTransaction;
    }

    public void setLastTransaction(Date LastTransaction) {
        this.LastTransaction = LastTransaction;
    }

    public double getIndividualTip() {
        return individualTip;
    }

    public void setIndividualTip(double individualTip) {
        this.individualTip = individualTip;
    }

    public int getDeliveryCount() {
        return deliveryCount;
    }

    public void setDeliveryCount(int deliveryCount) {
        this.deliveryCount = deliveryCount;
    }

    public Address getCustAddress() {
        return CustAddress;
    }

    public void setCustAddress(Address CustAddress) {
        this.CustAddress = CustAddress;
    }

}


