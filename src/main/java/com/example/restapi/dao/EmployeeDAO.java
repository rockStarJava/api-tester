package com.example.restapi.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.restapi.model.Employee;

@Repository
public class EmployeeDAO 
{
    private static Map<Integer ,Employee> repo = new HashMap<>();
    
    static 
    {
       repo.put(1 ,(new Employee(1, "Lokesh", "Gupta", "howtodoinjava@gmail.com")));
       repo.put(2 ,(new Employee(2, "Alex", "Kolenchiskey", "abc@gmail.com")));
       repo.put(3 ,(new Employee(3, "David", "Kameron", "titanic@gmail.com")));
    }
    
    public Collection<Employee> getAllEmployees() 
    {
        return repo.values();
    }
    
    public void addEmployee(Employee employee) {
    	repo.put(employee.getId() ,employee);
    }
    
    public Employee getEmployeeByID(int id) {
        return repo.get(id);
    }
}
