package device.management.demo.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import  device.management.demo.entity.UserRole;


public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
	List<UserRole> findByUserId(Long userId);
}
