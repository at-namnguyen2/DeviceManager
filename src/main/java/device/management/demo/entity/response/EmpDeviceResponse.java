package device.management.demo.entity.response;

import java.io.Serializable;
import java.util.Date;

public class EmpDeviceResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String employeeName;
	private String email;
	private String team;
	private String avatar;
	private String deviceName;
	private String productId;
	private Date dateDeliverReceive;
	private Date dateReturn;
	private String iconCatalog;
	public EmpDeviceResponse() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getIconCatalog() {
		return iconCatalog;
	}
	public void setIconCatalog(String iconCatalog) {
		this.iconCatalog = iconCatalog;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Date getDateDeliverReceive() {
		return dateDeliverReceive;
	}
	public void setDateDeliverReceive(Date dateDeliverReceive) {
		this.dateDeliverReceive = dateDeliverReceive;
	}
	public Date getDateReturn() {
		return dateReturn;
	}
	public void setDateReturn(Date dateReturn) {
		this.dateReturn = dateReturn;
	}
	
}
