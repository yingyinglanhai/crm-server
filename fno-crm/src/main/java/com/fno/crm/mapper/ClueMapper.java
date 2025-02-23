package com.fno.crm.mapper;

import com.fno.crm.domain.Clue;

import java.util.List;

/**
 * 客户管理Mapper接口
 * 
 * @author fno
 * date 2024-07-05
 */
public interface ClueMapper
{
    /**
     * 查询客户管理
     * 
     * @param id 客户管理主键
     * @return 客户管理
     */
    Clue selectClueById(Long id);

    /**
     * 查询客户管理列表
     * 
     * @param clue 客户管理
     * @return 客户管理集合
     */
    List<Clue> selectClueList(Clue clue);

    /**
     * 新增客户管理
     * 
     * @param clue 客户管理
     * @return 结果
     */
    int insertClue(Clue clue);

    /**
     * 修改客户管理
     * 
     * @param clue 客户管理
     * @return 结果
     */
    int updateClue(Clue clue);

    /**
     * 删除客户管理
     * 
     * @param id 客户管理主键
     * @return 结果
     */
    int deleteClueById(Long id);

    /**
     * 批量删除客户管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteClueByIds(Long[] ids);
}
