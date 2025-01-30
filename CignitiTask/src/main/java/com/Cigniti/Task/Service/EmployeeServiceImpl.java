package com.Cigniti.Task.Service;


import com.Cigniti.Task.Entity.Employee;
import com.Cigniti.Task.Repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService{

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        logger.info("Fetching all employees");
        try {
            return employeeRepository.findAll();
        } catch (Exception e) {
            logger.error("Error fetching employees", e);
            throw new RuntimeException("Failed to fetch employees");
        }
    }

    @Override
    public Employee getEmployeeById(Long empId) {
        logger.info("Fetching employee with ID {}", empId);
        try {
            Optional<Employee> employee = employeeRepository.findById(empId);
            if (employee.isPresent()) {
                return employee.get();
            } else {
                logger.error("Employee with ID {} not found", empId);
                throw new RuntimeException("Employee not found");
            }
        } catch (Exception e) {
            logger.error("Error fetching employee", e);
            throw new RuntimeException("Failed to fetch employee");
        }
    }

    @Override
    public Employee createEmployee(Employee employee) {
        logger.info("Creating new employee: {}", employee.getName());
        try {
            return employeeRepository.save(employee);
        } catch (Exception e) {
            logger.error("Error creating employee", e);
            throw new RuntimeException("Failed to create employee");
        }
    }

    @Override
    public Employee updateEmployee(Long empId, Employee employee) {
        logger.info("Updating employee with ID {}", empId);
        try {
            if (employeeRepository.existsById(empId)) {
                employee.setEmpId(empId);
                return employeeRepository.save(employee);
            } else {
                logger.error("Employee with ID {} not found for update", empId);
                throw new RuntimeException("Employee not found for update");
            }
        } catch (Exception e) {
            logger.error("Error updating employee", e);
            throw new RuntimeException("Failed to update employee");
        }
    }

    @Override
    public void deleteEmployee(Long empId) {
        logger.info("Deleting employee with ID {}", empId);
        try {
            if (employeeRepository.existsById(empId)) {
                employeeRepository.deleteById(empId);
            } else {
                logger.error("Employee with ID {} not found for deletion", empId);
                throw new RuntimeException("Employee not found for deletion");
            }
        } catch (Exception e) {
            logger.error("Error deleting employee", e);
            throw new RuntimeException("Failed to delete employee");
        }
    }
}


