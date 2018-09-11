//package device.management.demo.api;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import device.management.demo.entity.DeviceDetail;
//import device.management.demo.entity.Device_Deliver_Receive;
//import device.management.demo.entity.Employee;
//import device.management.demo.repository.Device_Deliver_ReceiveRepository;
//import device.management.demo.repository.EmployeeRepository;
//import device.management.demo.service.Device_Deliver_ReceiveService;
//import device.management.demo.service.EmployeeService;
//
////hien thi tat ca cac thiet bi cua 1 user...
//@RestController
//@RequestMapping("/api/employee")
//public class DeviceApi {
//	@Autowired
//	//private Device_Deliver_ReceiveService device_deliver_receive_service;
//	private Device_Deliver_ReceiveRepository device_deliver_receive_repository;
//	@Autowired
//	private EmployeeRepository employeeRepository;
//	@Autowired
//	private EmployeeService employeeService;
////	@GetMapping("/{id}/devices")
////	public ResponseEntity<Object> viewDeviceDetailOfUser(@PathVariable("id") long id) {
////		if (device_deliver_receive_service.existsByRole(id)) {
////			return new ResponseEntity<>("Role Not Found id: " + id,HttpStatus.NOT_FOUND);
////		}
////		List<GroupRole> list = roleGroupService.findAllGroupByRole(id);
////		List<Group> listGroup = roleGroupService.convertGroupRoleToGroup(list);
////		if (list.isEmpty()) {
////			return new ResponseEntity<>("no role", HttpStatus.NOT_FOUND);
////		}
////		return new ResponseEntity<>(listGroup, HttpStatus.OK);
////	}
//	
//	@GetMapping("/{id}/devices")
//	public ResponseEntity<Object> viewDeviceDetailOfUser(@PathVariable("id") long id) {
//		if(!employeeService.existsByEmployee(id)) {
//			return new ResponseEntity<>("Employee Not Found id: " + id,HttpStatus.NOT_FOUND);
//		}
//		Optional<Employee> employee = employeeRepository.findById(id);
//		//Long id1 = employee.get().getId();
//		//return new ResponseEntity<>(employee.get()  ,HttpStatus.OK);
//		//Optional<Employee> employee = employeeRepository.findById(id);
//		List<Device_Deliver_Receive> obj = device_deliver_receive_repository.findByEmployee(employee.get());
//		//Device_Deliver_Receive obj2 = new Device_Deliver_Receive();
//		//obj2.setEmployee(employee.get());
//		//System.out.println(employee.get().getId());
//		//DeviceDetail deviceDetail = 
//		
//		//deviceDetail.setDeviceDeliverReceive(deviceDeliverReceive);
//		//obj.setEmployee(employee.get());
//		//return new ResponseEntity<>(obj2 ,HttpStatus.OK);
//		//obj.setEmployee(employee.get());
//		//List<Device_Deliver_Receive> list = device_deliver_receive_reposi
//		
//		//obj.setEmployee(employee.get());
//		//List<DeviceDetail> obj1 = new ArrayList<DeviceDetail>();
//		
//		//List<Device_Deliver_Receive> device_Deliver_Receive_list = device_deliver_receive_repository.findByEmployee();
//		//for(Device_Deliver_Receive Device_Deliver_Receive: device_Deliver_Receive_list) {
//			//DeviceDetail deviceDetail = Device_Deliver_Receive.getDeviceDetail();
//			//Long device_Detail_id = deviceDetail.getId();
//			//obj1.add(deviceDetail);
//			
//		//}
//		//if(list.size() > 0) {
//			//return new ResponseEntity<Object>(employee.get(), HttpStatus.NOT_FOUND);
//		//}
//		//return new ResponseEntity<Object>("khong co danh sach nao", HttpStatus.NOT_FOUND);
//		//List<Device_Deliver_Receive> list = device_deliver_receive_repository.findByEmployee(employee.get());
//		List<DeviceDetail> deviceDetail = new ArrayList<DeviceDetail>();
//		for(Device_Deliver_Receive obj1: obj) {
//			deviceDetail.add(obj1.getDeviceDetail());
//		}
//		return new ResponseEntity<Object>(deviceDetail, HttpStatus.OK);	
//	}
//}
