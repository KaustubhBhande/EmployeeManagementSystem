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
import com.employemanagementsystem.model.Attendence;
import com.employemanagementsystem.service.AttendenceSerivce;

@RestController
@RequestMapping("/attendence")
public class AttendenceController {
	@Autowired
	private AttendenceSerivce attendenceService;

	@GetMapping("/all")
	public ResponseEntity<?> getCategories() {

		List<Attendence> attendence = attendenceService.getAllAttendence();
		if (attendence.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Attendence not found");
		}
		return ResponseEntity.ok(attendence);
	}

	@GetMapping("")
	public Page<Attendence> getAttendenceWithPagination(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize);
		return attendenceService.getAllAttendence1(pageable);
	}

	@GetMapping("/{id}")

	public ResponseEntity<?> getAttendenceById(@PathVariable Integer id) {

		Optional<Attendence> AttendenceById = attendenceService.getAttendence(id);
		if (AttendenceById.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Attendence By Id Not Found");

		} else {
			Attendence Attendence = AttendenceById.get();

			return ResponseEntity.ok().body(Attendence);
		}
	}

	@PostMapping(" ")
	public ResponseEntity<?> addAttendence(@RequestBody Attendence attendence) {
		List<String> errors = attendenceService.validate(attendence);
		if (!errors.isEmpty()) {
			return ResponseEntity.badRequest().body(errors);
		}

		attendenceService.addAttendence(attendence);
		return ResponseEntity.status(HttpStatus.CREATED).body("Attendence Added Sucessfully.");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteAttendence(@PathVariable Integer id) {

		boolean deleted = attendenceService.deleteAttendence(id);

		if (deleted) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT)
					.body("Attendence with ID " + id + " Deleted Sucessfully.");
		}

		else

		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Attendence with ID " + id + " Not Found.");

		}

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateAttendence(@PathVariable Integer id, @RequestBody Attendence attendence) {

		List<String> errors = attendenceService.validate(attendence);
		if (!errors.isEmpty()) {
			return ResponseEntity.badRequest().body(errors);
		}

		Optional<Attendence> existingAttendence = attendenceService.getAttendence(id);
		if (!existingAttendence.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Attendence with ID " + id + "Not Found.");
		}

		attendenceService.updateAttendence(id, attendence);
		return ResponseEntity.ok().body("Attendence with ID " + id + " Updated Sucessfully.");
	}
}
