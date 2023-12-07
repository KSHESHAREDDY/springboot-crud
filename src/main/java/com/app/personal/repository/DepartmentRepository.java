package com.app.personal.repository;

import com.app.personal.model.Department;
import com.app.personal.model.Employee;
import com.app.personal.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
