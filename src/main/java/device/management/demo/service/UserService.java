package device.management.demo.service;
import java.util.Optional;

import device.management.demo.entity.User;
import device.management.demo.entity.dto.UserDTO;

public interface UserService {
	
	/**
	 * @summary return User base email of User
	 * @date Aug 15, 2018
	 * @author Nam.Nguyen2
	 * @param email
	 * @return User
	 */
    User getUserByEmail(String email);
    
    User saveUser(User user);
    User editUser(User objUser);
    boolean checkDuplicateEmail(String email);
    User addUser(UserDTO userDTO);
    boolean activeUser(Long id);
    Optional<User> findUserByUserId(Long id);
}
