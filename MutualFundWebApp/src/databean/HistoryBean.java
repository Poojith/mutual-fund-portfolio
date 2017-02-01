package databean;

import org.genericdao.MaxSize;

public class HistoryBean {
	private String transactionDate;
	private String operation;
	private String status;
	private String fundName;
	private String numShares;
	private String amount;
	private String sharePrice;
	
	public String getTransactionDate() {
		return transactionDate;
	}
	
	@MaxSize(20)
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
	
	@MaxSize(20)
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFundName() {
		return fundName;
	}
	
	@MaxSize(50)
	public void setFundName(String fundName) {
		this.fundName = fundName;
	}
	public String getNumShares() {
		return numShares;
	}
	public void setNumShares(String numShares) {
		this.numShares = numShares;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getSharePrice() {
		return sharePrice;
	}
	public void setSharePrice(String d) {
		sharePrice = d;
	}
}
