package com.fno.crm.controller;

import com.fno.common.annotation.Log;
import com.fno.common.core.controller.BaseController;
import com.fno.common.core.domain.AjaxResult;
import com.fno.common.core.page.TableDataInfo;
import com.fno.common.enums.BusinessType;
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.crm.domain.Contract;
import com.fno.crm.service.ContractService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 合同Controller
 * 
 * @author fno
 * date 2024-07-25
 */
@RestController
@Api(tags = "合同")
@RequestMapping("/crm/contract")
public class ContractController extends BaseController
{
    @Autowired
    private ContractService contractService;

    /**
     * 查询合同列表
     */
    @ApiOperation(value = "查询合同列表")
    @PreAuthorize("@ss.hasPermi('crm:contract:list')")
    @GetMapping("/list")
    public TableDataInfo list(Contract contract)
    {
        startPage();
        List<Contract> list = contractService.selectContractList(contract);
        return getDataTable(list);
    }

    /**
     * 导出合同列表
     */
    @ApiOperation(value = "导出合同列表")
    @PreAuthorize("@ss.hasPermi('crm:contract:export')")
    @Log(title = "合同", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Contract contract)
    {
        List<Contract> list = contractService.selectContractList(contract);
        ExcelUtil<Contract> util = new ExcelUtil<Contract>(Contract.class);
        util.exportExcel(response, list, "合同数据");
    }

    /**
     * 获取合同详细信息
     */
    @ApiOperation(value = "获取合同详细信息")
    @PreAuthorize("@ss.hasPermi('crm:contract:query')")
    @GetMapping(value = "/{contractId}")
    public AjaxResult getInfo(@PathVariable("contractId") Long contractId)
    {
        return AjaxResult.success(contractService.selectContractByContractId(contractId));
    }

    /**
     * 新增合同
     */
    @ApiOperation(value = "新增合同")
    @PreAuthorize("@ss.hasPermi('crm:contract:add')")
    @Log(title = "合同", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Contract contract)
    {
        contractService.insertContract(contract);
        return success(contract);
    }

    /**
     * 修改合同
     */
    @ApiOperation(value = "修改合同")
    @PreAuthorize("@ss.hasPermi('crm:contract:edit')")
    @Log(title = "合同", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Contract contract)
    {
        contractService.updateContract(contract);
        return success(contract);
    }

    /**
     * 删除合同
     */
    @ApiOperation(value = "删除合同")
    @PreAuthorize("@ss.hasPermi('crm:contract:remove')")
    @Log(title = "合同", businessType = BusinessType.DELETE)
	@DeleteMapping("/{contractIds}")
    public AjaxResult remove(@PathVariable Long[] contractIds)
    {
        return toAjax(contractService.deleteContractByIds(contractIds));
    }
}
