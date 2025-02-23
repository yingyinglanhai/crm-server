package com.fno.crm.service;

import cn.hutool.core.date.DateUtil;
import com.fno.common.utils.DateUtils;
import com.fno.common.utils.SecurityUtils;
import com.fno.crm.domain.CompStat;
import com.fno.crm.domain.Product;
import com.fno.crm.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 产品管理Service业务层处理
 *
 * @author fno
 * date 2024-07-02
 */
@Service
public class ProductService
{
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CompStatService compStatService;

    /**
     * 查询产品管理
     *
     * @param id 产品管理主键
     * @return 产品管理
     */
    public Product selectProductById(Long id)
    {
        return productMapper.selectProductById(id);
    }

    /**
     * 查询产品管理列表
     *
     * @param product 产品管理
     * @return 产品管理
     */
    public List<Product> selectProductList(Product product)
    {
        //添加租户id条件
        product.setTenantId(SecurityUtils.getTenantId());
        return productMapper.selectProductList(product);
    }

    /**
     * 新增产品管理
     *
     * @param product 产品管理
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public int insertProduct(Product product)
    {
        product.setCreatedBy(SecurityUtils.getUserId());
        product.setCreateTime(DateUtils.getNowDate());
        //补充租户id
        product.setTenantId(SecurityUtils.getTenantId());
        productMapper.insertProduct(product);
        //同步更新统计
        CompStat compStat = new CompStat();
        compStat.setTenantId(SecurityUtils.getTenantId());
        compStat.setStatDate(DateUtil.format(new Date(), "yyyyMM"));
        compStat.setProductTotal(1);
        return compStatService.updateCompStat(compStat);
    }

    /**
     * 修改产品管理
     *
     * @param product 产品管理
     * @return 结果
     */
    public int updateProduct(Product product)
    {
        product.setUpdatedBy(SecurityUtils.getUserId());
        product.setUpdateTime(DateUtils.getNowDate());
        return productMapper.updateProduct(product);
    }

    /**
     * 批量删除产品管理
     *
     * @param ids 需要删除的产品管理主键
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public int deleteProductByIds(Long[] ids)
    {
        //先取出要删除的记录
        Product product = productMapper.selectProductById(ids[0]);
        productMapper.deleteProductByIds(ids);
        //同步更新统计
        CompStat compStat = new CompStat();
        compStat.setTenantId(SecurityUtils.getTenantId());
        compStat.setStatDate(DateUtil.format(product.getCreateTime(), "yyyyMM"));
        compStat.setProductTotal(-ids.length);
        return compStatService.updateCompStat(compStat);
    }

    /**
     * 删除产品管理信息
     *
     * @param id 产品管理主键
     * @return 结果
     */
    public int deleteProductById(Long id)
    {
        return productMapper.deleteProductById(id);
    }
}
