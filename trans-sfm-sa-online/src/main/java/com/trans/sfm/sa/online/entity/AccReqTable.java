package com.trans.sfm.sa.online.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("sfm_sa_acc_req_table")
public class AccReqTable {
    
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
     * 全局流水号
     */
    private String transId;
    
    /**
     * 子流水号
     */
    private String subTransSeq;
    
    /**
     * 交易日期
     */
    private String transDate;
    
    /**
     * 交易时间
     */
    private Integer transTime;
    
    /**
     * 合作方流水
     */
    private String partnerSerialNo;
    
    /**
     * 合作方代码
     */
    private String partnerCode;
    
    /**
     * 合作方渠道
     */
    private String partnerChannel;
    
    /**
     * 业务类别
     */
    private String bizType;
    
    /**
     * 基交交易账号
     */
    private String taClient;
    
    /**
     * TA代码
     */
    private String taCode;
    
    /**
     * 理财账号
     */
    private String assetAcc;
    
    /**
     * 客户类型: 0-机构,1-个人
     */
    private String clientType;
    
    /**
     * 客户号
     */
    private Long clientNo;
    
    /**
     * 银行账号
     */
    private String bankAcc;
    
    /**
     * 交易介质类型0-卡号,1-账号
     */
    private String transAccountType;
    
    /**
     * 交易介质
     */
    private String transAccount;
    
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
     * 性别
     */
    private String sex;
    
    /**
     * 出生日期
     */
    private Integer birthday;
    
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
     * 对账单发送方式
     */
    private String sendMode;
    
    /**
     * 对账单寄送频率
     */
    private String sendFreq;
    
    /**
     * 分红方式
     */
    private String divMode;
    
    /**
     * 关联日期
     */
    private Integer assoDate;
    
    /**
     * 冻结原因:0-司法 1-质押
     */
    private String frozenCause;
    
    /**
     * TA的确认流水号
     */
    private String assoSerialNo;
    
    /**
     * 机构投资人类型
     */
    private String inputType;
    
    /**
     * 法人代表姓名
     */
    private String reprName;
    
    /**
     * 法人代表证件类型
     */
    private String reprIdType;
    
    /**
     * 法人代表证件号码
     */
    private String reprIdCode;
    
    /**
     * 经办人姓名
     */
    private String actorName;
    
    /**
     * 经办人证件类型
     */
    private String actorIdType;
    
    /**
     * 经办人证件号码
     */
    private String actorIdCode;
    
    /**
     * 客户经理
     */
    private String clientManager;
    
    /**
     * 备注:银行调增调减原因/强行赎回等原因
     */
    private String remark;
    
    /**
     * 国籍
     */
    private String nationality;
    
    /**
     * 职业代码
     */
    private String occupation;
    
    /**
     * 证件有效截止日期
     */
    private Integer validity;
    
    /**
     * 行业
     */
    private String industry;
    
    /**
     * 交易所在地区编号
     */
    private String regionCode;
    
    /**
     * 学历
     */
    private String education;
    
    /**
     * 年收入
     */
    private BigDecimal income;
    
    /**
     * 网点号码
     */
    private String branchCode;
    
    /**
     * 交易手段
     */
    private String tradeMethod;
    
    /**
     * 未成年标志 0-否,1-是
     */
    private String minorFlag;
    
    /**
     * 经办人识别方式 1-书面委托,2-印鉴,3-密码,4-证件
     */
    private String transactorIdType;
    
    /**
     * 操作网点编号
     */
    private String netNo;
    
    /**
     * 风险等级
     */
    private String riskLevel;
    
    /**
     * 风险有效截止日期
     */
    private Integer riskDate;
    
    /**
     * 机构经办人身份证有效日期
     */
    private Integer instActorIdCodeDate;
    
    /**
     * 机构法人身份证有效日期
     */
    private Integer instReprIdCodeDate;
    
    /**
     * 机构法人经营范围
     */
    private String instReprManageRange;
    
    /**
     * 控股股东
     */
    private String controlHolder;
    
    /**
     * 实际控制人
     */
    private String actualController;
    
    /**
     * 婚姻状态
     */
    private String marriageStatus;
    
    /**
     * 家庭人口数
     */
    private Integer familyNum;
    
    /**
     * 家庭资产
     */
    private BigDecimal penates;
    
    /**
     * 媒体偏好
     */
    private String mediaHobby;
    
    /**
     * 投资人英文名
     */
    private String englishFirstName;
    
    /**
     * 投资人英文姓
     */
    private String englishFamilyName;
    
    /**
     * 企业质
     */
    private String corpoProperty;
    
    /**
     * 员工人数
     */
    private Integer staffNum;
    
    /**
     * 兴趣爱好
     */
    private String hobbyType;
    
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
     * 受理方式
     */
    private String dealMode;
    
    /**
     * 交易状态
     */
    private String transStatus;
    
    /**
     * 返回码
     */
    private String retCode;
    
    /**
     * 返回信息
     */
    private String retMsg;
    
    /**
     * 所属系统
     */
    private String sysCode;
    
    /**
     * 请求扩展数据
     */
    private String reqExtData;
    
    /**
     * 返回扩展数据
     */
    private String retExtData;
    
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