package com.amireux.authority.controller;

import com.amireux.authority.common.Result;
import com.amireux.authority.entity.User;
import com.amireux.authority.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController  {

    @Autowired
    private UserService userService;

    /*
    * 新增&修改
    * */
    @PostMapping("/save")
    public Result save(@RequestBody User user){

        // 设置创建时间为当前时间
        user.setCreateTime(new Date());

        //传过来有无主键，有就是修改，没有就是新增
        boolean b=userService.saveOrUpdate(user);
        if (b){
            return Result.success();
        }else{
            return Result.error();
        }
    }


    /*
     * 根据id删除
     * */
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable Integer id){
        //传过来有无主键，有就是修改，没有就是新增
        boolean b=userService.removeById(id);
        if (b){
            return Result.success();
        }else{
            return Result.error();
        }
    }


    /*
     * 批量删除
     * */
    @PostMapping("/deleteBatch")
    public Result deleteBatch(@RequestParam List<Integer> islist){
        //传过来有无主键，有就是修改，没有就是新增
        boolean b=userService.removeByIds(islist);
        if (b){
            return Result.success();
        }else{
            return Result.error();
        }
    }

    /*
    * 分页查询
    * */
    @GetMapping("/findPage")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(name = "username",defaultValue = "") String username,
                           @RequestParam(name = "email",defaultValue = "") String email){
        Page<User> page=new Page<>(pageNum,pageSize);
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)){
            queryWrapper.like("username",username);
        }
        if (StringUtils.isNotBlank(email)){
            queryWrapper.like("email",email);
        }
        Page<User> userPage = userService.page(page, queryWrapper);
        return  Result.success(userPage);

    }

}

