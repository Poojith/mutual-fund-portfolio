package viewbeans;

public class PortfolioBean {
	private String fundName;
	private String shares;
	private String totalValue;
	private String symbol;

		
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getTotalValue() {
		return totalValue;
	}

	public String getShares() {
		return shares;
	}

	public String getFundName() {
		return fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	public void setShares(String string) {
		shares = string;
	}
	
	public void setTotalValue (String string) {
		totalValue = string;
	}
}
