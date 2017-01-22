
package databean;

import org.genericdao.MaxSize;
import org.genericdao.PrimaryKey;

@PrimaryKey("customerId")
public class CustomerBean {
	private int customerId;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String addrLine1;
	private String addrLine2;
	private String city;
	private String state;
	private String zip;
	private long cash;

	public int getCustomerId() {
		return customerId;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAddrLine1() {
		return addrLine1;
	}

	public String getAddrLine2() {
		return addrLine2;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getZip() {
		return zip;
	}

	public long getCash() {
		return cash;
	}

	public void setCustomerId(int i) {
		customerId = i;
	}

	@MaxSize(50)
	public void setUsername(String s) {
		username = s;
	}

	@MaxSize(50)
	public void setPassword(String s) {
		password = s;
	}

	@MaxSize(50)
	public void setFirstName(String s) {
		firstName = s;
	}

	@MaxSize(50)
	public void setLastName(String s) {
		lastName = s;
	}

	@MaxSize(50)
	public void setAddrLine1(String s) {
		addrLine1 = s;
	}

	@MaxSize(50)
	public void setAddrLine2(String s) {
		addrLine2 = s;
	}

	@MaxSize(50)
	public void setCity(String s) {
		city = s;
	}

	@MaxSize(50)
	public void setState(String s) {
		state = s;
	}

	@MaxSize(50)
	public void setZip(String s) {
		zip = s;
	}

	@MaxSize(50)
	public void setCash(long l) {
		cash = l;
	}

}
