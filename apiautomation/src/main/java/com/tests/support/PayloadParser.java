package com.tests.support;

public class PayloadParser
{
    private String firstname;
    private String lastname;
    private boolean depositpaid;
    private int roomid;
    private Bookingdates bookingdates;
    public Bookingdates getBookingdates()
    {
        return bookingdates;
    }
    public void setBookingdates(Bookingdates bookingdates)
    {
        this.bookingdates = bookingdates;
    }
    public int getRoomid()
    {
        return roomid;
    }
    public void setRoomid(int roomid)
    {
        this.roomid = roomid;
    }
    public String getFirstname()
    {
        return firstname;
    }
    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }
    public String getLastname()
    {
        return lastname;
    }
    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }
    public boolean isDepositpaid()
    {
        return depositpaid;
    }
    public void setDepositpaid(boolean depositpaid)
    {
        this.depositpaid = depositpaid;
    }
    
}
