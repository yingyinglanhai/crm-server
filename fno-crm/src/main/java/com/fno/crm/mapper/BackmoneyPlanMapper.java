package com.fno.crm.mapper;

import com.fno.crm.domain.BackmoneyPlan;

import java.util.List;

/**
 * 回款计划Mapper接口
 * 
 * @author fno
 * date 2024-07-26
 */
public interface BackmoneyPlanMapper 
{
    /**
     * 查询回款计划
     * 
     * @param backmoneyPlanId 回款计划主键
     * @return 回款计划
     */
    public BackmoneyPlan selectBackmoneyPlanById(Long backmoneyPlanId);

    /**
     * 查询回款计划列表
     * 
     * @param backmoneyPlan 回款计划
     * @return 回款计划集合
     */
    public List<BackmoneyPlan> selectBackmoneyPlanList(BackmoneyPlan backmoneyPlan);

    /**
     * 新增回款计划
     * 
     * @param backmoneyPlan 回款计划
     * @return 结果
     */
    public int insertBackmoneyPlan(BackmoneyPlan backmoneyPlan);

    /**
     * 修改回款计划
     * 
     * @param backmoneyPlan 回款计划
     * @return 结果
     */
    public int updateBackmoneyPlan(BackmoneyPlan backmoneyPlan);

    /**
     * 删除回款计划
     * 
     * @param backmoneyPlanId 回款计划主键
     * @return 结果
     */
    public int deleteBackmoneyPlanById(Long backmoneyPlanId);

    /**
     * 批量删除回款计划
     * 
     * @param backmoneyPlanIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBackmoneyPlanByIds(Long[] backmoneyPlanIds);
}
