package com.app.personal.controller;

import com.app.personal.dto.EmployeeDTO;
import com.app.personal.dto.EmployeeRequestDTO;
import com.app.personal.exception.ResourceNotFoundException;
import com.app.personal.model.Employee;
import com.app.personal.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping("/welcome")
	public String welcome(){
		return "I'm Free To all";
	}

	@GetMapping("/employee/{id}/{firstName}")
	@PreAuthorize("hasRole('ADMIN')")
	public EmployeeDTO getEmployeeByIDAndFirstName(@PathVariable  Long id, @PathVariable String firstName) {
		EmployeeDTO employeeDTO = employeeRepository.getEmployeeByFirstNameAndId(id , firstName);
		return employeeDTO;
	}

	@GetMapping("/employee")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public EmployeeDTO getEmployeeByIDAndFirstNameFromBody(@RequestBody EmployeeRequestDTO employeeRequestDTO) {
		EmployeeDTO employeeDTO = employeeRepository.getEmployeeByFirstNameAndId(employeeRequestDTO.getId(), employeeRequestDTO.getFirstName());
		return employeeDTO;
	}

	// get all employees
	@GetMapping("/employees")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}

	// create employee rest api
	@PostMapping("/employees")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}

	// get employee by id rest api
	@GetMapping("/employees/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable String id) {
		Employee employee = employeeRepository.findById(Long.parseLong(id))
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
		return ResponseEntity.ok(employee);
	}

	// update employee rest api
	@PutMapping("/employees/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmail(employeeDetails.getEmail());
		Employee updatedEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}

	// delete employee rest api
	@DeleteMapping("/employees/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
