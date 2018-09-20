package device.management.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import device.management.demo.entity.DeviceCatalog;
import java.lang.String;
import java.util.List;
import java.util.Optional;

public interface DeviceCatalogRepository extends JpaRepository<DeviceCatalog, Long>{
	Optional<DeviceCatalog> findByName(String name);
}
