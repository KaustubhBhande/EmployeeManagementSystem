package com.employemanagementsystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.employemanagementsystem.model.Employee;
import com.employemanagementsystem.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;

	public List<String> validate(Employee employee) {

		List<String> error = new ArrayList<>();

		if (employee.getFirstName() == null) {
			error.add("Employee FistName Cannot Be Empty");
		}

		if (employee.getLastName() == null) {
			error.add("Employee LastName Cannot Be Empty");
		}

		if (employee.getAddress() == null) {
			error.add("Employee Address Cannot Be Empty");
		}

		if (employee.getEmail() == null) {
			error.add("Employee Email Cannot Be Empty");
		}

		if (employee.getJobTitle() == null) {
			error.add("Employee Job Title Cannot Be Empty");
		}

		return error;
	}

	public Page<Employee> getAllEmployee1(Pageable pageable) {
		return employeeRepository.findAll(pageable);
	}

	public List<Employee> getAllEmployee() {
		return (List<Employee>) this.employeeRepository.findAll();

	}

	public Optional<Employee> getEmployee(Integer id) {
		return this.employeeRepository.findById(id);
	}

	public Employee addEmployee(Employee employee) {
		return this.employeeRepository.save(employee);
	}

	public boolean deleteEmployee(Integer id) {
		boolean exists = employeeRepository.existsById(id);
		if (exists) {
			employeeRepository.deleteById(id);
			return true;
		} else {

			return false;
		}
	}

	public Employee updateEmployee(Integer id, Employee employee) {
		Employee existingemployee = employeeRepository.findById(id).orElse(null);
		existingemployee.setFirstName(employee.getFirstName());
		existingemployee.setLastName(employee.getLastName());
		existingemployee.setEmail(employee.getEmail());
		existingemployee.setAddress(employee.getAddress());
		existingemployee.setJobTitle(employee.getJobTitle());
		return employeeRepository.save(existingemployee);
	}
}
