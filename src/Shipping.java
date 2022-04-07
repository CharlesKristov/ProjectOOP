
public class Shipping {

	private String shipID;
	private String shippingName;
	private String shippingMethodName;
	public Shipping(String shipID, String shippingName, String shippingMethodName) {
		super();
		this.shipID = shipID;
		this.shippingName = shippingName;
		this.shippingMethodName = shippingMethodName;
	}
	public String getShipID() {
		return shipID;
	}
	public void setShipID(String shipID) {
		this.shipID = shipID;
	}
	public String getShippingName() {
		return shippingName;
	}
	public void setShippingName(String shippingName) {
		this.shippingName = shippingName;
	}
	public String getShippingMethodName() {
		return shippingMethodName;
	}
	public void setShippingMethodName(String shippingMethodName) {
		this.shippingMethodName = shippingMethodName;
	}
}
class land extends Shipping {
	private int shippingCost;
	public land(String shipID, String shippingName, String shippingMethodName, int shippingCost) {
		super(shipID, shippingName, shippingMethodName);
		this.setShippingCost(20000);
	}
	public int getShippingCost() {
		return shippingCost;
	}
	public void setShippingCost(int shippingCost) {
		this.shippingCost = shippingCost;
	}
}
class sea extends Shipping {
	private int shippingCost;
	public sea(String shipID, String shippingName, String shippingMethodName, int shippingCost) {
		super(shipID, shippingName, shippingMethodName);
		this.setShippingCost(50000);
	}
	public int getShippingCost() {
		return shippingCost;
	}
	public void setShippingCost(int shippingCost) {
		this.shippingCost = shippingCost;
	}
}
class air extends Shipping {
	private int shippingCost;
	public air(String shipID, String shippingName, String shippingMethodName, int shippingCost) {
		super(shipID, shippingName, shippingMethodName);
		this.setShippingCost(100000);
	}
	public int getShippingCost() {
		return shippingCost;
	}
	public void setShippingCost(int shippingCost) {
		this.shippingCost = shippingCost;
	}
}
