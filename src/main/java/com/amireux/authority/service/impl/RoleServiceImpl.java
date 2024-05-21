package com.amireux.authority.service.impl;

import com.amireux.authority.entity.Role;
import com.amireux.authority.entity.User;
import com.amireux.authority.mapper.RoleMapper;
import com.amireux.authority.mapper.UserMapper;
import com.amireux.authority.service.RoleService;
import com.amireux.authority.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
}
