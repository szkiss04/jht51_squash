package pti.sb_squash_mvc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "squash_places")
public class SquashPlace {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "place_name")
	private String placeName;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "rent_fee_per_hour_huf")
	private int rentFeePerHourInHuf;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getRentFeePerHourInHuf() {
		return rentFeePerHourInHuf;
	}

	public void setRentFeePerHourInHuf(int rentFeePerHourInHuf) {
		this.rentFeePerHourInHuf = rentFeePerHourInHuf;
	}
	
}
