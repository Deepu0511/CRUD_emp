package com.jocata.learning.EmployeeMgmt.service;

import com.jocata.learning.EmployeeMgmt.model.ActionResponse;
import com.jocata.learning.EmployeeMgmt.model.Employee;
import com.jocata.learning.EmployeeMgmt.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    //@Autowired
    private EmployeeRepository employeeRepository;

    private List<Employee> employees = new ArrayList<Employee>();

     public List<Employee> GetEmployees(){
        return employees;
    }

    public String AddEmployee(Employee employee) {
        String response = null;
        try{
            if (this.employees.contains(employee)) {
                response  = "Employee " + employee.getId() + " already exists in employee database.";
                //System.out.println("Employee " + employee.getId() + " already exists in employee database.");
                return response;
            }else{
                this.employees.add(employee);
                response = "Employee added successfully";
                //System.out.println("Employee " + employee.getId() +"-"+ employee.getName() + " has been added to employee database.");
                //response.setMessage("Employee added successfully");
                return response;
            }

        }catch (Exception e){
            return String.valueOf(e);
        }
    }

    public ActionResponse deleteEmployee(Employee employee) {
        ActionResponse response = new ActionResponse();
        if (!this.employees.contains(employee)) {
            response.setMessage("Employee does not exist");
            response.setStatus("FAILURE");
            return response;
        }
        this.employees.remove(employee);
        response.setMessage("Employee deleted successfully");
        response.setStatus("SUCCESS");
        return response;
    }

    public ActionResponse updateEmployee(Employee employee) {
        ActionResponse response = new ActionResponse();
        if (!this.employees.contains(employee)) {
            response.setMessage("Employee does not exist");
            response.setStatus("FAILURE");
            return response;
        }
        for(Employee emp: employees) {
            if(employee.getId()==emp.getId()) {
                emp.setName(employee.getName());
                emp.setSalary(employee.getSalary());
            }
        }
        response.setMessage("Employee updated successfully");
        response.setStatus("SUCCESS");
        return response;
    }
}
