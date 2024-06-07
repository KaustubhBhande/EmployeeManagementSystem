 package com.employemanagementsystem.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.employemanagementsystem.model.Performance;
import com.employemanagementsystem.repository.PerformanceRepository;

@Service
public class PerformanceService {
	@Autowired
	private PerformanceRepository performanceRepository;

	public List<String> validate(Performance performance) {

		List<String> error = new ArrayList<>();

		if (performance.getFeedback() == null) {
			error.add("Performance FeedBack Cannot Be Empty");
		}

		if (performance.getReviewPeriod() == null) {
			error.add("Performance Period Cannot Be Empty");
		}

		if (performance.getAreasForImprovement() == null) {
			error.add("AreasForImprovement Cannot Be Empty");
		}
		return error;
	}

	public Page<Performance> getAllPerformance1(Pageable pageable) {
		return performanceRepository.findAll(pageable);
	}

	public List<Performance> getAllPerformance() {
		return (List<Performance>) this.performanceRepository.findAll();

	}

	public Optional<Performance> getPerformance(Integer id) {
		return this.performanceRepository.findById(id);
	}

	public Performance addPerformance(Performance performance) {
		return this.performanceRepository.save(performance);
	}

	public boolean deletePerformance(Integer id) {
		boolean exists = performanceRepository.existsById(id);
		if (exists) {
			performanceRepository.deleteById(id);
			return true;
		} else {

			return false;
		}
	}

	public Performance updatePerformance(Integer id, Performance performance) {
		Performance existingperformance = performanceRepository.findById(id).orElse(null);
		existingperformance.setPerformanceID(performance.getPerformanceID());
		existingperformance.setPerformanceScore(performance.getPerformanceScore());
		existingperformance.setAreasForImprovement(performance.getAreasForImprovement());
		existingperformance.setFeedback(performance.getFeedback());
		existingperformance.setReviewPeriod(performance.getReviewPeriod());
		existingperformance.setEmployee(performance.getEmployee());
		return performanceRepository.save(existingperformance);
	}
}
