package com.employemanagementsystem.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name = "attendence")
public class Attendence {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "attendence_id")
	private Integer attendenceID;

	@Column(name = "employee_id")
	private Integer employeeID;

	@Column(name = "date")
	private Date date;
	
	@ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
	
	@Column(name = "clockintime")
	private Date clockInTIme;
	
	@Column(name = "clockouttime")
	private Date clockOutTime;
}
