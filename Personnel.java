package com.maker.lp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Personnel {

    @ApiModelProperty(value="记录编号")
    @TableId(type= IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value="员工号")
    private Integer person;


    @ApiModelProperty(value = "变更代码")
    @TableField("change_code")
    private Integer changeCode;

    @ApiModelProperty(value = "详细记录")
    private String description;
}


