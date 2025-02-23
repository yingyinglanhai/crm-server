package com.fno.crm.controller;

import com.fno.common.annotation.Log;
import com.fno.common.core.controller.BaseController;
import com.fno.common.core.domain.AjaxResult;
import com.fno.common.core.page.TableDataInfo;
import com.fno.common.enums.BusinessType;
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.crm.domain.Backmoney;
import com.fno.crm.service.BackmoneyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 回款Controller
 * 
 * @author fno
 * date 2024-07-26
 */
@RestController
@Api(tags = "回款")
@RequestMapping("/crm/backmoney")
public class BackmoneyController extends BaseController
{
    @Autowired
    private BackmoneyService backmoneyService;

    /**
     * 查询回款列表
     */
    @ApiOperation(value = "查询回款列表")
    @PreAuthorize("@ss.hasPermi('crm:backmoney:list')")
    @GetMapping("/list")
    public TableDataInfo list(Backmoney backmoney)
    {
        startPage();
        List<Backmoney> list = backmoneyService.selectBackmoneyList(backmoney);
        return getDataTable(list);
    }

    /**
     * 通过contractId 查询指定合同id的回款表
     */
    @ApiOperation(value = "查询指定合同id的回款表")
    @PreAuthorize("@ss.hasPermi('crm:backmoney:list')")
    @GetMapping(value = "/listByContractId/{contractId}")
    public AjaxResult listByContractId(@PathVariable("contractId") Long contractId)
    {
        Backmoney backmoney = new Backmoney();
        backmoney.setContractId(contractId);
        List<Backmoney> list = backmoneyService.selectBackmoneyList(backmoney);
        return success(list);
    }
    /**
     * 导出回款列表
     */
    @ApiOperation(value = "导出回款列表")
    @PreAuthorize("@ss.hasPermi('crm:backmoney:export')")
    @Log(title = "回款", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Backmoney backmoney)
    {
        List<Backmoney> list = backmoneyService.selectBackmoneyList(backmoney);
        ExcelUtil<Backmoney> util = new ExcelUtil<Backmoney>(Backmoney.class);
        util.exportExcel(response, list, "回款数据");
    }

    /**
     * 获取回款详细信息
     */
    @ApiOperation(value = "获取回款详细信息")
    @PreAuthorize("@ss.hasPermi('crm:backmoney:query')")
    @GetMapping(value = "/{backmoneyId}")
    public AjaxResult getInfo(@PathVariable("backmoneyId") Long backmoneyId)
    {
        return AjaxResult.success(backmoneyService.selectBackmoneyById(backmoneyId));
    }

    /**
     * 新增回款
     */
    @ApiOperation(value = "新增回款")
    @PreAuthorize("@ss.hasPermi('crm:backmoney:add')")
    @Log(title = "回款", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Backmoney backmoney)
    {
        backmoneyService.insertBackmoney(backmoney);
        return success(backmoney);
    }

    /**
     * 修改回款
     */
    @ApiOperation(value = "修改回款")
    @PreAuthorize("@ss.hasPermi('crm:backmoney:edit')")
    @Log(title = "回款", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Backmoney backmoney)
    {
        backmoneyService.updateBackmoney(backmoney);
        return success(backmoney);
    }

    /**
     * 删除回款
     */
    @ApiOperation(value = "删除回款")
    @PreAuthorize("@ss.hasPermi('crm:backmoney:remove')")
    @Log(title = "回款", businessType = BusinessType.DELETE)
	@DeleteMapping("/{backmoneyIds}")
    public AjaxResult remove(@PathVariable Long[] backmoneyIds)
    {
        return toAjax(backmoneyService.deleteBackmoneyByIds(backmoneyIds));
    }
}
