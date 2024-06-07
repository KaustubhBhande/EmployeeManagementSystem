package com.employemanagementsystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employemanagementsystem.model.Employee;
import com.employemanagementsystem.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/all")
	public ResponseEntity<?> getCategories() {

		List<Employee> employee = employeeService.getAllEmployee();
		if (employee.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
		}
		return ResponseEntity.ok(employee);
	}

	@GetMapping("")
	public Page<Employee> getEmployeeWithPagination(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize);
		return employeeService.getAllEmployee1(pageable);
	}

	@GetMapping("/{id}")

	public ResponseEntity<?> getEmployeeById(@PathVariable Integer id) {

		Optional<Employee> EmployeeById = employeeService.getEmployee(id);
		if (EmployeeById.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee By Id Not Found");

		} else {
			Employee Employee = EmployeeById.get();

			return ResponseEntity.ok().body(Employee);
		}
	}

	@PostMapping(" ")
	public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
		List<String> errors = employeeService.validate(employee);
		if (!errors.isEmpty()) {
			return ResponseEntity.badRequest().body(errors);
		}

		employeeService.addEmployee(employee);
		return ResponseEntity.status(HttpStatus.CREATED).body("Employee Added Sucessfully.");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable Integer id) {

		boolean deleted = employeeService.deleteEmployee(id);

		if (deleted) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT)
					.body("Employee with ID " + id + " Deleted Sucessfully.");
		}

		else

		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee with ID " + id + " Not Found.");

		}

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateEmployee(@PathVariable Integer id, @RequestBody Employee employee) {

		List<String> errors = employeeService.validate(employee);
		if (!errors.isEmpty()) {
			return ResponseEntity.badRequest().body(errors);
		}

		Optional<Employee> existingEmployee = employeeService.getEmployee(id);
		if (!existingEmployee.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee with ID " + id + "Not Found.");
		}

		employeeService.updateEmployee(id, employee);
		return ResponseEntity.ok().body("Employee with ID " + id + " Updated Sucessfully.");
	}
}
