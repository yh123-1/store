package com.yh.store.controller;

import com.yh.store.pojo.Product;
import com.yh.store.service.IProductService;
import com.yh.store.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;


    @RequestMapping("/hot_list")
    public JsonResult<List<Product>> getHotList(){
        List<Product> productList = productService.findHotList();
        return new JsonResult<>(JsonResult.OK,productList);
    }

    @RequestMapping("/{id}/details")
    public JsonResult<Product> productDetails(@PathVariable("id") Integer id){

        Product product = productService.findProductById(id);
        return new JsonResult<>(JsonResult.OK,product);
    }
}
