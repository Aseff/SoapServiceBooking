
package impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import javax.jws.WebService;

import seatreservation.ArrayOfSeat;
import seatreservation.BuyResponse;
import seatreservation.CinemaException;
import seatreservation.GetAllSeatsResponse;
import seatreservation.GetSeatStatusResponse;
import seatreservation.ICinema;
import seatreservation.ICinemaBuyCinemaException;
import seatreservation.ICinemaGetAllSeatsCinemaException;
import seatreservation.ICinemaGetSeatStatusCinemaException;
import seatreservation.ICinemaInitCinemaException;
import seatreservation.ICinemaLockCinemaException;
import seatreservation.ICinemaReserveCinemaException;
import seatreservation.ICinemaUnlockCinemaException;
import seatreservation.Init;
import seatreservation.InitResponse;
import seatreservation.LockResponse;
import seatreservation.ReserveResponse;
import seatreservation.Seat;
import seatreservation.SeatStatus;
import seatreservation.UnlockResponse;

@WebService(name = "CinemaService", portName = "ICinema_HttpSoap11_Port", targetNamespace="http://www.iit.bme.hu/soi/hw/SeatReservation", endpointInterface = "seatreservation.ICinema", wsdlLocation = "WEB-INF/wsdl/SeatReservation.wsdl")

public class Cinema implements ICinema {
	private ArrayOfSeat room;
	private Init init;
	private int increment = 0;
	List<Lock> listoflockedseats = new ArrayList();
	Map<Integer,ManageStatus> map = new TreeMap<Integer,ManageStatus>();

	@Override
	public void init(int rows, int columns) throws ICinemaInitCinemaException {
		if (rows >=1 && rows <= 26 && columns >= 1 && columns <= 100) {
		
			room = new ArrayOfSeat();
			init = new Init();
			init.setRows(rows);
			init.setColumns(columns);

			char rowCount = 64;
			int columnCount = 1;
			
			List<Seat> listOfSeat = new ArrayList();
			for (int i = 1; i <= init.getRows(); i++) {
				for (int j = 0; j < init.getColumns(); j++) {
					Seat seat=new Seat();	
					String r=String.valueOf((char)(i+rowCount));
					String c=String.valueOf(j+columnCount);
									
					seat.setRow(r);
					seat.setColumn(c);
					
					listOfSeat.add(seat);
					
					map.put(increment, new ManageStatus(seat,SeatStatus.FREE));
 				    increment++;
				}
			}
			room.getSeat().addAll(listOfSeat);	
			increment=0;
			listoflockedseats.clear();
			
		}
		else {
			throw new ICinemaInitCinemaException("Number of rows is outside of the bound", new CinemaException());

		}

	}

	@Override
	public ArrayOfSeat getAllSeats() throws ICinemaGetAllSeatsCinemaException {
		if(init.getRows()>=1) {
			GetAllSeatsResponse allSeatsResponse = new GetAllSeatsResponse();
			allSeatsResponse.setGetAllSeatsResult(room);
			return allSeatsResponse.getGetAllSeatsResult();
		}
		else {
			throw new ICinemaGetAllSeatsCinemaException("Number of rows or coulm is out of bound",new CinemaException());
		}
	}

	@Override
	public SeatStatus getSeatStatus(Seat seat) throws ICinemaGetSeatStatusCinemaException {
		if (room.getSeat().stream()
				.anyMatch(s -> (s.getColumn().equals(seat.getColumn()) && s.getRow().equals(seat.getRow())))) {
				
			List<ManageStatus> list = new ArrayList<ManageStatus>(map.values());
			
			ManageStatus instanceOfManageStatus = list.stream()
					.filter(res -> (res.getSeat().getColumn().equals(seat.getColumn())
							&& res.getSeat().getRow().equals(seat.getRow())))
					.findAny().get();

			GetSeatStatusResponse getSeatStatusResponse = new GetSeatStatusResponse();
			getSeatStatusResponse.setGetSeatStatusResult(instanceOfManageStatus.getSeatStatus());
			return getSeatStatusResponse.getGetSeatStatusResult();
		} else {
			throw new ICinemaGetSeatStatusCinemaException("Bad seat number", new CinemaException());
		}

	}

