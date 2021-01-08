package com.bjfu.web.user;

import com.bjfu.entity.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjfu.entity.Classification;
import com.bjfu.entity.OrderItem;
import com.bjfu.entity.Product;
import com.bjfu.entity.pojo.ResultBean;
import com.bjfu.service.ClassificationService;
import com.bjfu.service.CommtentService;
import com.bjfu.service.ProductService;
import com.bjfu.service.ShopCartService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private ClassificationService classificationService;
	@Autowired
	private ShopCartService shopCartService;
	@Autowired
	private CommtentService commtentService;

	/**
	 * 分页查询所有
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getTotal.do")
	public ResultBean<Integer> geTotal() {
		Pageable pageable = new PageRequest(1, 15, null);
		int total = (int) commtentService.findAll(pageable).getTotalElements();
		return new ResultBean<>(total);
	}

	/**
	 * 获取商品信息
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping("/get.do")
	public ResultBean<Product> getProduct(int id) {
		Product product = productService.findById(id);
		return new ResultBean<>(product);
	}

	/**
	 * 打开商品详情页面
	 *
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("/get.html")
	public String toProductPage(int id, Map<String, Object> map) {
		Product product = productService.findById(id);
		map.put("product", product);
		return "mall/product/info";


	}

	/**
	 * 查找特价公告商品
	 *
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/hot.do")
	public ResultBean<List<Product>> getHotProduct() {
		List<Product> products = productService.findHotProduct();
		return new ResultBean<>(products);
	}

	/**
	 * 查找最新商品
	 *
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/new.do")
	public ResultBean<List<Product>> getNewProduct(int pageNo, int pageSize) {
		Pageable pageable = new PageRequest(pageNo, pageSize);
		List<Product> products = productService.findNewProduct(pageable);
		return new ResultBean<>(products);
	}

	/**
	 * 打开分类查看商品页面
	 *
	 * @return
	 */
	@RequestMapping("/category.html")
	public String toCatePage(int cid, Map<String, Object> map) {
		Classification classification = classificationService.findById(cid);
		map.put("category", classification);
		return "mall/product/category";
	}

	@RequestMapping("/toCart.html")
	public String toCart(){
		return "mall/product/cart";
	}

	/**
	 * 按一级分类查找商品
	 *
	 * @param cid
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/category.do")
	public ResultBean<List<Product>> getCategoryProduct(int cid, int pageNo, int pageSize) {
		Pageable pageable = new PageRequest(pageNo, pageSize);
		List<Product> products = productService.findByCid(cid, pageable);
		return new ResultBean<>(products);
	}

	/**
	 * 按二级分类查找商品
	 *
	 * @param csId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/categorySec.do")
	public ResultBean<List<Product>> getCategorySecProduct(int csId, int pageNo, int pageSize) {
		Pageable pageable = new PageRequest(pageNo, pageSize);
		List<Product> products = productService.findByCsid(csId, pageable);
		return new ResultBean<>(products);
	}

	/**
	 * 根据一级分类查询它所有的二级分类
	 * @param cid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getCategorySec.do")
	public ResultBean<List<Classification>> getCategorySec(int cid){
		List<Classification> list = classificationService.findByParentId(cid);
		return new ResultBean<>(list);
	}

	/**
	 * 加购物车
	 *
	 * @param productId
	 * @param productAmount
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addCart.do")
	public ResultBean<Boolean> addToCart(Integer productId,Integer productAmount,HttpServletRequest request) throws Exception {

		CartItem cartItem=new CartItem(productId,productAmount);

		shopCartService.addCart(cartItem, request);
		return new ResultBean<>(true);
	}

	/**
	 * 移除购物车
	 *
	 * @param productId
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delCart.do")
	public ResultBean<Boolean> delToCart(int productId, HttpServletRequest request) throws Exception {
		CartItem cartItem=new CartItem(productId);
		shopCartService.remove(cartItem, request);
		return new ResultBean<>(true);
	}

	/**
	 * 查看购物车商品
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/listCart.do")
	public ResultBean<List<OrderItem>> listCart(HttpServletRequest request) throws Exception {
		List<OrderItem> orderItems = shopCartService.listCart(request);
		return new ResultBean<>(orderItems);
	}


}
