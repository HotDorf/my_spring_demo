package com.example.annotation.swagger;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("響應實體")
@Data
public class QueryResponseResult {

    @ApiModelProperty("狀態")
    private boolean status;

    @ApiModelProperty("信息")
    private String message;

    public QueryResponseResult OK(){
        this.status=true;
        return this;
    }

    public QueryResponseResult fail(){
        this.status=false;
        return this;
    }

    public QueryResponseResult message(String str){
        this.message=str;
        return this;
    }
}
