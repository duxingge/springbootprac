package com.wjx.springbootprac.common.compoment;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collection;
//定制决策管理器，菜单对应角色与用户角色信息对比，控制资源是否可以请求通过
public class CustomAccessDecisionManager implements AccessDecisionManager {
    /**
     * 登录用户具备请求对应的角色信息直接return;不具备抛出AccessDeniedException
     * @param auth 用户角色信息集合
     * @param object 请求对象 可强转为FilterInvoke
     * @param ca 菜单对应角色信息集合
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication auth, Object object, Collection<ConfigAttribute> ca) throws AccessDeniedException, InsufficientAuthenticationException {
        Collection<? extends GrantedAuthority> auths = auth.getAuthorities();
//        ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession()
        for (ConfigAttribute configAttribute : ca) {
            String role = configAttribute.getAttribute();
            if ("ROLE_LOGIN".equals(role)) {
                return;
            }
            for (GrantedAuthority grantedAuthority : auths) {
                if(grantedAuthority.getAuthority().equals(role)) {
                    return;
                }
            }
            throw new AccessDeniedException("权限不足");

        }
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return false;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
}
