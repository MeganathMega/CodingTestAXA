package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.customexception.EmployeeNotFoundException;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.pojo.EmployeeRequest;
import jp.co.axa.apidemo.pojo.EmployeeResponse;

import java.util.List;


public interface EmployeeService {

    public List<Employee> retrieveEmployees();

    public EmployeeResponse getEmployee(Long employeeId) throws EmployeeNotFoundException;

    public void deleteEmployee(Long employeeId) throws EmployeeNotFoundException;

	public void updateEmployee(Long employeeId, EmployeeRequest employee) throws EmployeeNotFoundException;

	public Employee saveEmployee(EmployeeRequest employeeRequest);

}