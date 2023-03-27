package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.customexception.EmployeeNotFoundException;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.pojo.EmployeeRequest;
import jp.co.axa.apidemo.pojo.EmployeeResponse;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public void setEmployeeRepository(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public List<Employee> retrieveEmployees() {
		List<Employee> employees = employeeRepository.findAll();
		return employees;
	}

	public EmployeeResponse getEmployee(Long employeeId) throws EmployeeNotFoundException {
		Employee employeeDetail = employeeRepository.findById(employeeId).orElse(null);
		if(employeeDetail!=null) {
			EmployeeResponse employeeResponse = new EmployeeResponse();
			employeeResponse.setId(employeeDetail.getId());
			employeeResponse.setName(employeeDetail.getName());
			employeeResponse.setDepartment(employeeDetail.getDepartment());
			employeeResponse.setSalary(employeeDetail.getSalary());
			return employeeResponse;
		} else {
			throw new EmployeeNotFoundException("Employee not found with id: " + employeeId);
		}
		
	}

	public Employee saveEmployee(EmployeeRequest employeeRequest) {
		Employee employee = new Employee();
		employee.setName(employeeRequest.getEmployeeName());
		employee.setSalary(employeeRequest.getSalary());
		employee.setDepartment(employeeRequest.getDepartment());
		return employeeRepository.save(employee);
	}

	public void deleteEmployee(Long employeeId) throws EmployeeNotFoundException {
		Employee employeeDetail = employeeRepository.findById(employeeId).orElse(null);
		if (employeeDetail != null) {
			employeeRepository.deleteById(employeeId);
	} else {

		throw new EmployeeNotFoundException("Employee not found with id: " + employeeId);
	}
	}

	@Override
	public void updateEmployee(Long employeeId, EmployeeRequest employee) throws EmployeeNotFoundException {
		Employee employeeDetail = employeeRepository.findById(employeeId).orElse(null);
		if (employeeDetail != null) {
			employeeDetail.setName(
					employee.getEmployeeName() != null ? employee.getEmployeeName() : employeeDetail.getName());
			employeeDetail.setDepartment(
					employee.getDepartment() != null ? employee.getDepartment() : employeeDetail.getDepartment());
			employeeDetail.setSalary(employee.getSalary() != null ? employee.getSalary() : employeeDetail.getSalary());
			employeeRepository.save(employeeDetail);
		} else {

			throw new EmployeeNotFoundException("Employee not found with id: " + employeeId);
		}

	}

}