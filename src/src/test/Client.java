package src.test;

public class Client {
	
	@Override
	public String toString() {
		return "Client [name=" + name + ", noOfTickets=" + noOfTickets + "]";
	}
	private String name;
	private String noOfTickets;
	
	public Client(String name, String noOfTickets) {
		this.name = name;
		this.noOfTickets = noOfTickets;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNoOfTickets() {
		return noOfTickets;
	}
	public void setNoOfTickets(String noOfTickets) {
		this.noOfTickets = noOfTickets;
	}
	

}
