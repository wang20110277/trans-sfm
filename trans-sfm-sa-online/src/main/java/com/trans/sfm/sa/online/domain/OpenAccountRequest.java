package com.trans.sfm.sa.online.domain;

import java.math.BigDecimal;

/**
 * 开户请求类
 */
public class OpenAccountRequest extends BaseRequest {
    /**
     * 交易账号
     */
    private String taClient;
    /**
     * 账户名
     */
    private String customerName;
    
    /**
     * 客户类型
     */
    private String customerType;
    
    /**
     * 业务类型
     */
    private String bizType;
    
    /**
     * 订单号
     */
    private String requestNo;
    
    /**
     * 申请时间
     */
    private String applyTime;
    
    /**
     * 申请日期
     */
    private String applyDate;
    
    /**
     * 账户介质号
     */
    private String bankCardNo;
    
    /**
     * 账户类型
     */
    private String bankCardType;
    
    /**
     * 付款方证件类型
     */
    private String idType;
    
    /**
     * 付款方证件编号
     */
    private String idNumber;
    
    /**
     * 付款卡绑定手机号
     */
    private String mobilePhone;
    
    /**
     * 证件有效期
     */
    private String idExpiryDate;
    
    /**
     * 签发机关
     */
    private String issuingAuthority;
    
    /**
     * 国籍
     */
    private String nation;
    
    /**
     * 出生日期
     */
    private String birthday;
    
    /**
     * 税收居民类型
     */
    private String taxResdType;
    
    /**
     * 学历
     */
    private String educationLevel;
    
    /**
     * 住宅地址/工作单位地址
     */
    private String address;
    
    /**
     * 邮编
     */
    private String postCode;
    
    /**
     * 省/直辖市
     */
    private String province;
    
    /**
     * 市
     */
    private String city;
    
    /**
     * 县
     */
    private String county;
    
    /**
     * 年收入
     */
    private BigDecimal annualIncome;
    
    /**
     * 职业代码
     */
    private String occupationType;
    
    /**
     * 风险等级
     */
    private String riskLevel;
    
    /**
     * 风险有效期截止日
     */
    private String riskDate;
    
    /**
     * 性别
     */
    private String gender;
    
    /**
     * TA代码
     */
    private String taCode;
    
    /**
     * 产品代码
     */
    private String productNo;
    
    /**
     * 扩展字段
     */
    private String extData;

    // Getter和Setter方法

    public String getTaClient() {
        return taClient;
    }

    public void setTaClient(String taClient) {
        this.taClient = taClient;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getBankCardType() {
        return bankCardType;
    }

    public void setBankCardType(String bankCardType) {
        this.bankCardType = bankCardType;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getIdExpiryDate() {
        return idExpiryDate;
    }

    public void setIdExpiryDate(String idExpiryDate) {
        this.idExpiryDate = idExpiryDate;
    }

    public String getIssuingAuthority() {
        return issuingAuthority;
    }

    public void setIssuingAuthority(String issuingAuthority) {
        this.issuingAuthority = issuingAuthority;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getTaxResdType() {
        return taxResdType;
    }

    public void setTaxResdType(String taxResdType) {
        this.taxResdType = taxResdType;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public BigDecimal getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(BigDecimal annualIncome) {
        this.annualIncome = annualIncome;
    }

    public String getOccupationType() {
        return occupationType;
    }

    public void setOccupationType(String occupationType) {
        this.occupationType = occupationType;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getRiskDate() {
        return riskDate;
    }

    public void setRiskDate(String riskDate) {
        this.riskDate = riskDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTaCode() {
        return taCode;
    }

    public void setTaCode(String taCode) {
        this.taCode = taCode;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getExtData() {
        return extData;
    }

    public void setExtData(String extData) {
        this.extData = extData;
    }
}