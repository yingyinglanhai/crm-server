package com.fno.crm.mapper;

import com.fno.crm.domain.Product;

import java.util.List;

/**
 * 产品管理Mapper接口
 * 
 * @author fno
 * date 2024-07-02
 */
public interface ProductMapper
{
    /**
     * 查询产品管理
     * 
     * @param id 产品管理主键
     * @return 产品管理
     */
    Product selectProductById(Long id);

    /**
     * 查询产品管理列表
     * 
     * @param product 产品管理
     * @return 产品管理集合
     */
    List<Product> selectProductList(Product product);

    /**
     * 新增产品管理
     * 
     * @param product 产品管理
     * @return 结果
     */
    int insertProduct(Product product);

    /**
     * 修改产品管理
     * 
     * @param product 产品管理
     * @return 结果
     */
    int updateProduct(Product product);

    /**
     * 删除产品管理
     * 
     * @param id 产品管理主键
     * @return 结果
     */
    int deleteProductById(Long id);

    /**
     * 批量删除产品管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteProductByIds(Long[] ids);
}
