package org.acumen.training.codes.model.data;
 
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlTransient;
 
@Entity
@Table(name = "reservation")
public class Reservation {
	private Integer id;
	private Integer sid;
	private Integer bid;
	private Date date;
	
	@XmlTransient
	@JsonbTransient
	@JsonIgnore
	private Sailor sailor;
	@XmlTransient
	@JsonbTransient
	@JsonIgnore
	private Boat boat;
 
	public Reservation() {
	}
 
	public Reservation(Integer sid, Integer bid, Date date) {
		this.sid = sid;
		this.bid = bid;
		this.date = date;
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
 
	@Column(name = "sid", nullable = false)
	public Integer getSid() {
		return sid;
	}
 
	public void setSid(Integer sid) {
		this.sid = sid;
	}
 
	@Column(name = "bid", nullable = false)
	public Integer getBid() {
		return bid;
	}
 
	public void setBid(Integer bid) {
		this.bid = bid;
	}
 
	@Column(name = "date", nullable = false)
	public Date getDate() {
		return date;
	}
 
	public void setDate(Date date) {
		this.date = date;
	}
 
	@ManyToOne
	@JoinColumn(name = "sid", referencedColumnName = "id", insertable = false, updatable = false)
	public Sailor getSailor() {
		return sailor;
	}
 
	public void setSailor(Sailor sailor) {
		this.sailor = sailor;
	}
 
	@ManyToOne
	@JoinColumn(name = "bid", referencedColumnName = "id", insertable = false, updatable = false)
	public Boat getBoat() {
		return boat;
	}
 
	public void setBoat(Boat boat) {
		this.boat = boat;
	}

	@Override
	public String toString() {
		return String.format("Reservation [id=%s, sid=%s, bid=%s, date=%s]", id, sid, bid, date);
	}
	
}