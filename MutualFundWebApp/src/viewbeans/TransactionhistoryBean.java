package viewbeans;

public class TransactionhistoryBean {
	private String fundName;
	private String executeDate;
	private String shares;
	private String sharePrice;
	private String status;
	private String transactionType;
	private String amount;
	public String getFundName() {
		return fundName;
	}
	public void setFundName(String fundName) {
		this.fundName = fundName;
	}
	public String getExecuteDate() {
		return executeDate;
	}
	public void setExecuteDate(String executeDate) {
		this.executeDate = executeDate;
	}
	public String getShares() {
		return shares;
	}
	public void setShares(String shares) {
		this.shares = shares;
	}
	public String getSharePrice() {
		return sharePrice;
	}
	public void setSharePrice(String sharePrice) {
		this.sharePrice = sharePrice;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String string) {
		this.amount = string;
	}
	
}
