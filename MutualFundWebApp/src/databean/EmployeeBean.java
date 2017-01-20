

package databean;

import org.genericdao.MaxSize;
import org.genericdao.PrimaryKey;

@PrimaryKey("username")
public class EmployeeBean {
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	
    public String getUsername()              { return username;         }
    public String    getPassword()          { return password;     }
    public String getFirstName()         { return firstName;    }
    public String getLastName()          { return lastName;     }

	@MaxSize(50)
	public void setUsername(String s)              { username = s;         }
	@MaxSize(50)
	public void    setPassword(String s)          { password = s;     }
	@MaxSize(50)
	public void setFirstName(String s)         { firstName = s;    }
	@MaxSize(50)
	public void setLastName(String s)          { lastName = s;     }

}
