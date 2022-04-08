
public class TransactionHistory {

	private String transID;
	private String transType;
	private int transQty;
	private String custID;
	private String partID;
	private String shipID;
	private String supplierName;
	private int transPrice;
	private String paymentMethod;
	
	public TransactionHistory(String transID, String transType, int transQty, String partID, String custID,
			String supplierName, int transPrice, String paymentMethod, String shipID) {
		super();
		this.transID = transID;
		this.transType = transType;
		this.transQty = transQty;
		this.partID = partID;
		this.custID = custID;
		this.supplierName = supplierName;
		this.transPrice = transPrice;
		this.paymentMethod = paymentMethod;
		this.shipID = shipID;
	}
	
	public String getShipID() {
		return shipID;
	}
	public void setShipID(String shipID) {
		this.shipID = shipID;
	}
	public String getTransID() {
		return transID;
	}
	public void setTransID(String transID) {
		this.transID = transID;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public int getTransQty() {
		return transQty;
	}
	public void setTransQty(int transQty) {
		this.transQty = transQty;
	}
	public String getPartID() {
		return partID;
	}
	public void setPartID(String partID) {
		this.partID = partID;
	}
	public String getCustID() {
		return custID;
	}
	public void setCustID(String custID) {
		this.custID = custID;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public int getTransPrice() {
		return transPrice;
	}
	public void setTransPrice(int transPrice) {
		this.transPrice = transPrice;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	
	

}
