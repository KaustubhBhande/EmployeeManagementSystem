package com.employemanagementsystem.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employemanagementsystem.model.Salary;

@Repository
public interface SalaryRepository extends JpaRepository<Salary,Integer> {

}
