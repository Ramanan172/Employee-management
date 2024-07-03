package com.example.employee_management.service;

import com.example.employee_management.model.Employee_Model;
import org.springframework.data.domain.Page;

import java.util.List;

public interface Employee_Service {
    List<Employee_Model> getAllEmployees();
    void saveEmployee(Employee_Model employee_model);
    Employee_Model getEmployeeById(long id);
    void deleteEmployeeById(long id);
    Page<Employee_Model> findPaginated(int pageNo, int pageSize, String sortField, String sortDir);
}
