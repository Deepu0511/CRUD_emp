package com.jocata.learning.EmployeeMgmt.controller;

import com.jocata.learning.EmployeeMgmt.model.ActionResponse;
import com.jocata.learning.EmployeeMgmt.model.Employee;
import com.jocata.learning.EmployeeMgmt.service.Constantmy;
import com.jocata.learning.EmployeeMgmt.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/get-employee-details", method = RequestMethod.GET)

    @ResponseBody
    public List<Employee> GetEmployees(){
        return this.employeeService.GetEmployees();
    }

    @RequestMapping(value = "/add-employee", method = RequestMethod.POST)
    @ResponseBody
    public ActionResponse AddEmployee(@RequestBody Employee employee) {
        String result = null;
        ActionResponse actionResponse = new ActionResponse();
        try{
            String check = checkRequest(employee);
            if(check.equalsIgnoreCase("OK")){
                result = this.employeeService.AddEmployee(employee);
                if(result!=null && result.equalsIgnoreCase("Employee added successfully")){
                    actionResponse.setMessage(result);
                    actionResponse.setStatus("SUCCESS");
                    return actionResponse;
                }else{
                    actionResponse.setMessage(result);
                    actionResponse.setStatus(Constantmy.STATUS_FAILED);
                    return actionResponse;
                }
            }else {
                actionResponse.setMessage(result);
                actionResponse.setStatus("Bad request ");
                return actionResponse;
            }
        } catch (Exception e) {
            actionResponse.setMessage(String.valueOf(e));
            actionResponse.setStatus(Constantmy.STATUS_FAILED);
            return actionResponse;
        }
    }

    @RequestMapping(value = "/delete-employee", method = RequestMethod.POST)
    @ResponseBody
    public ActionResponse deleteEmployee(@RequestBody Employee employee) {
        return this.employeeService.deleteEmployee(employee);
    }
    @RequestMapping(value = "/update-employee", method = RequestMethod.POST)
    @ResponseBody
    public ActionResponse updateEmployee(@RequestBody Employee employee) {
        return this.employeeService.updateEmployee(employee);
    }

    private String checkRequest(Employee employee){
        if(employee!=null){
            if (ObjectUtils.isEmpty(employee.getId()) && ObjectUtils.isEmpty(employee.getName()) &&ObjectUtils.isEmpty(employee.getSalary()) ) {
                return " Employee Id , Name & salary is mandatory ";
            }
            if (ObjectUtils.isEmpty(employee.getId())) {
                return " Employee Id is mandatory ";
            }
            if (ObjectUtils.isEmpty(employee.getName())) {
                return " Employee Name is mandatory ";
            }
            if (ObjectUtils.isEmpty(employee.getSalary())) {
                return " Employee Salary is mandatory ";
            }
        }else{
            return "request Object is mandatory";
        }
        return "OK";
    }
}
