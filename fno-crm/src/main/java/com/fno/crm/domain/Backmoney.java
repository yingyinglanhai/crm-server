package com.fno.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fno.common.annotation.Excel;
import com.fno.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 回款对象 crm_backmoney
 * 
 * @author fno
 * date 2024-07-26
 */
@Data
@ApiModel(value = "Backmoney", description = "回款")
public class Backmoney extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    @ApiModelProperty("${comment}")
    private Long backmoneyId;

    /** 关联合同id */
    @Excel(name = "关联合同id")
    @ApiModelProperty("关联合同id")
    private Long contractId;

    /** 关联合同编号 */
    @Excel(name = "关联合同编号")
    @ApiModelProperty("关联合同编号")
    private String contractNo;

    /** 关联合同名称 */
    @Excel(name = "关联合同名称")
    @ApiModelProperty("关联合同名称")
    private String contractName;

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

    /** 回款日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "回款日期", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty("回款日期")
    private Date backmoneyDate;

    /** 回款金额 */
    @Excel(name = "回款金额")
    @ApiModelProperty("回款金额")
    private BigDecimal backmoneyAmount;

    /** 回款方式 */
    @Excel(name = "回款方式")
    @ApiModelProperty("回款方式")
    private String backmoneyWay;

    /** 备注 */
    @Excel(name = "备注")
    @ApiModelProperty("备注")
    private String backmoneyDesc;

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
            .append("backmoneyId", getBackmoneyId())
            .append("contractId", getContractId())
            .append("contractNo", getContractNo())
            .append("contractName", getContractName())
            .append("customerId", getCustomerId())
            .append("customerName", getCustomerName())
            .append("tenantId", getTenantId())
            .append("backmoneyDate", getBackmoneyDate())
            .append("backmoneyAmount", getBackmoneyAmount())
            .append("backmoneyWay", getBackmoneyWay())
            .append("createdBy", getCreatedBy())
            .append("createTime", getCreateTime())
            .append("updatedBy", getUpdatedBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
