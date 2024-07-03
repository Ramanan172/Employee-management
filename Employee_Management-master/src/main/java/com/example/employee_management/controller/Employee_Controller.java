package com.example.employee_management.controller;

import com.example.employee_management.model.Employee_Model;
import com.example.employee_management.service.Employee_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class Employee_Controller {
    @Autowired
    private Employee_Service employee_service;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    //Display list of employees
    @GetMapping("/showEmployeeList")
    public String viewEmployeeList(Model model){
        return findPaginated(1, "firstName", "asc", model);
    }

    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model){
        //Create model attribute to bind a form data
        Employee_Model employee_model = new Employee_Model();
        model.addAttribute("employee", employee_model);
        return "new_employee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee_Model employee_model){
        //Save Employee to Database
        employee_service.saveEmployee(employee_model);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model){
        Employee_Model employee_model = employee_service.getEmployeeById(id);
        model.addAttribute("employee", employee_model);
        return "update_employee";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable (value = "id") long id){
        this.employee_service.deleteEmployeeById(id);
        return "redirect:/showEmployeeList";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model){
        int pageSize = 5;

        Page<Employee_Model> page = employee_service.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Employee_Model> listEmployees = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listEmployees", listEmployees);
        return "employee_list";
    }

}
