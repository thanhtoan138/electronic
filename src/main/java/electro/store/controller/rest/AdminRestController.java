package electro.store.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import electro.store.service.AdminChartService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/admin")
public class AdminRestController {
	@Autowired
	AdminChartService adminService;

	@GetMapping("/chartMonthlySalesByYear")
	public String[][] doGetChartMonthlySalesByYear(Model model) {
		String[][] chartData = adminService.getQuantityChartByMonth();
		return chartData;
	}
	
	@GetMapping("/chartMonthlyPriceByYear")
	public String[][] doGetChartMonthlyPriceByYear(Model model) {
		String[][] PriceChartByMonth = adminService.getPriceChartByMonth();
		return PriceChartByMonth;
	}
	
	@GetMapping("/quantityChartBrand")
	public String[][] doGetQuantityChartBrand(Model model) {
		String[][] QuantityChartBrand = adminService.getQuantityChartBrand();
		return QuantityChartBrand;
	}
	
	@GetMapping("/quantityChartCategory")
	public String[][] doGetQuantityChartCategory(Model model) {
		String[][] QuantityChartCategory = adminService.getQuantityChartCategory();
		return QuantityChartCategory;
	}
}
