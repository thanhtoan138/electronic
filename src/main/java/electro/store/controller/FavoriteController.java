package electro.store.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import electro.store.entity.Brand;
import electro.store.entity.Category;
import electro.store.entity.Product;
import electro.store.service.BrandService;
import electro.store.service.CategoryService;
import electro.store.service.FavoriteService;
import electro.store.service.ProductService;

@Controller
public class FavoriteController {

	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	BrandService brandService;

	@Autowired
	FavoriteService favoriteService;
	
	@Autowired
	HttpServletRequest httpServletRequest;

	@GetMapping("/favorite/list")
	public String list(Model model, 
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size, 
			@RequestParam("sort") Optional<String> sort,
			@RequestParam("cid") Optional<Integer> cid, 
			@RequestParam("bid") Optional<Integer> bid) {

		// hiện thị list checkbox
		List<Category> categories = categoryService.findAll();
		model.addAttribute("cateList", categories);
		List<Brand> brands = brandService.findAll();
		model.addAttribute("brandList", brands);

		// hiển thị giá trị ngầm định, tính toán kích thước của trang
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(6);
		
		String username = httpServletRequest.getRemoteUser();

		// tạo đối tượng pageable và sắp xếp
		Pageable pageable;
		if (sort.isPresent()) {
			pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by(sort.get()));
			if (sort.get().equals("id")) {
				model.addAttribute("sortPage", sort.get());
			}
			if (sort.get().equals("product.name")) {
				model.addAttribute("sortPage", sort.get());
			}
		} else {
			pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("id"));
			model.addAttribute("sortPage", "id");
		}

		// nếu thông tin của name đc truyền tới server
		Page<Product> resultPage = null;
		// nếu không có cateid và brandid nào được truyền tới server
		if (!cid.isPresent() && !bid.isPresent()) {
			resultPage = favoriteService.findFavoriteProductByUser(username, pageable);
		} else {
			if (cid.isPresent()) {
				resultPage = favoriteService.findFavoriteByCategoryId(username, cid.get(), pageable);
				model.addAttribute("cid", cid.get());
			}
			if (bid.isPresent()) {
				resultPage = favoriteService.findFavoriteByBrandId(username, bid.get(), pageable);
				model.addAttribute("bid", bid.get());
			}
		}

		// trả về tổng số trang đã được phân trang
		int totalPages = resultPage.getTotalPages();

		if (totalPages > 0) {
			int start = Math.max(1, currentPage - 2);
			int end = Math.min(currentPage + 2, totalPages);

			if (totalPages > 5) {
				if (end == totalPages) {
					start = end - 5;
				} else {
					if (start == 1) {
						end = start + 5;
					}
				}
			}
			// chuyển giá trị từ start tới end thành danh sách
			List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());

			model.addAttribute("numbershowstart", (pageSize * currentPage) - pageSize + 1);
			model.addAttribute("numbershowend", pageSize * currentPage);
			model.addAttribute("pageNumbers", pageNumbers);
		}

		model.addAttribute("productPage", resultPage);
		return "favorite/list";
	}
	
	

}
