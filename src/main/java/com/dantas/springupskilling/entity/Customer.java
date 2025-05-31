package com.dantas.springupskilling.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

	@Id
	private Long id;
	private String name;
	private String street;
	private String city;
	private String state;
	private Double creditLimit;
	
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", street=" + street + ", city=" + city + ", state=" + state
				+ ", creditLimit=" + creditLimit + "]";
	}
}
