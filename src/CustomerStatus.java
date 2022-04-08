
public abstract class CustomerStatus {
	
	public abstract double diskon();
}

class premiumCust extends CustomerStatus {
	public double diskon()
	{
		return 0.1;
	}
}
class goldCust extends CustomerStatus {
	public double diskon()
	{
		return 0.05;
	}
}
