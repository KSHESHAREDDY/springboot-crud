package com.app.personal.repository;

import com.app.personal.dto.EmployeeDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.personal.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    @Query(value = "select new com.app.personal.dto.EmployeeDto(e.id, e.firstName, e.lastName, e.email, e.phone, e.hireDate) from Employee e " +
            "where e.employeeId  = :id and e.firstName = :firstName")
    public EmployeeDto getEmployeeByFirstNameAndId(Long id, String firstName);
    @Query(value = "select new com.app.personal.dto.EmployeeDto(e.id, e.firstName, e.lastName, e.email, e.phone, e.hireDate, s.salaryAmount, s.effectiveDate) from Employee e join Salary s on e.employeeId = s.employee.employeeId where e.firstName = :firstName ")
    public EmployeeDto getAllEmployeeDetails(String firstName);
}
