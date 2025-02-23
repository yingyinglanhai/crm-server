package com.fno.crm.controller;

import com.fno.common.annotation.Log;
import com.fno.common.core.controller.BaseController;
import com.fno.common.core.domain.AjaxResult;
import com.fno.common.core.page.TableDataInfo;
import com.fno.common.enums.BusinessType;
import com.fno.common.utils.SecurityUtils;
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.crm.domain.Customer;
import com.fno.crm.service.CustomerService;
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
@RequestMapping("/crm/customer")
public class CustomerController extends BaseController
{
    @Autowired
    private CustomerService customerService;

    /**
     * 查询客户管理列表
     */
    @ApiOperation(value = "查询客户管理列表")
    @PreAuthorize("@ss.hasPermi('crm:customer:list')")
    @GetMapping("/list")
    public TableDataInfo list(Customer customer)
    {
        startPage();
        List<Customer> list = customerService.selectCustomerList(customer);
        return getDataTable(list);
    }

    /**
     * 查询符合条件的全部客户列表
     */
    @ApiOperation(value = "查询符合条件的全部客户列表")
    @PreAuthorize("@ss.hasPermi('crm:customer:list')")
    @GetMapping("/listAll")
    public AjaxResult listAll(Customer customer)
    {
        //只查询属于当前用户的客户
        customer.setClaimUserId(SecurityUtils.getUserId());
        List<Customer> list = customerService.selectCustomerList(customer);
        return AjaxResult.success(list);
    }
    /**
     * 导出客户管理列表
     */
    @ApiOperation(value = "导出客户管理列表")
    @PreAuthorize("@ss.hasPermi('crm:customer:export')")
    @Log(title = "客户管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Customer customer)
    {
        List<Customer> list = customerService.selectCustomerList(customer);
        ExcelUtil<Customer> util = new ExcelUtil<Customer>(Customer.class);
        util.exportExcel(response, list, "客户管理数据");
    }

    /**
     * 获取客户管理详细信息
     */
    @ApiOperation(value = "获取客户管理详细信息")
    @PreAuthorize("@ss.hasPermi('crm:customer:query')")
    @GetMapping(value = "/{customerId}")
    public AjaxResult getInfo(@PathVariable("customerId") Long customerId)
    {
        return AjaxResult.success(customerService.selectCustomerById(customerId));
    }

    /**
     * 新增客户管理
     */
    @ApiOperation(value = "新增客户管理")
    @PreAuthorize("@ss.hasPermi('crm:customer:add')")
    @Log(title = "客户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Customer customer)
    {
        customerService.insertCustomer(customer);
        return success(customer);
    }

    /**
     * 修改客户管理
     */
    @ApiOperation(value = "修改客户管理")
    @PreAuthorize("@ss.hasPermi('crm:customer:edit')")
    @Log(title = "客户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Customer customer)
    {
        customerService.updateCustomer(customer);
        return success(customer);
    }

    /**
     * 删除客户管理
     */
    @ApiOperation(value = "删除客户管理")
    @PreAuthorize("@ss.hasPermi('crm:customer:remove')")
    @Log(title = "客户管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{customerIds}")
    public AjaxResult remove(@PathVariable Long[] customerIds)
    {
        return toAjax(customerService.deleteCustomerByIds(customerIds));
    }
    /**
     * 认领客户
     */
    @ApiOperation(value = "认领客户")
    @PreAuthorize("@ss.hasPermi('crm:customer:edit')")
    @Log(title = "客户管理", businessType = BusinessType.UPDATE)
    @PutMapping("/claim")
    public AjaxResult claim(@RequestBody Customer customer)
    {
        //补充认领人id
        customer.setClaimUserId(SecurityUtils.getUserId());
        customerService.updateCustomer(customer);
        return success(customer);
    }
}
