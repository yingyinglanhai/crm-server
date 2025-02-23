package com.fno.crm.domain;

import com.fno.common.annotation.Excel;
import com.fno.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 客户管理对象 crm_customer
 * 
 * @author fno
 * date 2024-07-05
 */
@Data
@ApiModel(value = "Customer", description = "客户管理")
public class Customer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 客户ID */
    @ApiModelProperty("${comment}")
    private Long customerId;

    /** 客户名称 */
    @Excel(name = "客户名称")
    @ApiModelProperty("客户名称")
    private String customerName;

    /** 行业 */
    @Excel(name = "行业")
    @ApiModelProperty("行业")
    private String industry;

    /** 等级 */
    @Excel(name = "等级")
    @ApiModelProperty("等级")
    private String grade;

    /** 客户来源 */
    @Excel(name = "客户来源")
    @ApiModelProperty("客户来源")
    private String source;

    /** 电话 */
    @Excel(name = "电话")
    @ApiModelProperty("电话")
    private String phone;

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

    /** 网址 */
    @Excel(name = "网址")
    @ApiModelProperty("网址")
    private String website;

    /** 描述 */
    @Excel(name = "描述")
    @ApiModelProperty("描述")
    private String description;

    /** 认领人id */
    @Excel(name = "认领人id")
    @ApiModelProperty("认领人id")
    private Long claimUserId;

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
            .append("customerId", getCustomerId())
            .append("customerName", getCustomerName())
            .append("industry", getIndustry())
            .append("grade", getGrade())
            .append("source", getSource())
            .append("phone", getPhone())
            .append("wechat", getWechat())
            .append("email", getEmail())
            .append("address", getAddress())
            .append("website", getWebsite())
            .append("description", getDescription())
            .append("claimUserId", getClaimUserId())
            .append("tenantId", getTenantId())
            .append("createdBy", getCreatedBy())
            .append("createTime", getCreateTime())
            .append("updatedBy", getUpdatedBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
