package com.wjx.springbootprac.common.compoment;

import com.wjx.springbootprac.po.Menu;
import com.wjx.springbootprac.po.Role;
import com.wjx.springbootprac.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;
//一个请求对应哪些角色
@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Autowired
    private MenuService menuService;


    //菜单对应角色集合
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        List<Menu> allMenu = menuService.getAll();
        //1.有对应菜单控制返回菜单相应角色列表
        for (Menu menu : allMenu) {
            if(antPathMatcher.match(menu.getPattern(),requestUrl)) {
                List<Role> roleList = menu.getRoleList();
                String[] roleArr = new String[roleList.size()];
                for (int i = 0; i < roleList.size(); i++) {
                    roleArr[i] = roleList.get(i).getName();
                }
                return SecurityConfig.createList(roleArr);
            }
        }
        //没有相应菜单返回登陆角色
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    //定义好的资源权限，项目启动时校验
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
