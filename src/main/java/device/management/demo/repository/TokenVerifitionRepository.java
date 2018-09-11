package device.management.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import device.management.demo.entity.TokenVerifition;

public interface TokenVerifitionRepository extends JpaRepository<TokenVerifition, Long> {

	Optional<TokenVerifition> findByTokenCode(String token);
	///////////////////////////////////////////////////////
	@Modifying
	@Query(value = "delete   from token_verfication where id = ?1", nativeQuery = true)
	int  deleteTokenById(Long id);

}
