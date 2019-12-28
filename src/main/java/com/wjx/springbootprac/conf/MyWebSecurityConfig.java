package com.wjx.springbootprac.conf;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wjx.springbootprac.compoment.CustomAccessDecisionManager;
import com.wjx.springbootprac.compoment.CustomFilterInvocationSecurityMetadataSource;
import com.wjx.springbootprac.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    //加密算法
    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    //菜单角色资源Bean
    CustomFilterInvocationSecurityMetadataSource fisms(){
        return new CustomFilterInvocationSecurityMetadataSource();
    }
    @Bean
    //访问请求过滤管理Bean
    CustomAccessDecisionManager adm() {
        return new CustomAccessDecisionManager();
    }




//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("wjx").password("1").roles("ADMIN","USER")
//                .and()
//                .withUser("wjx2").password("1").roles("USER");
//    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //静态配置权限
//                .antMatchers("/admin/**")
//                .hasRole("admin")
//                .antMatchers("/user/**")
//                .hasRole("user")
                //动态配置权限
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {

                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setSecurityMetadataSource(fisms());
                        object.setAccessDecisionManager(adm());
                        return object;
                    }
                })
                .anyRequest()//任何请求
                .authenticated()//都要验证
                .and()
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .usernameParameter("name")
                .passwordParameter("pass")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException {
                       Object principal = auth.getPrincipal();
                       response.setContentType("application/json;charset=utf-8");
                       response.setStatus(200);
                       PrintWriter out = response.getWriter();
                       Map<String,Object> m = new HashMap();
                       m.put("status","200");
                       m.put("msg",principal);
                       ObjectMapper om = new ObjectMapper();
                       out.write(om.writeValueAsString(m));
                       out.flush();
                       out.close();

                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException{
                        response.setContentType("application/json;charset=utf-8");
                        response.setStatus(401);
                        PrintWriter out = response.getWriter();
                        Map<String,String> m = new HashMap();
                        m.put("status","401");
                        if(e instanceof LockedException) {
                            m.put("msg","用户已被锁定");
                        }else if (e instanceof BadCredentialsException){
                            m.put("msg","用户或密码错误");
                        }else if(e instanceof DisabledException) {
                            m.put("msg","用户被禁用");
                        } else{
                            m.put("msg","登录失败");
                        }
                        ObjectMapper o = new ObjectMapper();
                        out.write(o.writeValueAsString(m));
                    }
                })

                .permitAll()//登录相关接口不用验证
                .and()
                //csrf关闭，不然使用自定义登录页报301
                .csrf()
                .disable();
    }

}
