package device.management.demo.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import device.management.demo.entity.Request;
import device.management.demo.entity.User;
import device.management.demo.entity.response.RequestResponse;
import device.management.demo.repository.RequestRepository;
import device.management.demo.repository.UserRepsository;
import device.management.demo.service.RequestService;
import device.management.demo.util.requestconst;

@Service
public class RequestServiceImpl implements RequestService{

	@Autowired
	RequestRepository requestRepository;
	
	@Autowired
	UserRepsository userRepsository;
	
	/**
	* @summary add new Request
	* @date sep 13, 2018
	* @author Nam.Nguyen2
	* @param request
	* @return Request
   	**/
	@Override
	public RequestResponse createRequest(RequestResponse request) {
		Optional<User> optionalUser = userRepsository.findByEmail(request.getEmail());
		if (!optionalUser.isPresent()) {
			return null;
		}
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
//		System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
		Request addrequest = new Request(request.getContent(), request.getType(), request.getStatus(), date, optionalUser.get());
		Request r = requestRepository.save(addrequest);
		RequestResponse rr = ConverToRequestRes(r);
		return rr;
	}
	
	/**
   	* @summary return list requests of user
   	* @date sep 12, 2018
   	* @author Nam.Nguyen2
   	* @param user
   	* @return listrequest
   	**/	
	@Override
	public List<Request> listRequestbyuser(User user) {
		// TODO Auto-generated method stub
		return requestRepository.findTop10ByUserOrderByIdDesc(user);
	}
	
	/**
   	* @summary return list requests pending
   	* @date sep 12, 2018
   	* @author Nam.Nguyen2
   	* @param 
   	* @return listRequestr
   	**/	
	@Override
	public List<RequestResponse> listRequestpending() {
		List<Request> lr = requestRepository.findByStatus(requestconst.Pending);
		if (lr.size() == 0) {
			return null;
		}
		List<RequestResponse> listRequestr = new ArrayList<>();
		for (Request r : lr) {
			RequestResponse rr = ConverToRequestRes(r);
			listRequestr.add(rr);
		}
		return listRequestr;
	}
	
	/**
   	* @summary return requests not pending
   	* @date sep 12, 2018
   	* @author Nam.Nguyen2
   	* @param 
   	* @return listRequestr
   	**/	
	@Override
	public List<RequestResponse> listOldRequest() {
		List<Request> lr = requestRepository.findByStatusNotLike("Pending");
		if (lr.size() == 0) {
			return null;
		}
		List<RequestResponse> listRequestr = new ArrayList<>();
		for (Request r : lr) {
			RequestResponse rr = ConverToRequestRes(r);
			listRequestr.add(rr);
		}
		return listRequestr;
	}
	
	/**
   	* @summary edit status request
   	* @date sep 12, 2018
   	* @author Nam.Nguyen2
   	* @param 
   	* @return RequestResponse
   	**/
	@Override
	public RequestResponse editRequest(RequestResponse request) {
		Request editrequest = requestRepository.findById(request.getId()).get();
		editrequest.setStatus(request.getStatus());
		editrequest = requestRepository.save(editrequest);
		RequestResponse rr = ConverToRequestRes(editrequest);
		return rr;
	}
	
	public RequestResponse ConverToRequestRes(Request r) {
		RequestResponse rr = new RequestResponse();
		rr.setContent(r.getContent());
		rr.setEmail(r.getUser().getEmail());
		rr.setFullname(r.getUser().getEmployee().getEmployeeName());
		rr.setEmpId(r.getUser().getEmployee().getId());
		rr.setId(r.getId());
		rr.setStatus(r.getStatus());
		rr.setTeam(r.getUser().getEmployee().getTeam());
		rr.setType(r.getType());
		rr.setUpdatedate(r.getUpdateDate());
		return rr;
	}
	
	
	@Override
	public Request editRequest(Request request) {
		// TODO Auto-generated method stub
		return requestRepository.save(request);
	}
	@Override
	public Request getRequestbyid(long id) {
		// TODO Auto-generated method stub
		return requestRepository.findById(id).get();
	}

	@Override
	public List<Request> filterRequestByUser(User user) {
		List<Request> list = requestRepository.findByUserAndTypeAndStatusOrderByUpdateDateDesc(user, requestconst.Allocation, requestconst.Pending);
		System.out.println(list);
		return list;
	}


}
