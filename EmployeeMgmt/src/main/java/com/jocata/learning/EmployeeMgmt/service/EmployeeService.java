package com.jocata.learning.EmployeeMgmt.service;

import com.jocata.learning.EmployeeMgmt.model.ActionResponse;
import com.jocata.learning.EmployeeMgmt.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployees();
    String addEmployee(Employee employee);
    String deleteEmployee(Employee employee);
    String updateEmployee(Employee employee);

    }


