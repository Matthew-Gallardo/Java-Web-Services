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
@Table(name = "boat")
public class Boat {
	private Integer id;
	private String name;
	private String colour;
	@XmlTransient
	@JsonbTransient
	@JsonIgnore
	private Set<Reservation> reservations;
 
	public Boat() {
	}
 
	public Boat(String name, String colour) {
		this.name = name;
		this.colour = colour;
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
 
	@Column(name = "colour", nullable = false)
	public String getColour() {
		return colour;
	}
 
	public void setColour(String colour) {
		this.colour = colour;
	}
 
	@OneToMany(mappedBy = "boat", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public Set<Reservation> getReservations() {
		return reservations;
	}
 
	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}

	@Override
	public String toString() {
		return String.format("Boat [id=%s, name=%s, colour=%s]", id, name, colour);
	}
	
}