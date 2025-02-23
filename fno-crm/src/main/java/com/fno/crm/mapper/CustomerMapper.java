package com.fno.crm.mapper;

import com.fno.crm.domain.Customer;

import java.util.List;

/**
 * 客户管理Mapper接口
 * 
 * @author fno
 * date 2024-07-05
 */
public interface CustomerMapper
{
    /**
     * 查询客户管理
     * 
     * @param id 客户管理主键
     * @return 客户管理
     */
    Customer selectCustomerById(Long id);

    /**
     * 查询客户管理列表
     * 
     * @param customer 客户管理
     * @return 客户管理集合
     */
    List<Customer> selectCustomerList(Customer customer);

    /**
     * 新增客户管理
     * 
     * @param customer 客户管理
     * @return 结果
     */
    int insertCustomer(Customer customer);

    /**
     * 修改客户管理
     * 
     * @param customer 客户管理
     * @return 结果
     */
    int updateCustomer(Customer customer);

    /**
     * 删除客户管理
     * 
     * @param id 客户管理主键
     * @return 结果
     */
    int deleteCustomerById(Long id);

    /**
     * 批量删除客户管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteCustomerByIds(Long[] ids);
}
