package com.employemanagementsystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.employemanagementsystem.model.Attendence;
import com.employemanagementsystem.repository.AttendenceRepository;

@Service
public class AttendenceSerivce {
	@Autowired
	private AttendenceRepository attendenceRepository;

	public List<String> validate(Attendence attendence) {

		List<String> error = new ArrayList<>();

		if (attendence.getClockInTIme() == null) {
			error.add("ClockInTime Cannot Be Empty");
		}
		if (attendence.getClockOutTime() == null) {
			error.add("ClockOutTime Cannot Be Empty");
		}
		if (attendence.getDate() == null) {
			error.add("Date Cannot Be Empty");
		}

		return error;
	}

	public Page<Attendence> getAllAttendence1(Pageable pageable) {
		return attendenceRepository.findAll(pageable);
	}

	public List<Attendence> getAllAttendence() {
		return (List<Attendence>) this.attendenceRepository.findAll();

	}

	public Optional<Attendence> getAttendence(Integer id) {
		return this.attendenceRepository.findById(id);
	}

	public Attendence addAttendence(Attendence attendence) {
		return this.attendenceRepository.save(attendence);
	}

	public boolean deleteAttendence(Integer id) {
		boolean exists = attendenceRepository.existsById(id);
		if (exists) {
			attendenceRepository.deleteById(id);
			return true;
		} else {

			return false;
		}
	}

	public Attendence updateAttendence(Integer id, Attendence attendence) {
		Attendence existingattendence = attendenceRepository.findById(id).orElse(null);
		existingattendence.setClockInTIme(attendence.getClockInTIme());
		existingattendence.setClockOutTime(attendence.getClockOutTime());
		existingattendence.setDate(attendence.getDate());
		existingattendence.setAttendenceID(attendence.getAttendenceID());
		existingattendence.setEmployeeID(attendence.getEmployeeID());
		return attendenceRepository.save(existingattendence);
	}
}
