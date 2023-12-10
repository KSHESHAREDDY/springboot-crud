package com.app.personal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.app.personal.dto.EmployeeDTO;
import com.app.personal.dto.EmployeeRequestDTO;
import com.app.personal.model.UserInfo;
import com.app.personal.repository.EmployeeRepository;
import com.app.personal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.personal.exception.ResourceNotFoundException;
import com.app.personal.model.Employee;

@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/welcome")
	public UserInfo welcome(){
		UserInfo userInfo = userRepository.getUserByFirstNameAndId();
		return userInfo;
	}

	@GetMapping("/employee/{id}/{firstName}")
	public EmployeeDTO getEmployeeByIDAndFirstName(@PathVariable  Long id, @PathVariable String firstName) {
		EmployeeDTO employeeDTO = employeeRepository.getEmployeeByFirstNameAndId(id , firstName);
		return employeeDTO;
	}

	@GetMapping("/employee")
	public EmployeeDTO getEmployeeByIDAndFirstNameFromBody(@RequestBody EmployeeRequestDTO employeeRequestDTO) {
		EmployeeDTO employeeDTO = employeeRepository.getEmployeeByFirstNameAndId(employeeRequestDTO.getId(), employeeRequestDTO.getFirstName());
		return employeeDTO;
	}

	// get all employees
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}

	// create employee rest api
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}

	// get employee by id rest api
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable String id) {
		Employee employee = employeeRepository.findById(Long.parseLong(id))
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
		return ResponseEntity.ok(employee);
	}

	// update employee rest api
	@PutMapping("/employees/{id}")
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
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
