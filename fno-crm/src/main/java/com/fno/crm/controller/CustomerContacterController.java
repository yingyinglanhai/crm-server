package com.fno.crm.controller;

import com.fno.common.annotation.Log;
import com.fno.common.core.controller.BaseController;
import com.fno.common.core.domain.AjaxResult;
import com.fno.common.core.page.TableDataInfo;
import com.fno.common.enums.BusinessType;
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.crm.domain.CustomerContacter;
import com.fno.crm.service.CustomerContacterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 客户联系人Controller
 * 
 * @author fno
 * date 2024-07-12
 */
@RestController
@Api(tags = "客户联系人")
@RequestMapping("/crm/contacter")
public class CustomerContacterController extends BaseController
{
    @Autowired
    private CustomerContacterService customerContacterService;

    /**
     * 分页查询客户联系人列表
     */
    @ApiOperation(value = "查询客户联系人列表")
    @PreAuthorize("@ss.hasPermi('crm:contacter:list')")
    @GetMapping("/list")
    public TableDataInfo list(CustomerContacter contacter)
    {
        startPage();
        List<CustomerContacter> list = customerContacterService.selectCustomerContacterList(contacter);
        return getDataTable(list);
    }

    /**
     * 通过customerId 查询指定一个客户的联系人列表
     */
    @ApiOperation(value = "查询指定一个客户的联系人列表")
    @PreAuthorize("@ss.hasPermi('crm:contacter:list')")
    @GetMapping(value = "/listByCustomerId/{customerId}")
    public AjaxResult listByCustomerId(@PathVariable("customerId") Long customerId)
    {
        CustomerContacter contacter = new CustomerContacter();
        contacter.setCustomerId(customerId);
        List<CustomerContacter> list = customerContacterService.selectCustomerContacterList(contacter);
        return success(list);
    }
    /**
     * 导出客户联系人列表
     */
    @ApiOperation(value = "导出客户联系人列表")
    @PreAuthorize("@ss.hasPermi('crm:contacter:export')")
    @Log(title = "客户联系人", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CustomerContacter contacter)
    {
        List<CustomerContacter> list = customerContacterService.selectCustomerContacterList(contacter);
        ExcelUtil<CustomerContacter> util = new ExcelUtil<CustomerContacter>(CustomerContacter.class);
        util.exportExcel(response, list, "客户联系人数据");
    }

    /**
     * 获取客户联系人详细信息
     */
    @ApiOperation(value = "获取客户联系人详细信息")
    @PreAuthorize("@ss.hasPermi('crm:contacter:query')")
    @GetMapping(value = "/{contacterId}")
    public AjaxResult getInfo(@PathVariable("contacterId") Long contacterId)
    {
        return AjaxResult.success(customerContacterService.selectCustomerContacterById(contacterId));
    }

    /**
     * 新增客户联系人
     */
    @ApiOperation(value = "新增客户联系人")
    @PreAuthorize("@ss.hasPermi('crm:contacter:add')")
    @Log(title = "客户联系人", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CustomerContacter customerContacter)
    {
        customerContacterService.insertCustomerContacter(customerContacter);
        return success(customerContacter);
    }

    /**
     * 修改客户联系人
     */
    @ApiOperation(value = "修改客户联系人")
    @PreAuthorize("@ss.hasPermi('fno-crm:contacter:edit')")
    @Log(title = "客户联系人", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CustomerContacter customerContacter)
    {
        customerContacterService.updateCustomerContacter(customerContacter);
        return success(customerContacter);
    }

    /**
     * 删除客户联系人
     */
    @ApiOperation(value = "删除客户联系人")
    @PreAuthorize("@ss.hasPermi('fno-crm:contacter:remove')")
    @Log(title = "客户联系人", businessType = BusinessType.DELETE)
	@DeleteMapping("/{contacterIds}")
    public AjaxResult remove(@PathVariable Long[] contacterIds)
    {
        return toAjax(customerContacterService.deleteCustomerContacterByIds(contacterIds));
    }
}
