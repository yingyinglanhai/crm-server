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
 * 商机对象 crm_chance
 * 
 * @author fno
 * date 2024-07-21
 */
@Data
@ApiModel(value = "Chance", description = "商机")
public class Chance extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商机id */
    @Excel(name = "商机id")
    @ApiModelProperty("商机id")
    private Long chanceId;

    /** 商机标题 */
    @Excel(name = "商机标题")
    @ApiModelProperty("商机标题")
    private String chanceTitle;

    /**  发现时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = " 发现时间", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty(" 发现时间")
    private Date findDate;

    /** 期望签约时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "期望签约时间", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty("期望签约时间")
    private Date expectSignDate;

    /**  期望金额 */
    @Excel(name = " 期望金额")
    @ApiModelProperty(" 期望金额")
    private Long expectMoney;

    /** 商机描述 */
    @Excel(name = "商机描述")
    @ApiModelProperty("商机描述")
    private String chanceDesc;

    /** 客户id */
    @Excel(name = "客户id")
    @ApiModelProperty("客户id")
    private Long customerId;

    /** 客户名称 */
    @Excel(name = "客户名称")
    @ApiModelProperty("客户名称")
    private String customerName;

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
            .append("chanceId", getChanceId())
            .append("changeTitle", getChanceTitle())
            .append("findDate", getFindDate())
            .append("expectSignDate", getExpectSignDate())
            .append("expectMoney", getExpectMoney())
            .append("chanceDesc", getChanceDesc())
            .append("customerId", getCustomerId())
            .append("tenantId", getTenantId())
            .append("createdBy", getCreatedBy())
            .append("createTime", getCreateTime())
            .append("updatedBy", getUpdatedBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
