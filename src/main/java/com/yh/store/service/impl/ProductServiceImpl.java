package com.yh.store.service.impl;

import com.yh.store.exception.ProductNotFoundException;
import com.yh.store.mapper.ProductMapper;
import com.yh.store.pojo.Product;
import com.yh.store.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> findHotList() {
        List<Product> productList = productMapper.findHotList();
        for (Product product : productList){
            product.setPriority(null);
            product.setCreatedUser(null);
            product.setCreatedTime(null);
            product.setModifiedUser(null);
            product.setModifiedTime(null);
        }
        return productList;
    }

    @Override
    public Product findProductById(Integer id) {
        Product product = productMapper.findProductById(id);
        if (product == null){
            throw new ProductNotFoundException("该商品没有找到");
        }
        product.setPriority(null);
        product.setCreatedUser(null);
        product.setCreatedTime(null);
        product.setModifiedUser(null);
        product.setModifiedTime(null);
        return product;
    }
}
