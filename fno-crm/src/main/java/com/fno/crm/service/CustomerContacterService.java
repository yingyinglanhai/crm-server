package com.fno.crm.service;

import com.fno.common.utils.DateUtils;
import com.fno.common.utils.SecurityUtils;
import com.fno.crm.domain.CustomerContacter;
import com.fno.crm.mapper.CustomerContacterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 客户联系人Service业务层处理
 *
 * @author fno
 * date 2024-07-12
 */
@Service
public class CustomerContacterService
{
    @Autowired
    private CustomerContacterMapper customerContacterMapper;

    /**
     * 查询客户联系人
     *
     * @param id 客户联系人主键
     * @return 客户联系人
     */
    public CustomerContacter selectCustomerContacterById(Long id)
    {
        return customerContacterMapper.selectCustomerContacterById(id);
    }

    /**
     * 查询客户联系人列表
     *
     * @param contacter 客户联系人
     * @return 客户联系人
     */
    public List<CustomerContacter> selectCustomerContacterList(CustomerContacter contacter)
    {
        //补充租户ID
        contacter.setTenantId(SecurityUtils.getTenantId());
        return customerContacterMapper.selectCustomerContacterList(contacter);
    }

    /**
     * 新增客户联系人
     *
     * @param contacter 客户联系人
     * @return 结果
     */
    public int insertCustomerContacter(CustomerContacter contacter)
    {
        contacter.setCreatedBy(SecurityUtils.getUserId());
        contacter.setCreateTime(DateUtils.getNowDate());
        contacter.setTenantId(SecurityUtils.getTenantId());
        return customerContacterMapper.insertCustomerContacter(contacter);
    }

    /**
     * 修改客户联系人
     *
     * @param contacter 客户联系人
     * @return 结果
     */
    public int updateCustomerContacter(CustomerContacter contacter)
    {
        contacter.setUpdatedBy(SecurityUtils.getUserId());
        contacter.setUpdateTime(DateUtils.getNowDate());
        return customerContacterMapper.updateCustomerContacter(contacter);
    }

    /**
     * 批量删除客户联系人
     *
     * @param ids 需要删除的客户联系人主键
     * @return 结果
     */
    public int deleteCustomerContacterByIds(Long[] ids)
    {
        return customerContacterMapper.deleteCustomerContacterByIds(ids);
    }

    /**
     * 删除客户联系人信息
     *
     * @param id 客户联系人主键
     * @return 结果
     */
    public int deleteCustomerContacterById(Long id)
    {
        return customerContacterMapper.deleteCustomerContacterById(id);
    }
}
