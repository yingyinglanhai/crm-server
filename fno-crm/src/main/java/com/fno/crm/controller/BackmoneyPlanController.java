package com.fno.crm.controller;

import com.fno.common.annotation.Log;
import com.fno.common.core.controller.BaseController;
import com.fno.common.core.domain.AjaxResult;
import com.fno.common.core.page.TableDataInfo;
import com.fno.common.enums.BusinessType;
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.crm.domain.BackmoneyPlan;
import com.fno.crm.service.BackmoneyPlanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 回款计划Controller
 * 
 * @author fno
 * @date 2024-07-26
 */
@RestController
@Api(tags = "回款计划")
@RequestMapping("/crm/backmoneyPlan")
public class BackmoneyPlanController extends BaseController
{
    @Autowired
    private BackmoneyPlanService backmoneyPlanService;

    /**
     * 查询回款计划列表
     */
    @ApiOperation(value = "查询回款计划列表")
    @PreAuthorize("@ss.hasPermi('crm:backmoneyPlan:list')")
    @GetMapping("/list")
    public TableDataInfo list(BackmoneyPlan backmoneyPlan)
    {
        startPage();
        List<BackmoneyPlan> list = backmoneyPlanService.selectBackmoneyPlanList(backmoneyPlan);
        return getDataTable(list);
    }

    /**
     * 通过contractId 查询指定合同id的回款计划表
     */
    @ApiOperation(value = "查询指定合同id的回款计划表")
    @PreAuthorize("@ss.hasPermi('crm:backmoneyPlan:list')")
    @GetMapping(value = "/listByContractId/{contractId}")
    public AjaxResult listByContractId(@PathVariable("contractId") Long contractId)
    {
        BackmoneyPlan plan = new BackmoneyPlan();
        plan.setContractId(contractId);
        List<BackmoneyPlan> list = backmoneyPlanService.selectBackmoneyPlanList(plan);
        return success(list);
    }
    /**
     * 导出回款计划列表
     */
    @ApiOperation(value = "导出回款计划列表")
    @PreAuthorize("@ss.hasPermi('crm:backmoneyPlan:export')")
    @Log(title = "回款计划", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BackmoneyPlan backmoneyPlan)
    {
        List<BackmoneyPlan> list = backmoneyPlanService.selectBackmoneyPlanList(backmoneyPlan);
        ExcelUtil<BackmoneyPlan> util = new ExcelUtil<BackmoneyPlan>(BackmoneyPlan.class);
        util.exportExcel(response, list, "回款计划数据");
    }

    /**
     * 获取回款计划详细信息
     */
    @ApiOperation(value = "获取回款计划详细信息")
    @PreAuthorize("@ss.hasPermi('crm:backmoneyPlan:query')")
    @GetMapping(value = "/{backmoneyPlanId}")
    public AjaxResult getInfo(@PathVariable("backmoneyPlanId") Long backmoneyPlanId)
    {
        return AjaxResult.success(backmoneyPlanService.selectBackmoneyPlanById(backmoneyPlanId));
    }

    /**
     * 新增回款计划
     */
    @ApiOperation(value = "新增回款计划")
    @PreAuthorize("@ss.hasPermi('crm:backmoneyPlan:add')")
    @Log(title = "回款计划", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BackmoneyPlan backmoneyPlan)
    {
        backmoneyPlanService.insertBackmoneyPlan(backmoneyPlan);
        return success(backmoneyPlan);
    }

    /**
     * 修改回款计划
     */
    @ApiOperation(value = "修改回款计划")
    @PreAuthorize("@ss.hasPermi('crm:backmoneyPlan:edit')")
    @Log(title = "回款计划", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BackmoneyPlan backmoneyPlan)
    {
        backmoneyPlanService.updateBackmoneyPlan(backmoneyPlan);
        return success(backmoneyPlan);
    }

    /**
     * 删除回款计划
     */
    @ApiOperation(value = "删除回款计划")
    @PreAuthorize("@ss.hasPermi('crm:backmoneyPlan:remove')")
    @Log(title = "回款计划", businessType = BusinessType.DELETE)
	@DeleteMapping("/{backmoneyPlanIds}")
    public AjaxResult remove(@PathVariable Long[] backmoneyPlanIds)
    {
        return toAjax(backmoneyPlanService.deleteBackmoneyPlanByIds(backmoneyPlanIds));
    }
}
