
package databean;

import org.genericdao.MaxSize;
import org.genericdao.PrimaryKey;

@PrimaryKey("username")
public class EmployeeBean {
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String addrLine1;
	private String addrLine2;
	private String city;
	private String state;
	private String zip;

	public String getUsername() {
		return username;
	}

	@MaxSize(50)
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	@MaxSize(50)
	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	@MaxSize(50)
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	@MaxSize(50)
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddrLine1() {
		return addrLine1;
	}

	@MaxSize(50)
	public void setAddrLine1(String addrLine1) {
		this.addrLine1 = addrLine1;
	}

	public String getAddrLine2() {
		return addrLine2;
	}

	@MaxSize(50)
	public void setAddrLine2(String addrLine2) {
		this.addrLine2 = addrLine2;
	}

	public String getCity() {
		return city;
	}

	@MaxSize(50)
	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}
	
	@MaxSize(50)
	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	@MaxSize(50)
	public void setZip(String zip) {
		this.zip = zip;
	}
}
