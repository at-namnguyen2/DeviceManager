package device.management.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@GetMapping(path="/myprofile")
	public String MyProfile() {
		return "page-user";
	}
	
	@GetMapping("my-profile")
	public String showUserProfile() {
		return "user-home";
	}
}
