package device.management.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import device.management.demo.entity.User;

public interface UserRepsository extends JpaRepository<User, Long> {
	
	
	Optional<User> findByEmail(String email);
	//kich hoat tai khoan nguoi dung...

	@Modifying
	@Query(value = "update user set enable = 1 where id=?1", nativeQuery = true)
	int activeUser(Long id);
}
