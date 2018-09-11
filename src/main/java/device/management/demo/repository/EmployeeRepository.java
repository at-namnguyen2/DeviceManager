package device.management.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import device.management.demo.entity.Employee;;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
