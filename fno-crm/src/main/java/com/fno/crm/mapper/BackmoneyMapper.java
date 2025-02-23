package com.fno.crm.mapper;

import com.fno.crm.domain.Backmoney;

import java.util.List;

/**
 * 回款Mapper接口
 * 
 * @author fno
 * date 2024-07-26
 */
public interface BackmoneyMapper 
{
    /**
     * 查询回款
     * 
     * @param backmoneyId 回款主键
     * @return 回款
     */
    public Backmoney selectBackmoneyById(Long backmoneyId);

    /**
     * 查询回款列表
     * 
     * @param backmoney 回款
     * @return 回款集合
     */
    public List<Backmoney> selectBackmoneyList(Backmoney backmoney);

    /**
     * 新增回款
     * 
     * @param backmoney 回款
     * @return 结果
     */
    public int insertBackmoney(Backmoney backmoney);

    /**
     * 修改回款
     * 
     * @param backmoney 回款
     * @return 结果
     */
    public int updateBackmoney(Backmoney backmoney);

    /**
     * 删除回款
     * 
     * @param backmoneyId 回款主键
     * @return 结果
     */
    public int deleteBackmoneyById(Long backmoneyId);

    /**
     * 批量删除回款
     * 
     * @param backmoneyIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBackmoneyByIds(Long[] backmoneyIds);
}
