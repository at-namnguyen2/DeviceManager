package device.management.demo.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import device.management.demo.entity.Employee;
import device.management.demo.entity.Request;
import device.management.demo.entity.User;
import device.management.demo.repository.RequestRepository;
import device.management.demo.repository.UserRepsository;
import device.management.demo.service.UserService;
import device.management.demo.util.requestconst;
import device.management.demo.entity.dto.UserDTO;
import device.management.demo.entity.response.PhoneSA;
import device.management.demo.entity.response.RequestResponse;
import device.management.demo.entity.response.UserResponse;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepsository userRepository;

	@Autowired
	private RequestRepository requestRepository;

	/**
	 * @summary return UserResonse via email
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param email
	 * @return User
	 **/
	@Override
	public User getUserByEmail(String email) {
		Optional<User> optionalUser = userRepository.findByEmail(email);
		if (!optionalUser.isPresent()) {
			return null;
		}
		return optionalUser.get();
	}

	/**
	 * @summary return UserResonse via email
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param email
	 * @return UserResponse
	 **/
	@Override
	public UserResponse findUserByEmail(String email) {
		Optional<User> optionalUser = userRepository.findByEmail(email);
		if (!optionalUser.isPresent()) {
			return null;
		}
		UserResponse ur = convertToUserResponse(optionalUser.get());
		return ur;
	}

	/**
	 * @summary convert from User entity to UserResponse entity
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param user
	 * @return UserResponse
	 */
	public UserResponse convertToUserResponse(User user) {
		UserResponse ur = new UserResponse();
		ur.setAddress(user.getEmployee().getAddress());
		ur.setAvatar(user.getEmployee().getAvatar());
		ur.setDateOfBirth(user.getEmployee().getDateOfBirth());
		ur.setEmail(user.getEmail());
		ur.setGender(user.getEmployee().getGender());
		ur.setId(user.getId());
		ur.setName(user.getEmployee().getEmployeeName());
		ur.setPhone(user.getEmployee().getPhone());
		ur.setTeam(user.getEmployee().getTeam());
		return ur;
	}

	/**
	 * @summary edit profile of user
	 * @date sep 13, 2018
	 * @author Nam.Nguyen2
	 * @param userdto
	 * @return true
	 **/
	@Override
	public Boolean editProfileUser(UserDTO userdto) {
		Date date = new Date();
		User user = getUserByEmail(userdto.getEmail());
		Request request = new Request("" + userdto, requestconst.Update_Info, requestconst.Pending, date, user);
		requestRepository.save(request);
		return true;
	}

	/**
	 * @summary list SA contact: phone via rolename Admin
	 * @date sep 13, 2018
	 * @author Nam.Nguyen2
	 * @param
	 * @return listPhone
	 **/
	@Override
	public List<PhoneSA> getSAContact() {
		List<PhoneSA> listPhone = new ArrayList<>();
		List<User> user = userRepository.findByUserRolesRoleRoleName("ADMIN");
		for (User user2 : user) {
			PhoneSA phone = new PhoneSA();
			phone.setPhone(user2.getEmployee().getPhone());
			System.out.println("testfor:" + phone.getPhone());
			listPhone.add(phone);
		}
		return listPhone;
	}

//	@Override
//	public List<User> getUserAdmin(String roleName) {
//		// TODO Auto-generated method stub
//		return userRepository.findByUserRolesRoleRoleName(roleName);
//	}

	/**
	 * @summary return User
	 * @date sep 13, 2018
	 * @author Nam.Nguyen2
	 * @param request
	 * @return User
	 */
	@Override
	public User updateUser(RequestResponse request) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			UserDTO userdto = mapper.readValue(request.getContent(), UserDTO.class);
			Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
			if (!optionalUser.isPresent()) {
				return null;
			}
			User user = optionalUser.get();
			user.getEmployee().setEmployeeName(userdto.getFullname());
			user.getEmployee().setTeam(userdto.getTeam());
			user.getEmployee().setDateOfBirth(userdto.getBirthDay());
			user.getEmployee().setGender(userdto.getGender());
			user.getEmployee().setPhone(userdto.getPhone());
			user.getEmployee().setAddress(userdto.getAddress());
			user.getEmployee().setAvatar(userdto.getAvatar());
			System.out.println("show3:" + user);
			return userRepository.save(user);
		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public User convertUserDtoToUser(UserDTO userDTO) {
		User user = new User();
		Employee employee = new Employee(userDTO.getFullname(), userDTO.getPhone(), userDTO.getAddress(),
				userDTO.getGender(), userDTO.getBirthDay());
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		employee.setUser(user);
		user.setEmployee(employee);
		return user;
	}

	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		
	}
}
