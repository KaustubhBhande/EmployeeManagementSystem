package com.employemanagementsystem.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.employemanagementsystem.model.Attendence;

@Repository
public interface AttendenceRepository extends JpaRepository<Attendence,Integer>{

}
