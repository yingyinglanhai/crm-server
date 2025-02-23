package com.fno.crm.mapper;

import com.fno.crm.domain.ChanceFollow;

import java.util.List;

/**
 * 商机跟进Mapper接口
 * 
 * @author fno
 * date 2024-07-21
 */
public interface ChanceFollowMapper 
{
    /**
     * 查询商机跟进
     * 
     * @param followId 商机跟进主键
     * @return 商机跟进
     */
    public ChanceFollow selectChanceFollowByFollowId(Long followId);

    /**
     * 查询商机跟进列表
     * 
     * @param chanceFollow 商机跟进
     * @return 商机跟进集合
     */
    public List<ChanceFollow> selectChanceFollowList(ChanceFollow chanceFollow);

    /**
     * 新增商机跟进
     * 
     * @param chanceFollow 商机跟进
     * @return 结果
     */
    public int insertChanceFollow(ChanceFollow chanceFollow);

    /**
     * 修改商机跟进
     * 
     * @param chanceFollow 商机跟进
     * @return 结果
     */
    public int updateChanceFollow(ChanceFollow chanceFollow);

    /**
     * 删除商机跟进
     * 
     * @param followId 商机跟进主键
     * @return 结果
     */
    public int deleteChanceFollowByFollowId(Long followId);

    /**
     * 批量删除商机跟进
     * 
     * @param followIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteChanceFollowByFollowIds(Long[] followIds);
}
