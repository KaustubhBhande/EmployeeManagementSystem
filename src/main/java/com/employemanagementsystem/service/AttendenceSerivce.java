package com.employemanagementsystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.employemanagementsystem.model.Attendance;
import com.employemanagementsystem.repository.AttendenceRepository;

@Service
public class AttendenceSerivce {
	@Autowired
	private AttendenceRepository attendenceRepository;

	public List<String> validate(Attendance attendence) {

		List<String> error = new ArrayList<>();

		if (attendence.getClockInTime() == null) {
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

	public Page<Attendance> getAllAttendence1(Pageable pageable) {
		return attendenceRepository.findAll(pageable);
	}

	public List<Attendance> getAllAttendence() {
		return (List<Attendance>) this.attendenceRepository.findAll();

	}

	public Optional<Attendance> getAttendence(Integer id) {
		return this.attendenceRepository.findById(id);
	}

	public Attendance addAttendence(Attendance attendence) {
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

	public Attendance updateAttendence(Integer id, Attendance attendence) {
		Attendance existingattendence = attendenceRepository.findById(id).orElse(null);
		existingattendence.setClockInTime(attendence.getClockInTime());
		existingattendence.setClockOutTime(attendence.getClockOutTime());
		existingattendence.setDate(attendence.getDate());
		existingattendence.setAttendanceID(attendence.getAttendanceID());
		existingattendence.setEmployee(attendence.getEmployee());
		return attendenceRepository.save(existingattendence);
	}
}
