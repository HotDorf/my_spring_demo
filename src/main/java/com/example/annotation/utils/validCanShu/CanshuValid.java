package com.example.annotation.utils.validCanShu;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.annotation.poi.Person;

import java.util.HashMap;
import java.util.Objects;

public class CanshuValid {

    public static void main(String[] args) {
        Valid person = new Valid();
        person.setName("1");
        System.out.println((JSONObject) JSONObject.toJSON(person));
        valide(person);
        HashMap<Object, Object> map = com.google.common.collect.Maps.newHashMap();
        map.put("name","sa");
        System.out.println(map);
        System.out.println((JSONObject) JSONObject.toJSON(map));
        valide(map);
    }

    private static void valide(Object obj) throws ValidParamException{
        JSONObject objJson = (JSONObject) JSONObject.toJSON(obj);
        String[] split = StringUtils.split("name,list", ",");
        for (String s : split) {
            if (Objects.isNull(objJson.get(s))){
                throw  new ValidParamException(String.format("参数[%s]不能为空！",s));
            }
        }
    }

}
