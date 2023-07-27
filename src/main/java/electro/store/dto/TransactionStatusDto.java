package electro.store.dto;

import lombok.Data;

@Data
public class TransactionStatusDto {
	private String status;
	private String message;
	private String data;
}
