package databean;

public class HistoryBean {
	private String transactionDate;
	private String operation;
	private String status;
	private String fundName;
	private double numShares;
	private double amount;
	private double sharePrice;
	
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(int optype) {
		if (optype == 1) {
			operation = "Buy Fund";
		} else if (optype == 2) {
			operation = "Sell Fund";
		} else if (optype == 3) {
			operation = "Deposit Check";
		} else if (optype == 4) {
			operation = "Request Check";
		}
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFundName() {
		return fundName;
	}
	public void setFundName(String fundName) {
		this.fundName = fundName;
	}
	public double getNumShares() {
		return numShares;
	}
	public void setNumShares(double numShares) {
		this.numShares = numShares;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getSharePrice() {
		return sharePrice;
	}
	public void setSharePrice(double d) {
		sharePrice = d;
	}
}
