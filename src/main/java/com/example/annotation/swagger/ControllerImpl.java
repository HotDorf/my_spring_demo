package com.example.annotation.swagger;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/swagger")
@Api(tags = {"任务控制层 "})
public class ControllerImpl implements Controller {

    @Override
    @RequestMapping("a")
    public String controller01(@PathVariable int i) {
        System.out.println("業務01");
        return "業務01";
    }

    @Override
    @RequestMapping("b")
    public String controller01(QueryPageRequestEntity query) {
        return "業務02";
    }

    @Override
    @RequestMapping("c")
    public QueryResponseResult findList(int page, int size, QueryPageRequestEntity queryPageRequestEntity) {
        return null;
    }
}
