package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class LoginForm extends FormBean {
	private String userName;
	private String passWord;
	private String CusLogin;
	private String EmpLogin;
	
	public String getUserName()  { return userName; }
	public String getPassWord()  { return passWord; }
	public String getCusLogin()  { return CusLogin; }
	public String getEmpLogin()  { return EmpLogin; }
	
	public void setUserName(String s) { userName = trimAndConvert(s,"<>\"");  }
	public void setPassWord(String s) {	passWord = s.trim();                  }
	public void setCusLogin(String cusLogin) {CusLogin = cusLogin;            }
	public void setEmpLogin(String empLogin) {EmpLogin = empLogin;            }
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (userName == null || userName.length() == 0) {
			errors.add("User Name is required");
		}
		
		if (passWord == null || passWord.length() == 0) {
			errors.add("Password is required");
		}
		if (CusLogin == null && EmpLogin == null) {
			errors.add("Login button is required");
		}
		
		return errors;
	}
}
