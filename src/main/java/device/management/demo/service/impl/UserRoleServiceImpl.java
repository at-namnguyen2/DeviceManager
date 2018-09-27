package device.management.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import device.management.demo.entity.UserRole;
import device.management.demo.respository.UserRoleRepository;
import device.management.demo.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	UserRoleRepository userRoleRepository;
	
	@Override
	public UserRole addUserRole(UserRole userrole) {
		// TODO Auto-generated method stub
		return userRoleRepository.save(userrole);
	}

}
