package src.test;

public class ClientBookingResponse {
	
	private String name;
	private int rowNo;
	private int sectionNo;
	private String errorMsg;
	
	public ClientBookingResponse(String name, int rowNo, int sectionNo) {
		this.name = name;
		this.rowNo = rowNo;
		this.sectionNo = sectionNo;
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	

	@Override
	public String toString() {
		if (null  == errorMsg)
			return name +" Row " + rowNo + " Section " + sectionNo;
		else 
			 return name +"  "+errorMsg ;
				
	}
}
