package jp.co.axa.apidemo.customexception;

@SuppressWarnings("serial")
public class EmployeeNotFoundException extends Exception {
	
	public EmployeeNotFoundException(String message) {
		super(message);
	}

}
