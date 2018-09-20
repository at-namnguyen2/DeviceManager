package device.management.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import device.management.demo.entity.User;
import java.lang.Boolean;

public interface UserRepsository extends JpaRepository<User, Long> {
	
	/**
   	* @summary find user via email
   	* @date sep 12, 2018
   	* @author Nam.Nguyen2
   	* @param email
   	* @return Optional<User>
   	**/
	Optional<User> findByEmail(String email);
	
    /**
	* @summary return list user via rolename
	* @date sep 13, 2018
	* @author Nam.Nguyen2
	* @param roleName
	* @return List<User>
   	**/	
	List<User> findByUserRolesRoleRoleName(String roleName);
	
	//kich hoat tai khoan nguoi dung...
	
	@Modifying
	@Query(value = "update user set enable = 1 where id=?1", nativeQuery = true)
	int activeUser(Long id);

	List<User> findByNonDelIsTrueAndActiveIsTrue();
}
