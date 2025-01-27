package com.mcloud.matrix.outstock.mybatis.mapper;

import com.mcloud.matrix.outstock.mybatis.entity.TGmOutstockSku;
import com.mcloud.matrix.outstock.mybatis.entity.TGmOutstockSkuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TGmOutstockSkuMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_gm_outstock_sku
     *
     * @mbg.generated
     */
    long countByExample(TGmOutstockSkuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_gm_outstock_sku
     *
     * @mbg.generated
     */
    int deleteByExample(TGmOutstockSkuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_gm_outstock_sku
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_gm_outstock_sku
     *
     * @mbg.generated
     */
    int insert(TGmOutstockSku record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_gm_outstock_sku
     *
     * @mbg.generated
     */
    int insertSelective(TGmOutstockSku record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_gm_outstock_sku
     *
     * @mbg.generated
     */
    List<TGmOutstockSku> selectByExample(TGmOutstockSkuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_gm_outstock_sku
     *
     * @mbg.generated
     */
    TGmOutstockSku selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_gm_outstock_sku
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") TGmOutstockSku record, @Param("example") TGmOutstockSkuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_gm_outstock_sku
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") TGmOutstockSku record, @Param("example") TGmOutstockSkuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_gm_outstock_sku
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TGmOutstockSku record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_gm_outstock_sku
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TGmOutstockSku record);
}