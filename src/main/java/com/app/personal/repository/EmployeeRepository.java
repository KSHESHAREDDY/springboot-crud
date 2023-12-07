package com.app.personal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.personal.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    @Query(value = "select * from employees e where e.employee_id  = 2 and e.first_name ='test'", nativeQuery = true)
    public Employee getEmployeeByFirstNameAndId();
}
