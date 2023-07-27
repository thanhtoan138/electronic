package electro.store.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "Products")
public class Product  implements Serializable{
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String name;
	Double price;
	Double discount;
	String image;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "Createdate")
	Date createDate = new Date();
	
	Boolean available;
	String title_description;
	Integer guarantee;
	String seri;
	
	@ManyToOne
	@JoinColumn(name = "Category_Id")
	Category category;
	
	@ManyToOne
	@JoinColumn(name = "Brand_Id")
	Brand brand;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	List<OrderDetail> orderDetails;	
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	List<Description> descriptions;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	List<Gallary> gallarys;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	List<Review> reviews;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	List<Favorite> favorites;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	List<ConfigDetail> configDetails;
}
