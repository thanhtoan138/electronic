package electro.store.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.google.gson.JsonObject;

import electro.store.dto.PaymentRestDto;
import electro.store.dto.TransactionStatusDto;
import electro.store.payment.Config;
import electro.store.service.OrderService;

@Controller
@RequestMapping("/api/payment")
public class PaymentController {
	
	@Autowired
	OrderService orderService;
	
//	Ngân hàng	NCB
//	Số thẻ	9704198526191432198
//	Tên chủ thẻ	NGUYEN VAN A
//	Ngày phát hành	07/15
//	Mật khẩu OTP	123456
//	link demo khác: https://sandbox.vnpayment.vn/apis/vnpay-demo/

	@GetMapping("/create_payment/{amount}")
	public RedirectView createPayment(
		@PathVariable("amount") long amount
			) throws IOException {
		
        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String orderType = "other";
        amount = amount*23865*100;
        String bankCode = "";
        
        String vnp_TxnRef = Config.getRandomNumber(8);
        String vnp_IpAddr = "123.123.123.123";

        String vnp_TmnCode = Config.vnp_TmnCode;
        
        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");
        
        if (bankCode != null && !bankCode.isEmpty()) {
            vnp_Params.put("vnp_BankCode", bankCode);
        }
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", orderType);

        String locate = "";
        if (locate != null && !locate.isEmpty()) {
            vnp_Params.put("vnp_Locale", locate);
        } else {
            vnp_Params.put("vnp_Locale", "vn");
        }
        vnp_Params.put("vnp_ReturnUrl", Config.vnp_ReturnUrl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

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
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
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
        String vnp_SecureHash = Config.hmacSHA512(Config.secretKey, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;
        JsonObject job = new JsonObject();
        job.addProperty("code", "00");
        job.addProperty("message", "success");
        job.addProperty("data", paymentUrl);
//        Gson gson = new Gson();
//        resp.getWriter().write(gson.toJson(job));
        
		PaymentRestDto paymentRestDto = new PaymentRestDto();
		paymentRestDto.setStatus("OKE");
		paymentRestDto.setMessage("Successfully");
		paymentRestDto.setURL(paymentUrl);
		
		return new RedirectView(paymentUrl);
		//return ResponseEntity.status(HttpStatus.OK).body(paymentRestDto);
        
	}
	
	@GetMapping("/payment_result")
	public String transaction(Model model,
			@RequestParam(value = "vnp_Amount") String vnp_Amount,
			@RequestParam(value = "vnp_BankCode") String vnp_BankCode,
			@RequestParam(value = "vnp_OrderInfo") String vnp_OrderInfo,
			@RequestParam(value = "vnp_CardType") String vnp_CardType,
			@RequestParam(value = "vnp_PayDate") String vnp_PayDate,
			@RequestParam(value = "vnp_ResponseCode") String vnp_ResponseCode) {
		
		Date payDate = new Date();
		try {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	        payDate = dateFormat.parse(vnp_PayDate);

//            model.addAttribute("payDate", payDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
		
		if (vnp_ResponseCode.equals("00")) {
			model.addAttribute("vnp_OrderInfo", vnp_OrderInfo);
			model.addAttribute("vnp_Amount", vnp_Amount);
			model.addAttribute("vnp_BankCode", vnp_BankCode);
			model.addAttribute("vnp_CardType", vnp_CardType);
			model.addAttribute("vnp_PayDate", payDate);
			model.addAttribute("vnp_ResponseCode", "Successfully");
			orderService.updateStatusPayment();
		} else {
			model.addAttribute("vnp_OrderInfo", vnp_OrderInfo);
			model.addAttribute("vnp_Amount", vnp_Amount);
			model.addAttribute("vnp_BankCode", vnp_BankCode);
			model.addAttribute("vnp_CardType", vnp_CardType);
			model.addAttribute("vnp_PayDate", payDate);
			model.addAttribute("vnp_ResponseCode", "Fail");
		}
		return "order/payment-result";
	}
}
