package device.management.demo.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import device.management.demo.entity.Request;
import device.management.demo.entity.User;


public interface RequestRepository extends JpaRepository<Request, Long>{

	/**
   	* @summary return list requests of user
   	* @date sep 12, 2018
   	* @author Nam.Nguyen2
   	* @param user
   	* @return listrequest
   	**/	
	List<Request> findByUser(User user);
	
	/**
   	* @summary return list requests via status pending
   	* @date sep 12, 2018
   	* @author Nam.Nguyen2
   	* @param status
   	* @return listRequest
   	**/	
	List<Request> findByStatus(String status);
	
	/**
   	* @summary return list requests via status not pending
   	* @date sep 12, 2018
   	* @author Nam.Nguyen2
   	* @param status
   	* @return listRequest
   	**/	
	List<Request> findByStatusNotLike(String status);

}