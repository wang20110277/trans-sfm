-- Spring Batch tables
CREATE TABLE IF NOT EXISTS batch_job_instance  (
	job_instance_id BIGINT  NOT NULL PRIMARY KEY ,
	version BIGINT ,
	job_name VARCHAR(100) NOT NULL,
	job_key VARCHAR(32) NOT NULL,
	constraint job_inst_un unique (job_name, job_key)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS batch_job_execution  (
	job_execution_id BIGINT  NOT NULL PRIMARY KEY ,
	version BIGINT  ,
	job_instance_id BIGINT NOT NULL,
	create_time DATETIME NOT NULL,
	start_time DATETIME DEFAULT NULL ,
	end_time DATETIME DEFAULT NULL ,
	status VARCHAR(10) ,
	exit_code VARCHAR(2500) ,
	exit_message VARCHAR(2500) ,
	last_updated DATETIME,
	job_configuration_location VARCHAR(2500) NULL,
	constraint job_inst_exec_fk foreign key (job_instance_id)
	references batch_job_instance(job_instance_id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS batch_job_execution_params  (
	job_execution_id BIGINT NOT NULL ,
	type_cd VARCHAR(6) NOT NULL ,
	key_name VARCHAR(100) NOT NULL ,
	string_val VARCHAR(250) ,
	date_val DATETIME DEFAULT NULL ,
	long_val BIGINT ,
	double_val DOUBLE PRECISION ,
	identifying CHAR(1) NOT NULL ,
	constraint job_exec_params_fk foreign key (job_execution_id)
	references batch_job_execution(job_execution_id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS batch_step_execution  (
	step_execution_id BIGINT  NOT NULL PRIMARY KEY ,
	version BIGINT NOT NULL,
	step_name VARCHAR(100) NOT NULL,
	job_execution_id BIGINT NOT NULL,
	start_time DATETIME NOT NULL ,
	end_time DATETIME DEFAULT NULL ,
	status VARCHAR(10) ,
	commit_count BIGINT ,
	read_count BIGINT ,
	filter_count BIGINT ,
	write_count BIGINT ,
	read_skip_count BIGINT ,
	write_skip_count BIGINT ,
	process_skip_count BIGINT ,
	rollback_count BIGINT ,
	exit_code VARCHAR(2500) ,
	exit_message VARCHAR(2500) ,
	last_updated DATETIME,
	constraint job_exec_step_fk foreign key (job_execution_id)
	references batch_job_execution(job_execution_id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS batch_step_execution_context  (
	step_execution_id BIGINT NOT NULL PRIMARY KEY,
	short_context VARCHAR(2500) NOT NULL,
	serialized_context TEXT ,
	constraint step_exec_ctx_fk foreign key (step_execution_id)
	references batch_step_execution(step_execution_id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS batch_job_execution_context  (
	job_execution_id BIGINT NOT NULL PRIMARY KEY,
	short_context VARCHAR(2500) NOT NULL,
	serialized_context TEXT ,
	constraint job_exec_ctx_fk foreign key (job_execution_id)
	references batch_job_execution(job_execution_id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS batch_step_execution_seq (
	id BIGINT NOT NULL,
	unique_key CHAR(1) NOT NULL,
	constraint unique_key_un unique (unique_key)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS batch_job_execution_seq (
	id BIGINT NOT NULL,
	unique_key CHAR(1) NOT NULL,
	constraint unique_key_un unique (unique_key)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS batch_job_seq (
	id BIGINT NOT NULL,
	unique_key CHAR(1) NOT NULL,
	constraint unique_key_un unique (unique_key)
) ENGINE=InnoDB;

INSERT INTO batch_step_execution_seq (id, unique_key) select * from (select 0 as id, '0' as unique_key) as tmp where not exists(select * from batch_step_execution_seq);
INSERT INTO batch_job_execution_seq (id, unique_key) select * from (select 0 as id, '0' as unique_key) as tmp where not exists(select * from batch_job_execution_seq);
INSERT INTO batch_job_seq (id, unique_key) select * from (select 0 as id, '0' as unique_key) as tmp where not exists(select * from batch_job_seq);