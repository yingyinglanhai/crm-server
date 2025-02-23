package com.fno.crm.service;

import cn.hutool.core.date.DateUtil;
import com.fno.common.utils.DateUtils;
import com.fno.common.utils.SecurityUtils;
import com.fno.common.utils.bean.BeanUtils;
import com.fno.crm.domain.Clue;
import com.fno.crm.domain.CompStat;
import com.fno.crm.domain.Customer;
import com.fno.crm.mapper.ClueMapper;
import com.fno.crm.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 客户管理Service业务层处理
 *
 * @author fno
 * date 2024-07-05
 */
@Service
public class ClueService
{
    @Autowired
    private ClueMapper clueMapper;
    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CompStatService compStatService;

    /**
     * 查询客户管理
     *
     * @param id 客户管理主键
     * @return 客户管理
     */
    public Clue selectClueById(Long id)
    {
        return clueMapper.selectClueById(id);
    }

    /**
     * 查询客户管理列表
     *
     * @param clue 客户管理
     * @return 客户管理
     */
    public List<Clue> selectClueList(Clue clue)
    {
        //补充租户ID
        clue.setTenantId(SecurityUtils.getTenantId());
        return clueMapper.selectClueList(clue);
    }

    /**
     * 新增客户管理
     *
     * @param clue 客户管理
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public int insertClue(Clue clue)
    {
        clue.setCreatedBy(SecurityUtils.getUserId());
        clue.setCreateTime(DateUtils.getNowDate());
        clue.setTenantId(SecurityUtils.getTenantId());
        clueMapper.insertClue(clue);
        //同步更新统计
        CompStat compStat = new CompStat();
        compStat.setTenantId(SecurityUtils.getTenantId());
        compStat.setStatDate(DateUtil.format(new Date(), "yyyyMM"));
        compStat.setClueTotal(1);
        return compStatService.updateCompStat(compStat);
    }

    /**
     * 修改客户管理
     *
     * @param clue 客户管理
     * @return 结果
     */
    public int updateClue(Clue clue)
    {
        clue.setUpdatedBy(SecurityUtils.getUserId());
        clue.setUpdateTime(DateUtils.getNowDate());
        return clueMapper.updateClue(clue);
    }

    /**
     * 批量删除客户管理
     *
     * @param ids 需要删除的客户管理主键
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public int deleteClueByIds(Long[] ids)
    {
        //先取出要删除的线索
        Clue clue = clueMapper.selectClueById(ids[0]);
        clueMapper.deleteClueByIds(ids);
        //同步更新统计
        CompStat compStat = new CompStat();
        compStat.setTenantId(SecurityUtils.getTenantId());
        compStat.setStatDate(DateUtil.format(clue.getCreateTime(), "yyyyMM"));
        //统计值需要减去1
        compStat.setClueTotal(-ids.length);
        return compStatService.updateCompStat(compStat);
    }

    /**
     * 转化线索为客户
     *
     * @param clueId 线索clueId
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public void convertToCustomer(long clueId)
    {
        //先获取clueId对应的线索
        Clue clue = clueMapper.selectClueById(clueId);
        //定义一个customer
        Customer customer = new Customer();
        //复制properties
        BeanUtils.copyBeanProp(customer,clue);
        //补充名称不一致属性
        //customerId为自增，固不处理
        customer.setCustomerName(clue.getClueName());
        //先添加客户，再删除线索
        customerMapper.insertCustomer(customer);
        clueMapper.deleteClueById(clueId);
    }
}
