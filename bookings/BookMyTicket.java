package bookings;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.HashMap;

import enums.Genre;
import enums.Language;
import model.GuestUser;
import model.Movie;
import model.RegisteredUser;
import model.Show;
import model.Theater;
import model.Ticket;

public class BookMyTicket {
	ArrayList<Theater> theaters;
	static HashMap<String, ArrayList<Show>> movieMap;

	private void generateMovieMap() {
		for (Theater theater : theaters) {
			ArrayList<Show> showArray = theater.getShows();
			for (Show show : showArray) {
				if (show != null) {
					if (movieMap.containsKey(show.getMovie().getName().toLowerCase())) {
						movieMap.get(show.getMovie().getName()).add(show);
					} else {
						ArrayList<Show> movieShowList = new ArrayList<>();
						movieShowList.add(show);
						movieMap.put(show.getMovie().getName().toLowerCase(), movieShowList);
					}
				}
			}
		}
	}

	public BookMyTicket(ArrayList<Theater> theaters) {
		this.theaters = theaters;
		this.movieMap = new HashMap<>();
		generateMovieMap();
		System.out.println(movieMap);
	}

	public static ArrayList<Show> searchShows(String movieName) {
		if (movieMap.containsKey(movieName.toLowerCase())) {
			return movieMap.get(movieName.toLowerCase());
		} else
			return null;
	}

	public static void main(String[] args) {


		//Adding Movies
		Movie actionMovie = new Movie("Action Movie", Language.TELUGU, Genre.ACTION);

		Movie comedyMovie = new Movie("Comedy movie", Language.ENGLISH, Genre.COMEDY);

		Movie romanticMovie = new Movie("Romantic Movie", Language.HINDI, Genre.ROMANCE);


		// Adding Theaters
		Theater diamondJublee = new Theater("Diamond Jublee", "Cityville", 50);

		Theater silverJublee = new Theater("Silver Jublee", "Townsville", 50);

		// Initializing  shows
		Show show1 = null;
		Show show2 = null;
		Show show3 = null;
		
		//Adding Dummy users
		RegisteredUser rico = new RegisteredUser("Rico");

		GuestUser mehmaan = new GuestUser("Mehmaan");

		RegisteredUser alice = new RegisteredUser("Alice");

		RegisteredUser bob = new RegisteredUser("Bob");

		SimpleDateFormat dateformatter = new SimpleDateFormat("EEEE, MMM dd, yyyy HH:mm:ss a");

		try {
			// Adding Shows
			String dateInString = "Friday, May 6, 2022 07:00:00 PM";
			Date date = dateformatter.parse(dateInString);
			show1 = new Show(date, actionMovie, diamondJublee);

			dateInString = "Friday, May 6, 2022 04:00:00 PM";
			date = dateformatter.parse(dateInString);
			show2 = new Show(date, comedyMovie, diamondJublee);

			dateInString = "Friday, Apr 7, 2022 09:00:00 PM";
			date = dateformatter.parse(dateInString);
			show3 = new Show(date, romanticMovie, silverJublee);


		} catch (ParseException e) {
			e.printStackTrace();
		}


		ArrayList<Theater> theatersList = new ArrayList<>();
		
		theatersList.add(diamondJublee);
		theatersList.add(silverJublee);
		
		BookMyTicket bookMyTicket = new BookMyTicket(theatersList);

		ArrayList<Show> searchedShow = BookMyTicket.searchShows("Comedy Movie");
		Show bookingShow = searchedShow.get(0);

		BookingThread t1 = new BookingThread(bookingShow, alice, 15);
		BookingThread t2 = new BookingThread(bookingShow, bob, 10);

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Ticket aliceTicket = t1.getTicket();
		if(aliceTicket!=null) {
			System.out.println("\n Booking Confirmed  \n");
			System.out.println(aliceTicket);
		}else {
			System.out.println("\n----------\n" +"Booking Failed for  " +t1.user.getName() + 
								" for "+  t1.getNumberOfSeats()+"tickets \n" );

		}
		
		Ticket bobTicket = t2.getTicket();
		if(bobTicket!=null) {
			System.out.println("\n Booking Confirmed  \n");
			System.out.println(bobTicket);
		}else {
			System.out.println("\n----------\n" +"Booking Failed for  " +t2.user.getName() + 
					" for "+  t2.getNumberOfSeats()+"tickets \n" );

		}

		

		BookingThread t3 = new BookingThread(bookingShow, alice, 15);
		BookingThread t4 = new BookingThread(bookingShow, bob, 100);

		t3.start();
		t4.start();

		try {

			t4.join();
			t3.join();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Ticket aliceNewTicket = t3.getTicket();
		if(aliceNewTicket!=null) {
			System.out.println("\n Booking Confirmed  \n");
			System.out.println(aliceNewTicket);
		}
		else {
			System.out.println("\n----------\n" +"Booking Failed for  " +t3.user.getName() + 
					" for "+  t3.getNumberOfSeats()+"tickets \n" );
			
		}

		Ticket bobNewTicket = t4.getTicket();
		
		if(bobNewTicket!=null) {
			System.out.println("\n Booking Confirmed  \n");
			System.out.println(bobNewTicket);
		}else {
			System.out.println("\n----------\n" +"Booking Failed for  " +t4.user.getName() + 
					" for "+  t4.getNumberOfSeats()+"tickets \n" );

		}

		

	}
}