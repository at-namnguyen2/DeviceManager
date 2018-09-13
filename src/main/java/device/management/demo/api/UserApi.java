package device.management.demo.api;

import java.security.Principal;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import device.management.demo.entity.Employee;
import device.management.demo.entity.Role;
import device.management.demo.entity.User;
import device.management.demo.entity.UserRole;
import device.management.demo.service.RoleService;
import device.management.demo.service.UserRoleService;
import device.management.demo.service.UserService;



@Controller
public class UserApi {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRoleService userRoleService;
	
	@Autowired
	RoleService roleService;
	
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@RequestMapping(value = {"/login" }, method = RequestMethod.GET)
	public String Login() {
		System.out.println("loginapi");
		return "login";
	}
	@GetMapping(path = "/userapi/myprofile/{email}")
	public ResponseEntity<Object> viewCurrentUser(@PathVariable("email") String email) {
//		String email = principal.getName();
	User user = userService.getUserByEmail(email);
	return new ResponseEntity<>(user, HttpStatus.OK);
}
	
	@RequestMapping(value = "/api/admin/adduser", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public ResponseEntity<Object> createNewUser() {
		System.out.println( "check"); 
		User user1 = new User();
		Employee employee1 = new Employee();
		employee1.setEmployeeName("Nguyen Phuong Nam");
		String password = "123456";
		employee1.setGender(true);
		
		user1.setEmail("namnguyen2@gmail.com");
		user1.setPassword(bCryptPasswordEncoder.encode(password));
		user1.setEnable(true);
		User i = userService.saveUser(user1);
		
		Role role = roleService.findByRoleName("USER");
		Optional<User> userobj = userService.findUserByUserId(i.getId());
		UserRole userrole = new UserRole(userobj.get(), role);
		userRoleService.addUserRole(userrole);
		return new ResponseEntity<>("User Existed!", HttpStatus.BAD_REQUEST);
		
	}
}
	
