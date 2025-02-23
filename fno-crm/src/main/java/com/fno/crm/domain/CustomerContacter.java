package com.fno.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fno.common.annotation.Excel;
import com.fno.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 客户联系人对象 crm_customer_contacter
 * 
 * @author fno
 * date 2024-07-12
 */
@Data
@ApiModel(value = "CustomerContacter", description = "客户联系人")
public class CustomerContacter extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 客户联系人ID */
    @ApiModelProperty("${comment}")
    private Long contacterId;

    /** 联系人姓名 */
    @Excel(name = "联系人姓名")
    @ApiModelProperty("联系人姓名")
    private String contacterName;

    /** 性别 */
    @Excel(name = "性别")
    @ApiModelProperty("性别")
    private String gender;

    /** 职位 */
    @Excel(name = "职位")
    @ApiModelProperty("职位")
    private String position;

    /** 电话 */
    @Excel(name = "电话")
    @ApiModelProperty("电话")
    private String mobile;

    /** 微信 */
    @Excel(name = "微信")
    @ApiModelProperty("微信")
    private String wechat;

    /** 邮箱 */
    @Excel(name = "邮箱")
    @ApiModelProperty("邮箱")
    private String email;

    /** 联系地址 */
    @Excel(name = "联系地址")
    @ApiModelProperty("联系地址")
    private String address;

    /** 最后一次联系时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后一次联系时间", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty("最后一次联系时间")
    private Date lastContactDate;

    /** 下一次联系时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "下一次联系时间", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty("下一次联系时间")
    private Date nextContactDate;

    /** 是否决策人 */
    @Excel(name = "是否决策人")
    @ApiModelProperty("是否决策人")
    private String isDecider;

    /** 客户id */
    @Excel(name = "客户id")
    @ApiModelProperty("客户id")
    private Long customerId;

    /** 租户id */
    @Excel(name = "租户id")
    @ApiModelProperty("租户id")
    private Long tenantId;

    /** 创建人 */
    @Excel(name = "创建人")
    @ApiModelProperty("创建人")
    private Long createdBy;

    /** 更新人 */
    @Excel(name = "更新人")
    @ApiModelProperty("更新人")
    private Long updatedBy;



    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("contacterId", getContacterId())
            .append("contacterName", getContacterName())
            .append("gender", getGender())
            .append("position", getPosition())
            .append("mobile", getMobile())
            .append("wechat", getWechat())
            .append("email", getEmail())
            .append("address", getAddress())
            .append("lastContactDate", getLastContactDate())
            .append("nextContactDate", getNextContactDate())
            .append("isDecider", getIsDecider())
            .append("tenantId", getTenantId())
            .append("createdBy", getCreatedBy())
            .append("createTime", getCreateTime())
            .append("updatedBy", getUpdatedBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
