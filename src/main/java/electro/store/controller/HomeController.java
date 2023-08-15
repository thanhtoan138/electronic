package electro.store.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import electro.store.entity.Product;
import electro.store.service.FavoriteService;
import electro.store.service.ProductService;
import electro.store.service.ReviewService;

@Controller
public class HomeController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	HttpServletRequest httpServletRequest;
	
	@Autowired
	FavoriteService favoriteService;
	
	@Autowired
	ReviewService reviewService;
	
	@Autowired
	ServletContext con;
	
	@GetMapping("/home/index")
	public String home(Model model) {
		int c1 = 1; int c2 = 2; int c3 = 3; int c4 = 4;
		
		List<Product> listc1 = productService.findByCategoryId(c1);
		List<Product> listc2 = productService.findByCategoryId(c2);
		List<Product> listc3 = productService.findByCategoryId(c3);
		List<Product> listc4 = productService.findByCategoryId(c4);
		
		model.addAttribute("listc1", listc1);
		model.addAttribute("listc2", listc2);
		model.addAttribute("listc3", listc3);
		model.addAttribute("listc4", listc4);
		
		List<Product> top3list1 = productService.findTop3ByCategoryId(c1);
		List<Product> top3list2 = productService.findTop3ByCategoryId(c2);
		List<Product> top3list3 = productService.findTop3ByCategoryId(c3);
		model.addAttribute("top3list1", top3list1);
		model.addAttribute("top3list2", top3list2);
		model.addAttribute("top3list3", top3list3);
		
		//lấy list favorite product by user
		String username = httpServletRequest.getRemoteUser();
		List<Product> listFPbyUser = favoriteService.findFavoriteProductByUser(username);
		String FVProductId = "";
		for (Product product : listFPbyUser) {
			FVProductId += product.getName()+"#";
		}
		
		
		// tinh tb star danh gia
//		List<Integer> rate =reviewService.findByIdProducts(productId);
//		int sum =0; double average =0;
//		for(Integer r :rate) {
//			sum+=r;			
//		}
//		average = sum/5;
//		model.addAttribute("average", Math.ceil(average));
		
		model.addAttribute("FVProductId", FVProductId);
		
		return "home/view";
	}
	
	@RequestMapping({"/admin","/admin/home/index"})
	public String admin() {
		return "redirect:/assets/admin/index.html";
	}
}
