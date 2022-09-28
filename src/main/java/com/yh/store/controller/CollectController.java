package com.yh.store.controller;

import com.yh.store.pojo.Collect;
import com.yh.store.service.ICollectService;
import com.yh.store.utils.GetFromSession;
import com.yh.store.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/collects")
@RestController
public class CollectController {

    @Autowired
    private ICollectService collectService;


    @RequestMapping("/add_collect")
    public JsonResult<Void> addCollect(Integer pid, HttpSession session){
        String username = GetFromSession.getUsernameFromSession(session);
        Integer uid = GetFromSession.getUidFromSession(session);
        Integer row = collectService.addCollect(uid, pid, username);
        if (row == 0){
            return JsonResult.error("收藏列表以及存在该商品");
        }else{
            return JsonResult.success("添加收藏成功");
        }
    }

    @RequestMapping("/all_collect")
    public JsonResult<List<Collect>> getAllCollects(HttpSession session){
        Integer uid = GetFromSession.getUidFromSession(session);
        List<Collect> collects = collectService.getCollects(uid);
        return new JsonResult<>(JsonResult.OK,collects);
    }

    @RequestMapping("/update_status")
    public JsonResult<Void> updateStatus(Integer pid,Integer status){
        collectService.updateStatus(pid, status);
        return JsonResult.success("取消收藏成功");
    }
}
