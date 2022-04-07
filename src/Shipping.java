
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
class Land extends Shipping {
	private int shippingCost;
	public Land(String shipID, String shippingName, String shippingMethodName) {
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
class Sea extends Shipping {
	private int shippingCost;
	public Sea(String shipID, String shippingName, String shippingMethodName) {
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
class Air extends Shipping {
	private int shippingCost;
	public Air(String shipID, String shippingName, String shippingMethodName) {
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
