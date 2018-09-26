package device.management.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminUserController {
	
	@GetMapping("adduser")
	public String showUserProfile() {
		return "add-user";
	}
	@GetMapping("notify")
	public String showNotify() {
		return "notify";
	}
	
	@GetMapping("device")
	public String showDevice() {
		return "device";
	}
}
