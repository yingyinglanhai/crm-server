package com.fno.crm.mapper;

import com.fno.crm.domain.CustomerContacter;

import java.util.List;

/**
 * 客户联系人Mapper接口
 * 
 * @author fno
 * date 2024-07-12
 */
public interface CustomerContacterMapper 
{
    /**
     * 查询客户联系人
     * 
     * @param id 客户联系人主键
     * @return 客户联系人
     */
    CustomerContacter selectCustomerContacterById(Long id);

    /**
     * 查询客户联系人列表
     * 
     * @param contacter 客户联系人
     * @return 客户联系人集合
     */
    List<CustomerContacter> selectCustomerContacterList(CustomerContacter contacter);

    /**
     * 新增客户联系人
     * 
     * @param contacter 客户联系人
     * @return 结果
     */
    int insertCustomerContacter(CustomerContacter contacter);

    /**
     * 修改客户联系人
     * 
     * @param contacter 客户联系人
     * @return 结果
     */
    int updateCustomerContacter(CustomerContacter contacter);

    /**
     * 删除客户联系人
     * 
     * @param id 客户联系人主键
     * @return 结果
     */
    int deleteCustomerContacterById(Long id);

    /**
     * 批量删除客户联系人
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteCustomerContacterByIds(Long[] ids);
}
