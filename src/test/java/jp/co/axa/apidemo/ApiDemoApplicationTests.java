package jp.co.axa.apidemo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;

import jp.co.axa.apidemo.customexception.EmployeeNotFoundException;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.pojo.EmployeeRequest;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import jp.co.axa.apidemo.services.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiDemoApplicationTests {

	@Autowired
	private EmployeeService employeeService;

	@MockBean
	private EmployeeRepository employeeRepository;

	@Test
	public void saveEmployeeTest() throws JsonProcessingException {
		Employee employee = new Employee(5675L, "TestEmp1", 78000, "HR");
		when(employeeRepository.save(employee)).thenReturn(employee);
		EmployeeRequest employeeRequest = new EmployeeRequest();
		employeeRequest.setEmployeeName("TestEmp1");
		employeeRequest.setDepartment("HR");
		employeeRequest.setSalary(78000);
		assertEquals(employeeRequest, employeeService.saveEmployee(employeeRequest));
	}

	@Test
	public void getAllEmployeesTest() {
		when(employeeRepository.findAll()).thenReturn(Stream
				.of(new Employee(5675L, "TestEmp1", 78000, "HR"), new Employee(9876L, "TestEmp1",56000, "IT")).collect(Collectors.toList()));
		assertEquals(2, employeeService.retrieveEmployees().size());
	}

	@Test
	public void deleteEmployeeTest() throws EmployeeNotFoundException {
		Employee employee = new Employee(5675L, "TestEmp1", 78000, "HR");
		employeeService.deleteEmployee(employee.getId());
		verify(employeeRepository, times(1)).delete(employee);
	}

}
