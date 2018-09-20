package device.management.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import device.management.demo.entity.Device;
import device.management.demo.repository.DeviceRepository;
import device.management.demo.service.DeviceService;

@Service
public class DeviceImpl implements DeviceService{
	@Autowired
	DeviceRepository deviceRepository;
	

	@Override
	public Device getDeviceById(long id) {
		// TODO Auto-generated method stub
		return deviceRepository.findById(id).get();
	}
	
}
