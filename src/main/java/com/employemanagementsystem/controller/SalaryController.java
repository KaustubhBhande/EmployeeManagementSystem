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
import com.employemanagementsystem.model.Salary;
import com.employemanagementsystem.service.SalaryService;

@RestController
@RequestMapping("/salary")
public class SalaryController {
	@Autowired
	private SalaryService salaryService;

	@GetMapping("/all")
	public ResponseEntity<?> getCategories() {

		List<Salary> salary = salaryService.getAllSalary();
		if (salary.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Salary not found");
		}
		return ResponseEntity.ok(salary);
	}

	@GetMapping("")
	public Page<Salary> getSalaryWithPagination(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize);
		return salaryService.getAllSalary1(pageable);
	}

	@GetMapping("/{id}")

	public ResponseEntity<?> getSalaryById(@PathVariable Integer id) {

		Optional<Salary> SalaryById = salaryService.getSalary(id);
		if (SalaryById.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Salary By Id Not Found");

		} else {
			Salary Salary = SalaryById.get();

			return ResponseEntity.ok().body(Salary);
		}
	}

	@PostMapping(" ")
	public ResponseEntity<?> addSalary(@RequestBody Salary salary) {
		List<String> errors = salaryService.validate(salary);
		if (!errors.isEmpty()) {
			return ResponseEntity.badRequest().body(errors);
		}

		salaryService.addSalary(salary);
		return ResponseEntity.status(HttpStatus.CREATED).body("Salary Added Sucessfully.");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteSalary(@PathVariable Integer id) {

		boolean deleted = salaryService.deleteSalary(id);

		if (deleted) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Salary with ID " + id + " Deleted Sucessfully.");
		}

		else

		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Salary with ID " + id + " Not Found.");

		}

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateSalary(@PathVariable Integer id, @RequestBody Salary salary) {

		List<String> errors = salaryService.validate(salary);
		if (!errors.isEmpty()) {
			return ResponseEntity.badRequest().body(errors);
		}

		Optional<Salary> existingSalary = salaryService.getSalary(id);
		if (!existingSalary.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Salary with ID " + id + "Not Found.");
		}

		salaryService.updateSalary(id, salary);
		return ResponseEntity.ok().body("Salary with ID " + id + " Updated Sucessfully.");
	}
}
