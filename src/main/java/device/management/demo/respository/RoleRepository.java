package device.management.demo.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import device.management.demo.entity.Role;
import device.management.demo.entity.User;
import java.lang.String;;

public interface RoleRepository extends JpaRepository<Role, Long>{


	@Transactional
	@Modifying
	@Query(value = "insert into role values(0, 'Quyen User', 'USER')", nativeQuery = true)
    int addRole();
	
	@Transactional
	@Query(value = "select * from role where role_name = 'ADMIN' ", nativeQuery = true)
	List<Role> findAdmin();
	
	//Optional<Role> findByUserRolesUserEmail(String email);
	Optional<Role> findByRoleName(String rolename);

	Optional<Role> findByUserRolesUserEmail(String email);
	

}
