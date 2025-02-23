package com.fno.crm.mapper;

import com.fno.crm.domain.Chance;

import java.util.List;

/**
 * 商机Mapper接口
 * 
 * @author fno
 * date 2024-07-21
 */
public interface ChanceMapper 
{
    /**
     * 查询商机
     * 
     * @param chanceId 商机主键
     * @return 商机
     */
    public Chance selectChanceByChanceId(Long chanceId);

    /**
     * 查询商机列表
     * 
     * @param chance 商机
     * @return 商机集合
     */
    public List<Chance> selectChanceList(Chance chance);

    /**
     * 新增商机
     * 
     * @param chance 商机
     * @return 结果
     */
    public int insertChance(Chance chance);

    /**
     * 修改商机
     * 
     * @param chance 商机
     * @return 结果
     */
    public int updateChance(Chance chance);

    /**
     * 删除商机
     * 
     * @param chanceId 商机主键
     * @return 结果
     */
    public int deleteChanceByChanceId(Long chanceId);

    /**
     * 批量删除商机
     * 
     * @param chanceIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteChanceByChanceIds(Long[] chanceIds);
}
