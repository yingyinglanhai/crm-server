package com.fno.crm.service;

import cn.hutool.core.date.DateUtil;
import com.fno.common.utils.DateUtils;
import com.fno.common.utils.SecurityUtils;
import com.fno.crm.domain.BackmoneyPlan;
import com.fno.crm.domain.CompStat;
import com.fno.crm.mapper.BackmoneyPlanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 回款计划Service业务层处理
 *
 * @author fno
 * @date 2024-07-26
 */
@Service
public class BackmoneyPlanService
{
    @Autowired
    private BackmoneyPlanMapper backmoneyPlanMapper;

    @Autowired
    private CompStatService compStatService;

    /**
     * 查询回款计划
     *
     * @param backmoneyPlanId 回款计划主键
     * @return 回款计划
     */
    public BackmoneyPlan selectBackmoneyPlanById(Long backmoneyPlanId)
    {
        return backmoneyPlanMapper.selectBackmoneyPlanById(backmoneyPlanId);
    }

    /**
     * 查询回款计划列表
     *
     * @param backmoneyPlan 回款计划
     * @return 回款计划
     */
    public List<BackmoneyPlan> selectBackmoneyPlanList(BackmoneyPlan backmoneyPlan)
    {
        //添加租户id条件
        backmoneyPlan.setTenantId(SecurityUtils.getTenantId());
        return backmoneyPlanMapper.selectBackmoneyPlanList(backmoneyPlan);
    }

    /**
     * 新增回款计划
     *
     * @param backmoneyPlan 回款计划
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public int insertBackmoneyPlan(BackmoneyPlan backmoneyPlan)
    {
        backmoneyPlan.setCreatedBy(SecurityUtils.getUserId());
        backmoneyPlan.setCreateTime(DateUtils.getNowDate());
        backmoneyPlan.setTenantId(SecurityUtils.getTenantId());
        backmoneyPlanMapper.insertBackmoneyPlan(backmoneyPlan);
        //同步更新统计
        CompStat compStat = new CompStat();
        compStat.setTenantId(SecurityUtils.getTenantId());
        compStat.setStatDate(DateUtil.format(backmoneyPlan.getPlanBackmoneyDate(), "yyyyMM"));
        compStat.setPlanBackmoneyTotal(backmoneyPlan.getPlanBackmoneyAmount());
        return compStatService.updateCompStat(compStat);
    }

    /**
     * 修改回款计划
     *
     * @param backmoneyPlan 回款计划
     * @return 结果
     */
    public int updateBackmoneyPlan(BackmoneyPlan backmoneyPlan)
    {
        //先取出旧记录
        BackmoneyPlan oldBackmoneyPlan = backmoneyPlanMapper.selectBackmoneyPlanById(backmoneyPlan.getBackmoneyPlanId());
        backmoneyPlan.setUpdatedBy(SecurityUtils.getUserId());
        backmoneyPlan.setUpdateTime(DateUtils.getNowDate());
        backmoneyPlanMapper.updateBackmoneyPlan(backmoneyPlan);
        //同步更新统计
        CompStat compStat = new CompStat();
        compStat.setTenantId(SecurityUtils.getTenantId());
        compStat.setStatDate(DateUtil.format(oldBackmoneyPlan.getPlanBackmoneyDate(), "yyyyMM"));
        //统计值需要减去回款金额
        compStat.setPlanBackmoneyTotal(backmoneyPlan.getPlanBackmoneyAmount().subtract(oldBackmoneyPlan.getPlanBackmoneyAmount()));
        return compStatService.updateCompStat(compStat);
    }

    /**
     * 批量删除回款计划
     *
     * @param backmoneyPlanIds 需要删除的回款计划主键
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public int deleteBackmoneyPlanByIds(Long[] backmoneyPlanIds)
    {
        //先取出对应的回款计划记录
        BackmoneyPlan backmoneyPlan = backmoneyPlanMapper.selectBackmoneyPlanById(backmoneyPlanIds[0]);
        backmoneyPlanMapper.deleteBackmoneyPlanByIds(backmoneyPlanIds);
        //同步更新统计
        CompStat compStat = new CompStat();
        compStat.setTenantId(SecurityUtils.getTenantId());
        compStat.setStatDate(DateUtil.format(backmoneyPlan.getPlanBackmoneyDate(), "yyyyMM"));
        //统计值需要减去回款计划金额
        compStat.setPlanBackmoneyTotal(backmoneyPlan.getPlanBackmoneyAmount().negate());
        return compStatService.updateCompStat(compStat);
    }

    /**
     * 删除回款计划信息
     *
     * @param backmoneyPlanId 回款计划主键
     * @return 结果
     */
    public int deleteBackmoneyPlanById(Long backmoneyPlanId)
    {
        return backmoneyPlanMapper.deleteBackmoneyPlanById(backmoneyPlanId);
    }
}
