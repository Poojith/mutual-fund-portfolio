package viewbeans;

public class PortfolioBean {
	private String fundName;
	private double shares;
	private double totalValue;

	
	public double getTotalValue() {
		return totalValue;
	}

	public double getShares() {
		return shares;
	}

	public String getFundName() {
		return fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	public void setShares(double d) {
		shares = d;
	}
	
	public void setTotalValue (double d) {
		totalValue = d;
	}
}
