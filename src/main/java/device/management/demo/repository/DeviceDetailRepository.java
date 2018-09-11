package device.management.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import device.management.demo.entity.DeviceDetail;;

public interface DeviceDetailRepository extends JpaRepository<DeviceDetail, Long>{

}
