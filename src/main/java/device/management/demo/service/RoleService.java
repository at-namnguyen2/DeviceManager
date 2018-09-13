package device.management.demo.service;

import device.management.demo.entity.Role;

public interface RoleService {

	
	/**
	 * @summary find Role base roleName of role
	 * @date Sep 12, 2018
	 * @author namnguyen2
	 * @param roleName
	 * @return Role
	 */
	Role findByRoleName(String rolename);
}
