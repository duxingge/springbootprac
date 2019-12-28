package com.wjx.springbootprac.service;

import com.wjx.springbootprac.Exception.TestException;
import com.wjx.springbootprac.enums.ResultEnum;
import com.wjx.springbootprac.po.User;
import com.wjx.springbootprac.repository.RoleRepository;
import com.wjx.springbootprac.repository.UserRepository;
import com.wjx.springbootprac.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);
        if(user==null) {
            throw new UsernameNotFoundException("账户不存在");
        }
        List roleList = roleRepository.getRolesByUid(user.getId());
        user.setRoleList(roleList);
        return user;
    }
}
