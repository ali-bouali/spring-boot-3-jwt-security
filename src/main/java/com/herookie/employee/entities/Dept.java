package com.herookie.employee.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "depts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dept implements Serializable {

	private static final long serialVersionUID = 661437749532475329L;

	@Id
	private long id;

}
