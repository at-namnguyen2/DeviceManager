package device.management.demo.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import device.management.demo.entity.DeviceDetail;
import device.management.demo.entity.Device_Deliver_Receive;
import device.management.demo.entity.Employee;
import device.management.demo.entity.dto.EmpDeviceDTO;
import device.management.demo.entity.response.EmpDeviceResponse;
import device.management.demo.repository.DeviceDetailRepository;
import device.management.demo.repository.Device_Deliver_ReceiveRepository;
import device.management.demo.service.Device_Deliver_ReceiveService;
import device.management.demo.util.detailConst;

@Service
public class Device_Deliver_ReceiveServiceImpl implements Device_Deliver_ReceiveService {

	@Autowired
	Device_Deliver_ReceiveRepository device_Deliver_ReceiveRepository;

	@Autowired
	DeviceDetailRepository deviceDetailRepository;

	/**
	 * @summary filter record via employee
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param team,name,email
	 * @return listDevDeRe
	 **/
	@Override
	public List<Device_Deliver_Receive> filterDevDeRe(String team, String name, String email) {
		// TODO Auto-generated method stub
		return device_Deliver_ReceiveRepository
				.findByEmployeeTeamContainingAndEmployeeEmployeeNameContainingAndEmployeeUserEmailContaining(team, name,
						email);
	}

	/**
	 * @summary add new record allocation device
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  EmpDeviceDTO empdev
	 * @return Device_Deliver_Receive obj
	 **/
	@Override
	public Device_Deliver_Receive addDevDeRe(EmpDeviceDTO empdev) {
		Employee e = new Employee();
		e.setId(empdev.getEmployeeId());
		DeviceDetail dd = new DeviceDetail();
		dd.setId(empdev.getDetailId());
		Device_Deliver_Receive ddr = new Device_Deliver_Receive();
		ddr.setDateDeliverReceive(empdev.getDateDeliverReceive());
		ddr.setEmployee(e);
		ddr.setDeviceDetail(dd);
		return device_Deliver_ReceiveRepository.save(ddr);
	}
	/**
	 * @summary del a record allocation device
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  Long id
	 * @return void
	 **/
	@Override
	public void delDevDeRe(Long id) {
		device_Deliver_ReceiveRepository.deleteById(id);
	}

	/**
	 * @summary filter record aloction
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  
	 * @return List<EmpDeviceResponse>
	 **/
	@Override
	public List<EmpDeviceResponse> getDevAllocation() {
		List<Device_Deliver_Receive> lddr = device_Deliver_ReceiveRepository
				.findTop10ByDateReturnNullOrderByIdDesc();
		List<EmpDeviceResponse> ledr = new ArrayList<>();
		for (Device_Deliver_Receive ddr : lddr) {
			ledr.add(convertToEmpDevRes(ddr));
		}
		return ledr;
	}

	/**
	 * @summary filter record return
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  
	 * @return List<EmpDeviceResponse> ledr
	 **/	
	@Override
	public List<EmpDeviceResponse> getDevHistory() {
		List<Device_Deliver_Receive> lddr = device_Deliver_ReceiveRepository
				.findTop50ByDateReturnNotNullOrderByIdDesc();
		List<EmpDeviceResponse> ledr = new ArrayList<>();
		for (Device_Deliver_Receive ddr : lddr) {
			ledr.add(convertToEmpDevRes(ddr));
		}
		return ledr;
	}

	/**
	 * @summary set record to return
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  
	 * @return
	 **/
	@Override
	public EmpDeviceResponse setReturn(EmpDeviceResponse edr) {
		Optional<Device_Deliver_Receive> ddr= device_Deliver_ReceiveRepository.findById(edr.getId());
		if(ddr.isPresent()) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			ddr.get().setDateReturn(date);	
			device_Deliver_ReceiveRepository.save(ddr.get());
			DeviceDetail dd = deviceDetailRepository.findByProductId(edr.getProductId());
			dd.setWorking(detailConst.NOTUSED);
			deviceDetailRepository.save(dd);
			return null;
		}
		return null;
	}

	/**
	 * @summary convert from Device_Deliver_Receive to EmpDeviceResponse
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  Device_Deliver_Receive ddr
	 * @return EmpDeviceResponse edr
	 **/
	public EmpDeviceResponse convertToEmpDevRes(Device_Deliver_Receive ddr) {
		EmpDeviceResponse edr = new EmpDeviceResponse();
		edr.setId(ddr.getId());
		edr.setAvatar(ddr.getEmployee().getAvatar());
		edr.setEmployeeName(ddr.getEmployee().getEmployeeName());
		edr.setEmail(ddr.getEmployee().getUser().getEmail());
		edr.setDateDeliverReceive(ddr.getDateDeliverReceive());
		edr.setDateReturn(ddr.getDateReturn());
		edr.setDeviceName(ddr.getDeviceDetail().getDevice().getName());
		edr.setProductId(ddr.getDeviceDetail().getProductId());
		edr.setTeam(ddr.getEmployee().getTeam());
		return edr;
	}	
	@Override
	public Device_Deliver_Receive getDevDeRe(DeviceDetail deviceDetail) {
		// TODO Auto-generated method stub
		return device_Deliver_ReceiveRepository.findByDeviceDetail(deviceDetail);
	}

}
