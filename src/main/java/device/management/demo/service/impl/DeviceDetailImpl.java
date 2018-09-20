package device.management.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import device.management.demo.entity.Device;
import device.management.demo.entity.DeviceCatalog;
import device.management.demo.entity.DeviceDetail;
import device.management.demo.entity.dto.DetailDTO;
import device.management.demo.entity.response.DetailResponse;
import device.management.demo.repository.DeviceCatalogRepository;
import device.management.demo.repository.DeviceDetailRepository;
import device.management.demo.repository.DeviceRepository;
import device.management.demo.service.DeviceDetailService;
import device.management.demo.util.detailConst;

@Service
public class DeviceDetailImpl implements DeviceDetailService {

	@Autowired
	DeviceDetailRepository deviceDetailRepository;

	@Autowired
	DeviceCatalogRepository deviceCatalogRepository;

	@Autowired
	DeviceRepository deviceRepository;

	@Override
	public List<DeviceDetail> getDeviceDetails(Device device) {
		// TODO Auto-generated method stub
		return deviceDetailRepository.findByDevice(device);
	}

	@Override
	public DeviceDetail getDeviceDetailById(long id) {
		// TODO Auto-generated method stub
		return deviceDetailRepository.findById(id).get();
	}

	@Override
	public DeviceDetail editDeviceDetails(DeviceDetail deviceDetail) {
		DeviceDetail Detailobj = getDeviceDetailById(deviceDetail.getId());
		Detailobj.setDescriptionDeviceDetail(deviceDetail.getDescriptionDeviceDetail());
		Detailobj.setProductId(deviceDetail.getProductId());
		Detailobj.setStatus(deviceDetail.getStatus());
		Detailobj.setWorking(deviceDetail.getWorking());
		Detailobj.setUpdateDate(deviceDetail.getUpdateDate());
		return deviceDetailRepository.save(Detailobj);
	}

	@Override
	public DeviceDetail setWorking(Long id) {
		DeviceDetail Detailobj = getDeviceDetailById(id);
		Detailobj.setWorking(detailConst.WORKING);
		return deviceDetailRepository.save(Detailobj);
	}
	@Override
	public Boolean DelDevDetails(List<DeviceDetail> deviceDetail) {
		deviceDetailRepository.deleteAll(deviceDetail);
		return true;
	}

	@Override
	public Boolean DelDevDetailById(long id) {
		deviceDetailRepository.deleteById(id);
		return true;
	}

	@Override
	public List<DetailResponse> filterDetails(Boolean working, long status, String name, String catalog) {
		List<DeviceDetail> detail = deviceDetailRepository.findByWorkingAndStatusAndDeviceNameContainingAndDeviceDeviceCatalogNameContaining(
				working, status, name, catalog);
		List<DetailResponse> detailRes = new ArrayList<>();
		for (DeviceDetail d : detail) {
			DetailResponse res = ConverttoDetailRes(d);
			detailRes.add(res);
		}
		return detailRes;
	}

	@Override
	public DeviceDetail addDeviceDetail(DetailDTO d) {
		Optional<DeviceCatalog> catalogObj = deviceCatalogRepository.findByName(d.getCatalogname());
		DeviceCatalog catalog = new DeviceCatalog(d.getCatalogname());
		if (catalogObj.isPresent()) {
			catalog = catalogObj.get();
		} else {
			catalog = deviceCatalogRepository.save(catalog);
		}
		Optional<Device> deviceObj = deviceRepository.findByName(d.getDevicename());
		long quantity = deviceDetailRepository.count() + 1;
		Device device = new Device(catalog, d.getDevicename(), quantity, d.getPrice(), d.getDecription());
		if (deviceObj.isPresent()) {
			device = deviceObj.get();
		} else {
			device = deviceRepository.save(device);
		}

		DeviceDetail deviceDetail = new DeviceDetail(device, d.getProductid(), d.getStatus(), d.getUpdatedate(),
				detailConst.NOTUSED);
		return deviceDetailRepository.save(deviceDetail);
	}

	public DetailResponse ConverttoDetailRes(DeviceDetail d){
		DetailResponse res = new DetailResponse();
		res.setId(d.getId());
		res.setDecription(d.getDescriptionDeviceDetail());
		res.setCatalogname(d.getDevice().getDeviceCatalog().getName());
		res.setDevicename(d.getDevice().getName());
		res.setPrice(d.getDevice().getPrice());
		res.setProductid(d.getProductId());
		res.setUpdatedate(d.getUpdateDate());
		return res;
	}


}
