package device.management.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import device.management.demo.entity.Device;

public interface DeviceRepository extends JpaRepository<Device, Long>{

}
