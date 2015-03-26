package com.cmsc128.stadtra.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

//Base class to be implemented by each app entity class
@MappedSuperclass			
public class AbstractEntity implements Serializable {
	
	protected Long id;

	@Id
	//let the persistence provider determine the best strategy 
	//for autogenerating primary keys.
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Transient
	@JsonIgnore
	public String getClassName() {
		String name = this.getClass().getName(); 
		
		return name.substring(name.lastIndexOf(".")+1);
	}
	
	//Over ride equals() and hashCode() to consider entities having
	//the same id as equal or the same entity.
	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}

		if (this.id == null || obj == null || !(this.getClass().equals(obj.getClass()))) {
			return false;
		}

		AbstractEntity that = (AbstractEntity) obj;

		return this.id.equals(that.getId());
	}

	@Override
	public int hashCode() {
		return id == null ? 0 : id.hashCode();
	}
}
