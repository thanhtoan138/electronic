package electro.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
	
	@GetMapping("/security/login/form")
	public String loginForm(Model model) {
		model.addAttribute("message", "Vui lòng đăng nhập!");
		return "security/login";
	}
	
	@GetMapping("/security/login/success")
	public String loginSuccess(Model model) {
		model.addAttribute("message", "Đăng nhập thành công!");
		return "security/login";
	}
	
	@GetMapping("/security/login/error")
	public String loginError(Model model) {
		model.addAttribute("message", "Sai thông tin đăng nhập!");
		return "security/login";
	}
	
	@GetMapping("/security/unauthoried")
	public String anauthoried(Model model) {
		model.addAttribute("message", "Khong có quyền truy xuất");
		return "security/login";
	}
	
	@GetMapping("/security/logoff/success")
	public String logoffSuccess(Model model) {
		model.addAttribute("message", "Bạn đã đăng xuất");
		return "security/login";
	}
}