	@Override
	public String lock(Seat seat, int count) throws ICinemaLockCinemaException {
		String str = "";
		int chosenSeat = Integer.parseInt(seat.getColumn());
		if ((chosenSeat + count-1) > init.getColumns()) {
			throw new ICinemaLockCinemaException("Not enough seats", new CinemaException());
		} else if (room.getSeat().stream()
				.anyMatch(s -> (s.getColumn().equals(seat.getColumn()) && s.getRow().equals(seat.getRow())))) {
			List<ManageStatus> listOfSeats = new ArrayList<ManageStatus>(map.values());
			ManageStatus instanceOfManageStatus = listOfSeats.stream()
					.filter(result -> (result.getSeat().getColumn().equals(seat.getColumn())
							&& result.getSeat().getRow().equals(seat.getRow())))
					.findAny().get();
			if (!instanceOfManageStatus.getSeatStatus().equals(SeatStatus.FREE)) {
				throw new ICinemaLockCinemaException("Seat is not free", new CinemaException());
			}
			for(int i=0;i<count;i++) {
				int n=chosenSeat+i;
				char c=seat.getRow().charAt(0);
				ManageStatus m=map.get((c-65)*init.getColumns()+n-1);
				if(m.getSeatStatus().equals(SeatStatus.LOCKED)){
					throw new ICinemaLockCinemaException("Seat is not free", new CinemaException());

				}

				
			}
			
			
			

			if(listoflockedseats.isEmpty()) {
				str="lock0";
			}
			else {
				
			str=listoflockedseats.get(listoflockedseats.size()-1).getLockId();
	        String	numberOnly= str.replaceAll("[^0-9]", "");

			if(str.endsWith("0")) {
				str="lock1";
			}
			else {
				
				int foo = Integer.parseInt(numberOnly);
			    foo++;
			    str="lock"+foo;
			    
			}

			}
			
			for(int i=0;i<count;i++) {
				int columnNumber=chosenSeat+i;
				
				Seat s=new Seat();
				s.setColumn(Integer.toString(columnNumber));
				s.setRow(instanceOfManageStatus.getSeat().getRow());
				char c=s.getRow().charAt(0);
				map.put((c-65)*init.getColumns()+columnNumber-1, new ManageStatus(s,SeatStatus.LOCKED));
				listOfSeats.get(columnNumber-1).getSeatStatus().toString();
				listoflockedseats.add(new Lock(s,count,str));

			 }	
			
			}
			
			    LockResponse lockResponse = new LockResponse();
			    lockResponse.setLockResult(str);
			    return lockResponse.getLockResult();


	}

	@Override
	public void unlock(String lockId) throws ICinemaUnlockCinemaException {

		if (!listoflockedseats.stream().anyMatch(loc -> loc.getLockId().equalsIgnoreCase(lockId))) {
			throw new ICinemaUnlockCinemaException("Lock is invalid", new CinemaException());
        }
		else {
			Lock lockeditem = listoflockedseats.stream().filter(loc -> loc.getLockId().equals(lockId)).findAny().get();
			
	
			List<ManageStatus> list = new ArrayList<ManageStatus>(map.values());

			for (int i = 0; i < lockeditem.getCount(); i++) {
				int numberOfColumn = Integer.parseInt(lockeditem.getSeat().getColumn()) + i;
				ManageStatus instanceOfManageStatus = list.stream()
					.filter(res -> (res.getSeat().getRow().equals(lockeditem.getSeat().getRow())
								&& res.getSeat().getColumn().equals(Integer.toString(numberOfColumn))))
					.findAny().get();
				if(instanceOfManageStatus.getSeatStatus().equals(SeatStatus.RESERVED)){
					throw new ICinemaUnlockCinemaException("Unlock does not release reservations", new CinemaException());
				
				}
				Seat s=new Seat();
				s.setColumn(Integer.toString(numberOfColumn));
				s.setRow(lockeditem.getSeat().getRow());
				char c=s.getRow().charAt(0);
				map.put((c-65)*init.getColumns()+numberOfColumn-1,new ManageStatus(s, SeatStatus.FREE));
				list.get(numberOfColumn-1).getSeatStatus().toString();
			}	
				
				
			
			
				
			}
		
		

	}

