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
 * 产品管理对象 crm_product
 * 
 * @author fno
 * date 2024-07-03
 */
@Data
@ApiModel(value = "Product", description = "产品管理")
public class Product extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @ApiModelProperty("${comment}")
    private Long productId;

    /** 产品名称 */
    @Excel(name = "产品名称")
    @ApiModelProperty("产品名称")
    private String productName;

    /** 产品编码 */
    @Excel(name = "产品编码")
    @ApiModelProperty("产品编码")
    private String productCode;

    /** 产品分类 */
    @Excel(name = "产品分类")
    @ApiModelProperty("产品分类")
    private String categoryName;

    /** 单位 */
    @Excel(name = "单位")
    @ApiModelProperty("单位")
    private String unit;

    /** 价格 */
    @Excel(name = "价格")
    @ApiModelProperty("价格")
    private BigDecimal price;

    /** 状态;1=上架 0 =下架 3 删除 */
    @Excel(name = "状态;1=上架 0 =下架 3 删除")
    @ApiModelProperty("状态;1=上架 0 =下架 3 删除")
    private String status;

    /** 产品分类ID,关联分类表 */
    @Excel(name = "产品分类ID,关联分类表")
    @ApiModelProperty("产品分类ID,关联分类表")
    private Long categoryId;

    /** 产品描述 */
    @Excel(name = "产品描述")
    @ApiModelProperty("产品描述")
    private String description;

    /** 创建人 */
    @Excel(name = "创建人")
    @ApiModelProperty("创建人")
    private Long createdBy;

    /** 更新人 */
    @Excel(name = "更新人")
    @ApiModelProperty("更新人")
    private Long updatedBy;

    /** 租户id */
    @Excel(name = "租户id")
    @ApiModelProperty("租户id")
    private Long tenantId;



    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("productId", getProductId())
            .append("productName", getProductName())
            .append("productCode", getProductCode())
            .append("unit", getUnit())
            .append("price", getPrice())
            .append("status", getStatus())
            .append("categoryId", getCategoryId())
            .append("description", getDescription())
            .append("createdBy", getCreatedBy())
            .append("createTime", getCreateTime())
            .append("updatedBy", getUpdatedBy())
            .append("updateTime", getUpdateTime())
            .append("tenantId", getTenantId())
            .toString();
    }
}
