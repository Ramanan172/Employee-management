package com.example.employee_management.repository;

import com.example.employee_management.model.Employee_Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Employee_Repository extends JpaRepository<Employee_Model, Long> {
}
