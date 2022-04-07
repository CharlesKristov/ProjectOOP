
public class Customer {
	private String custID;
	private String custName;
	private String custStatus;
	private double diskon;
	public Customer(String custID, String custName, String custStatus, double diskon) {
		super();
		this.custID = custID;
		this.custName = custName;
		this.custStatus = custStatus;
		this.diskon = diskon;
	}
	public String getCustID() {
		return custID;
	}
	public void setCustID(String custID) {
		this.custID = custID;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustStatus() {
		return custStatus;
	}
	public void setCustStatus(String custStatus) {
		this.custStatus = custStatus;
	}
	public double getDiskon() {
		return diskon;
	}
	public void setDiskon(double diskon) {
		this.diskon = diskon;
	}
	

}