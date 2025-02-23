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
@RequestMapping("/crm/myClue")
public class MyClueController extends BaseController
{
    @Autowired
    private ClueService clueService;

    /**
     * 查询客户管理列表
     */
    @ApiOperation(value = "查询客户管理列表")
    @PreAuthorize("@ss.hasPermi('crm:myclue:list')")
    @GetMapping("/list")
    public TableDataInfo list(Clue clue)
    {
        startPage();
        //设置认领人id条件
        clue.setClaimUserId(SecurityUtils.getUserId());
        List<Clue> list = clueService.selectClueList(clue);
        return getDataTable(list);
    }
    /**
     * 导出客户管理列表
     */
    @ApiOperation(value = "导出客户管理列表")
    @PreAuthorize("@ss.hasPermi('crm:myclue:export')")
    @Log(title = "客户管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Clue clue)
    {
        //设置认领人id条件
        clue.setClaimUserId(SecurityUtils.getUserId());
        List<Clue> list = clueService.selectClueList(clue);
        ExcelUtil<Clue> util = new ExcelUtil<Clue>(Clue.class);
        util.exportExcel(response, list, "客户管理数据");
    }

    /**
     * 获取客户管理详细信息
     */
    @ApiOperation(value = "获取客户管理详细信息")
    @PreAuthorize("@ss.hasPermi('crm:myclue:query')")
    @GetMapping(value = "/{clueId}")
    public AjaxResult getInfo(@PathVariable("clueId") Long clueId)
    {
        return AjaxResult.success(clueService.selectClueById(clueId));
    }

    /**
     * 新增客户管理
     */
    @ApiOperation(value = "新增客户管理")
    @PreAuthorize("@ss.hasPermi('crm:myclue:add')")
    @Log(title = "客户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Clue clue)
    {
        //初始化认领人id
        clue.setClaimUserId(SecurityUtils.getUserId());
        clueService.insertClue(clue);
        return success(clue);
    }

    /**
     * 修改客户管理
     */
    @ApiOperation(value = "修改客户管理")
    @PreAuthorize("@ss.hasPermi('crm:myclue:edit')")
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
    @PreAuthorize("@ss.hasPermi('crm:myclue:remove')")
    @Log(title = "客户管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{clueIds}")
    public AjaxResult remove(@PathVariable Long[] clueIds)
    {
        return toAjax(clueService.deleteClueByIds(clueIds));
    }
    /**
     * 认领客户
     */
    @ApiOperation(value = "退回公海")
    @PreAuthorize("@ss.hasPermi('crm:myclue:edit')")
    @Log(title = "线索管理", businessType = BusinessType.UPDATE)
    @PutMapping("/return")
    public AjaxResult returnBack(@RequestBody Clue clue)
    {
        //补充认领人id
        clue.setClaimUserId(0L);
        clueService.updateClue(clue);
        return success(clue);
    }
    /**
     * 转化为客户
     */
    @ApiOperation(value = "转化为客户")
    @PreAuthorize("@ss.hasPermi('crm:myclue:edit')")
    @Log(title = "线索管理", businessType = BusinessType.UPDATE)
    @PutMapping("/convert")
    public AjaxResult convertToCustomer(@RequestBody Clue clue)
    {
        clueService.convertToCustomer(clue.getClueId());
        return success(clue);
    }
}
