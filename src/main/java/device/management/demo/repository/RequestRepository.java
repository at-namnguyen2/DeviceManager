package device.management.demo.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import device.management.demo.entity.Request;
import device.management.demo.entity.User;
import device.management.demo.repository.extenstion.RequestRepositoryExtenstion;

@Repository
@Transactional
public interface RequestRepository extends JpaRepository<Request, Long> , RequestRepositoryExtenstion{

	/**
   	* @summary return list requests of user
   	* @date sep 12, 2018
   	* @author Nam.Nguyen2
   	* @param user
   	* @return listrequest
   	**/	
	List<Request> findTop10ByUserOrderByIdDesc(User user);
	
	/**
   	* @summary return list requests via status pending
   	* @date sep 12, 2018
   	* @author Nam.Nguyen2
   	* @param status
   	* @return listRequest
   	**/	
	Page<Request> findByStatus(String status, Pageable page);
	
	/**
   	* @summary return list requests via status not pending
   	* @date sep 12, 2018
   	* @author Nam.Nguyen2
   	* @param status
	 * @param page 
   	* @return listRequest
   	**/	
	Page<Request> findByStatusNotLike(String status, Pageable page);
	List<Request> findByUserAndTypeAndStatusOrderByUpdateDateDesc(User user, String type, String status);
	
	
//	@Query(value="select * from request q where curdate() = DATE_FORMAT(q.update_date, '%Y-%m-%d')",nativeQuery=true)
//	List<Request> findByUpdateDateBetween(Date dateStart,Date dateEnd);

	List<Request> findByStatusAndUpdateDateBetween(String Status, Date dateStart, Date dateEnd);
	
	Optional<Request> findByUserAndType(User user, String type);
}
