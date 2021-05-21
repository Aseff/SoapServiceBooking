package cinema;

import javax.xml.ws.BindingProvider;

import seatreservation.CinemaService;
import seatreservation.ICinema;
import seatreservation.ICinemaBuyCinemaException;
import seatreservation.ICinemaLockCinemaException;
import seatreservation.ICinemaReserveCinemaException;
import seatreservation.Seat;

public class Program {

	public static void main(String[] args) {
		if(args.length!=4) {
			System.out.println("Bad input");
			return;
		}
		
		String url;
		String row;
		String column;
		String task;
		String result;
		
	try {
		url=args[0];
		row=args[1];
		column=args[2];
		task=args[3];
	}
		
	catch (Exception e) {
		System.out.println("Inappropriate input");
		return;
	}
		
		
		
		CinemaService cinemaService=new CinemaService();
		
		ICinema cinema=cinemaService.getICinemaHttpSoap11Port();
		
		BindingProvider bp = (BindingProvider)cinema;
		// Set the URL of the service:
		bp.getRequestContext().put(
		BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
		url);
		// Call the service:
		Seat seat=new Seat();
		switch(task) {
		 case "Lock":
			seat.setRow(row);
			seat.setColumn(column);
			try {
				result=cinema.lock(seat, 1);
				System.out.println(result);
			} catch (ICinemaLockCinemaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		break;
			
		  case "Reserve":
			seat.setRow(row);
			seat.setColumn(column);
			try {
				result=cinema.lock(seat, 1);
				try {
					cinema.reserve(result);
				} catch (ICinemaReserveCinemaException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Reserved");
			} catch (ICinemaLockCinemaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		

	
		  case "Buy":
			seat.setRow(row);
			seat.setColumn(column);
			try {
				result=cinema.lock(seat, 1);
				try {
					cinema.buy(result);
				} catch (ICinemaBuyCinemaException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Sold");
			} catch (ICinemaLockCinemaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		
		}
	}
}

