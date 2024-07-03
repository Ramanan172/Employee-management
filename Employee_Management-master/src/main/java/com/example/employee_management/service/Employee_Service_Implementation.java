package com.example.employee_management.service;

import com.example.employee_management.EmployeeManagementApplication;
import com.example.employee_management.model.Employee_Model;
import com.example.employee_management.repository.Employee_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Employee_Service_Implementation implements Employee_Service{

    @Autowired
    private Employee_Repository employee_repository;

    @Override
    public List<Employee_Model> getAllEmployees() {
        return employee_repository.findAll();
    }

    @Override
    public void saveEmployee(Employee_Model employee_model) {
        this.employee_repository.save(employee_model);
    }

    @Override
    public Employee_Model getEmployeeById(long id) {
        Optional<Employee_Model> optional = employee_repository.findById(id);
        Employee_Model employee_model = null;
        if(optional.isPresent()){
            employee_model = optional.get();
        }
        else {
            throw new RuntimeException("Employee Not Found for id ::" + id);
        }
        return employee_model;
    }

    @Override
    public void deleteEmployeeById(long id) {
        this.employee_repository.deleteById(id);
    }

    @Override
    public Page<Employee_Model> findPaginated(int pageNo, int pageSize, String sortField, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo-1, pageSize, sort);
        return this.employee_repository.findAll(pageable);
    }

}
