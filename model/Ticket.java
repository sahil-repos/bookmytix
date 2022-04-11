package model;

import java.util.Date;

public class Ticket {
    private static int idCounter=0;
    private int id;
    private String person;
    private Date bookingTime;
    private int numberOfSeats;
    private Show bookedShow;

    public Ticket() {
        idCounter += 1;
        this.id = idCounter;
    }


    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(Date bookingTime) {
        this.bookingTime = bookingTime;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public String toString() {
        return "Ticket" +
        		"\n##########################################################\n"+
                " For :" + person + "\n" +
                " booking Time : " + bookingTime + "\n" +
                " Seats  : " + numberOfSeats + "\n" +
                " Show :\n" + bookedShow +
        		"\n##########################################################\n";
    }

    public Show getBookedShow() {
        return bookedShow;
    }

    public void setBookedShow(Show bookedShow) {
        this.bookedShow = bookedShow;
    }
}
