package device.management.demo.service;

import java.util.List;

import device.management.demo.entity.Employee;
import device.management.demo.entity.response.UserResponse;

public interface EmployeeService {

	/**
   	* @summary filter employee
   	* @date sep 12, 2018
   	* @author Nam.Nguyen2
   	* @param email, name, team
   	* @return List<UserResponse> emp
   	**/
	List<UserResponse> listEmployeeByFilter(String name, String team, String email);
}
