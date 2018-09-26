package device.management.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import device.management.demo.entity.DeviceDetail;
import device.management.demo.entity.Device_Deliver_Receive;
import device.management.demo.entity.Employee;
import java.util.Date;

public interface Device_Deliver_ReceiveRepository extends JpaRepository<Device_Deliver_Receive, Long> {

	/**
	 * @summary return device deliver receive return
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  Device
	 * @return Device_Deliver_Receive
	 **/
	Device_Deliver_Receive findByDeviceDetail(DeviceDetail deviceDetail);

	/**
	 * @summary filter record via employee
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param team,name,email
	 * @return List<Device_Deliver_Receive>
	 **/
	List<Device_Deliver_Receive> findByEmployeeTeamContainingOrEmployeeEmployeeNameContainingOrEmployeeUserEmailContaining(
			String team, String name, String email);
	
	/**
	 * @summary find detail allocation via email
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param email
	 * @return List<Device_Deliver_Receive>
	 **/
	List<Device_Deliver_Receive> findByEmployeeUserEmailAndDateReturnNull(String email);
//	findByEmployeeUserEmail
	Long countByEmployeeUserEmail(String email);
	Long countByEmployeeUserEmailAndDateReturnNull(String email);
	Device_Deliver_Receive findTop1ByEmployeeUserEmail(String email);
	/**
	 * @summary filter record aloction
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  
	 * @return List<Device_Deliver_Receive>
	 **/
	List<Device_Deliver_Receive> findTop10ByDateReturnNullOrderByIdDesc();
	
	/**
	 * @summary filter record return
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  
	 * @return List<Device_Deliver_Receive>
	 **/
	List<Device_Deliver_Receive> findTop50ByDateReturnNotNullOrderByIdDesc();
}
