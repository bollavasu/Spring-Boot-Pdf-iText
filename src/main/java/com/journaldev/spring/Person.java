package com.journaldev.spring;

import org.springframework.stereotype.Component;

@Component
public class Person {
	
	private String name;
	private String sal;
	private String age;
	private String address;
	private String phoneNumber;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSal() {
		return sal;
	}
	public void setSal(String sal) {
		this.sal = sal;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Override
	public String toString() {
		return "Person [name=" + name + ", sal=" + sal + ", age=" + age + ", address=" + address + ", phoneNumber="
				+ phoneNumber + "]";
	}

}

