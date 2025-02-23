package com.fno.crm.mapper;

import com.fno.crm.domain.CompStat;
import com.fno.crm.domain.KV;

import java.util.List;
import java.util.Map;

/**
 * 综合统计Mapper接口
 * 
 * @author fno
 * @date 2024-07-30
 */
public interface CompStatMapper 
{
    /**
     * 查询综合统计
     * 
     * @param statDate 综合统计主键
     * @return 综合统计
     */
    public CompStat selectCompStatByStatDate(CompStat compStat);

    /**
     * 查询综合统计列表
     * 
     * @param compStat 综合统计
     * @return 综合统计集合
     */
    public List<CompStat> selectCompStatList(CompStat compStat);

    /**
     * 新增综合统计
     * 
     * @param compStat 综合统计
     * @return 结果
     */
    public int insertCompStat(CompStat compStat);

    /**
     * 修改综合统计
     * 
     * @param compStat 综合统计
     * @return 结果
     */
    public int updateCompStat(CompStat compStat);

    /**
     * 删除综合统计
     * 
     * @param statDate 综合统计主键
     * @return 结果
     */
    public int deleteCompStatByStatDate(String statDate);

    /**
     * 批量删除综合统计
     * 
     * @param statDates 需要删除的数据主键集合
     * @return 结果
     */
    int deleteCompStatByStatDates(String[] statDates);

    /**
     * 查询所有统计数据
     *
     * @param stat 统计数据
     * @return 结果
     */
    CompStat selectAllCompStat(CompStat stat);

    /**
     * 按线索来源统计线索分布
     *
     * @param tenantId 租户id
     * @return 结果
     */
    public List<KV> selectClueStatBySource(Long tenantId);
    /**
     * 按线索来源统计线索转化
     */
    public List<CompStat> selectLastYearStat(Map<String,Object> map);
}
