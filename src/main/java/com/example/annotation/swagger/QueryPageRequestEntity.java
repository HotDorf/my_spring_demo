package com.example.annotation.swagger;

import com.github.pagehelper.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("請求實體")
@Data
public class QueryPageRequestEntity {
    @ApiModelProperty("頁面數量")
    private int pageSize;

    @ApiModelProperty("當前頁面")
    private int currentPage;

    @ApiModelProperty("頁面數據")
    private List<?> data;
}
