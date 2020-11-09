package com.example.annotation.swagger;

import io.swagger.annotations.*;


// @Api(value = "頁面管理",description = "測試swagger")
public interface Controller {

    @ApiOperation(value = "業務01", notes = "第一个业务", response = String.class, produces = "application/json;charset=UTF-8", httpMethod = "GET")
    @ApiParam(name = "i",value = "業務01单个参数描述")
    public String controller01(int i);

    @ApiOperation(value = "業務02", notes = "第二个业务", response = String.class, produces = "application/json;charset=UTF-8", httpMethod = "POST")
    public String controller01(QueryPageRequestEntity query);

    @ApiOperation(value = "findList", notes = "第三个业务", response = QueryResponseResult.class, produces = "application/json;charset=UTF-8", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page",value = "頁碼",required = true,dataType = "int"),
            @ApiImplicitParam(paramType = "path", name = "size",value = "每頁數量",required = true,dataType = "int")
    })
    public QueryResponseResult findList(int page, int size, QueryPageRequestEntity queryPageRequestEntity);
}
