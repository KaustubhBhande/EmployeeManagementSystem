package com.employemanagementsystem.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employemanagementsystem.model.Performance;

@Repository
public interface PerformanceRepository extends JpaRepository<Performance,Integer> {

}
