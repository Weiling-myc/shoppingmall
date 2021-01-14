package com.bjfu.web.user;

import com.bjfu.entity.Classification;
import com.bjfu.entity.Product;
import com.bjfu.entity.vo.CarouselItemVO;
import com.bjfu.entity.vo.MerchandiseMenuVO;
import com.bjfu.service.ClassificationService;
import com.bjfu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private ClassificationService classificationService;
    @Autowired
    private ProductService productService;

    /**
     * 打开首页
     *
     * @return
     */
    @RequestMapping("/index.html")
    public String toIndex(HttpServletRequest request) {
        List<Classification> classificationList = classificationService.findAll(1);
        List<Classification> enClassificationList = classificationService.findAll(2);
        List<MerchandiseMenuVO> categories = new ArrayList<>();
        for (Classification classification : classificationList) {
            MerchandiseMenuVO category = new MerchandiseMenuVO();
            category.setCategoryName(classification.getCname());
            category.setCategoryId(enClassificationList.stream()
                    .filter(i -> i.getParentId().equals(classification.getId())).findAny().get().getId());
            category.setProducts(productService.findByCsid(category.getCategoryId()));
            categories.add(category);
        }
        List<CarouselItemVO> carouselItems = new ArrayList<>();
        carouselItems.add(new CarouselItemVO());
        carouselItems.get(0).setRedirectUri("/product/get.html?id=14");
        carouselItems.get(0).setImgUri("/admin/product/img/15hnt.jpg");
        carouselItems.add(new CarouselItemVO());
        carouselItems.get(1).setRedirectUri("/product/get.html?id=15");
        carouselItems.get(1).setImgUri("/admin/product/img/15czs.png");
        carouselItems.add(new CarouselItemVO());
        carouselItems.get(2).setRedirectUri("/product/get.html?id=18");
        carouselItems.get(2).setImgUri("/admin/product/img/15jbc.png");
        request.setAttribute("categories", categories);
        request.setAttribute("carouselItems", carouselItems);
        return "mall/index";
    }

    /**
     * 访问根目录转发到首页
     *
     * @return
     */
    @RequestMapping("/")
    public String index() {
        return "forward:/index.html";
    }

    @RequestMapping("/search")
    public String search(@RequestParam(name = "title") String title) {
        List<Product> result = productService.findByTitle(title);
        if (result.isEmpty())
            throw new RuntimeException("商品找不到了");
        return "forward:/product/get.html?id=" + result.get(0).getId();
    }
}
