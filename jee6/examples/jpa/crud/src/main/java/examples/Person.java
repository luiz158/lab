package examples;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Cacheable(false)
public class Person {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	@Temporal(TemporalType.DATE)
	private Date birthday;
	
	public Person() {
	}
	
	public Person(String name, Date birthday) {
		this.name = name;
		this.birthday = birthday;
	}
	
	public Date getBirthday() {
		return birthday;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
