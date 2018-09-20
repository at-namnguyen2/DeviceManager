package device.management.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import device.management.demo.entity.Role;;

public interface RoleRepository extends JpaRepository<Role, Long>{


	// @summary find by role
	Role findByRoleName(String roleName);

}
