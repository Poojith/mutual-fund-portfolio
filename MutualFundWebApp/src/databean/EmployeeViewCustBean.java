
package databean;

//import org.genericdao.MaxSize;
import org.genericdao.PrimaryKey;

@PrimaryKey("customerId,fundId")
public class EmployeeViewCustBean {
	private int customerId;
	private int fundId;
	private double shares;
	private double totalValue;
	private String fundTicker;
	public String getFundTicker() {
		return fundTicker;
	}

	public void setFundTicker(String fundTicker) {
		this.fundTicker = fundTicker;
	}

	public int getCustomerId() {
		return customerId;
	}

	public int getFundId() {
		return fundId;
	}
	
	public double getTotalValue() {
		return totalValue;
	}

	public double getShares() {
		return shares;
	}

	public void setCustomerId(int i) {
		customerId = i;
	}

	public void setFundId(int i) {
		fundId = i;
	}

	public void setShares(double d) {
		shares = d;
	}
	
	public void setTotalValue (double d) {
		totalValue = d;
	}
}
