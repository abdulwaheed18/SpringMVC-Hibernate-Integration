package com.waheed.spring.hibernate;

/**
 * 
 * @author abdul
 *
 */
public interface EmployeeService {
    
    public long addEmployee(Employee employee);

    public void deleteEmployee(Employee employee);
  
    public Employee getEmployee(long employeeId);
  
    public void updateEmployee(Employee e);

    public void display();
    
    public void display(long employeeId);
}
