package device.management.demo.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import device.management.demo.entity.DeviceDetail;
import device.management.demo.entity.Device;
import java.util.List;
import java.util.Optional;;

public interface DeviceDetailRepository extends JpaRepository<DeviceDetail, Long> {
	
	/**
	 * @summary return list devicedetails
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  Device
	 * @return List<DeviceDetail>
	 **/
	List<DeviceDetail> findByDevice(Device device);

	/**
	 * @summary return device detail via id
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  String productId
	 * @return DeviceDetail
	 **/
	DeviceDetail findByProductId(String productId);
	
	/**
	 * @summary filter devicedetails not used and normal for allocation
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  working, status, name, catalog
	 * @return List<DeviceDetail>
	 **/
	List<DeviceDetail> findByWorkingAndStatusAndDeviceNameContainingAndDeviceDeviceCatalogNameContaining(Boolean working,
			long status, String name, String catalog);
	
}
