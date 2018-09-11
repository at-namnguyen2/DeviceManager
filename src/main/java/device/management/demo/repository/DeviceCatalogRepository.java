package device.management.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import device.management.demo.entity.DeviceCatalog;

public interface DeviceCatalogRepository extends JpaRepository<DeviceCatalog, Long>{

}
