package com.fno.crm.service;

import cn.hutool.core.date.DateUtil;
import com.fno.common.utils.DateUtils;
import com.fno.common.utils.SecurityUtils;
import com.fno.crm.domain.CompStat;
import com.fno.crm.domain.Customer;
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
public class CustomerService
{
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
    public Customer selectCustomerById(Long id)
    {
        return customerMapper.selectCustomerById(id);
    }

    /**
     * 查询客户管理列表
     *
     * @param customer 客户管理
     * @return 客户管理
     */
    public List<Customer> selectCustomerList(Customer customer)
    {
        //补充租户ID
        customer.setTenantId(SecurityUtils.getTenantId());
        return customerMapper.selectCustomerList(customer);
    }

    /**
     * 新增客户管理
     *
     * @param customer 客户管理
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public int insertCustomer(Customer customer)
    {
        customer.setCreatedBy(SecurityUtils.getUserId());
        customer.setCreateTime(DateUtils.getNowDate());
        customer.setTenantId(SecurityUtils.getTenantId());
        customerMapper.insertCustomer(customer);
        //同步更新统计
        CompStat compStat = new CompStat();
        compStat.setTenantId(SecurityUtils.getTenantId());
        compStat.setStatDate(DateUtil.format(new Date(), "yyyyMM"));
        compStat.setCustomerTotal(1);
        return compStatService.updateCompStat(compStat);

    }

    /**
     * 修改客户管理
     *
     * @param customer 客户管理
     * @return 结果
     */
    public int updateCustomer(Customer customer)
    {
        customer.setUpdatedBy(SecurityUtils.getUserId());
        customer.setUpdateTime(DateUtils.getNowDate());
        return customerMapper.updateCustomer(customer);
    }

    /**
     * 批量删除客户管理
     *
     * @param ids 需要删除的客户管理主键
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public int deleteCustomerByIds(Long[] ids)
    {
        //先取出要删除的记录
        Customer customer = customerMapper.selectCustomerById(ids[0]);
        customerMapper.deleteCustomerByIds(ids);
        //同步更新统计
        CompStat compStat = new CompStat();
        compStat.setTenantId(SecurityUtils.getTenantId());
        compStat.setStatDate(DateUtil.format(customer.getCreateTime(), "yyyyMM"));
        compStat.setCustomerTotal(-ids.length);
        return compStatService.updateCompStat(compStat);
    }

    /**
     * 删除客户管理信息
     *
     * @param id 客户管理主键
     * @return 结果
     */
    public int deleteCustomerById(Long id)
    {
        return customerMapper.deleteCustomerById(id);
    }
}
