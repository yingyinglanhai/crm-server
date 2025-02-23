package com.fno.crm.domain;

import com.fno.common.annotation.Excel;
import com.fno.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 综合统计对象 crm_comp_stat
 * 
 * @author fno
 * date 2024-07-30
 */
@Data
@ApiModel(value = "CompStat", description = "综合统计")
public class CompStat extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 统计日期 */
    @Excel(name = "统计日期")
    @ApiModelProperty("统计日期")
    private String statDate;

    /** 线索统计 */
    @Excel(name = "线索统计")
    @ApiModelProperty("线索统计")
    private Integer clueTotal;

    /** 客户统计 */
    @Excel(name = "客户统计")
    @ApiModelProperty("客户统计")
    private Integer customerTotal;

    /** 商机统计 */
    @Excel(name = "商机统计")
    @ApiModelProperty("商机统计")
    private Integer chanceTotal;

    /** 商机跟进统计 */
    @Excel(name = "商机跟进统计")
    @ApiModelProperty("商机跟进统计")
    private Integer chanceFollowTotal;

    /** 产品统计 */
    @Excel(name = "产品统计")
    @ApiModelProperty("产品统计")
    private Integer productTotal;

    /** 合同统计 */
    @Excel(name = "合同统计")
    @ApiModelProperty("合同统计")
    private BigDecimal contractTotal;

    /** 回款统计 */
    @Excel(name = "回款统计")
    @ApiModelProperty("回款统计")
    private BigDecimal backmoneyTotal;

    /** 计划回款统计 */
    @Excel(name = "计划回款统计")
    @ApiModelProperty("计划回款统计")
    private BigDecimal planBackmoneyTotal;

    /** $column.columnComment */
    @ApiModelProperty("计划回款统计")
    private Long createdBy;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    @ApiModelProperty("${comment}")
    private Long updatedBy;

    /** 租户id */
    @Excel(name = "租户id")
    @ApiModelProperty("租户id")
    private Long tenantId;



    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("statDate", getStatDate())
            .append("clueTotal", getClueTotal())
            .append("customerTotal", getCustomerTotal())
            .append("chanceTotal", getChanceTotal())
            .append("chanceFollowTotal", getChanceFollowTotal())
            .append("productTotal", getProductTotal())
            .append("contractTotal", getContractTotal())
            .append("backmoneyTotal", getBackmoneyTotal())
            .append("planBackmoneyTotal", getPlanBackmoneyTotal())
            .append("createdBy", getCreatedBy())
            .append("createTime", getCreateTime())
            .append("updatedBy", getUpdatedBy())
            .append("updateTime", getUpdateTime())
            .append("tenantId", getTenantId())
            .toString();
    }
}
