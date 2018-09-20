package device.management.demo.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import device.management.demo.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	/**
   	* @summary filter employee containing
   	* @date sep 12, 2018
   	* @author Nam.Nguyen2
   	* @param email, name, team, nondel, active
   	* @return List<Employee>
   	**/
	List<Employee> findByEmployeeNameContainingAndTeamContainingAndUserEmailContainingAndUserNonDelAndUserActive(
			String name, String team, String email, Boolean nondel, Boolean active);
}
