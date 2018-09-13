package device.management.demo.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import device.management.demo.entity.User;
import device.management.demo.service.UserService;



@Controller
public class registrationApi {
	
	@Autowired
	UserService userService;
	
//	@Autowired
//	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@RequestMapping(value = "/registrations", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public ResponseEntity<Object> createNewUssser(@RequestBody @Valid User user, 
			BindingResult bindingResult) {
		System.out.println( "check"); 
		User user1 = new User();
		user1.setEmail("namnguyen2@gmail.com");
//		user1.setPassword(bCryptPasswordEncoder.encode("123456"));
		
		User i = userService.saveUser(user);
		if (i == null) {
			return new ResponseEntity<>("Created!", HttpStatus.CREATED);
		}
			return new ResponseEntity<>("User Existed!", HttpStatus.BAD_REQUEST);
		
	}
}
