package device.management.demo.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import device.management.demo.entity.BlockUser;
import device.management.demo.entity.User;
import device.management.demo.service.BlockUserService;
import device.management.demo.service.UserService;

@Component
public class SuccessLoginHandler implements AuthenticationSuccessHandler{
	
	@Autowired 
	private UserService userSevice;
	
	@Autowired
	private BlockUserService blockUserService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		Object principal = authentication.getPrincipal();
		String email = null;
		if (principal instanceof UserDetails) {
		     email = ((UserDetails) principal).getUsername();
		} else {
		     email = principal.toString();
		}
		
		User objUser = userSevice.getUserByEmail(email);
		System.out.println(objUser.getEmail());
		BlockUser blockUser = objUser.getBlockUser();
		if(blockUser != null) {
			blockUserService.deleteBlockUser(blockUser.getId());			
		}		
		response.sendRedirect("/home");
	}
}
