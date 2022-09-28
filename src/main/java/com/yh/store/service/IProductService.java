package com.yh.store.service;

import com.yh.store.pojo.Product;

import java.util.List;

public interface IProductService {

    /**
     * 查找热门商品
     * @return 商品集合
     */
    List<Product> findHotList();

    /**
     * 根据id查询商品
     * @param id 商品id
     * @return 查询到的商品
     */
    Product findProductById(Integer id);
}
