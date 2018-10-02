package device.management.demo.service;

import java.util.List;
import java.util.Optional;

import device.management.demo.entity.Request;
import device.management.demo.entity.User;
import device.management.demo.entity.response.RequestResponse;

public interface RequestService {

	/**
	* @summary add new Request
	* @date sep 13, 2018
	* @author Nam.Nguyen2
	* @param request
	* @return Request
   	**/
	RequestResponse createRequest(RequestResponse request);
	
	/**
   	* @summary return list requests of user
   	* @date sep 12, 2018
   	* @author Nam.Nguyen2
   	* @param user
   	* @return listrequest
   	**/	
	List<Request> listRequestbyuser(User user);
	
	/**
   	* @summary return list requests pending
   	* @date sep 12, 2018
   	* @author Nam.Nguyen2
   	* @param 
   	* @return listRequestResponse
   	**/	
	List<RequestResponse> listRequestpending();
	
	/**
   	* @summary return list requests via status pending
   	* @date sep 12, 2018
   	* @author Nam.Nguyen2
   	* @param status
   	* @return listRequest
   	**/	
	List<RequestResponse> listOldRequest();
	
	/**
   	* @summary edit status request
   	* @date sep 12, 2018
   	* @author Nam.Nguyen2
   	* @param 
   	* @return RequestResponse
   	**/
	RequestResponse editRequest(RequestResponse request);
	
	Request getRequestbyid(long id);
	Request editRequest(Request request);
	List<Request> filterRequestByUser(User user);
}
