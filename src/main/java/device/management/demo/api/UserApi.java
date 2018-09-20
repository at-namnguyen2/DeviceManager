package device.management.demo.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import device.management.demo.entity.dto.UserDTO;
import device.management.demo.entity.response.PhoneSA;
import device.management.demo.entity.response.UserResponse;
import device.management.demo.service.EmployeeService;
import device.management.demo.service.RequestService;
import device.management.demo.service.UserService;

@RestController
public class UserApi {
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	UserService userService;		
	
	@Autowired
	RequestService requestService;
	
	/**
   	* @summary return profile of user
   	* @date sep 12, 2018
   	* @author Nam.Nguyen2
   	* @param email
   	* @return user
   	**/
	@GetMapping(path = "/userapi/myprofile/{email}")
	public ResponseEntity<Object> ViewCurrentUser(@PathVariable("email") String email) {
	UserResponse user = userService.findUserByEmail(email);
	return new ResponseEntity<>(user, HttpStatus.OK);		
}
	
	/**
	* @summary edit profile of user
	* @date sep 13, 2018
	* @author Nam.Nguyen2
	* @param userdto
	* @return String message
   	**/
	@PostMapping(path = "/userapi/editmyprofile")
	public ResponseEntity<Object> EditCurrentUser(@RequestBody UserDTO userdto ) {
		System.out.println("hihi"+ userdto + "end");
		if (userdto == null) {
			return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);			
		}
		userService.editProfileUser(userdto);		
		return new ResponseEntity<>("Your update request is pending", HttpStatus.OK);
	}
	
	/**
	* @summary list SA contact: phone via rolename Admin
	* @date sep 13, 2018
	* @author Nam.Nguyen2
	* @param 
	* @return listPhone
   	**/
	@GetMapping(path = "userapi/sacontact")
	public ResponseEntity<Object> ShowSaContact() {
		System.out.println("test contact");
		List<PhoneSA> listPhone = userService.getSAContact();		
		return new ResponseEntity<>(listPhone, HttpStatus.OK);
	}
}
	
