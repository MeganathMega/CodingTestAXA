package jp.co.axa.apidemo.controllers;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jp.co.axa.apidemo.customexception.EmployeeNotFoundException;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.pojo.EmployeeRequest;
import jp.co.axa.apidemo.pojo.EmployeeResponse;
import jp.co.axa.apidemo.services.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/retrieveEmployees")
	public List<Employee> getEmployees() {
		List<Employee> employees = employeeService.retrieveEmployees();
		return employees;
	}

	@GetMapping("/getEmployee/{employeeId}")
	public EmployeeResponse getEmployee(@PathVariable(name = "employeeId") Long employeeId) throws EmployeeNotFoundException {
		return employeeService.getEmployee(employeeId);
	}

	@PostMapping("/saveEmployee")
	public ResponseEntity<String> addUser(@Valid @RequestBody EmployeeRequest employeeRequest) {
		employeeService.saveEmployee(employeeRequest);
		return ResponseEntity.ok("Employee Saved Successfully");
	}

	@DeleteMapping("/deleteEmployee/{employeeId}")
	public ResponseEntity<String> deleteEmployee(@PathVariable(name = "employeeId") Long employeeId) throws EmployeeNotFoundException {
		employeeService.deleteEmployee(employeeId);
		return ResponseEntity.ok("Employee Deleted Successfully");
	}

	@PutMapping("/updateEmployee/{employeeId}")
	public ResponseEntity<String> updateEmployee(@RequestBody EmployeeRequest employee,
			@PathVariable(name = "employeeId") Long employeeId) throws EmployeeNotFoundException {
		employeeService.updateEmployee(employeeId, employee);
        return ResponseEntity.ok("Employee Updated Successfully");

	}

}
