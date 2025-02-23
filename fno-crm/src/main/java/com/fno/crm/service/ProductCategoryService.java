package com.fno.crm.service;

import com.fno.common.utils.DateUtils;
import com.fno.common.utils.SecurityUtils;
import com.fno.crm.domain.ProductCategory;
import com.fno.crm.mapper.ProductCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 产品分类Service业务层处理
 *
 * @author fno
 * date 2024-06-27
 */
@Service
public class ProductCategoryService
{
    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    /**
     * 查询产品分类
     *
     * @param categoryId 产品分类主键
     * @return 产品分类
     */
    public ProductCategory selectProductCategoryByCategoryId(Long categoryId)
    {
        return productCategoryMapper.selectProductCategoryByCategoryId(categoryId);
    }

    /**
     * 查询产品分类列表
     *
     * @param productCategory 产品分类
     * @return 产品分类
     */
    public List<ProductCategory> selectProductCategoryList(ProductCategory productCategory)
    {
        //增加租户条件
        productCategory.setTenantId(SecurityUtils.getTenantId());
        return productCategoryMapper.selectProductCategoryList(productCategory);
    }

    /**
     * 新增产品分类
     *
     * @param productCategory 产品分类
     * @return 结果
     */
    public int insertProductCategory(ProductCategory productCategory)
    {
        //补充值
        productCategory.setCreatedBy(SecurityUtils.getUserId());
        productCategory.setCreatedTime(DateUtils.getNowDate());
        productCategory.setTenantId(SecurityUtils.getTenantId());
        return productCategoryMapper.insertProductCategory(productCategory);
    }

    /**
     * 修改产品分类
     *
     * @param productCategory 产品分类
     * @return 结果
     */
    public int updateProductCategory(ProductCategory productCategory)
    {
        productCategory.setUpdatedBy(SecurityUtils.getUserId());
        productCategory.setUpdatedTime(DateUtils.getNowDate());
        return productCategoryMapper.updateProductCategory(productCategory);
    }

    /**
     * 批量删除产品分类
     *
     * @param categoryIds 需要删除的产品分类主键
     * @return 结果
     */
    public int deleteProductCategoryByCategoryIds(Long[] categoryIds)
    {
        return productCategoryMapper.deleteProductCategoryByCategoryIds(categoryIds);
    }

    /**
     * 删除产品分类信息
     *
     * @param categoryId 产品分类主键
     * @return 结果
     */
    public int deleteProductCategoryByCategoryId(Long categoryId)
    {
        return productCategoryMapper.deleteProductCategoryByCategoryId(categoryId);
    }
}
