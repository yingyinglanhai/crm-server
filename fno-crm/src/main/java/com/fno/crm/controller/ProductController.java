package com.fno.crm.controller;

import com.fno.common.annotation.Log;
import com.fno.common.core.controller.BaseController;
import com.fno.common.core.domain.AjaxResult;
import com.fno.common.core.page.TableDataInfo;
import com.fno.common.enums.BusinessType;
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.crm.domain.Product;
import com.fno.crm.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 产品管理Controller
 * 
 * @author fno
 * date 2024-07-02
 */
@RestController
@Api(tags = "产品管理")
@RequestMapping("/crm/product")
public class ProductController extends BaseController
{
    @Autowired
    private ProductService productService;

    /**
     * 查询产品管理列表
     */
    @ApiOperation(value = "查询产品列表")
    @PreAuthorize("@ss.hasPermi('crm:product:list')")
    @GetMapping("/list")
    public TableDataInfo list(Product product)
    {
        startPage();
        List<Product> list = productService.selectProductList(product);
        return getDataTable(list);
    }

    /**
     * 导出产品管理列表
     */
    @ApiOperation(value = "导出产品管理列表")
    @PreAuthorize("@ss.hasPermi('crm:product:export')")
    @Log(title = "产品管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Product product)
    {
        List<Product> list = productService.selectProductList(product);
        ExcelUtil<Product> util = new ExcelUtil<>(Product.class);
        util.exportExcel(response, list, "产品管理数据");
    }

    /**
     * 获取产品管理详细信息
     */
    @ApiOperation(value = "获取产品管理详细信息")
    @PreAuthorize("@ss.hasPermi('crm:product:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(productService.selectProductById(id));
    }

    /**
     * 新增产品管理
     */
    @ApiOperation(value = "新增产品管理")
    @PreAuthorize("@ss.hasPermi('crm:product:add')")
    @Log(title = "产品管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Product product)
    {
        productService.insertProduct(product);
        return success(product);
    }

    /**
     * 修改产品管理
     */
    @ApiOperation(value = "修改产品管理")
    @PreAuthorize("@ss.hasPermi('crm:product:edit')")
    @Log(title = "产品管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Product product)
    {
        productService.updateProduct(product);
        return success(product);
    }

    /**
     * 删除产品管理
     */
    @ApiOperation(value = "删除产品管理")
    @PreAuthorize("@ss.hasPermi('crm:product:remove')")
    @Log(title = "产品管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(productService.deleteProductByIds(ids));
    }
}
