package com.fno.crm.controller;

import com.fno.common.annotation.Log;
import com.fno.common.core.controller.BaseController;
import com.fno.common.core.domain.AjaxResult;
import com.fno.common.core.page.TableDataInfo;
import com.fno.common.enums.BusinessType;
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.crm.domain.ChanceFollow;
import com.fno.crm.service.ChanceFollowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 商机跟进Controller
 * 
 * @author fno
 * @date 2024-07-21
 */
@RestController
@Api(tags = "商机跟进")
@RequestMapping("/crm/chanceFollow")
public class ChanceFollowController extends BaseController
{
    @Autowired
    private ChanceFollowService chanceFollowService;

    /**
     * 查询商机跟进列表
     */
    @ApiOperation(value = "查询商机跟进列表")
    @PreAuthorize("@ss.hasPermi('crm:chanceFollow:list')")
    @GetMapping("/list")
    public TableDataInfo list(ChanceFollow chanceFollow)
    {
        startPage();
        List<ChanceFollow> list = chanceFollowService.selectChanceFollowList(chanceFollow);
        return getDataTable(list);
    }

    /**
     * 通过chanceId 查询指定商机id的商机跟进列表
     */
    @ApiOperation(value = "查询指定商机id的商机跟进列表")
    @PreAuthorize("@ss.hasPermi('crm:chanceFollow:list')")
    @GetMapping(value = "/listByChanceId/{chanceId}")
    public AjaxResult listByChanceId(@PathVariable("chanceId") Long chanceId)
    {
        ChanceFollow follow = new ChanceFollow();
        follow.setChanceId(chanceId);
        List<ChanceFollow> list = chanceFollowService.selectChanceFollowList(follow);
        return success(list);
    }

    /**
     * 导出商机跟进列表
     */
    @ApiOperation(value = "导出商机跟进列表")
    @PreAuthorize("@ss.hasPermi('crm:chanceFollow:export')")
    @Log(title = "商机跟进", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ChanceFollow chanceFollow)
    {
        List<ChanceFollow> list = chanceFollowService.selectChanceFollowList(chanceFollow);
        ExcelUtil<ChanceFollow> util = new ExcelUtil<ChanceFollow>(ChanceFollow.class);
        util.exportExcel(response, list, "商机跟进数据");
    }

    /**
     * 获取商机跟进详细信息
     */
    @ApiOperation(value = "获取商机跟进详细信息")
    @PreAuthorize("@ss.hasPermi('crm:chanceFollow:query')")
    @GetMapping(value = "/{followId}")
    public AjaxResult getInfo(@PathVariable("followId") Long followId)
    {
        return AjaxResult.success(chanceFollowService.selectChanceFollowByFollowId(followId));
    }

    /**
     * 新增商机跟进
     */
    @ApiOperation(value = "新增商机跟进")
    @PreAuthorize("@ss.hasPermi('crm:chanceFollow:add')")
    @Log(title = "商机跟进", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ChanceFollow chanceFollow)
    {
        chanceFollowService.insertChanceFollow(chanceFollow);
        return success(chanceFollow);
    }

    /**
     * 修改商机跟进
     */
    @ApiOperation(value = "修改商机跟进")
    @PreAuthorize("@ss.hasPermi('crm:chanceFollow:edit')")
    @Log(title = "商机跟进", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ChanceFollow chanceFollow)
    {
        chanceFollowService.updateChanceFollow(chanceFollow);
        return success(chanceFollow);
    }

    /**
     * 删除商机跟进
     */
    @ApiOperation(value = "删除商机跟进")
    @PreAuthorize("@ss.hasPermi('crm:chanceFollow:remove')")
    @Log(title = "商机跟进", businessType = BusinessType.DELETE)
	@DeleteMapping("/{followIds}")
    public AjaxResult remove(@PathVariable Long[] followIds)
    {
        return toAjax(chanceFollowService.deleteChanceFollowByFollowIds(followIds));
    }
}
