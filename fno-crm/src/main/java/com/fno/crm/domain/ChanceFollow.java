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
 * 商机跟进对象 crm_chance_follow
 * 
 * @author fno
 * date 2024-07-21
 */
@Data
@ApiModel(value = "ChanceFollow", description = "商机跟进")
public class ChanceFollow extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 跟进id */
    @ApiModelProperty("${comment}")
    private Long followId;

    /** 跟进时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "跟进时间", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty("跟进时间")
    private Date followDate;

    /** 跟进方式 */
    @Excel(name = "跟进方式")
    @ApiModelProperty("跟进方式")
    private String followWay;

    /** 跟进沟通内容 */
    @Excel(name = "跟进沟通内容")
    @ApiModelProperty("跟进沟通内容")
    private String followContent;

    /** 下次跟进时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "下次跟进时间", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty("下次跟进时间")
    private Date nextFollowDate;

    /** 客户满意度 */
    @Excel(name = "客户满意度")
    @ApiModelProperty("客户满意度")
    private String satisfiedLevel;

    /** 客户联系人id */
    @Excel(name = "客户联系人id")
    @ApiModelProperty("客户联系人id")
    private Long contacterId;

    /** 客户联系人名称 */
    @Excel(name = "客户联系人名称")
    @ApiModelProperty("客户联系人名称")
    private String contacterName;

    /** 商机id */
    @Excel(name = "商机id")
    @ApiModelProperty("商机id")
    private Long chanceId;

    /** 租户id */
    @Excel(name = "租户id")
    @ApiModelProperty("租户id")
    private Long tenantId;

    /**  */
    @Excel(name = "")
    @ApiModelProperty("")
    private Long createdBy;

    /**  */
    @Excel(name = "")
    @ApiModelProperty("")
    private Long updatedBy;



    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("followId", getFollowId())
            .append("followDate", getFollowDate())
            .append("followWay", getFollowWay())
            .append("followContent", getFollowContent())
            .append("nextFollowDate", getNextFollowDate())
            .append("satisfiedLevel", getSatisfiedLevel())
            .append("contacterId", getContacterId())
            .append("chanceId", getChanceId())
            .append("tenantId", getTenantId())
            .append("createdBy", getCreatedBy())
            .append("createTime", getCreateTime())
            .append("updatedBy", getUpdatedBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
