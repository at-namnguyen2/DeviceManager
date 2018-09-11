package device.management.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import device.management.demo.entity.BlockUser;

public interface BlockUserRepository extends JpaRepository<BlockUser, Long>{
    
	@Modifying
	@Query(value = "delete from block_user where id = ?1", nativeQuery = true)
	int deleteBlockUserById(Long id);

}
