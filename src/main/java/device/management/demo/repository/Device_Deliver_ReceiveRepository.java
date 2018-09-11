package device.management.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import device.management.demo.entity.Device_Deliver_Receive;
import device.management.demo.entity.Employee;

public interface Device_Deliver_ReceiveRepository extends JpaRepository<Device_Deliver_Receive, Long> {

	List<Device_Deliver_Receive> findByEmployee(Employee employee);

	//List<Device_Deliver_Receive> findByEmployee(Employee employee);

	//List<Device_Deliver_Receive> findByEmployee();

	//List<Device_Deliver_Receive> findById();

	//List<Device_Deliver_Receive> findByEmployee();

    

}
