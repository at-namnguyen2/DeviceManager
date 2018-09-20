package device.management.demo.service.impl;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import device.management.demo.entity.Employee;
import device.management.demo.entity.Role;
import device.management.demo.entity.User;
import device.management.demo.entity.UserRole;
import device.management.demo.entity.response.UserResponse2;
import device.management.demo.service.PasswordService;
import device.management.demo.service.UserService;


@Service
public class PasswordServiceImpl implements PasswordService  {
	
	@Autowired
	UserService userService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	@Override
	public UserResponse2 viewCurrentUserResponse(Principal principal) {
		Role role = null;
		String email = principal.getName();
		User user = userService.getUserByEmail(email);
		Employee employee = user.getEmployee();
		
			List<UserRole> list = user.getUserRoles();
			for(UserRole userRole: list) {
				role = userRole.getRole();
				System.out.println(role.getRoleName());
			}
			//UserResponse2 userResponse1 = userService.
		
		
		//userRespone.addPropertiesFromUser(user);
		//return userResponse;
			return null;
	}

	@Override
	public User viewCurrentUsers(Principal principal) {
		User user = userService.getUserByEmail(principal.getName());
		return user;
	}

	@Override
	public Boolean checkDuplicatePasswordCurrent(String existingPassword, String dbPassword) {
		return passwordEncoder.matches(existingPassword, dbPassword);
	}

	@Override
	public Boolean checkDuplicateNewPasswords(String newPassword, String existingPassword) {
		return newPassword.equals(existingPassword);
	}

	@Override
	public Boolean checkDuplicateMatchingPassword(String newPassword, String newMatchingPassword) {
		return newPassword.equals(newMatchingPassword);
	}

	@Override
	public User saveNewPasswords(User user, String newPassword) {
		user.setPassword(passwordEncoder.encode(newPassword));
		userService.saveUser(user);
		return user;
	}

}
