package com.yh.store.service.impl;

import com.yh.store.exception.*;
import com.yh.store.mapper.UserMapper;
import com.yh.store.pojo.User;
import com.yh.store.service.IUserService;
import com.yh.store.utils.Encryption;
import com.yh.store.utils.GetFromSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 用户模块业务层的实现类
 */
//@Service将当前类的对象交给spring管理，自动创建对象以及对象的管理
@Service
public class UserServiceImpl implements IUserService {

    //设置上传文件最大值
    private static final int AVATAR_MAX_SIZE = 10 * 1024 * 1024;
    //限制上传文件类型
    private static final List<String> AVATAR_TYPE = new ArrayList<>();
    //访问地址
    private final String URL = "http://localhost:8080/";

    static {
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/gif");

    }

    @Autowired
    private UserMapper userMapper;

    @Override
    public void insert(User user) {
        //调用selectByUserName()方法查询用户，判断用户是否注册过
        String username = user.getUsername();
        User result = userMapper.selectByUserName(username);
        //判断result是否为空
        if(result != null){
            //抛出异常
            throw new UserNameDuplicatedException("用户名被占用");
        }

        //密码加密处理:md5算法
        //盐值 + password + 盐值 ---->md5算法加密
        String oldPassword = user.getPassword();
        //获取盐值(随机生成)
        String salt = UUID.randomUUID().toString().toUpperCase();
        //补全数据：保存盐值
        user.setSalt(salt);
        //将密码和盐值作为一个整体进行加密处理
        String md5Password= Encryption.getMD5Password(oldPassword, salt);
        //将加密之后的密码补全到user对象中
        user.setPassword(md5Password);

        //补全数据：is_delete设置为0
        user.setIsDelete(0);
        //补全数据：4个日志字段信息
        user.setCreatedUser(username);
        user.setModifiedUser(username);
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);

        //执行注册业务功能
        Integer rows = userMapper.insert(user);
        if (rows != 1){
            throw new InsertException("在用户注册过程中产生了未知的异常");
        }
    }

    @Override
    public User login(String username, String password) {
        User result = userMapper.selectByUserName(username);
        //判断用户是否存在，是否删除，is_delete是否为1
        if (result == null || result.getIsDelete() == 1){
            throw new UserNotFoundException("用户不存在");
        }
        //检测用户密码是否匹配
        //1.获取到数据库中加密的密码
        String resultPassword = result.getPassword();
        //2.和用户传递过来的密码进行比较
        //2.1获取对象的盐值
        String salt = result.getSalt();
        //2.2将用户传递的密码按照相同的MD5算法加密
        String newMd5Password = Encryption.getMD5Password(password, salt);
        //3.将密码进行比较
        if (!resultPassword.equals(newMd5Password)){
            throw new PasswordNotMatchException("用户密码错误");
        }


        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        //设置头像
        user.setAvatar(result.getAvatar());
        //将用户的数据返回
        return user;
    }


    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        User result = UserIsExist(uid);
        //原始密码和数据库密码进行比较
        String oldMD5Password = Encryption.getMD5Password(oldPassword,result.getSalt());
        if (!result.getPassword().equals(oldMD5Password)){
            throw new PasswordNotMatchException("用户密码错误");
        }
        //将新密码设置到数据库
        //1.先将密码进行加密
        String newMD5Password = Encryption.getMD5Password(newPassword, result.getSalt());
        //2.再更新
        Integer rows = userMapper.updatePasswordByUid(uid, newMD5Password, username, new Date());

        if (rows != 1){
            throw new UpdateException("更新数据时产生了未知的异常");
        }
    }

    @Override
    public User getInfoByUid(Integer uid) {
        User result = UserIsExist(uid);

        User user = new User();
        user.setUsername(result.getUsername());
        user.setPhone(result.getPhone());
        user.setEmail(result.getEmail());
        user.setGender(result.getGender());
        return user;
    }

    @Override
    public void changeInfo(Integer uid, User user) {
        User result = UserIsExist(uid);

        user.setUid(uid);
        user.setModifiedUser(user.getUsername());
        user.setModifiedTime(new Date());

        Integer rows = userMapper.updateInfoByUid(user);
        if (rows != 1){
            throw new UpdateException("更新数据时产生了未知的错误");
        }
    }

    @Override
    public String changeAvatar(HttpSession session, MultipartFile file) {



        Integer uid = GetFromSession.getUidFromSession(session);
        User result = UserIsExist(uid);
        String username = GetFromSession.getUsernameFromSession(session);

        //判断文件是否为空
        if (file.isEmpty()){
            throw new FileEmptyException("文件为空");
        }
        //判断文件大小
        if (file.getSize() > AVATAR_MAX_SIZE){
            throw new FileSizeException("文件大小超出限制");
        }
        //判断文件的类型是否是我们规定的后缀类型
        String type = file.getContentType();
        if (!AVATAR_TYPE.contains(type)){
            throw new FileTypeException("文件类型不支持");
        }

        ApplicationHome applicationHome = new ApplicationHome();
        String path = applicationHome.getDir() + "\\src\\main\\resources\\static\\upload";
        //上传的文件   ... /upload/文件.png
//        String parentPath = session.getServletContext().getRealPath("upload");
//        System.out.println(applicationHome.getDir());

//        System.out.println(parentPath);
        //File对象指向这个路径，File是否存在
        System.out.println(path);
        File dir = new File(path);
        if (!dir.exists()){
            dir.mkdirs();
        }

        //修改头像名称--->uid + 时间 + 文件类型
        String originalFilename = file.getOriginalFilename();
        int index = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String fileName = uid + dateFormat.format(new Date()) + suffix;
        File dest = new File(dir,fileName);

        //将参数file中数据写入到这个文件
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            throw new FileUploadIOException("文件读写异常");
        }

        //头像路径
        String avatar = "../upload/" + fileName;
        System.out.println(avatar);
        Integer rows = userMapper.updateAvatarByUid(uid, avatar, username, new Date());
        if (rows != 1){
            throw new UpdateException("更新用户头像时产生了未知的错误");
        }
        //返回图片访问地址
        return avatar;
    }


    /**
     * 用户是否存在或者是否已删除
     * @param uid 用户id
     * @return 存在则返回用户对象，不存在则抛出异常
     */
    protected User UserIsExist(Integer uid){
        User result = userMapper.selectByUid(uid);
        if (result == null || result.getIsDelete() == 1){
//            System.out.println("用户数据不存在");
            throw new UserNotFoundException("用户数据不存在");
        }
        return result;
    }


}
