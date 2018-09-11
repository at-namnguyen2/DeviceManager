package device.management.demo.service.impl;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import device.management.demo.entity.Employee;

import device.management.demo.repository.EmployeeRepository;
import device.management.demo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Boolean existsByEmployee(long userId) {
		Optional<Employee> exist = employeeRepository.findById(userId);
		if(!exist.isPresent()) {
			return false;
		}
		return true;
		
	}

}
