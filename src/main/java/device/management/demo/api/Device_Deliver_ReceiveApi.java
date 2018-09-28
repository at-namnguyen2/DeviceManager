package device.management.demo.api;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import device.management.demo.entity.Device_Deliver_Receive;
import device.management.demo.entity.dto.EmpDeviceDTO;
import device.management.demo.entity.dto.filterDevDeReDTO;
import device.management.demo.entity.response.DetailResponse;
import device.management.demo.entity.response.EmpDeviceResponse;
import device.management.demo.entity.response.RequestResponse;
import device.management.demo.entity.response.countResponse;
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
	@GetMapping(path = "/empdevapi/getuserdevdere")
	public ResponseEntity<Object> getUserDevDeRe(Principal p) {
		System.out.println("checklog");
		String emailprincipal = p.getName();
		List<DetailResponse> listDevDeRe = device_Deliver_ReceiveService.getDevByMail(emailprincipal);
		System.out.println("show"+ emailprincipal);
		return new ResponseEntity<>(listDevDeRe, HttpStatus.OK);
		
	}
	
	/**
	 * @summary filter record via employee
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  team,name,email
	 * @return listDevDeRe
	 **/
	@PostMapping(path = "/filterdevdere")
	public ResponseEntity<Object> filterDevDeRe(@RequestParam String filter) {
		if(filter.isEmpty()) {
			return new ResponseEntity<>("fill to search", HttpStatus.OK);
		}
		List<DetailResponse> listDevDeRe = device_Deliver_ReceiveService.filterDevDeRe(filter);
		System.out.println("show"+ filter);
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
		Device_Deliver_Receive Res = device_Deliver_ReceiveService.addDevDeRe(ddr);
		if(Res.equals(null)) {
			new ResponseEntity<>("Allocation fail", HttpStatus.OK);
		}
		deviceDetailService.setWorking(ddr.getDetailId());
		return new ResponseEntity<>("ADD SUCCESS", HttpStatus.OK);
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
	
	@GetMapping(path = "/empdevapi/countdevice")
	public ResponseEntity<Object> countQuantity(Principal p){
		String principal = p.getName();
	
		countResponse cr = device_Deliver_ReceiveService.countQuantity(principal);
		System.out.println("count1"+cr);
		return new ResponseEntity<>(cr, HttpStatus.OK);
	}
}
