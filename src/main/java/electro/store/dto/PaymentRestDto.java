package electro.store.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class PaymentRestDto implements Serializable{
	
	private String status;
	private String message;
	private String URL;
}
