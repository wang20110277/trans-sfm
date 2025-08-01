package com.trans.sfm.sa.online.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("sfm_sa_ta_client")
public class TaClient {
    
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    
    /**
     * 合作方代码
     */
    private String partnerCode;
    
    /**
     * 合作方渠道
     */
    private String partnerChannel;
    
    /**
     * 客户编号
     */
    private Long clientNo;
    
    /**
     * 客户类型 (0-机构，1-个人)
     */
    private String clientType;
    
    /**
     * TA代码
     */
    private String taCode;
    
    /**
     * 理财账号
     */
    private String assetAcc;
    
    /**
     * 理财账户状态：1-新建，2-正常，3-冻结，4-开户失败，9-销户
     */
    private String assetAccStatus;
    
    /**
     * 基交交易账号
     */
    private String taClient;
    
    /**
     * 交易账号状态：1-新建，2-正常，3-开户失败，9-取消
     */
    private String taClientStatus;
    
    /**
     * 交易介质类型0-卡号,1-账号
     */
    private String transAccountType;
    
    /**
     * 交易介质
     */
    private String transAccount;
    
    /**
     * 开户日期
     */
    private String openDate;
    
    /**
     * 所属系统
     */
    private String sysCode;
    
    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private String createdBy;
    
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    
    /**
     * 更新人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updatedBy;
    
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}