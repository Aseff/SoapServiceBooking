package impl;

import seatreservation.Seat;
import seatreservation.SeatStatus;

public class ManageStatus {
	private Seat seat;
	private SeatStatus seatStatus;
	
	
	public ManageStatus(Seat seat, SeatStatus seatStatus) {
		super();
		this.seat = seat;
		this.seatStatus = seatStatus;
	}
	
	public Seat getSeat() {
		return seat;
	}
	public void setSeat(Seat seat) {
		this.seat = seat;
	}
	public SeatStatus getSeatStatus() {
		return seatStatus;
	}
	public void setSeatStatus(SeatStatus seatStatus) {
		this.seatStatus = seatStatus;
	}
	
	

}
