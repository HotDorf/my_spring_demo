package com.example.annotation.mybatisplus.autoGenerator;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
/*import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.example.annotation.springContextAweraTest.SpringContextAweraTest;*/

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TimerTask;

// 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
@SuppressWarnings("all")
public class CodeGenerators {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    private static  String path = "C:/develop/javaCode/mySpringDemo/my_spring_demo/src/main/java"; //java源码地址
    private static  String parentPackage = "com.example.annotation";  //java下的三级包名
    private static  String modelPackage = "utils.duo.entities";  //实体对应包
    private static  String tableNames = "nod_parent_son";  //数据库表名
    //数据库表名
    private static  String dateSourceUrl = "jdbc:mysql://127.0.0.1:3306/myspringboot?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Hongkong&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true";
    private static  String dateSourceDriverName = "com.mysql.cj.jdbc.Driver";  //数据库driver
    private static  String dateSourceName = "mysql";  //数据库用户名
    private static  String dateSourcePassWord = "mysql";  //数据库密码


    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        //String path = "C:/develop/javaCode/mySpringDemo/my_spring_demo/src/main/java"; //所有 输出总文件夹
        gc.setFileOverride(true); //覆盖
        gc.setOutputDir(path);
        gc.setAuthor("csj");
        gc.setOpen(false);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        //gc.setServiceImplName("%sService");        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/myspringboot?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Hongkong&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        gc.setIdType(IdType.NONE); //设置主键生成策略，雪花算法
        //gc.setIdType(IdType.AUTO); //设置主键生成策略，依赖数据库自增
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(dateSourceUrl);
        // dsc.setSchemaName("public");
        dsc.setDriverName(dateSourceDriverName);
        dsc.setUsername(dateSourceName);
        dsc.setPassword(dateSourcePassWord);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(parentPackage); //java下的三级包名
        pc.setModuleName(modelPackage); //对应包
        //pc.setServiceImpl("service");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        //String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
         String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return path + "/" + pc.getParent().replace(".","/") +  "/" + pc.getMapper() +
                        "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML; //xml对应地址
            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录");
                return false;
            }
        });
        */
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        //templateConfig.setService(""); //不输出Service层
        templateConfig.setController(""); //不输出Controller层
        templateConfig.setXml(null);
       // templateConfig.setServiceImpl("/templates/serviceImpl.self.java");
        //templateConfig.setMapper("/templates/mapper.self.java");
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");
        strategy.setEntityLombokModel(true);
        //strategy.setRestControllerStyle(true);
        // 公共父类
        //strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
        // 写于父类中的公共字段
        //strategy.setSuperEntityColumns("id");
        strategy.setInclude(tableNames.split(","));
        strategy.setControllerMappingHyphenStyle(true);
        //strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        //mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        //mpg.setTemplateEngine(new VelocityTemplateEngine()); // 默认的
        mpg.execute();
    }

}