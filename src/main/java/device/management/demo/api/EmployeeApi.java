package device.management.demo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import device.management.demo.entity.dto.filterDevDeReDTO;
import device.management.demo.entity.response.UserResponse;
import device.management.demo.service.EmployeeService;

@RestController
public class EmployeeApi {

	@Autowired
	EmployeeService employeeService;

	/**
   	* @summary filter employee
   	* @date sep 12, 2018
   	* @author Nam.Nguyen2
   	* @param email, name, team
   	* @return List<UserResponse> emp
   	**/
	@PostMapping(path = "/filteremployee")
	public ResponseEntity<Object> FilterEmployee(@RequestBody filterDevDeReDTO f) {
		if (f.getTeam().isEmpty() && f.getEmployeeName().isEmpty() && f.getEmail().isEmpty()) {
			return new ResponseEntity<>("fill to search", HttpStatus.OK);
		}
		List<UserResponse> emp = employeeService.listEmployeeByFilter(f.getEmployeeName(), f.getTeam(), f.getEmail());
		return new ResponseEntity<>(emp, HttpStatus.OK);
	}
}
