package electro.store.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "Configdetails")
public class ConfigDetail implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String contentc;
	
	@ManyToOne
	@JoinColumn(name = "Config_Id")
	Config config;
	
	@ManyToOne
	@JoinColumn(name = "Product_Id")
	Product product;	
}
