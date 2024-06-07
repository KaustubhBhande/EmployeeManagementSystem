package com.employemanagementsystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.employemanagementsystem.model.Salary;
import com.employemanagementsystem.repository.SalaryRepository;

@Service
public class SalaryService {
	@Autowired
	private SalaryRepository salaryRepository;

	public List<String> validate(Salary salary) {

		List<String> error = new ArrayList<>();

		if (salary.getSalaryID() == null) {
			error.add("Salary Id Cannot Be Empty");
		}

		if (salary.getBaseSalary() == null) {
			error.add("Base Salary Cannot Be Empty");
		}

		if (salary.getPayDate() == null) {
			error.add("Pay Date Cannot Be Empty");
		}
		return error;
	}

	public Page<Salary> getAllSalary1(Pageable pageable) {
		return salaryRepository.findAll(pageable);
	}

	public List<Salary> getAllSalary() {
		return (List<Salary>) this.salaryRepository.findAll();

	}

	public Optional<Salary> getSalary(Integer id) {
		return this.salaryRepository.findById(id);
	}

	public Salary addSalary(Salary salary) {
		return this.salaryRepository.save(salary);
	}

	public boolean deleteSalary(Integer id) {
		boolean exists = salaryRepository.existsById(id);
		if (exists) {
			salaryRepository.deleteById(id);
			return true;
		} else {

			return false;
		}
	}

	public Salary updateSalary(Integer id, Salary salary) {
		Salary existingsalary = salaryRepository.findById(id).orElse(null);
		existingsalary.setSalaryID(salary.getSalaryID());
		existingsalary.setBaseSalary(salary.getBaseSalary());
		existingsalary.setPayDate(salary.getPayDate());
		;
		return salaryRepository.save(existingsalary);
	}
}
