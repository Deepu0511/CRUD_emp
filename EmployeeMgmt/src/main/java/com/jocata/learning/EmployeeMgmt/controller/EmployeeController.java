package com.jocata.learning.EmployeeMgmt.controller;

import com.jocata.learning.EmployeeMgmt.exception.ApplicationException;
import com.jocata.learning.EmployeeMgmt.model.ActionResponse;
import com.jocata.learning.EmployeeMgmt.model.Employee;
import com.jocata.learning.EmployeeMgmt.service.Constantmy;
import com.jocata.learning.EmployeeMgmt.service.EmployeeService;
import javafx.application.Application;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private static final Logger logger = LogManager.getLogger(EmployeeController.class);
    @Autowired
    private EmployeeService employeeService;
    @RequestMapping(value = "/get-employee-details", method = RequestMethod.GET)
    public List<Employee> getEmployees(){
        return this.employeeService.getEmployees();
    }
    @RequestMapping(value = "/add-employee", method = RequestMethod.POST)
    public ActionResponse addEmployee(@RequestBody Employee employee) {
       logger.info("addEmployee() has started");
        String result = null;
        ActionResponse actionResponse = null;
        try{
            actionResponse=new ActionResponse();
            String check = checkRequest(employee);
            if(check.equalsIgnoreCase("OK")) {
                result = this.employeeService.addEmployee(employee);
                if (result != null && result.equalsIgnoreCase(Constantmy.STATUS_SUCCESS)) {
                    actionResponse.setMessage("Employee added successfully");
                    actionResponse.setStatus(Constantmy.STATUS_SUCCESS);

                    return actionResponse;
                } else if (result != null && result.equalsIgnoreCase(Constantmy.STATUS_FAILED)) {
                    actionResponse.setMessage("Employee alreday exists");
                    actionResponse.setStatus(Constantmy.STATUS_FAILED);
                    return actionResponse;
                }
            }else {
                actionResponse.setMessage("REQUEST CANNOT BE EMPTY");
                actionResponse.setStatus(Constantmy.BAD_REQUEST);
                return actionResponse;
            }
        } catch (ApplicationException e) {
            actionResponse.setMessage(e.getMessage());
            actionResponse.setStatus(Constantmy.STATUS_FAILED);
            return actionResponse;
        }
        catch (Exception e) {
            logger.error("Exception occured in addEmployee()",e);
        }
        return null;
    }
    @RequestMapping(value = "/delete-employee", method = RequestMethod.POST)
    public ActionResponse deleteEmployee(@RequestBody Employee employee) {
        logger.info("deleteEmployee() has started");
        String result = null;
        ActionResponse actionResponse = null;
        try{
            actionResponse=new ActionResponse();
            String check = checkRequest(employee);
            if(check.equalsIgnoreCase("OK")) {
                result = this.employeeService.deleteEmployee(employee);
                if (result != null && result.equalsIgnoreCase(Constantmy.STATUS_SUCCESS)) {
                    actionResponse.setMessage("Employee deleted successfully");
                    actionResponse.setStatus(Constantmy.STATUS_SUCCESS);
                    return actionResponse;
                } else if (result != null && result.equalsIgnoreCase(Constantmy.STATUS_FAILED)) {
                    actionResponse.setMessage("Employee doesn't exist");
                    actionResponse.setStatus(Constantmy.STATUS_FAILED);
                    return actionResponse;
                }
            }else {
                actionResponse.setMessage("REQUEST CANNOT BE EMPTY");
                actionResponse.setStatus(Constantmy.BAD_REQUEST);
                return actionResponse;
            }
        } catch (ApplicationException e) {
            actionResponse.setMessage(e.getMessage());
            actionResponse.setStatus(Constantmy.STATUS_FAILED);
            return actionResponse;
        }
        catch (Exception e) {
            logger.error("Exception occured in deleteEmployee()",e);
        }
        return null;
    }
    @RequestMapping(value = "/update-employee", method = RequestMethod.POST)
    public ActionResponse updateEmployee(@RequestBody Employee employee) {
        logger.info("updateEmployee() has started");
        String result = null;
        ActionResponse actionResponse = null;
        try{
            actionResponse=new ActionResponse();
            String check = checkRequest(employee);
            if(check.equalsIgnoreCase("OK")) {
                result = this.employeeService.updateEmployee(employee);
                if (result != null && result.equalsIgnoreCase(Constantmy.STATUS_SUCCESS)) {
                    actionResponse.setMessage("Employee updated successfully");
                    actionResponse.setStatus(Constantmy.STATUS_SUCCESS);
                    return actionResponse;
                } else if (result != null && result.equalsIgnoreCase(Constantmy.STATUS_FAILED)) {
                    actionResponse.setMessage("Employee not updated");
                    actionResponse.setStatus(Constantmy.STATUS_FAILED);
                    return actionResponse;
                }
            }else {
                actionResponse.setMessage("REQUEST CANNOT BE EMPTY");
                actionResponse.setStatus(Constantmy.BAD_REQUEST);
                return actionResponse;
            }
        } catch (ApplicationException e) {
            actionResponse.setMessage(e.getMessage());
            actionResponse.setStatus(Constantmy.STATUS_FAILED);
            return actionResponse;
        }
        catch (Exception e) {
            logger.error("Exception occured in updateEmployee()",e);
        }
        return null;
    }
    private String checkRequest(Employee employee)throws ApplicationException{
        if(employee!=null){
            if (ObjectUtils.isEmpty(employee.getId()) && ObjectUtils.isEmpty(employee.getName()) &&ObjectUtils.isEmpty(employee.getSalary()) ) {
                throw new ApplicationException(" Employee Id , Name & salary is mandatory ");
            }
            if (ObjectUtils.isEmpty(employee.getId())) {
                throw new ApplicationException(" Employee Id is mandatory ");
            }
            if (ObjectUtils.isEmpty(employee.getName())) {
                throw new ApplicationException(" Employee Name is mandatory ");
            }
            if (ObjectUtils.isEmpty(employee.getSalary())) {
                throw new ApplicationException(" Employee Salary is mandatory ");
            }
        }else{
            return "request Object is mandatory";
        }
        return "OK";
    }
}
