package com.example.annotation.utils.duojicaidang;


import com.example.annotation.utils.duojicaidang.entities.entity.NodParentSon;
import com.example.annotation.utils.duojicaidang.entities.mapper.NodParentSonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AllSonNodeService {

    @Autowired
    NodParentSonMapper nodParentSonMapper;

    public Map<String,Object> findTree(){
        Map<String,Object> data = new HashMap<String,Object>();
        List<NodParentSon> nodParentSons = nodParentSonMapper.selectList(null);
        try {//查询所有菜单
            //List<Menu> allMenu = menuDao.findTree();
            ArrayList<Menu> allMenu = new ArrayList<>();
            for (NodParentSon nodParentSon : nodParentSons) {
                Menu menu = new Menu();
                menu.setId(nodParentSon.getId());
                menu.setParentId(nodParentSon.getPid());
                allMenu.add(menu);
            }
            //根节点
            List<Menu> rootMenu = new ArrayList<Menu>();
            for (Menu nav : allMenu) {
                if("0".equals(nav.getParentId())){ //父节点是0的，为根节点。
                    rootMenu.add(nav);
                }
            }
            /* 根据Menu类的order排序 */
            rootMenu.sort(order());
            //为根菜单设置子菜单，getClild是递归调用的
            for (Menu nav : rootMenu) {
                /* 获取根节点下的所有子节点 使用getChild方法*/
                List<Menu> childList = getChild(nav.getId(), allMenu);
                nav.setChildren(childList);//给根节点设置子节点
            }
            /**
             * 输出构建好的菜单数据。
             *
             */
            //data.put("success", "true");
            data.put("menus", rootMenu);
            return data;
        } catch (Exception e) {
            data.put("success", "false");
            data.put("list", new ArrayList());
            return data;
        }
    }

    /**
     * 获取子节点
     * @param id 父节点id
     * @param allMenu 所有菜单列表
     * @return 每个根节点下，所有子菜单列表
     */
    public List<Menu> getChild(String id,List<Menu> allMenu){
        //子菜单
        List<Menu> childList = new ArrayList<Menu>();
        for (Menu nav : allMenu) {
            // 遍历所有节点，将所有菜单的父id与传过来的根节点的id比较
            //相等说明：为该根节点的子节点。
            if(id.equals(nav.getParentId())){
                childList.add(nav);
            }
        }
        //递归
        for (Menu nav : childList) {
            nav.setChildren(getChild(nav.getId(), allMenu));
        }
        childList.sort(order());//排序
        //如果节点下没有子节点，返回一个空List（递归退出）
        if(childList.size() == 0){
            return new ArrayList<Menu>();
        }
        return childList;
    }

    /*
     * 排序,根据order排序
     * int大于0  01 > 02
     */
    public Comparator<Menu> order(){
        return new Comparator<Menu>() {
            @Override
            public int compare(Menu o1, Menu o2) {
                if(o1.getOrder() != o2.getOrder()){
                    return o1.getOrder() - o2.getOrder();
                }
                return 0;
            }
        };
    }
}