	@Override
	public void reserve(String lockId) throws ICinemaReserveCinemaException {
		if (!listoflockedseats.stream().anyMatch(loc -> loc.getLockId().equals(lockId))) {
			throw new ICinemaReserveCinemaException("Lock is invalid", new CinemaException());

		} else {
			Lock lockeditem = listoflockedseats.stream().filter(loc -> loc.getLockId().equals(lockId)).findAny().get();
			
			List<ManageStatus> list = new ArrayList<ManageStatus>(map.values());
			for (int i = 0; i < lockeditem.getCount(); i++) {
				int numberOfColumn = Integer.parseInt(lockeditem.getSeat().getColumn()) + i;
				ManageStatus instanceOfManageStatus = list.stream()
						.filter(res -> (res.getSeat().getRow().equals(lockeditem.getSeat().getRow())
								&& res.getSeat().getColumn().equals(Integer.toString(numberOfColumn))))
						.findAny().get();

				if (!instanceOfManageStatus.getSeatStatus().equals(SeatStatus.LOCKED)) {
					throw new ICinemaReserveCinemaException("Seat is not locked", new CinemaException());

				}

			}
			for(int i=0;i<lockeditem.getCount();i++) {
				int numberOfColumn = Integer.parseInt(lockeditem.getSeat().getColumn()) + i;
				Seat seat=new Seat();
				seat.setColumn(Integer.toString(numberOfColumn));
				seat.setRow(lockeditem.getSeat().getRow());
				char c=seat.getRow().charAt(0);
				map.put((c-65)*init.getColumns()+numberOfColumn-1,new ManageStatus(seat, SeatStatus.RESERVED));
				list.get(numberOfColumn-1).getSeatStatus().toString();
			
				
			}
		
			
		}

	}

	@Override
	public void buy(String lockId) throws ICinemaBuyCinemaException {
		if (!listoflockedseats.stream().anyMatch(loc -> loc.getLockId().equals(lockId))) {
			throw new ICinemaBuyCinemaException("Lock is invalid", new CinemaException());

		}else {
			Lock lockeditem = listoflockedseats.stream().filter(loc -> loc.getLockId().equals(lockId)).findAny().get();
			
			List<ManageStatus> list = new ArrayList<ManageStatus>(map.values());
			for (int i = 0; i < lockeditem.getCount(); i++) {
				int numberOfColumn = Integer.parseInt(lockeditem.getSeat().getColumn()) + i;
				ManageStatus instanceOfManageStatus = list.stream()
						.filter(res -> (res.getSeat().getRow().equals(lockeditem.getSeat().getRow())
								&& res.getSeat().getColumn().equals(Integer.toString(numberOfColumn))))
						.findAny().get();

				if (!instanceOfManageStatus.getSeatStatus().equals(SeatStatus.LOCKED) && !instanceOfManageStatus.getSeatStatus().equals(SeatStatus.RESERVED)) {
					throw new ICinemaBuyCinemaException("Seat is not locked", new CinemaException());

				}
				
			}
				for(int i=0;i<lockeditem.getCount();i++) {
					int numberOfColumn = Integer.parseInt(lockeditem.getSeat().getColumn()) + i;
					Seat seat=new Seat();
					seat.setColumn(Integer.toString(numberOfColumn));
					seat.setRow(lockeditem.getSeat().getRow());
					char c=seat.getRow().charAt(0);
					map.put((c-65)*init.getColumns()+numberOfColumn-1,new ManageStatus(seat, SeatStatus.SOLD));
					list.get(numberOfColumn-1).getSeatStatus().toString();
				
					
				}
				

			}
		
		
	}
	}


