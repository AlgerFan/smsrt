package com.fan.smsrt.service.serviceimpl;

import com.fan.smsrt.dao.UserDao;
import com.fan.smsrt.entity.User;
import com.fan.smsrt.service.UserService;
import com.fan.smsrt.util.result.Result;
import com.fan.smsrt.util.result.ResultCodeEnum;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author AlgerFan
 * @date 2018/7/10 13:54
 * @Description
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Resource
    private HttpSession session;

    @Override
    public Result login(String phone, String password) {
        Result result = new Result(ResultCodeEnum.FAIL);
        if (phone==null || password==null) {
            result.setMessage("手机号或密码不能为空！");
            return result;
        }
        List<User> users = userDao.selectAllUser("");
        User user = null;
        for (User user1 : users) {
            if (phone.equals(user1.getPhone()) && password.equals(user1.getPassword())) {
                user = user1;
                break;
            }
        }
        if (user != null) {
            if (user.getRoleId() == 0) {
                result = new Result(ResultCodeEnum.SUCCESS,user);
                result.setMessage("登录成功");
                return result;
            }
            if (user.getRoleId() == 1) {
                session.setMaxInactiveInterval(60 * 20);
                session.setAttribute("user", user);
                result = new Result(ResultCodeEnum.SUCCESS);
                result.setMessage("管理员登录成功");
                return result;
            }
        }
        result = new Result(ResultCodeEnum.FAIL);
        result.setMessage("账号或密码错误");
        return result;
    }

    @Override
    public Result checkUserName(String userName) {
        User user = userDao.checkUserName(userName);
        Result result;
        if(user!=null){
            result = new Result(ResultCodeEnum.FAIL);
            result.setMessage("该用户名已存在");
            return result;
        }
        result = new Result(ResultCodeEnum.SUCCESS);
        result.setMessage("该用户名可以使用");
        return result;
    }

    @Override
    public Result checkPhone(String phone) {
        User user = userDao.checkPhone(phone);
        Result result;
        if(user!=null){
            result = new Result(ResultCodeEnum.FAIL);
            result.setMessage("该手机号已存在");
            return result;
        }
        result = new Result(ResultCodeEnum.SUCCESS);
        result.setMessage("该手机号可以使用");
        return result;
    }

    @Override
    public Result resister(String username, String password, String phone) {
        Result result;
        result = new Result(ResultCodeEnum.FAIL);
        if(username==null || password==null || phone==null) {
            result.setMessage("请填写完整注册信息！");
            return result;
        }
        if(userDao.checkPhone(phone) != null) {
            result.setMessage("手机号已存在");
            return result;
        }
        User user = new User(username,password,phone);
        user.setNickname(username);
        userDao.resister(user);
        result = new Result(ResultCodeEnum.SUCCESS,user);
        result.setMessage("注册成功");
        return result;
    }

    @Override
    public Result selectUser(int id) {
        Result result;
        User user = userDao.selectUserById(id);
        if(user==null){
            result = new Result(ResultCodeEnum.FAIL);
            result.setMessage("该用户不存在");
            return result;
        }
        result = new Result(ResultCodeEnum.SUCCESS,user);
        result.setMessage("查询成功");
        return result;
    }

    @Override
    public Result updateUser(int userId,User user) {
        User userdata = userDao.selectUserById(userId);
        Result result = new Result(ResultCodeEnum.FAIL);
        if(user.getNickname()==null ||  user.getPhone() == null){
            result.setMessage("昵称、手机号不能为空！");
            return result;
        }
        User user2 = userDao.checkPhone(user.getPhone());
        if(!user.getPhone().equals(userdata.getPhone()) && user2!=null){
            result.setMessage("手机号已存在");
            return result;
        }
        userdata.setNickname(user.getNickname());
        userdata.setPhone(user.getPhone());
        if(user.getEmail()!=null) userdata.setEmail(user.getEmail());
        if(user.getAcceptAddress() != null) userdata.setAcceptAddress(user.getAcceptAddress());
        if(user.getCity() != null) userdata.setCity(user.getCity());
        userDao.updateUser(userdata);
        result = new Result(ResultCodeEnum.SUCCESS,userDao.selectUserById(userId));
        result.setMessage("更新成功");
        return result;
    }

    @Override
    public Result retrievePassword(int userId,String password,String newPassword) {
        Result result = new Result(ResultCodeEnum.FAIL);
        if(password==null || newPassword==null){
            result.setMessage("请填写完整密码！");
            return result;
        }
        User user = userDao.selectUserById(userId);
        if(!user.getPassword().equals(password)){
            result.setMessage("旧密码错误！");
            return result;
        }
        user.setPassword(newPassword);
        userDao.updatePassword(user);
        result = new Result(ResultCodeEnum.SUCCESS);
        result.setMessage("修改成功");
        return result;
    }

    @Override
    public User adminCheckUserName(String userName) {
        return userDao.checkUserName(userName);
    }

    @Override
    public User adminCheckPhone(String phone) {
        return userDao.checkPhone(phone);
    }

    @Override
    public void insertUser(User user) {
        user.setNickname(user.getUserName());
        userDao.insertUser(user);
    }

    @Override
    public PageInfo selectUserPage(String user_name, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> users = userDao.selectAllUser(user_name);
        return new PageInfo<>(users);
    }

    @Override
    public Result deleteById(int userId) {
        Result result = new Result(ResultCodeEnum.FAIL);
        User user = userDao.selectUserById(userId);
        if(user == null) {
            result.setMessage("该用户不存在！");
            return result;
        }
        userDao.deleteById(userId);
        result = new Result(ResultCodeEnum.SUCCESS);
        result.setMessage("删除成功");
        return result;
    }

    @Override
    public void adminUpdateUser(int userId, User user) {
        User user1 = userDao.selectUserById(userId);
        user1.setRoleId(user.getRoleId());
        user1.setUserName(user.getUserName());
        user1.setPassword(user.getPassword());
        user1.setPhone(user.getPhone());
        if(user.getEmail() != null) user1.setEmail(user.getEmail());
        if(user.getCity() != null) user1.setCity(user.getCity());
        userDao.adminUpdateUser(user1);
    }

    @Override
    public User findById(int userId) {
        return userDao.selectUserById(userId);
    }

}
