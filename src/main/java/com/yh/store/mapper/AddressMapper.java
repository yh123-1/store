package com.yh.store.mapper;

import com.yh.store.pojo.Address;

import java.util.Date;
import java.util.List;

/**
 * 收货地址持久层接口
 */
public interface AddressMapper {

    /**
     * 插入用户的收货地址数据
     * @param address 收货地址数据
     * @return 受影响的行数
     */
    Integer insertAddress(Address address);


    /**
     * 根据用户id统计收货地址总数
     * @return
     */
    Integer countByUid(Integer uid);

    /**
     * 根据用户uid查询收货地址数据
     * @param uid 用户uid
     * @return 收货地址数据
     */
    List<Address> findAddressByUid(Integer uid);


    /**
     * 根据aid查询收货地址
     * @param aid 收货id
     * @return 收货地址数据，没有则返回null
     */
    Address findAddressByAid(Integer aid);

    /**
     * 根据用户id设置所有收货地址为非默认
     * @param uid 用户id
     * @return 受影响的行数
     */
    Integer updateNonDefault(Integer uid);

    /**
     * 根据aid设置收货地址为默认
     * @param aid 收货地址id
     * @return 受影响的行数
     */
    Integer updateIsDefaultByAid(Integer aid, String modifiedUser, Date modifiedTime);

    /**
     * 根据aid删除地址
     * @param aid 收货地址id
     * @return 受影响的行数
     */
    Integer deleteAddressByAid(Integer aid);

    /**
     * 根据用户uid查询最后一次被修改的地址数据
     * @param uid 用户id
     * @return 地址数据
     */
    Address findByUidLastModifiedTime(Integer uid);

    /**
     * 根据aid修改地址
     * @param address 修改的收货地址
     * @return 受影响的行数
     */
    Integer updateAddressByAid(Address address);
}
