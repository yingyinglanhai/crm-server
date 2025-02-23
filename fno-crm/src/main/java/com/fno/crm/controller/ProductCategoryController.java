package com.fno.crm.controller;

import com.fno.common.annotation.Log;
import com.fno.common.core.controller.BaseController;
import com.fno.common.core.domain.AjaxResult;
import com.fno.common.core.page.TableDataInfo;
import com.fno.common.enums.BusinessType;
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.crm.domain.ProductCategory;
import com.fno.crm.service.ProductCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 产品分类Controller
 * 
 * @author fno
 * date 2024-06-27
 */
@RestController
@Api(tags = "产品分类")
@RequestMapping("/crm/productCategory")
public class ProductCategoryController extends BaseController
{
    @Autowired
    private ProductCategoryService ProductCategoryService;

    /**
     * 查询产品分类列表
     */
    @ApiOperation(value = "查询产品分类列表")
    @PreAuthorize("@ss.hasPermi('crm:productCategory:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProductCategory productCategory)
    {
        startPage();
        List<ProductCategory> list = ProductCategoryService.selectProductCategoryList(productCategory);
        return getDataTable(list);
    }
    /**
     * 查询全部产品分类
     */
    @ApiOperation(value = "查询全部产品分类")
    @PreAuthorize("@ss.hasPermi('crm:productCategory:list')")
    @GetMapping("/listAll")
    public AjaxResult listAll(ProductCategory productCategory)
    {
        if (productCategory == null) productCategory = new ProductCategory();
        List<ProductCategory> list = ProductCategoryService.selectProductCategoryList(productCategory);
        return AjaxResult.success(list);
    }
    /**
     * 导出产品分类列表
     */
    @ApiOperation(value = "导出产品分类列表")
    @PreAuthorize("@ss.hasPermi('crm:productCategory:export')")
    @Log(title = "产品分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProductCategory productCategory)
    {
        List<ProductCategory> list = ProductCategoryService.selectProductCategoryList(productCategory);
        ExcelUtil<ProductCategory> util = new ExcelUtil<ProductCategory>(ProductCategory.class);
        util.exportExcel(response, list, "产品分类数据");
    }

    /**
     * 获取产品分类详细信息
     */
    @ApiOperation(value = "获取产品分类详细信息")
    @PreAuthorize("@ss.hasPermi('crm:productCategory:query')")
    @GetMapping(value = "/{categoryId}")
    public AjaxResult getInfo(@PathVariable("categoryId") Long categoryId)
    {
        return AjaxResult.success(ProductCategoryService.selectProductCategoryByCategoryId(categoryId));
    }

    /**
     * 新增产品分类
     */
    @ApiOperation(value = "新增产品分类")
    @PreAuthorize("@ss.hasPermi('crm:productCategory:add')")
    @Log(title = "产品分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProductCategory ProductCategory)
    {
        ProductCategoryService.insertProductCategory(ProductCategory);
        return success(ProductCategory);
    }

    /**
     * 修改产品分类
     */
    @ApiOperation(value = "修改产品分类")
    @PreAuthorize("@ss.hasPermi('crm:productCategory:edit')")
    @Log(title = "产品分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProductCategory ProductCategory)
    {
        ProductCategoryService.updateProductCategory(ProductCategory);
        return success(ProductCategory);
    }

    /**
     * 删除产品分类
     */
    @ApiOperation(value = "删除产品分类")
    @PreAuthorize("@ss.hasPermi('crm:productCategory:remove')")
    @Log(title = "产品分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{categoryIds}")
    public AjaxResult remove(@PathVariable Long[] categoryIds)
    {
        return toAjax(ProductCategoryService.deleteProductCategoryByCategoryIds(categoryIds));
    }
}
