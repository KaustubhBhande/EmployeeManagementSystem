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

import com.employemanagementsystem.model.Performance;
import com.employemanagementsystem.service.PerformanceService;

@RestController
@RequestMapping("/performance")
public class PerformanceController {
	@Autowired
	private PerformanceService performanceService;

	@GetMapping("/all")
	public ResponseEntity<?> getCategories() {

		List<Performance> performance = performanceService.getAllPerformance();
		if (performance.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Performance not found");
		}
		return ResponseEntity.ok(performance);
	}

	@GetMapping("")
	public Page<Performance> getPerformanceWithPagination(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize);
		return performanceService.getAllPerformance1(pageable);
	}

	@GetMapping("/{id}")

	public ResponseEntity<?> getPerformanceById(@PathVariable Integer id) {

		Optional<Performance> PerformanceById = performanceService.getPerformance(id);
		if (PerformanceById.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Performance By Id Not Found");

		} else {
			Performance Performance = PerformanceById.get();

			return ResponseEntity.ok().body(Performance);
		}
	}

	@PostMapping(" ")
	public ResponseEntity<?> addPerformance(@RequestBody Performance performance) {
		List<String> errors = performanceService.validate(performance);
		if (!errors.isEmpty()) {
			return ResponseEntity.badRequest().body(errors);
		}

		performanceService.addPerformance(performance);
		return ResponseEntity.status(HttpStatus.CREATED).body("Performance Added Sucessfully.");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePerformance(@PathVariable Integer id) {

		boolean deleted = performanceService.deletePerformance(id);

		if (deleted) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Performance with ID " + id + " Deleted Sucessfully.");
		}

		else

		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Performance with ID " + id + " Not Found.");

		}

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updatePerformance(@PathVariable Integer id, @RequestBody Performance performance) {

		List<String> errors = performanceService.validate(performance);
		if (!errors.isEmpty()) {
			return ResponseEntity.badRequest().body(errors);
		}

		Optional<Performance> existingPerformance = performanceService.getPerformance(id);
		if (!existingPerformance.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Performance with ID " + id + "Not Found.");
		}

		performanceService.updatePerformance(id, performance);
		return ResponseEntity.ok().body("Performance with ID " + id + " Updated Sucessfully.");
	}
}

