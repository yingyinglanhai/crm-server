package com.fno.crm.service;

import cn.hutool.core.date.DateUtil;
import com.fno.common.utils.DateUtils;
import com.fno.common.utils.SecurityUtils;
import com.fno.crm.domain.CompStat;
import com.fno.crm.domain.KV;
import com.fno.crm.mapper.CompStatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 综合统计Service业务层处理
 *
 * @author fno
 * @date 2024-07-30
 */
@Service
public class CompStatService
{
    @Autowired
    private CompStatMapper compStatMapper;

    /**
     * 查询一个综合统计
     *
     * @param compStat 综合统计对象
     * @return 综合统计
     */
    public CompStat selectCompStatByStatDate(CompStat compStat)
    {
        return compStatMapper.selectCompStatByStatDate(compStat);
    }

    /**
     * 查询综合统计列表
     *
     * @param compStat 综合统计
     * @return 综合统计
     */
    public List<CompStat> selectCompStatList(CompStat compStat)
    {
        return compStatMapper.selectCompStatList(compStat);
    }

    /**
     * 新增综合统计
     *
     * @param compStat 综合统计
     * @return 结果
     */
    public int insertCompStat(CompStat compStat)
    {
        compStat.setCreatedBy(SecurityUtils.getUserId());
        compStat.setCreateTime(DateUtils.getNowDate());
        compStat.setTenantId(SecurityUtils.getTenantId());
        return compStatMapper.insertCompStat(compStat);
    }

    /**
     * 更新综合统计
     *
     * @param compStat 综合统计
     * @return 结果
     */
    public int updateCompStat(CompStat compStat)
    {
        CompStat ct = selectCompStatByStatDate(compStat);
        if (ct == null){
            //说明不存在，需要新增
            compStat.setCreatedBy(SecurityUtils.getUserId());
            compStat.setCreateTime(DateUtils.getNowDate());
            return compStatMapper.insertCompStat(compStat);
        }else {
            //说明已经存在，需要更新
            compStat.setUpdatedBy(SecurityUtils.getUserId());
            compStat.setUpdateTime(DateUtils.getNowDate());
            return compStatMapper.updateCompStat(compStat);
        }
    }

    /**
     * 所有统计
     * @return
     */
    public CompStat selectAllCompStat()
    {
        CompStat ct = new CompStat();
        ct.setTenantId(SecurityUtils.getTenantId());
        return compStatMapper.selectAllCompStat(ct);
    }
    /**
     * 按线索来源统计线索分布
     *
     * @param
     * @return 结果
     */
    public List<KV> selectClueStatBySource()
    {
        Long tenantId = SecurityUtils.getTenantId();
        return compStatMapper.selectClueStatBySource(tenantId);
    }
    /**
     * 获取近一年的线索转化
     *
     * @param
     * @return 结果
     */
    public List<CompStat> selectLastYearStat()
    {
        //获取租户id
        Long tenantId = SecurityUtils.getTenantId();
        String nowYearMonth = DateUtil.format(new Date(),"yyyyMM");
        //计算一年前的年月
        Date beforeYearMonth = DateUtil.offsetMonth(new Date(),-12);
        //封装参数
        Map<String, Object> map = new HashMap<>();
        map.put("tenantId", tenantId);
        map.put("beginDate", DateUtil.format(beforeYearMonth,"yyyyMM"));
        map.put("endDate", nowYearMonth);
        return compStatMapper.selectLastYearStat(map);
    }
}
