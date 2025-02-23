package com.fno.crm.service;

import cn.hutool.core.date.DateUtil;
import com.fno.common.utils.DateUtils;
import com.fno.common.utils.SecurityUtils;
import com.fno.crm.domain.ChanceFollow;
import com.fno.crm.domain.CompStat;
import com.fno.crm.mapper.ChanceFollowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商机跟进Service业务层处理
 *
 * @author fno
 * date 2024-07-21
 */
@Service
public class ChanceFollowService
{
    @Autowired
    private ChanceFollowMapper chanceFollowMapper;

    @Autowired
    private CompStatService compStatService;

    /**
     * 查询商机跟进
     *
     * @param followId 商机跟进主键
     * @return 商机跟进
     */
    public ChanceFollow selectChanceFollowByFollowId(Long followId)
    {
        return chanceFollowMapper.selectChanceFollowByFollowId(followId);
    }

    /**
     * 查询商机跟进列表
     *
     * @param chanceFollow 商机跟进
     * @return 商机跟进
     */
    public List<ChanceFollow> selectChanceFollowList(ChanceFollow chanceFollow)
    {
        //添加租户id条件
        chanceFollow.setTenantId(SecurityUtils.getTenantId());
        return chanceFollowMapper.selectChanceFollowList(chanceFollow);
    }

    /**
     * 新增商机跟进
     *
     * @param chanceFollow 商机跟进
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public int insertChanceFollow(ChanceFollow chanceFollow)
    {
        chanceFollow.setCreatedBy(SecurityUtils.getUserId());
        chanceFollow.setCreateTime(DateUtils.getNowDate());
        chanceFollow.setTenantId(SecurityUtils.getTenantId());
        chanceFollowMapper.insertChanceFollow(chanceFollow);
        //同步更新统计
        CompStat compStat = new CompStat();
        compStat.setTenantId(SecurityUtils.getTenantId());
        compStat.setStatDate(DateUtil.format(chanceFollow.getFollowDate(), "yyyyMM"));
        compStat.setChanceFollowTotal(1);
        return compStatService.updateCompStat(compStat);
    }

    /**
     * 修改商机跟进
     *
     * @param chanceFollow 商机跟进
     * @return 结果
     */
    public int updateChanceFollow(ChanceFollow chanceFollow)
    {
        chanceFollow.setUpdatedBy(SecurityUtils.getUserId());
        chanceFollow.setUpdateTime(DateUtils.getNowDate());
        return chanceFollowMapper.updateChanceFollow(chanceFollow);
    }

    /**
     * 批量删除商机跟进
     *
     * @param followIds 需要删除的商机跟进主键
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public int deleteChanceFollowByFollowIds(Long[] followIds)
    {
        //先通过id取出要删除的跟进记录
        ChanceFollow follow = chanceFollowMapper.selectChanceFollowByFollowId(followIds[0]);
        chanceFollowMapper.deleteChanceFollowByFollowIds(followIds);
        //同步更新统计
        CompStat compStat = new CompStat();
        compStat.setTenantId(SecurityUtils.getTenantId());
        compStat.setStatDate(DateUtil.format(follow.getFollowDate(), "yyyyMM"));
        //统计值需要减去1
        compStat.setChanceFollowTotal(-followIds.length);
        return compStatService.updateCompStat(compStat);
    }

    /**
     * 删除商机跟进信息
     *
     * @param followId 商机跟进主键
     * @return 结果
     */
    public int deleteChanceFollowByFollowId(Long followId)
    {
        return chanceFollowMapper.deleteChanceFollowByFollowId(followId);
    }
}
