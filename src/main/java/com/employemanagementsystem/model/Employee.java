package com.employemanagementsystem.model;

import java.util.Date;
import java.util.List;

import com.employemanagementsystem.allenum.Department;
import com.employemanagementsystem.allenum.Gender;
import com.employemanagementsystem.allenum.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Integer employeeID;

    @Column(name = "employee_firstname", unique = true, length = 128)
    private String firstName;

    @Column(name = "employee_lastname", length = 128)
    private String lastName;

    @Column(name = "employee_dateofbirth")
    private Date dateofbirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "employee_gender")
    private Gender gender;

    @Column(name = "employee_mobilenumber", unique = true)
    private Integer mobileNumber;

    @Column(name = "employee_email")
    private String email;

    @Column(name = "employee_address")
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "employee_department")
    private Department department;

    @Column(name = "employee_jobtitle")
    private String jobTitle;

    @Column(name = "employee_status")
    private Status status;

    @Column(name = "employee_dateofhire")
    private Date dateOfHire;

    @OneToMany(mappedBy = "employee")
    private List<Performance> performances;

    @OneToMany(mappedBy = "employee")
    private List<Attendance> attendances;

    @OneToOne(mappedBy = "employee")
    private Salary salary;
}
