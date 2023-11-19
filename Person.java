package com.maker.lp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
@TableName(value = "person")
@Data
public class Person {

    @ApiModelProperty(value="员工号（主关键字）")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value="密码")
    private String password;

    @ApiModelProperty(value="用户权限")
    private String authority;

    @ApiModelProperty(value="姓名")
    private String name;

    @ApiModelProperty(value="性别")
    private String sex;

    @JsonFormat(pattern ="yyyy-MM-dd",timezone = "GMT+8")
    @ApiModelProperty(value="生日")
    private Date birthday;

    @ApiModelProperty(value="所在部门")
    private Integer department;

    @ApiModelProperty(value="职务")
    private Integer job;

    @ApiModelProperty(value="教育程度")
    private Integer edulevel;

    @ApiModelProperty(value="专业技能")
    private String spcialty;

    @ApiModelProperty(value="地址")
    private String address;

    @ApiModelProperty(value="电话")
    private long tel;

    @ApiModelProperty(value="密码")
    private String email;

    @ApiModelProperty(value="当前状态（T-员工、F-非员工）")
    private char state='T';

    @ApiModelProperty(value="备注")
    private String remark;
}
