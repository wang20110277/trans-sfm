package com.trans.sfm.sa.online.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trans.sfm.sa.online.entity.ClientInfoTable;
import com.trans.sfm.sa.online.mapper.ClientInfoTableMapper;
import com.trans.sfm.sa.online.util.PartitionKeyUtils;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClientInfoTableService extends ServiceImpl<ClientInfoTableMapper, ClientInfoTable> {
    
    /**
     * 保存客户信息
     * @param clientInfoTable 客户信息
     * @return 是否保存成功
     */
    public boolean saveClientInfo(ClientInfoTable clientInfoTable) {
        // 确保partitionKey存在，如果不存在则根据clientNo生成
        if (clientInfoTable.getPartitionKey() == null) {
            clientInfoTable.setPartitionKey(PartitionKeyUtils.generateOptimizedPartitionKey(clientInfoTable.getClientNo()));
        }
        return this.save(clientInfoTable);
    }
    
    /**
     * 批量保存客户信息
     * @param clientInfoTables 客户信息列表
     * @return 是否保存成功
     */
    public boolean saveBatchClientInfos(List<ClientInfoTable> clientInfoTables) {
        // 确保每个实体的partitionKey都存在
        for (ClientInfoTable clientInfoTable : clientInfoTables) {
            if (clientInfoTable.getPartitionKey() == null) {
                clientInfoTable.setPartitionKey(PartitionKeyUtils.generateOptimizedPartitionKey(clientInfoTable.getClientNo()));
            }
        }
        return this.saveBatch(clientInfoTables);
    }
    
    /**
     * 根据ID获取客户信息
     * @param id 客户ID
     * @return 客户信息
     */
    public ClientInfoTable getClientInfoById(Long id) {
        return this.getById(id);
    }
    
    /**
     * 根据客户编号获取客户信息
     * 注意：此方法在分片环境中可能需要全表扫描，建议使用带有分片键的查询
     * @param clientNo 客户编号
     * @return 客户信息列表
     */
    public List<ClientInfoTable> getClientInfosByClientNo(Long clientNo) {
        QueryWrapper<ClientInfoTable> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("client_no", clientNo);
        return this.list(queryWrapper);
    }
    
    /**
     * 根据分片键获取客户信息列表
     * 这是最高效的分片查询方式，能够直接定位到具体的数据库和表
     * @param partitionKey 分片键
     * @return 客户信息列表
     */
    public List<ClientInfoTable> getClientInfosByPartitionKey(Long partitionKey) {
        if (!PartitionKeyUtils.isValidPartitionKey(partitionKey)) {
            throw new IllegalArgumentException("无效的分片键: " + partitionKey);
        }
        
        QueryWrapper<ClientInfoTable> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("partition_key", partitionKey);
        return this.list(queryWrapper);
    }
    
    /**
     * 根据分片键获取单个客户信息
     * 由于partition_key不是唯一键，所以此方法返回匹配的第一个记录
     * @param partitionKey 分片键
     * @return 客户信息
     */
    public ClientInfoTable getClientInfoByPartitionKey(Long partitionKey) {
        if (!PartitionKeyUtils.isValidPartitionKey(partitionKey)) {
            throw new IllegalArgumentException("无效的分片键: " + partitionKey);
        }
        
        QueryWrapper<ClientInfoTable> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("partition_key", partitionKey);
        return this.getOne(queryWrapper);
    }
    
    /**
     * 根据客户系统内部ID获取客户信息
     * @param customerId 客户系统内部ID
     * @return 客户信息
     */
    public ClientInfoTable getClientInfoByCustomerId(String customerId) {
        QueryWrapper<ClientInfoTable> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("customer_id", customerId);
        return this.getOne(queryWrapper);
    }
    
    /**
     * 获取所有客户信息
     * 注意：此方法在分片环境中会扫描所有分片，性能较差
     * @return 客户信息列表
     */
    public List<ClientInfoTable> getAllClientInfos() {
        return this.list();
    }
    
    /**
     * 分页查询客户信息
     * 注意：此方法在分片环境中可能需要扫描所有分片，性能较差
     * @param currentPage 当前页码
     * @param pageSize 每页大小
     * @return 分页结果
     */
    public Page<ClientInfoTable> getClientInfoPage(int currentPage, int pageSize) {
        Page<ClientInfoTable> page = new Page<>(currentPage, pageSize);
        QueryWrapper<ClientInfoTable> queryWrapper = new QueryWrapper<>();
        return this.page(page, queryWrapper);
    }
    
    /**
     * 根据分片键分页查询客户信息
     * 这是最高效的分片分页查询方式，能够直接定位到具体的数据库和表
     * @param partitionKey 分片键
     * @param currentPage 当前页码
     * @param pageSize 每页大小
     * @return 分页结果
     */
    public Page<ClientInfoTable> getClientInfoPageByPartitionKey(Long partitionKey, int currentPage, int pageSize) {
        if (!PartitionKeyUtils.isValidPartitionKey(partitionKey)) {
            throw new IllegalArgumentException("无效的分片键: " + partitionKey);
        }
        
        Page<ClientInfoTable> page = new Page<>(currentPage, pageSize);
        QueryWrapper<ClientInfoTable> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("partition_key", partitionKey);
        return this.page(page, queryWrapper);
    }
    
    /**
     * 根据ID更新客户信息
     * @param clientInfoTable 客户信息
     * @return 是否更新成功
     */
    public boolean updateClientInfoById(ClientInfoTable clientInfoTable) {
        return this.updateById(clientInfoTable);
    }
    
    /**
     * 根据客户类型获取客户信息列表
     * @param clientType 客户类型
     * @return 客户信息列表
     */
    public List<ClientInfoTable> getClientInfosByClientType(String clientType) {
        QueryWrapper<ClientInfoTable> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("client_type", clientType);
        return this.list(queryWrapper);
    }
    
    /**
     * 根据合作方代码获取客户信息列表
     * @param partnerCode 合作方代码
     * @return 客户信息列表
     */
    public List<ClientInfoTable> getClientInfosByPartnerCode(String partnerCode) {
        QueryWrapper<ClientInfoTable> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("partner_code", partnerCode);
        return this.list(queryWrapper);
    }
}