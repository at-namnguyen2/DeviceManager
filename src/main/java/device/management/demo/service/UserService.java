package device.management.demo.service;

import java.util.List;
import java.util.Optional;


import device.management.demo.entity.User;
import device.management.demo.entity.dto.UserDTO;
import device.management.demo.entity.response.PhoneSA;
import device.management.demo.entity.response.RequestResponse;
import device.management.demo.entity.response.UserResponse;

public interface UserService {
	

    /**
   	* @summary return UserResonse via email
   	* @date sep 12, 2018
   	* @author Nam.Nguyen2
   	* @param email
   	* @return User
   	**/

    User getUserByEmail(String email);
    
    /**
   	* @summary return UserResonse via email
   	* @date sep 12, 2018
   	* @author Nam.Nguyen2
   	* @param email
   	* @return UserResponse
   	**/
    UserResponse findUserByEmail(String email);
    
    /**
	* @summary edit profile of user
	* @date sep 13, 2018
	* @author Nam.Nguyen2
	* @param userdto
	* @return true
   	**/
    Boolean editProfileUser(UserDTO userdto);
    
    /**
	* @summary list SA contact: phone via rolename Admin
	* @date sep 13, 2018
	* @author Nam.Nguyen2
	* @param 
	* @return listPhone
   	**/
	List<PhoneSA> getSAContact();
	    
    /**
   	 * @summary return User
   	 * @date sep 13, 2018
   	 * @author Nam.Nguyen2
   	 * @param email
   	 * @return User
   	 */
    User updateUser(RequestResponse request);
    
    void saveUser(User user);
}
