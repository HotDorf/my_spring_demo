package com.example.annotation.poi;

public class Constant {

    // 标点符号为英文
    public static String head2Content = "编号,number;" +
            "姓名,name;" +
            "年龄,age;" +
            "性别,sex";

    // mybatisplusUsers表头
    public static String mybatisplusUsersTitles = "编号,id;" +
            "姓名,name;" +
            "年龄,age;" +
            "email,email";

    public static String mybatisplusUsersSheet = "mybatisplusUsers";

    // 导出process deploy信息Excel表头
    public static String processDefinitiontitles = "business_define_id,;" +
            "business_define_name,;" +
            "business_define_type,;" +
            "proc_def_key,processDefinKey;" +
            "proc_def_id,processDefinId;";

    public static String processDefinitionSheet = "processDefinition";
}
