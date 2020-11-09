package com.example.annotation.utils.duojicaidang;

import lombok.Data;

import java.util.Comparator;
import java.util.List;

@Data
public class Menu {
  // 菜单id
  private String id;
  // 菜单名称
  private String name;
  // 父菜单id
  private String parentId;
  // 菜单url
  private String url;
  // 菜单图标
  private String icon;
  // 菜单顺序
  private int order;
  // 子菜单
  private List<Menu> children;
  // ... 省去getter和setter方法以及toString方法

}