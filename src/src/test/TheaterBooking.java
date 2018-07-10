package src.test; 

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TheaterBooking {

	private static void bookSeat(Map<Integer, List<Integer>> layoutMap, Client it, List<ClientBookingResponse> bookingResponse) {
	
		if (!checkForNearSeatsAvilability(layoutMap, it, bookingResponse)) {
			
			boolean splitPartyAvil = false;
			for (int i = 1; i <= layoutMap.size(); i++) {
				List<Integer> seat = layoutMap.get(i);
				int totalrowSeats = seat.stream().mapToInt(s-> s).sum();
				if (totalrowSeats >  Integer.valueOf(it.getNoOfTickets()))
				{
					for (int k = 0; k < seat.size(); k++) {
						if (Integer.valueOf(it.getNoOfTickets()) <= seat.get(k)) {
							int avilSeats = seat.get(k);
							bookingResponse.add(new ClientBookingResponse(it.getName(),i, k+1));
							seat.set(k, avilSeats - Integer.valueOf(it.getNoOfTickets()));
							return;
						}
					}
					splitPartyAvil = true;
				}
				
			}
			ClientBookingResponse resp = new ClientBookingResponse(it.getName(),0, 0);
			if (splitPartyAvil)
			{
				resp.setErrorMsg("Call to split party.");
			}
			else
			{
				resp.setErrorMsg("Sorry, we can't handle your party.");
			}
			bookingResponse.add(resp);
		}
	}

	private static boolean checkForNearSeatsAvilability(Map<Integer, List<Integer>> layoutMap, Client it,
			List<ClientBookingResponse> resp) {
		int size = layoutMap.size() / 2;
		for (int i = 1; i <= size; i++) {
			List<Integer> seat = layoutMap.get(i);
			for (int k = 0; k < seat.size(); k++) {
				if (Integer.valueOf(it.getNoOfTickets()) == seat.get(k)) {
					int avilSeats = seat.get(k);
					resp.add(new ClientBookingResponse(it.getName(),i, k+1));
					seat.set(k, avilSeats - Integer.valueOf(it.getNoOfTickets()));
					return true;
				}
			}
		}
		return false;
	}

	private static List<ClientBookingResponse> seatAssignment(Map<Integer, List<Integer>> layoutMap, List<Client> clientList) {
		List<ClientBookingResponse> bookingResponse = new ArrayList<>();
		clientList.stream().forEach(it -> {
			bookSeat(layoutMap, it, bookingResponse);

		});
		return bookingResponse;
	}

	public static void main(String... args) {
		Map<Integer, List<Integer>> layoutMap = new HashMap<>();
		List<Client> clientList = new ArrayList<>();

		try (BufferedReader reader = Files.newBufferedReader(Paths.get(new File("src\\input.txt").getPath()))) {
			String line;
			boolean layout = true;
			Integer section = 1;
			while ((line = reader.readLine()) != null) {
				if (line.equals(" ")) {
					layout = false;
					continue;
				}
				if (layout) {
					String[] lineArr = line.split(" ");
					List<Integer> row = Arrays.stream(lineArr).map(Integer::valueOf).collect(Collectors.toList());
					layoutMap.put(section, row);
					section++;
				} else {
					String[] lineArr = line.split(" ");
					clientList.add(new Client(lineArr[0], lineArr[1]));

				}
			}
			
			List<ClientBookingResponse> bookingResp = seatAssignment(layoutMap, clientList);
			
			bookingResp.stream().forEach(System.out::println);
		} catch (IOException e) {
			throw new RuntimeException("Error in readding layout input file");
		}

	}
}
