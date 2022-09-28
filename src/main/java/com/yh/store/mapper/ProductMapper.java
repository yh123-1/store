package com.yh.store.mapper;

import com.yh.store.pojo.Product;

import java.util.List;

public interface ProductMapper {

    List<Product> findHotList();

    Product findProductById(Integer id);
}
