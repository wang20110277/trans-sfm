package com.trans.sfm.sa.online.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("sfm_sa_client_no")
public class ClientNo {
    
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
     * 客户编号
     */
    private Long clientNo;
    
    /**
     * 客户类型 (0-机构，1-个人)
     */
    private String clientType;
    
    /**
     * 证件类型
     */
    private String idType;
    
    /**
     * 证件号码
     */
    private String idCode;
    
    /**
     * 客户姓名/公司名称
     */
    private String customerName;
    
    /**
     * 客户系统内部ID
     */
    private String customerId;
    
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