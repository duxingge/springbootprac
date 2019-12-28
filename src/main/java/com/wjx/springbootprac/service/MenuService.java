package com.wjx.springbootprac.service;

import com.wjx.springbootprac.po.Menu;
import com.wjx.springbootprac.repository.MenuRepository;
import com.wjx.springbootprac.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private RoleRepository roleRepository;

    public Menu findById(Integer id){
        Optional<Menu> menuOp= menuRepository.findById(id);
        Menu menu = menuOp.get();
        menu.setRoleList(roleRepository.getRolesByMid(menu.getId()));
        return menu;
    }
    public List<Menu> getAll(){
        List<Menu> all = menuRepository.findAll();
        for (Menu menu : all) {
            menu.setRoleList(roleRepository.getRolesByMid(menu.getId()));
        }
        return all;
    }
}
