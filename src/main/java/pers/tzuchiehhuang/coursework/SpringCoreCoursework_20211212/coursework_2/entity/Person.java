package pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_2.entity;

import java.util.Date;
import java.util.Objects;

public class Person {
	private String name;
	private Integer age;
	private Date birth;

	public Person() {

	}

	public Person(String name, Integer age, Date birth) {
		super();
		this.name = name;
		this.age = age;
		this.birth = birth;
	}
	
	public Person(String name, Date birth) {
		super();
		this.name = name;
		this.birth = birth;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, birth, name);
	}

	/*
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(age, other.age) && Objects.equals(birth, other.birth) && Objects.equals(name, other.name);
	}
	*/

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", birth=" + birth + "]";
	}

}
