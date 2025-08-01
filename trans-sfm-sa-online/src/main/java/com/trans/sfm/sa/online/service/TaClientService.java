package com.trans.sfm.sa.online.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trans.sfm.sa.online.entity.TaClient;
import com.trans.sfm.sa.online.mapper.TaClientMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaClientService extends ServiceImpl<TaClientMapper, TaClient> {
    
    /**
     * 保存合作方交易账号信息
     * @param taClient 合作方交易账号信息
     * @return 是否保存成功
     */
    public boolean saveTaClient(TaClient taClient) {
        return this.save(taClient);
    }
    
    /**
     * 批量保存合作方交易账号信息
     * @param taClients 合作方交易账号信息列表
     * @return 是否保存成功
     */
    public boolean saveBatchTaClients(List<TaClient> taClients) {
        return this.saveBatch(taClients);
    }
    
    /**
     * 根据ID获取合作方交易账号信息
     * @param id 合作方交易账号ID
     * @return 合作方交易账号信息
     */
    public TaClient getTaClientById(Long id) {
        return this.getById(id);
    }
    
    /**
     * 根据客户编号获取合作方交易账号信息
     * @param clientNo 客户编号
     * @return 合作方交易账号信息列表
     */
    public List<TaClient> getTaClientsByClientNo(Long clientNo) {
        QueryWrapper<TaClient> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("client_no", clientNo);
        return this.list(queryWrapper);
    }
    
    /**
     * 根据合作方代码获取合作方交易账号信息列表
     * @param partnerCode 合作方代码
     * @return 合作方交易账号信息列表
     */
    public List<TaClient> getTaClientsByPartnerCode(String partnerCode) {
        QueryWrapper<TaClient> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("partner_code", partnerCode);
        return this.list(queryWrapper);
    }
    
    /**
     * 获取所有合作方交易账号信息
     * @return 合作方交易账号信息列表
     */
    public List<TaClient> getAllTaClients() {
        return this.list();
    }
    
    /**
     * 分页查询合作方交易账号信息
     * @param currentPage 当前页码
     * @param pageSize 每页大小
     * @return 分页结果
     */
    public Page<TaClient> getTaClientPage(int currentPage, int pageSize) {
        Page<TaClient> page = new Page<>(currentPage, pageSize);
        QueryWrapper<TaClient> queryWrapper = new QueryWrapper<>();
        return this.page(page, queryWrapper);
    }
    
    /**
     * 根据客户编号更新合作方交易账号信息
     * @param taClient 合作方交易账号信息
     * @return 是否更新成功
     */
    public boolean updateTaClientByClientNo(TaClient taClient) {
        QueryWrapper<TaClient> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("client_no", taClient.getClientNo());
        return this.update(taClient, queryWrapper);
    }
    
    /**
     * 根据TA代码获取合作方交易账号信息列表
     * @param taCode TA代码
     * @return 合作方交易账号信息列表
     */
    public List<TaClient> getTaClientsByTaCode(String taCode) {
        QueryWrapper<TaClient> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ta_code", taCode);
        return this.list(queryWrapper);
    }
    
    /**
     * 根据交易账号状态获取合作方交易账号信息列表
     * @param taClientStatus 交易账号状态
     * @return 合作方交易账号信息列表
     */
    public List<TaClient> getTaClientsByTaClientStatus(String taClientStatus) {
        QueryWrapper<TaClient> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ta_client_status", taClientStatus);
        return this.list(queryWrapper);
    }
}