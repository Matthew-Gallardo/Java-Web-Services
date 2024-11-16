package org.acumen.training.codes.model.data;
 
import java.util.Set;
 
import com.fasterxml.jackson.annotation.JsonIgnore;
 
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlTransient;
 
@Entity
@Table(name = "sailor")
public class Sailor {
	private Integer id;
	private String name;
	private Integer rating;
	private Integer age;
	@XmlTransient
	@JsonbTransient
	@JsonIgnore
	private Set<Reservation> reservations;
 
	public Sailor() {
	}
 
	@Id
	@Column(name = "id", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
 
	public void setId(Integer id) {
		this.id = id;
	}
 
	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
 
	@Column(name = "rating", nullable = false)
	public Integer getRating() {
		return rating;
	}
 
	public void setRating(Integer rating) {
		this.rating = rating;
	}
 
	@Column(name = "age", nullable = false)
	public Integer getAge() {
		return age;
	}
 
	public void setAge(Integer age) {
		this.age = age;
	}
 
	@OneToMany(mappedBy = "sailor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public Set<Reservation> getReservations() {
		return reservations;
	}
 
	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}

	@Override
	public String toString() {
		return String.format("Sailor [id=%s, name=%s, rating=%s, age=%s]", id, name, rating, age);
	}
	
	
}