/**
 * 
 */
package com.waheed.spring.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author abdul
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String addEmployee(@RequestBody Employee employee) {
        return String.valueOf(employeeService.addEmployee(employee));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Employee getEmployee(@PathVariable("id") long employeeId) {
        return employeeService.getEmployee(employeeId);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteEmployee(@PathVariable("id") long employeeId) {
        Employee employee = new Employee();
        employee.setId(employeeId);
        employeeService.deleteEmployee(employee);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public void updateHost(@RequestBody Employee employee, @PathVariable("id") long employeeId) {
        employee.setId(employeeId);
        employeeService.updateEmployee(employee);
    }
}
