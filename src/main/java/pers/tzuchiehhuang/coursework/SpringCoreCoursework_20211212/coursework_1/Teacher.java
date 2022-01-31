package pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_1;

import java.util.List;
import java.util.Map;
import java.util.Set;


public class Teacher {
	private Integer id;
	private String name;
	private Set<Clazz> clazzs;
	private List<String> experties;
	private Map<String, Integer> salary;


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Clazz> getClazzs() {
		return clazzs;
	}
	public void setClazzs(Set<Clazz> clazzs) {
		this.clazzs = clazzs;
	}
	public List<String> getExperties() {
		return experties;
	}
	public void setExperties(List<String> experties) {
		this.experties = experties;
	}
	public Map<String, Integer> getSalary() {
		return salary;
	}
	public void setSalary(Map<String, Integer> salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", clazzs=" + clazzs + ", experties=" + experties + ", salary="
				+ salary + "]";
	}
	

}
