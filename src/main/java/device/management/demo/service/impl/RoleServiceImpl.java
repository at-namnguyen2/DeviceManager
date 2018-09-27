package device.management.demo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import device.management.demo.entity.Role;
import device.management.demo.respository.RoleRepository;
import device.management.demo.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	RoleRepository roleRepository;

	@Override
	public Role findByRoleName(String rolename) {
		Optional<Role> role = roleRepository.findByRoleName(rolename);
		return role.get();
	}
	
//	@Override
//	public Role findByRoleName(String rolename) {
//		// TODO Auto-generated method stub
//		return roleRepository.findByRoleName(rolename);
//	}

	
}
