package com.fno.crm.controller;

import com.fno.common.annotation.Log;
import com.fno.common.core.controller.BaseController;
import com.fno.common.core.domain.AjaxResult;
import com.fno.common.core.page.TableDataInfo;
import com.fno.common.enums.BusinessType;
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.crm.domain.Chance;
import com.fno.crm.service.ChanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 商机Controller
 * 
 * @author fno
 * date 2024-07-21
 */
@RestController
@Api(tags = "商机")
@RequestMapping("/crm/chance")
public class ChanceController extends BaseController
{
    @Autowired
    private ChanceService chanceService;

    /**
     * 查询商机列表
     */
    @ApiOperation(value = "查询商机列表")
    @PreAuthorize("@ss.hasPermi('crm:chance:list')")
    @GetMapping("/list")
    public TableDataInfo list(Chance chance)
    {
        startPage();
        List<Chance> list = chanceService.selectChanceList(chance);
        return getDataTable(list);
    }

    /**
     * 导出商机列表
     */
    @ApiOperation(value = "导出商机列表")
    @PreAuthorize("@ss.hasPermi('crm:chance:export')")
    @Log(title = "商机", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Chance chance)
    {
        List<Chance> list = chanceService.selectChanceList(chance);
        ExcelUtil<Chance> util = new ExcelUtil<Chance>(Chance.class);
        util.exportExcel(response, list, "商机数据");
    }

    /**
     * 获取商机详细信息
     */
    @ApiOperation(value = "获取商机详细信息")
    @PreAuthorize("@ss.hasPermi('crm:chance:query')")
    @GetMapping(value = "/{chanceId}")
    public AjaxResult getInfo(@PathVariable("chanceId") Long chanceId)
    {
        return AjaxResult.success(chanceService.selectChanceByChanceId(chanceId));
    }

    /**
     * 新增商机
     */
    @ApiOperation(value = "新增商机")
    @PreAuthorize("@ss.hasPermi('crm:chance:add')")
    @Log(title = "商机", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Chance chance)
    {
        chanceService.insertChance(chance);
        return success(chance);
    }

    /**
     * 修改商机
     */
    @ApiOperation(value = "修改商机")
    @PreAuthorize("@ss.hasPermi('crm:chance:edit')")
    @Log(title = "商机", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Chance chance)
    {
        chanceService.updateChance(chance);
        return success(chance);
    }

    /**
     * 删除商机
     */
    @ApiOperation(value = "删除商机")
    @PreAuthorize("@ss.hasPermi('crm:chance:remove')")
    @Log(title = "商机", businessType = BusinessType.DELETE)
	@DeleteMapping("/{chanceIds}")
    public AjaxResult remove(@PathVariable Long[] chanceIds)
    {
        return toAjax(chanceService.deleteChanceByChanceIds(chanceIds));
    }
}
