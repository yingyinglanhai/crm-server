package com.fno.crm.controller;

import com.fno.common.annotation.Log;
import com.fno.common.core.controller.BaseController;
import com.fno.common.core.domain.AjaxResult;
import com.fno.common.core.page.TableDataInfo;
import com.fno.common.enums.BusinessType;
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.crm.domain.CompStat;
import com.fno.crm.service.CompStatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 综合统计Controller
 * 
 * @author fno
 * date 2024-07-30
 */
@RestController
@Api(tags = "综合统计")
@RequestMapping("/crm/compStat")
public class CompStatController extends BaseController
{
    @Autowired
    private CompStatService compStatService;

    /**
     * 查询综合统计列表
     */
    @ApiOperation(value = "查询综合统计列表")
    @PreAuthorize("@ss.hasPermi('crm:compStat:list')")
    @GetMapping("/list")
    public TableDataInfo list(CompStat compStat)
    {
        startPage();
        List<CompStat> list = compStatService.selectCompStatList(compStat);
        return getDataTable(list);
    }

    /**
     * 导出综合统计列表
     */
    @ApiOperation(value = "导出综合统计列表")
    @PreAuthorize("@ss.hasPermi('crm:compStat:export')")
    @Log(title = "综合统计", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CompStat compStat)
    {
        List<CompStat> list = compStatService.selectCompStatList(compStat);
        ExcelUtil<CompStat> util = new ExcelUtil<CompStat>(CompStat.class);
        util.exportExcel(response, list, "综合统计数据");
    }

    /**
     * 新增综合统计
     */
    @ApiOperation(value = "新增综合统计")
    @PreAuthorize("@ss.hasPermi('crm:compStat:add')")
    @Log(title = "综合统计", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CompStat compStat)
    {
        compStatService.insertCompStat(compStat);
        return success(compStat);
    }

    /**
     * 修改综合统计
     */
    @ApiOperation(value = "修改综合统计")
    @PreAuthorize("@ss.hasPermi('crm:compStat:edit')")
    @Log(title = "综合统计", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CompStat compStat)
    {
        compStatService.updateCompStat(compStat);
        return success(compStat);
    }

    /**
     * 所有统计
     */
    @ApiOperation(value = "所有统计")
    @PreAuthorize("@ss.hasPermi('crm:compStat:query')")
    @GetMapping("/allStat")
    public AjaxResult allStat()
    {
        return AjaxResult.success(compStatService.selectAllCompStat());
    }

    /**
     * 按线索来源统计线索分布
     */
    @ApiOperation(value = "线索分布统计")
    @PreAuthorize("@ss.hasPermi('crm:compStat:query')")
    @GetMapping("/clueDistribe")
    public AjaxResult clueDistribe()
    {
        return AjaxResult.success(compStatService.selectClueStatBySource());
    }
    /**
     * 获取近一年的所有统计
     */
    @ApiOperation(value = "获取近一年的所有统计")
    @PreAuthorize("@ss.hasPermi('crm:compStat:query')")
    @GetMapping("/lastYearStat")
    public AjaxResult lastYearStat()
    {
        return AjaxResult.success(compStatService.selectLastYearStat());
    }
}
