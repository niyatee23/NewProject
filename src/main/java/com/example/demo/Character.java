package com.example.demo;

public class Character {
	private String name;
	private int max_power;
	public Character(String name, int max_power) {
		super();
		this.name = name;
		this.max_power = max_power;
	}
	//hi
	//hello
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMax_power() {
		return max_power;
	}
	public void setMax_power(int max_power) {
		this.max_power = max_power;
	}
	@Override
	public String toString() {
		return "Character [name=" + name + ", max_power=" + max_power + "]";
	}
	

}
