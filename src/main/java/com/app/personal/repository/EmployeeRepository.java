package com.app.personal.repository;

import com.app.personal.dto.EmployeeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.personal.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    @Query(value = "select new com.app.personal.dto.EmployeeDTO(e.id, e.firstName, e.lastName, e.email, e.phone, e.hireDate) from Employee e " +
            "where e.employeeId  = :id and e.firstName = :firstName")
    public EmployeeDTO getEmployeeByFirstNameAndId(Long id, String firstName);
}
