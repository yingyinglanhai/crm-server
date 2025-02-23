package com.fno.crm.controller;

import com.fno.common.annotation.Log;
import com.fno.common.core.controller.BaseController;
import com.fno.common.core.domain.AjaxResult;
import com.fno.common.core.page.TableDataInfo;
import com.fno.common.enums.BusinessType;
import com.fno.common.utils.SecurityUtils;
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.crm.domain.Clue;
import com.fno.crm.service.ClueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 客户管理Controller
 * 
 * @author fno
 * date 2024-07-05
 */
@RestController
@Api(tags = "客户管理")
@RequestMapping("/crm/clue")
public class ClueController extends BaseController
{
    @Autowired
    private ClueService clueService;

    /**
     * 查询客户管理列表
     */
    @ApiOperation(value = "查询客户管理列表")
    @PreAuthorize("@ss.hasPermi('crm:clue:list')")
    @GetMapping("/list")
    public TableDataInfo list(Clue clue)
    {
        startPage();
        List<Clue> list = clueService.selectClueList(clue);
        return getDataTable(list);
    }
    /**
     * 导出客户管理列表
     */
    @ApiOperation(value = "导出客户管理列表")
    @PreAuthorize("@ss.hasPermi('crm:clue:export')")
    @Log(title = "客户管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Clue clue)
    {
        List<Clue> list = clueService.selectClueList(clue);
        ExcelUtil<Clue> util = new ExcelUtil<>(Clue.class);
        util.exportExcel(response, list, "客户管理数据");
    }

    /**
     * 获取客户管理详细信息
     */
    @ApiOperation(value = "获取客户管理详细信息")
    @PreAuthorize("@ss.hasPermi('crm:clue:query')")
    @GetMapping(value = "/{clueId}")
    public AjaxResult getInfo(@PathVariable("clueId") Long clueId)
    {
        return AjaxResult.success(clueService.selectClueById(clueId));
    }

    /**
     * 新增客户管理
     */
    @ApiOperation(value = "新增客户管理")
    @PreAuthorize("@ss.hasPermi('crm:clue:add')")
    @Log(title = "客户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Clue clue)
    {
        clueService.insertClue(clue);
        return success(clue);
    }

    /**
     * 修改客户管理
     */
    @ApiOperation(value = "修改客户管理")
    @PreAuthorize("@ss.hasPermi('crm:clue:edit')")
    @Log(title = "客户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Clue clue)
    {
        clueService.updateClue(clue);
        return success(clue);
    }

    /**
     * 删除客户管理
     */
    @ApiOperation(value = "删除客户管理")
    @PreAuthorize("@ss.hasPermi('crm:clue:remove')")
    @Log(title = "客户管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{clueIds}")
    public AjaxResult remove(@PathVariable Long[] clueIds)
    {
        return toAjax(clueService.deleteClueByIds(clueIds));
    }
    /**
     * 认领客户
     */
    @ApiOperation(value = "认领客户")
    @PreAuthorize("@ss.hasPermi('crm:clue:edit')")
    @Log(title = "客户管理", businessType = BusinessType.UPDATE)
    @PutMapping("/claim")
    public AjaxResult claim(@RequestBody Clue clue)
    {
        //补充认领人id
        clue.setClaimUserId(SecurityUtils.getUserId());
        clueService.updateClue(clue);
        return success(clue);
    }
}
