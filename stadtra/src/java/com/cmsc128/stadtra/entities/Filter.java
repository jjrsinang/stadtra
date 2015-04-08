package com.cmsc128.stadtra.entities;

public class Filter {
	private Object property;
	private Object value;
	
	public Filter(){}

	public Object getProperty() {
		return property;
	}

	public void setProperty(Object property) {
		this.property = property;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
