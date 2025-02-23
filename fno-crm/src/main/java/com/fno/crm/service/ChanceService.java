package com.fno.crm.service;

import cn.hutool.core.date.DateUtil;
import com.fno.common.utils.DateUtils;
import com.fno.common.utils.SecurityUtils;
import com.fno.crm.domain.Chance;
import com.fno.crm.domain.CompStat;
import com.fno.crm.mapper.ChanceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商机Service业务层处理
 *
 * @author fno
 * @date 2024-07-21
 */
@Service
public class ChanceService
{
    @Autowired
    private ChanceMapper chanceMapper;

    @Autowired
    private CompStatService compStatService;

    /**
     * 查询商机
     *
     * @param chanceId 商机主键
     * @return 商机
     */
    public Chance selectChanceByChanceId(Long chanceId)
    {
        return chanceMapper.selectChanceByChanceId(chanceId);
    }

    /**
     * 查询商机列表
     *
     * @param chance 商机
     * @return 商机
     */
    public List<Chance> selectChanceList(Chance chance)
    {
        //补充租户ID
        chance.setTenantId(SecurityUtils.getTenantId());
        return chanceMapper.selectChanceList(chance);
    }

    /**
     * 新增商机
     *
     * @param chance 商机
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public int insertChance(Chance chance)
    {
        chance.setCreatedBy(SecurityUtils.getUserId());
        chance.setCreateTime(DateUtils.getNowDate());
        chance.setTenantId(SecurityUtils.getTenantId());
        chanceMapper.insertChance(chance);
        //同步更新统计
        CompStat compStat = new CompStat();
        compStat.setTenantId(SecurityUtils.getTenantId());
        compStat.setStatDate(DateUtil.format(chance.getFindDate(), "yyyyMM"));
        compStat.setChanceTotal(1);
        return compStatService.updateCompStat(compStat);
    }

    /**
     * 修改商机
     *
     * @param chance 商机
     * @return 结果
     */
    public int updateChance(Chance chance)
    {
        chance.setUpdatedBy(SecurityUtils.getUserId());
        chance.setUpdateTime(DateUtils.getNowDate());
        return chanceMapper.updateChance(chance);
    }

    /**
     * 批量删除商机
     *
     * @param chanceIds 需要删除的商机主键
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public int deleteChanceByChanceIds(Long[] chanceIds)
    {
        //先通过id取出要删除的商机记录
        Chance chance = chanceMapper.selectChanceByChanceId(chanceIds[0]);
        chanceMapper.deleteChanceByChanceIds(chanceIds);
        //同步更新统计
        CompStat compStat = new CompStat();
        compStat.setTenantId(SecurityUtils.getTenantId());
        compStat.setStatDate(DateUtil.format(chance.getFindDate(), "yyyyMM"));
        //统计值需要减去1
        compStat.setChanceTotal(-chanceIds.length);
        return compStatService.updateCompStat(compStat);
    }

    /**
     * 删除商机信息
     *
     * @param chanceId 商机主键
     * @return 结果
     */
    public int deleteChanceByChanceId(Long chanceId)
    {
        return chanceMapper.deleteChanceByChanceId(chanceId);
    }
}
