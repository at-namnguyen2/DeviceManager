package device.management.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import device.management.demo.entity.DeviceDetail;
import device.management.demo.entity.Device;
import java.util.List;
import java.util.Optional;;

public interface DeviceDetailRepository extends JpaRepository<DeviceDetail, Long> {
	List<DeviceDetail> findByDevice(Device device);

//	List<DeviceDetail> findByDeviceNameContainingAndDeviceDeviceCatalogNameContaining(String name, String catalog);
	List<DeviceDetail> findByWorkingAndStatusAndDeviceNameContainingAndDeviceDeviceCatalogNameContaining(Boolean working,
			long status, String name, String catalog);
	
	DeviceDetail findByProductId(String productId);
}
