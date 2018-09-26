package device.management.demo.service;

import java.util.List;

import device.management.demo.entity.DeviceDetail;
import device.management.demo.entity.Device_Deliver_Receive;
import device.management.demo.entity.dto.EmpDeviceDTO;
import device.management.demo.entity.response.DetailResponse;
import device.management.demo.entity.response.EmpDeviceResponse;
import device.management.demo.entity.response.countResponse;

public interface Device_Deliver_ReceiveService {

	/**
	 * @summary filter record via employee
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param team,name,email
	 * @return listDevDeRe
	 **/
	List<DetailResponse> filterDevDeRe(String filter);
	
	/**
	 * @summary return list DetailResponse via email
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  email
	 * @return List<EmpDeviceResponse>
	 **/
	
	List<DetailResponse> getDevByMail(String email);
	
	/**
	 * @summary filter record aloction
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  
	 * @return List<EmpDeviceResponse>
	 **/
	List<EmpDeviceResponse> getDevAllocation();

	/**
	 * @summary filter record return
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  team,name,email
	 * @return List<EmpDeviceResponse>
	 **/
	List<EmpDeviceResponse> getDevHistory();	

	/**
	 * @summary add new record allocation device
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  EmpDeviceDTO empdev
	 * @return Device_Deliver_Receive obj
	 **/
	Device_Deliver_Receive addDevDeRe(EmpDeviceDTO empdev);
	
	/**
	 * @summary del a record allocation device
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  Long id
	 * @return void
	 **/
	void delDevDeRe(Long id);

	/**
	 * @summary set record to return
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  
	 * @return
	 **/
	EmpDeviceResponse setReturn(EmpDeviceResponse edr);
	
	/**
	 * @summary return device deliver receive
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  Device
	 * @return Device_Deliver_Receive
	 **/
	Device_Deliver_Receive getDevDeRe(DeviceDetail deviceDetail);
	
	countResponse countQuantity(String email);
}
