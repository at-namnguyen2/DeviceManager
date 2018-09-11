package device.management.demo.entity.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import device.management.demo.validation.Password;
import device.management.demo.validation.PasswordMatches;
import device.management.demo.validation.Phone;
import device.management.demo.validation.ValidEmail;

@PasswordMatches
public class UserDTO implements Serializable{
    @JsonIgnore
    private static final long serialVersionUID = 1L;
    @ValidEmail(message = "Email must not be blank and must valid")
    private String email;
    @NotBlank(message = "fullname must not blank")
    private String fullname;
    @Phone(message = "Phone must not be blank and must valid")
    private String phone;
    @NotNull(message = "Birthday must not be null")
    private Date birthDay;
    @NotBlank(message = "Address must not be blank")
    private String address;
    @NotNull(message = "Gender must not be null")
    private Boolean gender;
    @Password(message = "Password must not be valid, has at least one number, one special character, one uppercase character and has from 6 to 10 character")
    private String password;
    private String matchingPassword;
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Boolean getGender() {
		return gender;
	}
	public void setGender(Boolean gender) {
		this.gender = gender;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMatchingPassword() {
		return matchingPassword;
	}
	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}  
	public UserDTO() {
		super();
	}

	@Override
	public String toString() {
		return "UserDTO [email=" + email + ", fullname=" + fullname + ", phone=" + phone + ", birthDay=" + birthDay
				+ ", address=" + address + ", gender=" + gender + ", password=" + password + ", matchingPassword="
				+ matchingPassword + "]";
	}
	
	
}