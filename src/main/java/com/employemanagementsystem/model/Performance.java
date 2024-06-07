package com.employemanagementsystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "performance")
public class Performance {
	@Id
	@Column(name = "performance_id")
	private int performanceID;

	@Column(name = "employee_id")
	private int employeeID;

	@Column(name = "review_period")
	private String reviewPeriod;

	@Column(name = "performance_score")
	private double performanceScore;

	@Column(name = "feedback")
	private String feedback;

	@Column(name = "areas_for_improvement")
	private String areasForImprovement;
	
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;
}
