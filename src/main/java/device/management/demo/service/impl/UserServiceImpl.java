package device.management.demo.service.impl;
import java.util.Optional;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import device.management.demo.entity.Employee;
import device.management.demo.entity.User;
import device.management.demo.repository.UserRepsository;
import device.management.demo.service.UserService;

import device.management.demo.entity.dto.UserDTO;

@Service
public class UserServiceImpl implements UserService {
    
	@Autowired
	private UserRepsository userRepository;
	@Override
	public User getUserByEmail(String email) {
		Optional<User> optionalUser = userRepository.findByEmail(email);
		if (!optionalUser.isPresent()) {
			return null;
		}
		return optionalUser.get();
	}
	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	@Override
	public User editUser(User objUser) {
		return userRepository.save(objUser);
	}
	@Override
	public boolean checkDuplicateEmail(String email) {
		Optional<User> optionalUser = userRepository.findByEmail(email);
		if (optionalUser.isPresent())
			return true;
		return false;
	}
	@Modifying
	@Override
	public User addUser(UserDTO userDTO) {
		User user = convertUserDtoToUser(userDTO);
		return userRepository.save(user);
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
	
	@Transactional
	@Override
	public boolean activeUser(Long id) {
		Optional<User> optional = userRepository.findById(id);
		if(!optional.isPresent()) {
			return false;
		}
		userRepository.activeUser(id);
		return true;
//	}
	}
	@Override
	public Optional<User> findUserByUserId(Long id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id);
	}
}
