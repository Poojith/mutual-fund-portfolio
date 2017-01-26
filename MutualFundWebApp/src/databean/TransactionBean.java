
package databean;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.genericdao.MaxSize;
import org.genericdao.PrimaryKey;

@PrimaryKey("transactionId")

public class TransactionBean implements Comparable<TransactionBean> {
	private int transactionId;
	private int customerId;
	private int fundId;
	private String executeDate;
	private double shares;
	private double sharePrice;
	
	//null when pending
	//"completed" when completed
	private String status;
	
	// 1-buyfund
	// 2-sellfund
	// 3-depositcheck
	// 4-requestcheck
	private int transactionType;
	private double amount;

	public int getTransactionId() {
		return transactionId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public int getFundId() {
		return fundId;
	}

	public String getExecuteDate() {
		return executeDate;
	}

	public double getShares() {
		return shares;
	}
	
	public int getTransactionType() {
		return transactionType;
	}

	public double getAmount() {
		return amount;
	}
	
	public double getSharePrice() {
		return sharePrice;
	}
	
	public String getStatus() {
		return status;
	}

	public void setTransactionId(int i) {
		transactionId = i;
	}

	public void setCustomerId(int i) {
		customerId = i;
	}

	public void setFundId(int i) {
		fundId = i;
	}

	@MaxSize(50)
	public void setExecuteDate(String s) {
		executeDate = s;
	}

	public void setShares(double d) {
		shares = d;
	}

	public void setTransactionType(int i) {
		transactionType = i;
	}
	
	public void setAmount(double d) {
		amount = d;
	}
	
	public void setSharePrice(double d) {
		sharePrice = d;
	}
	
	public void setStatus(String s) {
		status = s;
	}
	
	public int compareTo(TransactionBean transaction) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        dateFormat.setLenient(false);
        if (this.getExecuteDate()== null && transaction.getExecuteDate()==null) {
        	return 0;
        }
        if (this.getExecuteDate()==null) {
        	return 1;
        } else if (transaction.getExecuteDate()==null) {
        	return -1;
        }
        try {
            return -dateFormat.parse(this.getExecuteDate()).compareTo(dateFormat.parse(transaction.getExecuteDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
	
}
