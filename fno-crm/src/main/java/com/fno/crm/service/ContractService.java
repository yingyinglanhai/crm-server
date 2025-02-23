package com.fno.crm.service;

import cn.hutool.core.date.DateUtil;
import com.fno.common.utils.DateUtils;
import com.fno.common.utils.SecurityUtils;
import com.fno.crm.domain.CompStat;
import com.fno.crm.domain.Contract;
import com.fno.crm.mapper.ContractMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 合同Service业务层处理
 *
 * @author fno
 * date 2024-07-25
 */
@Service
public class ContractService
{
    @Autowired
    private ContractMapper contractMapper;

    @Autowired
    private CompStatService compStatService;

    /**
     * 查询合同
     *
     * @param contractId 合同主键
     * @return 合同
     */
    public Contract selectContractByContractId(Long contractId)
    {
        return contractMapper.selectContractByContractId(contractId);
    }

    /**
     * 查询合同列表
     *
     * @param contract 合同
     * @return 合同
     */
    public List<Contract> selectContractList(Contract contract)
    {
        //添加租户id条件
        contract.setTenantId(SecurityUtils.getTenantId());
        return contractMapper.selectContractList(contract);
    }

    /**
     * 新增合同
     *
     * @param contract 合同
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public int insertContract(Contract contract)
    {
        contract.setCreatedBy(SecurityUtils.getUserId());
        contract.setCreateTime(DateUtils.getNowDate());
        //补充租户id
        contract.setTenantId(SecurityUtils.getTenantId());
        contractMapper.insertContract(contract);
        //同步更新统计
        CompStat compStat = new CompStat();
        compStat.setTenantId(SecurityUtils.getTenantId());
        compStat.setStatDate(DateUtil.format(contract.getSignDate(), "yyyyMM"));
        //统计值需要增加合同金额
        compStat.setContractTotal(contract.getContractMoney());
        return compStatService.updateCompStat(compStat);
    }

    /**
     * 修改合同
     *
     * @param contract 合同
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public int updateContract(Contract contract)
    {
        //先取出旧记录
        Contract oldContract = contractMapper.selectContractByContractId(contract.getContractId());
        contract.setUpdatedBy(SecurityUtils.getUserId());
        contract.setUpdateTime(DateUtils.getNowDate());
        contractMapper.updateContract(contract);
        //同步更新统计
        CompStat compStat = new CompStat();
        compStat.setTenantId(SecurityUtils.getTenantId());
        compStat.setStatDate(DateUtil.format(oldContract.getSignDate(), "yyyyMM"));
        //统计值需要减去合同金额
        compStat.setContractTotal(contract.getContractMoney().subtract(oldContract.getContractMoney()));
        return compStatService.updateCompStat(compStat);
    }

    /**
     * 批量删除合同
     * @param contractIds 需要删除的合同主键
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public int deleteContractByIds(Long[] contractIds)
    {
        //通过id取出对应的合同
        Contract contract = contractMapper.selectContractByContractId(contractIds[0]);
        contractMapper.deleteContractByContractIds(contractIds);
        //同步更新统计
        CompStat compStat = new CompStat();
        compStat.setTenantId(SecurityUtils.getTenantId());
        compStat.setStatDate(DateUtil.format(contract.getSignDate(), "yyyyMM"));
        //统计值需要减去合同金额
        compStat.setContractTotal(contract.getContractMoney().negate());
        return compStatService.updateCompStat(compStat);
    }

    /**
     * 删除合同信息
     *
     * @param contractId 合同主键
     * @return 结果
     */
    public int deleteContractByContractId(Long contractId)
    {
        return contractMapper.deleteContractByContractId(contractId);
    }
}
