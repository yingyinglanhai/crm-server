package com.fno.crm.mapper;

import com.fno.crm.domain.Contract;

import java.util.List;

/**
 * 合同Mapper接口
 * 
 * @author fno
 * date 2024-07-25
 */
public interface ContractMapper 
{
    /**
     * 查询合同
     * 
     * @param contractId 合同主键
     * @return 合同
     */
    public Contract selectContractByContractId(Long contractId);

    /**
     * 查询合同列表
     * 
     * @param contract 合同
     * @return 合同集合
     */
    public List<Contract> selectContractList(Contract contract);

    /**
     * 新增合同
     * 
     * @param contract 合同
     * @return 结果
     */
    public int insertContract(Contract contract);

    /**
     * 修改合同
     * 
     * @param contract 合同
     * @return 结果
     */
    public int updateContract(Contract contract);

    /**
     * 删除合同
     * 
     * @param contractId 合同主键
     * @return 结果
     */
    public int deleteContractByContractId(Long contractId);

    /**
     * 批量删除合同
     * 
     * @param contractIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteContractByContractIds(Long[] contractIds);
}
