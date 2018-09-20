package device.management.demo.service;

import java.util.List;

import device.management.demo.entity.Device;
import device.management.demo.entity.DeviceDetail;
import device.management.demo.entity.dto.DetailDTO;
import device.management.demo.entity.response.DetailResponse;

public interface DeviceDetailService {
	
	List<DeviceDetail> getDeviceDetails(Device device);
	DeviceDetail getDeviceDetailById(long id);
	DeviceDetail editDeviceDetails(DeviceDetail deviceDetail);
	DeviceDetail setWorking(Long id);
	Boolean DelDevDetails(List<DeviceDetail> deviceDetail);
	Boolean DelDevDetailById(long id);
	List<DetailResponse> filterDetails(Boolean working, long status, String name, String catalog);
	DeviceDetail addDeviceDetail(DetailDTO detailDTO);
}
