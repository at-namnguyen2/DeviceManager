package device.management.demo.service;
import device.management.demo.entity.User;
import device.management.demo.entity.dto.UserDTO;

public interface UserService {
	
    User getUserByEmail(String email);
    User saveUser(User user);
    User editUser(User objUser);
    boolean checkDuplicateEmail(String email);
    User addUser(UserDTO userDTO);
    boolean activeUser(Long id);
}
