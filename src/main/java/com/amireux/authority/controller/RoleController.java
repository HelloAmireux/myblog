package com.amireux.authority.controller;

import com.amireux.authority.common.Result;
import com.amireux.authority.entity.Role;
import com.amireux.authority.entity.User;
import com.amireux.authority.service.RoleService;
import com.amireux.authority.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
//@Configuration
@CrossOrigin
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /*
     * 新增&修改
     * */
    @PostMapping("/save")
    public Result save(@RequestBody Role role){

        // 设置创建时间为当前时间
//        role.setCreateTime(new Date());

        //传过来有无主键，有就是修改，没有就是新增
        boolean b=roleService.saveOrUpdate(role);
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
        boolean b=roleService.removeById(id);
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
    public Result deleteBatch(@RequestBody List<Integer> idlist){
        //传过来有无主键，有就是修改，没有就是新增
        boolean b=roleService.removeByIds(idlist);
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
                           @RequestParam(name = "rolename",defaultValue = "") String username,
                           @RequestParam(name = "email",defaultValue = "") String email){
        Page<Role> page=new Page<>(pageNum,pageSize);
        QueryWrapper<Role> queryWrapper=new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)){
            queryWrapper.like("username",username);
        }
        if (StringUtils.isNotBlank(email)){
            queryWrapper.like("email",email);
        }
        Page<Role> userPage = roleService.page(page, queryWrapper);
        return  Result.success(userPage);

    }
}
