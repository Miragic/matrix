package com.mcloud.matrix.outstock.mybatis.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Database Table Remarks:
 *   光明商品表
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table t_gm_outstock_sku
 */
public class TGmOutstockSku implements Serializable {
    /**
     * Database Column Remarks:
     *   主键 ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_gm_outstock_sku.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * Database Column Remarks:
     *   商品类别
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_gm_outstock_sku.sku_category
     *
     * @mbg.generated
     */
    private String skuCategory;

    /**
     * Database Column Remarks:
     *   商品名称
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_gm_outstock_sku.sku_name
     *
     * @mbg.generated
     */
    private String skuName;

    /**
     * Database Column Remarks:
     *   商品单位
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_gm_outstock_sku.sku_unit
     *
     * @mbg.generated
     */
    private String skuUnit;

    /**
     * Database Column Remarks:
     *   商品单价
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_gm_outstock_sku.sku_price
     *
     * @mbg.generated
     */
    private BigDecimal skuPrice;

    /**
     * Database Column Remarks:
     *   商品金额
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_gm_outstock_sku.sku_amount
     *
     * @mbg.generated
     */
    private BigDecimal skuAmount;

    /**
     * Database Column Remarks:
     *   备注
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_gm_outstock_sku.remark
     *
     * @mbg.generated
     */
    private String remark;

    /**
     * Database Column Remarks:
     *   创建人
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_gm_outstock_sku.create_user
     *
     * @mbg.generated
     */
    private String createUser;

    /**
     * Database Column Remarks:
     *   创建时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_gm_outstock_sku.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * Database Column Remarks:
     *   修改人
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_gm_outstock_sku.modify_user
     *
     * @mbg.generated
     */
    private String modifyUser;

    /**
     * Database Column Remarks:
     *   修改时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_gm_outstock_sku.modify_time
     *
     * @mbg.generated
     */
    private Date modifyTime;

    /**
     * Database Column Remarks:
     *   多少 kg
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_gm_outstock_sku.many_kg
     *
     * @mbg.generated
     */
    private BigDecimal manyKg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_gm_outstock_sku
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_gm_outstock_sku.id
     *
     * @return the value of t_gm_outstock_sku.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_gm_outstock_sku.id
     *
     * @param id the value for t_gm_outstock_sku.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_gm_outstock_sku.sku_category
     *
     * @return the value of t_gm_outstock_sku.sku_category
     *
     * @mbg.generated
     */
    public String getSkuCategory() {
        return skuCategory;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_gm_outstock_sku.sku_category
     *
     * @param skuCategory the value for t_gm_outstock_sku.sku_category
     *
     * @mbg.generated
     */
    public void setSkuCategory(String skuCategory) {
        this.skuCategory = skuCategory;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_gm_outstock_sku.sku_name
     *
     * @return the value of t_gm_outstock_sku.sku_name
     *
     * @mbg.generated
     */
    public String getSkuName() {
        return skuName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_gm_outstock_sku.sku_name
     *
     * @param skuName the value for t_gm_outstock_sku.sku_name
     *
     * @mbg.generated
     */
    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_gm_outstock_sku.sku_unit
     *
     * @return the value of t_gm_outstock_sku.sku_unit
     *
     * @mbg.generated
     */
    public String getSkuUnit() {
        return skuUnit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_gm_outstock_sku.sku_unit
     *
     * @param skuUnit the value for t_gm_outstock_sku.sku_unit
     *
     * @mbg.generated
     */
    public void setSkuUnit(String skuUnit) {
        this.skuUnit = skuUnit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_gm_outstock_sku.sku_price
     *
     * @return the value of t_gm_outstock_sku.sku_price
     *
     * @mbg.generated
     */
    public BigDecimal getSkuPrice() {
        return skuPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_gm_outstock_sku.sku_price
     *
     * @param skuPrice the value for t_gm_outstock_sku.sku_price
     *
     * @mbg.generated
     */
    public void setSkuPrice(BigDecimal skuPrice) {
        this.skuPrice = skuPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_gm_outstock_sku.sku_amount
     *
     * @return the value of t_gm_outstock_sku.sku_amount
     *
     * @mbg.generated
     */
    public BigDecimal getSkuAmount() {
        return skuAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_gm_outstock_sku.sku_amount
     *
     * @param skuAmount the value for t_gm_outstock_sku.sku_amount
     *
     * @mbg.generated
     */
    public void setSkuAmount(BigDecimal skuAmount) {
        this.skuAmount = skuAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_gm_outstock_sku.remark
     *
     * @return the value of t_gm_outstock_sku.remark
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_gm_outstock_sku.remark
     *
     * @param remark the value for t_gm_outstock_sku.remark
     *
     * @mbg.generated
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_gm_outstock_sku.create_user
     *
     * @return the value of t_gm_outstock_sku.create_user
     *
     * @mbg.generated
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_gm_outstock_sku.create_user
     *
     * @param createUser the value for t_gm_outstock_sku.create_user
     *
     * @mbg.generated
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_gm_outstock_sku.create_time
     *
     * @return the value of t_gm_outstock_sku.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_gm_outstock_sku.create_time
     *
     * @param createTime the value for t_gm_outstock_sku.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_gm_outstock_sku.modify_user
     *
     * @return the value of t_gm_outstock_sku.modify_user
     *
     * @mbg.generated
     */
    public String getModifyUser() {
        return modifyUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_gm_outstock_sku.modify_user
     *
     * @param modifyUser the value for t_gm_outstock_sku.modify_user
     *
     * @mbg.generated
     */
    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_gm_outstock_sku.modify_time
     *
     * @return the value of t_gm_outstock_sku.modify_time
     *
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_gm_outstock_sku.modify_time
     *
     * @param modifyTime the value for t_gm_outstock_sku.modify_time
     *
     * @mbg.generated
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_gm_outstock_sku.many_kg
     *
     * @return the value of t_gm_outstock_sku.many_kg
     *
     * @mbg.generated
     */
    public BigDecimal getManyKg() {
        return manyKg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_gm_outstock_sku.many_kg
     *
     * @param manyKg the value for t_gm_outstock_sku.many_kg
     *
     * @mbg.generated
     */
    public void setManyKg(BigDecimal manyKg) {
        this.manyKg = manyKg;
    }
}