package electro.store.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import electro.store.dto.PaymentRestDto;
import electro.store.dto.TransactionStatusDto;
import electro.store.payment.Config;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

	@GetMapping("/create_payment/{amount}")
	public RedirectView createPayment(
			@PathVariable("amount") long amount
			) throws UnsupportedEncodingException{

//		String orderType = req.getParameter("ordertype");
//	     long amount = Integer.parseInt(req.getParameter("amount")) * 100;
//		String bankCode = req.getParameter("bankCode");
		
		 amount = amount * 100000;

		String vnp_TxnRef = Config.getRandomNumber(8);
//		String vnp_IpAddr = Config.getIpAddress(req);
		String vnp_TmnCode = Config.vnp_TmnCode;

		Map<String, String> vnp_Params = new HashMap<>();
		vnp_Params.put("vnp_Version", Config.vnp_Version);
		vnp_Params.put("vnp_Command", Config.vnp_Command);
		vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
		vnp_Params.put("vnp_Amount", String.valueOf(amount));
		vnp_Params.put("vnp_CurrCode", "VND");
		vnp_Params.put("vnp_BankCode", "NCB");
		vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
		vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang: " + vnp_TxnRef);
		vnp_Params.put("vnp_Locale", "vn");
		vnp_Params.put("vnp_ReturnUrl", Config.vnp_Returnurl);

		Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String vnp_CreateDate = formatter.format(cld.getTime());
		vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

		cld.add(Calendar.MINUTE, 15);
		String vnp_ExpireDate = formatter.format(cld.getTime());
		vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

		List fieldNames = new ArrayList(vnp_Params.keySet());
		Collections.sort(fieldNames);
		StringBuilder hashData = new StringBuilder();
		StringBuilder query = new StringBuilder();
		Iterator itr = fieldNames.iterator();
		while (itr.hasNext()) {
			String fieldName = (String) itr.next();
			String fieldValue = (String) vnp_Params.get(fieldName);
			if ((fieldValue != null) && (fieldValue.length() > 0)) {
				// Build hash data
				hashData.append(fieldName);
				hashData.append('=');
				hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
				// Build query
				query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
				query.append('=');
				query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
				if (itr.hasNext()) {
					query.append('&');
					hashData.append('&');
				}
			}
		}
		String queryUrl = query.toString();
		String vnp_SecureHash = Config.hmacSHA512(Config.vnp_HashSecret, hashData.toString());
		queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
		String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;
		
		PaymentRestDto paymentRestDto = new PaymentRestDto();
		paymentRestDto.setStatus("OKE");
		paymentRestDto.setMessage("Successfully");
		paymentRestDto.setURL(paymentUrl);
		
		return new RedirectView(paymentUrl);
		//return ResponseEntity.status(HttpStatus.OK).body(paymentRestDto);
	}
	
	@GetMapping("/payment_infor")
	public ResponseEntity<?> transaction(
			@RequestParam(value = "vnp_Amount") String amount,
			@RequestParam(value = "vnp_BankCode") String bankcode,
			@RequestParam(value = "vnp_OrderInfo") String order,
			@RequestParam(value = "vnp_ResponseCode") String responseCode) {
		TransactionStatusDto transactionStatusDto = new TransactionStatusDto();
		if (responseCode.equals("00")) {
			transactionStatusDto.setStatus("OK");
			transactionStatusDto.setMessage("Successfully");
			transactionStatusDto.setData("");
		} else {
			transactionStatusDto.setStatus("No");
			transactionStatusDto.setMessage("Failed");
			transactionStatusDto.setData("");
		}
		return ResponseEntity.status(HttpStatus.OK).body(transactionStatusDto);
	}
}
