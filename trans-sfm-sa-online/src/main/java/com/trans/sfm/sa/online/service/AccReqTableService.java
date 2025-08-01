package com.trans.sfm.sa.online.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trans.sfm.sa.online.entity.AccReqTable;
import com.trans.sfm.sa.online.mapper.AccReqTableMapper;
import com.trans.sfm.sa.online.util.PartitionKeyUtils;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AccReqTableService extends ServiceImpl<AccReqTableMapper, AccReqTable> {
    
    /**
     * 保存账户申请信息
     * @param accReqTable 账户申请信息
     * @return 是否保存成功
     */
    public boolean saveAccReq(AccReqTable accReqTable) {
        // 确保partitionKey存在，如果不存在则根据clientNo生成
        if (accReqTable.getPartitionKey() == null) {
            accReqTable.setPartitionKey(PartitionKeyUtils.generateOptimizedPartitionKey(accReqTable.getClientNo()));
        }
        return this.save(accReqTable);
    }
    
    /**
     * 批量保存账户申请信息
     * @param accReqTables 账户申请信息列表
     * @return 是否保存成功
     */
    public boolean saveBatchAccReqs(List<AccReqTable> accReqTables) {
        // 确保每个实体的partitionKey都存在
        for (AccReqTable accReqTable : accReqTables) {
            if (accReqTable.getPartitionKey() == null) {
                accReqTable.setPartitionKey(PartitionKeyUtils.generateOptimizedPartitionKey(accReqTable.getClientNo()));
            }
        }
        return this.saveBatch(accReqTables);
    }
    
    /**
     * 根据ID获取账户申请信息
     * @param id 账户申请ID
     * @return 账户申请信息
     */
    public AccReqTable getAccReqById(Long id) {
        return this.getById(id);
    }
    
    /**
     * 根据全局流水号和子流水号获取账户申请信息
     * @param transId 全局流水号
     * @param subTransSeq 子流水号
     * @return 账户申请信息
     */
    public AccReqTable getAccReqByTransSeq(String transId, String subTransSeq) {
        QueryWrapper<AccReqTable> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("trans_id", transId).eq("sub_trans_seq", subTransSeq);
        return this.getOne(queryWrapper);
    }
    
    /**
     * 根据合作方流水和合作方代码获取账户申请信息
     * @param partnerSerialNo 合作方流水
     * @param partnerCode 合作方代码
     * @return 账户申请信息
     */
    public AccReqTable getAccReqBySerialNo(String partnerSerialNo, String partnerCode) {
        QueryWrapper<AccReqTable> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("partner_serial_no", partnerSerialNo).eq("partner_code", partnerCode);
        return this.getOne(queryWrapper);
    }
    
    /**
     * 根据客户号获取账户申请信息列表
     * 注意：此方法在分片环境中可能需要全表扫描，建议使用带有分片键的查询
     * @param clientNo 客户号
     * @return 账户申请信息列表
     */
    public List<AccReqTable> getAccReqsByClientNo(Long clientNo) {
        QueryWrapper<AccReqTable> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("client_no", clientNo);
        return this.list(queryWrapper);
    }
    
    /**
     * 根据分片键获取账户申请信息列表
     * 这是最高效的分片查询方式，能够直接定位到具体的数据库和表
     * @param partitionKey 分片键
     * @return 账户申请信息列表
     */
    public List<AccReqTable> getAccReqsByPartitionKey(Long partitionKey) {
        if (!PartitionKeyUtils.isValidPartitionKey(partitionKey)) {
            throw new IllegalArgumentException("无效的分片键: " + partitionKey);
        }
        
        QueryWrapper<AccReqTable> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("partition_key", partitionKey);
        return this.list(queryWrapper);
    }
    
    /**
     * 根据分片键获取单个账户申请信息
     * 由于partition_key不是唯一键，所以此方法返回匹配的第一个记录
     * @param partitionKey 分片键
     * @return 账户申请信息
     */
    public AccReqTable getAccReqByPartitionKey(Long partitionKey) {
        if (!PartitionKeyUtils.isValidPartitionKey(partitionKey)) {
            throw new IllegalArgumentException("无效的分片键: " + partitionKey);
        }
        
        QueryWrapper<AccReqTable> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("partition_key", partitionKey);
        return this.getOne(queryWrapper);
    }
    
    /**
     * 获取所有账户申请信息
     * 注意：此方法在分片环境中会扫描所有分片，性能较差
     * @return 账户申请信息列表
     */
    public List<AccReqTable> getAllAccReqs() {
        return this.list();
    }
    
    /**
     * 分页查询账户申请信息
     * 注意：此方法在分片环境中可能需要扫描所有分片，性能较差
     * @param currentPage 当前页码
     * @param pageSize 每页大小
     * @return 分页结果
     */
    public Page<AccReqTable> getAccReqPage(int currentPage, int pageSize) {
        Page<AccReqTable> page = new Page<>(currentPage, pageSize);
        QueryWrapper<AccReqTable> queryWrapper = new QueryWrapper<>();
        return this.page(page, queryWrapper);
    }
    
    /**
     * 根据分片键分页查询账户申请信息
     * 这是最高效的分片分页查询方式，能够直接定位到具体的数据库和表
     * @param partitionKey 分片键
     * @param currentPage 当前页码
     * @param pageSize 每页大小
     * @return 分页结果
     */
    public Page<AccReqTable> getAccReqPageByPartitionKey(Long partitionKey, int currentPage, int pageSize) {
        if (!PartitionKeyUtils.isValidPartitionKey(partitionKey)) {
            throw new IllegalArgumentException("无效的分片键: " + partitionKey);
        }
        
        Page<AccReqTable> page = new Page<>(currentPage, pageSize);
        QueryWrapper<AccReqTable> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("partition_key", partitionKey);
        return this.page(page, queryWrapper);
    }
    
    /**
     * 根据ID更新账户申请信息
     * @param accReqTable 账户申请信息
     * @return 是否更新成功
     */
    public boolean updateAccReqById(AccReqTable accReqTable) {
        return this.updateById(accReqTable);
    }
    
    /**
     * 根据交易状态获取账户申请信息列表
     * @param transStatus 交易状态
     * @return 账户申请信息列表
     */
    public List<AccReqTable> getAccReqsByTransStatus(String transStatus) {
        QueryWrapper<AccReqTable> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("trans_status", transStatus);
        return this.list(queryWrapper);
    }
    
    /**
     * 根据合作方代码获取账户申请信息列表
     * @param partnerCode 合作方代码
     * @return 账户申请信息列表
     */
    public List<AccReqTable> getAccReqsByPartnerCode(String partnerCode) {
        QueryWrapper<AccReqTable> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("partner_code", partnerCode);
        return this.list(queryWrapper);
    }
}