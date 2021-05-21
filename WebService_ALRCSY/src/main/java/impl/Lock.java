package impl;

import seatreservation.Seat;

public class Lock {
private Seat seat;
private int count;
private String lockId;



public Lock(Seat seat,int count, String lockId) {
	super();
	this.count = count;
	this.seat = seat;
	this.lockId = lockId;
}


public int getCount() {
	return count;
}
public void setCount(int count) {
	this.count = count;
}
public Seat getSeat() {
	return seat;
}
public void setSeat(Seat seat) {
	this.seat = seat;
}
public String getLockId() {
	return lockId;
}
public void setLockId(String lockId) {
	this.lockId = lockId;
}



}
