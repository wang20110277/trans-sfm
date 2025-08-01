CREATE TABLE sfm_sa_client_no (
	id BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
	partner_code VARCHAR ( 32 ) NOT NULL COMMENT '合作方代码',
	client_no BIGINT ( 20 ) NOT NULL COMMENT '客户编号',
	client_type VARCHAR ( 3 ) NOT NULL COMMENT '客户类型 (0-机构，1-个人)',
	id_type VARCHAR ( 3 ) NOT NULL COMMENT '证件类型',
	id_code VARCHAR ( 128 ) NOT NULL COMMENT '证件号码',
	customer_name VARCHAR ( 512 ) NOT NULL COMMENT '客户姓名/公司名称',
	customer_id VARCHAR ( 64 ) NOT NULL COMMENT '客户系统内部ID',
	created_by VARCHAR ( 64 ) NOT NULL DEFAULT 'SYSTEM' COMMENT '创建人',
	create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	updated_by VARCHAR ( 64 ) NOT NULL DEFAULT 'SYSTEM' COMMENT '更新人',
	update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	PRIMARY KEY ( id ),
    UNIQUE KEY uniq_client_no ( client_no )
) ENGINE = INNODB COMMENT = '客户编号表';

CREATE TABLE sfm_sa_ta_client (
	id BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
	partner_code VARCHAR ( 32 ) NOT NULL COMMENT '合作方代码',
	partner_channel VARCHAR ( 32 ) NOT NULL COMMENT '合作方代码',
	client_no BIGINT ( 20 ) NOT NULL COMMENT '客户编号',
	client_type VARCHAR ( 3 ) NOT NULL COMMENT '客户类型 (0-机构，1-个人)',
	ta_code varchar(32) NOT NULL DEFAULT '' COMMENT 'TA代码',
	asset_acc varchar(20) NOT NULL DEFAULT '' COMMENT '理财账号',
	asset_acc_status varchar(1) NOT NULL DEFAULT '' COMMENT '理财账户状态：1-新建，2-正常，3-冻结，4-开户失败，9-销户',
	ta_client varchar(32) NOT NULL DEFAULT '' COMMENT '基交交易账号',
	ta_client_status varchar(1) NOT NULL DEFAULT '' COMMENT '交易账号状态：1-新建，2-正常，3-开户失败，9-取消',
	trans_account_type varchar(1) DEFAULT '' COMMENT '交易介质类型0-卡号,1-账号',
    trans_account varchar(128) DEFAULT '' COMMENT '交易介质',
    open_date varchar(11) DEFAULT '' COMMENT '开户日期',
    sys_code varchar(8) NOT NULL DEFAULT '' COMMENT '所属系统',
	created_by VARCHAR ( 64 ) NOT NULL DEFAULT 'SYSTEM' COMMENT '创建人',
	create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	updated_by VARCHAR ( 64 ) NOT NULL DEFAULT 'SYSTEM' COMMENT '更新人',
	update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	PRIMARY KEY ( id ),
    UNIQUE KEY uniq_ta_client ( client_no, ta_code, ta_client )
) ENGINE = INNODB COMMENT = '合作方交易账号表';