package com.trans.sfm.sa.online.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("sfm_sa_client_info_table")
public class ClientInfoTable {
    
    /**
     * 物理主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    
    /**
     * 分片键
     */
    private Long partitionKey;
    
    /**
     * 合作方代码
     */
    private String partnerCode;
    
    /**
     * 合作方渠道
     */
    private String partnerChannel;
    
    /**
     * 客户系统内部ID
     */
    private String customerId;
    
    /**
     * 客户类型: 0-机构,1-个人
     */
    private String clientType;
    
    /**
     * 客户号
     */
    private Long clientNo;
    
    /**
     * 客户分组
     */
    private String clientGroup;
    
    /**
     * 证件类型
     */
    private String idType;
    
    /**
     * 证件号码
     */
    private String idCode;
    
    /**
     * 客户名称
     */
    private String clientName;
    
    /**
     * 客户简称
     */
    private String clientShortName;
    
    /**
     * 通讯地址
     */
    private String address;
    
    /**
     * 邮政编码
     */
    private String postCode;
    
    /**
     * 手机号码
     */
    private String mobile;
    
    /**
     * 联系电话
     */
    private String tel;
    
    /**
     * 传真号码
     */
    private String fax;
    
    /**
     * 电子邮箱
     */
    private String email;
    
    /**
     * 性别
     */
    private String sex;
    
    /**
     * 对账单发送方式
     */
    private String sendMode;
    
    /**
     * 对账单寄送频率
     */
    private String sendFreq;
    
    /**
     * 风险等级
     */
    private String riskLevel;
    
    /**
     * 风险有效截止日期
     */
    private Integer riskDate;
    
    /**
     * 出生日期
     */
    private Integer birthday;
    
    /**
     * 证件有效截止日期
     */
    private Integer validity;
    
    /**
     * 职业代码
     */
    private String occupation;
    
    /**
     * 学历
     */
    private String education;
    
    /**
     * 年收入
     */
    private BigDecimal income;
    
    /**
     * 国籍
     */
    private String nationality;
    
    /**
     * 开通业务
     */
    private String prdTypes;
    
    /**
     * 客户经理
     */
    private String clientManager;
    
    /**
     * 现居国家
     */
    private String currNation;
    
    /**
     * 现居地址-省
     */
    private String currProvince;
    
    /**
     * 现居地址-市
     */
    private String currCity;
    
    /**
     * 现居地址-县
     */
    private String currCounty;
    
    /**
     * 创建者
     */
    @TableField(fill = FieldFill.INSERT)
    private String createdBy;
    
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    
    /**
     * 更新者
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updatedBy;
    
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}