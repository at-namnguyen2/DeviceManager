package device.management.demo.api;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import device.management.demo.entity.Device_Deliver_Receive;
import device.management.demo.entity.dto.EmpDeviceDTO;
import device.management.demo.entity.dto.filterDevDeReDTO;
import device.management.demo.entity.response.EmpDeviceResponse;
import device.management.demo.entity.response.RequestResponse;
import device.management.demo.service.DeviceDetailService;
import device.management.demo.service.Device_Deliver_ReceiveService;
import device.management.demo.service.RequestService;
import device.management.demo.util.requestconst;

@RestController
public class Device_Deliver_ReceiveApi {

	@Autowired
	Device_Deliver_ReceiveService device_Deliver_ReceiveService;
	
	@Autowired
	DeviceDetailService deviceDetailService;
	
	@Autowired
	RequestService requestService;
	
	/**
	 * @summary filter record via employee
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  team,name,email
	 * @return listDevDeRe
	 **/
	@PostMapping(path = "/filterdevdere")
	public ResponseEntity<Object> filterDevDeRe(@RequestBody filterDevDeReDTO filter) {
		if(filter.getTeam().isEmpty() && filter.getEmployeeName().isEmpty() && filter.getEmail().isEmpty()) {
			return new ResponseEntity<>("fill to search", HttpStatus.OK);
		}
		List<Device_Deliver_Receive> listDevDeRe = device_Deliver_ReceiveService.filterDevDeRe(filter.getTeam(),
				filter.getEmployeeName(), filter.getEmail());
		System.out.println("show"+ filter.getEmployeeName());
		return new ResponseEntity<>(listDevDeRe, HttpStatus.OK);
		
	}

	/**
	 * @summary filter record aloction
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  
	 * @return List<EmpDeviceResponse>
	 **/
	@GetMapping(path = "/getdevallo")
	public ResponseEntity<Object> getDevAllo(){
		
		return new ResponseEntity<>(device_Deliver_ReceiveService.getDevAllocation(), HttpStatus.OK);
	}
	
	/**
	 * @summary filter record return
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  
	 * @return List<EmpDeviceResponse>
	 **/	
	@GetMapping(path = "/getdevhistory")
	public ResponseEntity<Object> getDevHistory(){
		
		return new ResponseEntity<>(device_Deliver_ReceiveService.getDevHistory(), HttpStatus.OK);
	}
	
	/**
	 * @summary add new record allocation device
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  EmpDeviceDTO ddr
	 * @return String message
	 **/
	@PostMapping(path = "/adddevdere")
	public ResponseEntity<Object> addDevDeRe(@RequestBody EmpDeviceDTO ddr){
		System.out.println("show :"+ddr);
		Device_Deliver_Receive Res = device_Deliver_ReceiveService.addDevDeRe(ddr);
		if(Res.equals(null)) {
			new ResponseEntity<>("Allocation fail", HttpStatus.OK);
		}
		deviceDetailService.setWorking(ddr.getDetailId());
		Date date = new Date();
		RequestResponse rr = new RequestResponse();
		rr.setContent(requestconst.AllcationMesage);
		rr.setType(requestconst.Allocation);
		rr.setUpdatedate(date);
		rr.setStatus(requestconst.Approved);
		rr.setEmail(ddr.getEmail());
		rr = requestService.createRequest(rr);
		return new ResponseEntity<>("Alloction device success", HttpStatus.OK);
	}
	
	/**
	 * @summary del a record allocation device
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  Device_Deliver_Receive dl
	 * @return String message
	 **/
	@DeleteMapping(path = "/delallocation")
	public ResponseEntity<String> dellAllocation(@RequestBody Device_Deliver_Receive dl){
		device_Deliver_ReceiveService.delDevDeRe(dl.getId());
		return new ResponseEntity<>("Delete Success", HttpStatus.OK);
	}
	
	/**
	 * @summary set record to return
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  EmpDeviceResponse edr
	 * @return String message
	 **/
	@PostMapping(path = "/setreturn")
	public ResponseEntity<Object> setReturn(@RequestBody EmpDeviceResponse edr){
		device_Deliver_ReceiveService.setReturn(edr);
		return new ResponseEntity<>("Delete Success", HttpStatus.OK);
	}
}
