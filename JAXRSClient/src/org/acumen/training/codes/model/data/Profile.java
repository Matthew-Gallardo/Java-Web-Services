package org.acumen.training.codes.model.data;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Profile {
	private Integer id;
	private String fullname;
	
	//@JsonbTransient - for privacy
	private Double salary;
	
	@XmlElement
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@XmlElement
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	@XmlElement
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "Profile [id=" + id + ", fullname=" + fullname + ", salary=" + salary + "]";
	}
	
	

}
