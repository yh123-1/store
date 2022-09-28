package com.yh.store.service.impl;

import com.yh.store.exception.*;
import com.yh.store.mapper.CartMapper;
import com.yh.store.mapper.ProductMapper;
import com.yh.store.pojo.Cart;
import com.yh.store.pojo.Product;
import com.yh.store.service.ICartService;
import com.yh.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;


    @Override
    public void addToCart(Integer uid, Integer pid, Integer amount, String username) {
        //查询当前要添加的购物是否在表中存在
        Cart result = cartMapper.findByUidAndPid(uid, pid);
        Date date = new Date();
        if (result == null){//表示没有添加到购物车中,进行添加操作
            //创建一个cart对象
            Cart cart = new Cart();
            //补全数据
            cart.setUid(uid);
            cart.setPid(pid);
            cart.setNum(amount);
            cart.setCreatedUser(username);
            cart.setCreatedTime(date);
            cart.setModifiedUser(username);
            cart.setModifiedTime(date);
            //价格，来自于商品中的数据
            Product product = productMapper.findProductById(pid);
            cart.setPrice(product.getPrice());
            Integer rows = cartMapper.insertCart(cart);
            if (rows != 1) {
                throw new InsertException("插入数据时发生未知异常");
            }
        }else {//表示当前商品在购物车中，则更新这条数据的num值
                Integer num = result.getNum() + amount;
                Integer rows = cartMapper.updateNumByCid(result.getCid(), num, username, date);
                if (rows != 1){
                    throw new UpdateException("更新数据时发生未知的异常");
                }
            }
    }

    @Override
    public List<CartVO> getVOByUid(Integer uid) {
        List<CartVO> cartVOList = cartMapper.findVOByUid(uid);
        return cartVOList;
    }

    @Override
    public Integer addNum(Integer cid, Integer uid, String username) {
        Cart result = cartMapper.findByCid(cid);
        if (result == null){
            throw new CartNotFoundExcetpion("数据不存在");
        }
        if (!result.getUid().equals(uid)){
            throw new AccessDeniedException("数据非法访问");
        }
        Integer num = result.getNum() + 1;
        Integer rows = cartMapper.updateNumByCid(cid, num, username, new Date());
        if (rows != 1){
            throw new UpdateException("更新数据失败");
        }
        //返回新的购物车数据总量
        return num;
    }

    @Override
    @Transactional(rollbackFor = {DeleteException.class,UpdateException.class})
    public Integer reduceNum(Integer cid, Integer uid, String username) {
        Cart result = cartMapper.findByCid(cid);
        if (result == null){
            throw new CartNotFoundExcetpion("数据不存在");
        }
        if (!result.getUid().equals(uid)){
            throw new AccessDeniedException("数据非法访问");
        }
        Integer num = result.getNum() - 1;
        if (num == 0){
            Integer rows = cartMapper.deleteByCid(cid);
            if (rows != 1){
                throw new DeleteException("删除数据失败");
            }
            return 0;
        }
        Integer rows = cartMapper.updateNumByCid(cid, num, username, new Date());
        if (rows != 1){
            throw new UpdateException("更新数据失败");
        }
        return num;
    }

    @Override
    public List<CartVO> getVOByCid(Integer uid, Integer[] cids) {
        List<CartVO> list = new ArrayList<>();
        List<CartVO> cartVOList = cartMapper.findVOListByCid(cids);
        Iterator<CartVO> iterator = cartVOList.iterator();
        while (iterator.hasNext()) {
            CartVO cartVO = iterator.next();
            if (cartVO.getUid().equals(uid)){
                list.add(cartVO);
            }
        }
        return list;
    }

    @Override
    public void delCartByCid(Integer[] cids) {
        Integer rows = cartMapper.delByCids(cids);
        if (rows == 0 || cids.length != rows){
            throw new DeleteException("删除时发生错误");
        }
        System.out.println(rows);
    }
}
