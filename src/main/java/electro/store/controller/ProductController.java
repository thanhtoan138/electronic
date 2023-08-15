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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import electro.store.entity.Brand;
import electro.store.entity.Category;
import electro.store.entity.Product;
import electro.store.entity.Review;
import electro.store.service.BrandService;
import electro.store.service.CategoryService;
import electro.store.service.FavoriteService;
import electro.store.service.ProductService;
import electro.store.service.ReviewService;

@Controller
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	BrandService brandService;
	
	@Autowired
	ReviewService reviewService;
	
	@Autowired
	FavoriteService favoriteService;
	
	@Autowired
	HttpServletRequest httpServletRequest;
	
	@GetMapping({"/product/list","/"})
	public String list(Model model, 
			@RequestParam(name = "name", required = false) String name,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size,
			@RequestParam("sort") Optional<String> sort,
			@RequestParam("cid") Optional<Integer> cid,
			@RequestParam("bid") Optional<Integer> bid)  {
		
		//hiện thị list checkbox
		List<Category> categories = categoryService.findAll();
		model.addAttribute("cateList", categories);
		List<Brand> brands = brandService.findAll();
		model.addAttribute("brandList", brands);
		
		//hiển thị giá trị ngầm định, tính toán kích thước của trang
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(6);
		
		//tạo đối tượng pageable và sắp xếp
		Pageable pageable;
		if (sort.isPresent()) {
			 pageable = PageRequest.of(currentPage-1, pageSize, Sort.by(sort.get()));
			 if (sort.get().equals("id")) {
				 model.addAttribute("sortPage", sort.get());
			 }
			 if (sort.get().equals("name")) {
				 model.addAttribute("sortPage", sort.get());
			 }
		} else {
			 pageable = PageRequest.of(currentPage-1, pageSize, Sort.by("id"));
			 model.addAttribute("sortPage", "id"); 
		}
		
		//nếu thông tin của name đc truyền tới server
		Page<Product> resultPage = null;
		if (StringUtils.hasText(name)) {
			resultPage = productService.findByNameContaining(name, pageable);
			model.addAttribute("name", name);
		} else {
			//nếu không có cateid và brandid nào được truyền tới server
			if (!cid.isPresent()&&!bid.isPresent()) {
				resultPage = productService.findAll(pageable);
			} else {
				if (cid.isPresent()) {
					resultPage = productService.findByCategoryId(cid.get(), pageable);
					model.addAttribute("cid",cid.get());
				}
				if (bid.isPresent()) {
					resultPage = productService.findByBrandId(bid.get(), pageable);
					model.addAttribute("bid",bid.get());
				}
			}
		}
		
		//trả về tổng số trang đã được phân trang
		int totalPages = resultPage.getTotalPages();
		
		if (totalPages > 0) {
			int start = Math.max(1, currentPage - 2);
			int end = Math.min(currentPage + 2, totalPages);
			
			if (totalPages > 5) {
				if (end == totalPages) {
					start = end -5;
				} else {
					if (start == 1) {
						end = start + 5;
					}
				}  
			}
			//chuyển giá trị từ start tới end thành danh sách
			List<Integer> pageNumbers = IntStream.rangeClosed(start, end)
					.boxed()
					.collect(Collectors.toList());
			
			model.addAttribute("numbershowstart", (pageSize*currentPage)-pageSize+1);
			model.addAttribute("numbershowend", pageSize*currentPage);
			model.addAttribute("pageNumbers", pageNumbers);
		}

		model.addAttribute("productPage", resultPage); 
		
		//lấy list favorite product by user
		String username = httpServletRequest.getRemoteUser();
		List<Product> listFPbyUser = favoriteService.findFavoriteProductByUser(username);
		String FVProductId = "";
		for (Product product : listFPbyUser) {
			FVProductId += product.getName()+"#";
		}
		
		model.addAttribute("FVProductId", FVProductId);
		
		return "product/list";
	}
	
	@GetMapping("/product/detail/{id}")
	public String detail(Model model, 
			@PathVariable("id") Integer id,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		
		Product item = productService.findById(id);
		model.addAttribute("item", item);
		
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(3);
		
		Pageable pageable = PageRequest.of(currentPage-1, pageSize);
		Page<Review> resultPageReview  = reviewService.findByProduct(id, pageable);
		
		//điếm số lượng đánh gia sp
		List<Integer> rate =reviewService.findByIdProducts(id);
		int star5 = 0;int star4 = 0;int star3 = 0;int star2 = 0;int star1 = 0;
		for(Integer r :rate) {
			if(r.equals(5)) {
				model.addAttribute("star5", star5+=1);
				}if(r.equals(4)) {
					model.addAttribute("star4", star4+=1);
					}if(r.equals(3)) {
						model.addAttribute("star3", star3+=1);
						}if(r.equals(2)) {
							model.addAttribute("star2", star2+=1);
							}if(r.equals(1)) {
								model.addAttribute("star1", star1+=1);
							}
					
		}
		// tinh tb danh gia
		int sum =0; double average =0;
		for(Integer r :rate) {
			sum+=r;			
		}
		average = sum/5;
		model.addAttribute("average", Math.ceil(average));
		
		// phan trang 
		int totalPages = resultPageReview.getTotalPages();		
		if (totalPages > 0) {
			int start = Math.max(1, currentPage - 2);
			int end = Math.min(currentPage + 2, totalPages);
			
			if (totalPages > 5) {
				if (end == totalPages) {
					start = end -5;
				} else {
					if (start == 1) {
						end = start + 5;
					}
				}  
			}
			
			List<Integer> pageNumbersReview = IntStream.rangeClosed(start, end)
					.boxed()
					.collect(Collectors.toList());

			model.addAttribute("pageNumbersReview", pageNumbersReview);
		}
		model.addAttribute("reviewPage", resultPageReview); 
		
		return "product/detail";
	}
	
}
