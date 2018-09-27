package device.management.demo.entity.response;

public class PhoneSA {
	private String name;

	private String phone;
	
	public PhoneSA() {
		super();
	}
	
	public PhoneSA(String phone, String name) {
		super();
		this.phone = phone;
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
