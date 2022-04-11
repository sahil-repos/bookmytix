package bookings;

import model.RegisteredUser;
import model.Show;
import model.Ticket;

public class BookingThread extends Thread {
    private Show show;
    public RegisteredUser user;
    private int numberOfSeats;
    private Ticket ticket;

    public BookingThread(Show show, RegisteredUser user, int numberOfSeats) {
        this.show = show;
        this.user = user;
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public void run() {
        this.ticket = show.bookTicket(user,numberOfSeats);
        
    }

    public Ticket getTicket() {
        return ticket;
    }
    public int getNumberOfSeats() {
    	return numberOfSeats;
    }
}