package com.fno.crm.service;

import cn.hutool.core.date.DateUtil;
import com.fno.common.utils.DateUtils;
import com.fno.common.utils.SecurityUtils;
import com.fno.crm.domain.Backmoney;
import com.fno.crm.domain.CompStat;
import com.fno.crm.mapper.BackmoneyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 回款Service业务层处理
 *
 * @author fno
 * date 2024-07-26
 */
@Service
public class BackmoneyService
{
    @Autowired
    private BackmoneyMapper backmoneyMapper;

    @Autowired
    private CompStatService compStatService;

    /**
     * 查询回款
     *
     * @param backmoneyId 回款主键
     * @return 回款
     */
    public Backmoney selectBackmoneyById(Long backmoneyId)
    {
        return backmoneyMapper.selectBackmoneyById(backmoneyId);
    }

    /**
     * 查询回款列表
     *
     * @param backmoney 回款
     * @return 回款
     */
    public List<Backmoney> selectBackmoneyList(Backmoney backmoney)
    {
        //添加租户id条件
        backmoney.setTenantId(SecurityUtils.getTenantId());
        return backmoneyMapper.selectBackmoneyList(backmoney);
    }

    /**
     * 新增回款
     *
     * @param backmoney 回款
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public int insertBackmoney(Backmoney backmoney)
    {
        backmoney.setCreatedBy(SecurityUtils.getUserId());
        backmoney.setCreateTime(DateUtils.getNowDate());
        backmoney.setTenantId(SecurityUtils.getTenantId());
        backmoneyMapper.insertBackmoney(backmoney);
        //同步更新统计
        CompStat compStat = new CompStat();
        compStat.setTenantId(SecurityUtils.getTenantId());
        compStat.setStatDate(DateUtil.format(backmoney.getBackmoneyDate(), "yyyyMM"));
        compStat.setBackmoneyTotal(backmoney.getBackmoneyAmount());
        return compStatService.updateCompStat(compStat);
    }

    /**
     * 修改回款
     *
     * @param backmoney 回款
     * @return 结果
     */
    public int updateBackmoney(Backmoney backmoney)
    {
        //先取出旧记录
        Backmoney oldBackmoney = backmoneyMapper.selectBackmoneyById(backmoney.getBackmoneyId());
        backmoney.setUpdatedBy(SecurityUtils.getUserId());
        backmoney.setUpdateTime(DateUtils.getNowDate());
        backmoneyMapper.updateBackmoney(backmoney);
        //同步更新统计
        CompStat compStat = new CompStat();
        compStat.setTenantId(SecurityUtils.getTenantId());
        compStat.setStatDate(DateUtil.format(oldBackmoney.getBackmoneyDate(), "yyyyMM"));
        //统计值需要减去回款金额
        compStat.setBackmoneyTotal(backmoney.getBackmoneyAmount().subtract(oldBackmoney.getBackmoneyAmount()));
        return compStatService.updateCompStat(compStat);
    }

    /**
     * 批量删除回款
     *
     * @param backmoneyIds 需要删除的回款主键
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public int deleteBackmoneyByIds(Long[] backmoneyIds)
    {
        //先取出对应的回款记录
        Backmoney backmoney = backmoneyMapper.selectBackmoneyById(backmoneyIds[0]);
        backmoneyMapper.deleteBackmoneyByIds(backmoneyIds);
        //同步更新统计
        CompStat compStat = new CompStat();
        compStat.setTenantId(SecurityUtils.getTenantId());
        compStat.setStatDate(DateUtil.format(backmoney.getBackmoneyDate(), "yyyyMM"));
        //统计值需要减去回款金额
        compStat.setBackmoneyTotal(backmoney.getBackmoneyAmount().negate());
        return compStatService.updateCompStat(compStat);
    }

    /**
     * 删除回款信息
     *
     * @param backmoneyId 回款主键
     * @return 结果
     */
    public int deleteBackmoneyById(Long backmoneyId)
    {
        return backmoneyMapper.deleteBackmoneyById(backmoneyId);
    }
}
