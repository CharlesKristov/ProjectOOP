
public class Parts {

	String partID;
	String partName;
	int partPrice;
	int partQty;
	String supplierName;
	
	public Parts(String partID, String partName, int partPrice, int partQty, String supplierName) {
		super();
		this.partID = partID;
		this.partName = partName;
		this.partPrice = partPrice;
		this.partQty = partQty;
		this.supplierName = supplierName;
	}
	public String getPartID() {
		return partID;
	}
	public void setPartID(String partID) {
		this.partID = partID;
	}
	public String getPartName() {
		return partName;
	}
	public void setPartName(String partName) {
		this.partName = partName;
	}
	public int getPartPrice() {
		return partPrice;
	}
	public void setPartPrice(int partPrice) {
		this.partPrice = partPrice;
	}
	public int getPartQty() {
		return partQty;
	}
	public void setPartQty(int partQty) {
		this.partQty = partQty;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	

}
