
package databean;

import org.genericdao.MaxSize;
import org.genericdao.PrimaryKey;

@PrimaryKey("FundId")
public class FundBean {
	private int fundId;
	private String name;
	private String symbol;

	public int getFundId() {
		return fundId;
	}

	public String getName() {
		return name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setFundId(int i) {
		fundId = i;
	}

	@MaxSize(50)
	public void setName(String s) {
		name = s;
	}

	@MaxSize(50)
	public void setSymbol(String s) {
		symbol = s;
	}
}