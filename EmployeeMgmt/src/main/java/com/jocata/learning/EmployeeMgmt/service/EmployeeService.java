package com.jocata.learning.EmployeeMgmt.service;

import com.jocata.learning.EmployeeMgmt.model.ActionResponse;
import com.jocata.learning.EmployeeMgmt.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> GetEmployees();
    String AddEmployee(Employee employee);
    ActionResponse deleteEmployee(Employee employee);
    ActionResponse updateEmployee(Employee employee);

    }


