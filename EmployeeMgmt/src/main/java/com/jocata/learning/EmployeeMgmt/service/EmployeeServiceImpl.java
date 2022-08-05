package com.jocata.learning.EmployeeMgmt.service;

import com.jocata.learning.EmployeeMgmt.controller.EmployeeController;
import com.jocata.learning.EmployeeMgmt.model.ActionResponse;
import com.jocata.learning.EmployeeMgmt.model.Employee;
//import com.jocata.learning.EmployeeMgmt.repository.EmployeeRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger logger = LogManager.getLogger(EmployeeServiceImpl.class);

    //  @Autowired
    // private EmployeeRepository employeeRepository;

    private List<Employee> employees = new ArrayList<Employee>();

    public List<Employee> getEmployees() {
        return employees;
    }

    public String addEmployee(Employee employee) {
        logger.info("addEmployee() has started");
        String response = null;
        try {
            if (this.employees.contains(employee)) {
                response = Constantmy.STATUS_FAILED;
                //System.out.println("Employee " + employee.getId() + " already exists in employee database.");
                return response;
            } else {
                this.employees.add(employee);
                response = Constantmy.STATUS_SUCCESS;
                //System.out.println("Employee " + employee.getId() +"-"+ employee.getName() + " has been added to employee database.");
                //response.setMessage("Employee added successfully");
                return response;
            }

        } catch (Exception e) {
            logger.error("Exception occured in addEmployee()");
        }
        return null;
    }

    public String deleteEmployee(Employee employee) {
        logger.info("deleteEmployee() has started");
        String response = null;
        Employee emp1 = null;
        try {
            emp1 = employees.stream().filter((kk->kk.getId()== employee.getId())).findAny().orElse(null);
            if(emp1!=null){
                employees.remove(emp1);
                response = Constantmy.STATUS_SUCCESS;

            }else{
                response = Constantmy.STATUS_FAILED;
            }
            return response;
            /*if (!this.employees.contains(employee)) {
                response = Constantmy.STATUS_FAILED;
                return response;
            } else {
                this.employees.remove(employee);
                response = Constantmy.STATUS_SUCCESS;
                return response;
            }*/
        } catch (Exception e) {
            logger.error("Exception occured in deleteEmployee()");
        }
        return null;
    }


    public String updateEmployee(Employee employee) {
        logger.info("updateEmployee() has started");
        String response = null;
        Employee emp1 = null;
        try {
            emp1 = employees.stream().filter((kk->kk.getId()== employee.getId())).findAny().orElse(null);
            if(emp1!=null){
                emp1.setName(employee.getName());
                emp1.setSalary(employee.getSalary());
                response = Constantmy.STATUS_SUCCESS;

            }else{
                response = Constantmy.STATUS_FAILED;
            }
            return response;
        }
     catch (Exception e) {
        logger.error("Exception occured in updateEmployee()");
    }
    return null;
}
}




