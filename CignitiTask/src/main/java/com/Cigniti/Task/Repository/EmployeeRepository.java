package com.Cigniti.Task.Repository;

import com.Cigniti.Task.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}