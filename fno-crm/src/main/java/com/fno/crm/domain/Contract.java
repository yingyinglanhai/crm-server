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
 * 合同对象 crm_contract
 * 
 * @author fno
 * date 2024-07-25
 */
@Data
@ApiModel(value = "Contract", description = "合同")
public class Contract extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 合同内部id */
    @ApiModelProperty("${comment}")
    private Long contractId;

    /** 合同编号 */
    @Excel(name = "合同编号")
    @ApiModelProperty("合同编号")
    private String contractNo;

    /** 合同名称 */
    @Excel(name = "合同名称")
    @ApiModelProperty("合同名称")
    private String contractName;

    /** 合同类型 */
    @Excel(name = "合同类型")
    @ApiModelProperty("合同类型")
    private String contractType;

    /** 合同金额（元） */
    @Excel(name = "合同金额", readConverterExp = "元=")
    @ApiModelProperty("合同金额")
    private BigDecimal contractMoney;

    /** 签订日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "签订日期", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty("签订日期")
    private Date signDate;

    /** 合同开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "合同开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty("合同开始时间")
    private Date beginDate;

    /** 合同结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "合同结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty("合同结束时间")
    private Date endDate;

    /** 合同备注 */
    @Excel(name = "合同备注")
    @ApiModelProperty("合同备注")
    private String contractDesc;

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

    /** 创建人id */
    @Excel(name = "创建人id")
    @ApiModelProperty("创建人id")
    private Long createdBy;

    /** 修改人id */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修改人id")
    @ApiModelProperty("修改人id")
    private Long updatedBy;



    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("contractId", getContractId())
            .append("contractNo", getContractNo())
            .append("contractName", getContractName())
            .append("contractType", getContractType())
            .append("contractMoney", getContractMoney())
            .append("signDate", getSignDate())
            .append("beginDate", getBeginDate())
            .append("endDate", getEndDate())
            .append("contractDesc", getContractDesc())
            .append("customerId", getCustomerId())
            .append("customerName", getCustomerName())
            .append("createdBy", getCreatedBy())
            .append("createTime", getCreateTime())
            .append("updatedBy", getUpdatedBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
