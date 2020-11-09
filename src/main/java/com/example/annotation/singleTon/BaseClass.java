package com.example.annotation.singleTon;

import java.util.ArrayList;
import java.util.List;

/**
 *  这是一个基类
 */
public class BaseClass {

    BaseClass() {
    }

    public List getList(){
       return new ArrayList();
    }

}
