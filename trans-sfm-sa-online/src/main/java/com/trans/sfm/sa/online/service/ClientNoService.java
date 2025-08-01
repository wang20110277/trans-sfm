package com.trans.sfm.sa.online.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trans.sfm.sa.online.entity.ClientNo;
import com.trans.sfm.sa.online.mapper.ClientNoMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClientNoService extends ServiceImpl<ClientNoMapper, ClientNo> {
    
    /**
     * 保存客户编号信息
     * @param clientNo 客户编号信息
     * @return 是否保存成功
     */
    public boolean saveClientNo(ClientNo clientNo) {
        return this.save(clientNo);
    }
    
    /**
     * 批量保存客户编号信息
     * @param clientNos 客户编号信息列表
     * @return 是否保存成功
     */
    public boolean saveBatchClientNos(List<ClientNo> clientNos) {
        return this.saveBatch(clientNos);
    }
    
    /**
     * 根据ID获取客户编号信息
     * @param id 客户编号ID
     * @return 客户编号信息
     */
    public ClientNo getClientNoById(Long id) {
        return this.getById(id);
    }
    
    /**
     * 根据客户编号获取客户信息
     * @param clientNo 客户编号
     * @return 客户编号信息
     */
    public ClientNo getClientNoByClientNo(Long clientNo) {
        QueryWrapper<ClientNo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("client_no", clientNo);
        return this.getOne(queryWrapper);
    }
    
    /**
     * 根据合作方代码获取客户编号列表
     * @param partnerCode 合作方代码
     * @return 客户编号信息列表
     */
    public List<ClientNo> getClientNosByPartnerCode(String partnerCode) {
        QueryWrapper<ClientNo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("partner_code", partnerCode);
        return this.list(queryWrapper);
    }
    
    /**
     * 获取所有客户编号信息
     * @return 客户编号信息列表
     */
    public List<ClientNo> getAllClientNos() {
        return this.list();
    }
    
    /**
     * 分页查询客户编号信息
     * @param currentPage 当前页码
     * @param pageSize 每页大小
     * @return 分页结果
     */
    public Page<ClientNo> getClientNoPage(int currentPage, int pageSize) {
        Page<ClientNo> page = new Page<>(currentPage, pageSize);
        QueryWrapper<ClientNo> queryWrapper = new QueryWrapper<>();
        return this.page(page, queryWrapper);
    }
    
    /**
     * 根据客户编号更新客户信息
     * @param clientNo 客户编号信息
     * @return 是否更新成功
     */
    public boolean updateClientNoByClientNo(ClientNo clientNo) {
        QueryWrapper<ClientNo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("client_no", clientNo.getClientNo());
        return this.update(clientNo, queryWrapper);
    }
    
    /**
     * 根据客户类型获取客户编号列表
     * @param clientType 客户类型
     * @return 客户编号信息列表
     */
    public List<ClientNo> getClientNosByClientType(String clientType) {
        QueryWrapper<ClientNo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("client_type", clientType);
        return this.list(queryWrapper);
    }
    
    /**
     * 根据客户姓名获取客户编号列表
     * @param customerName 客户姓名
     * @return 客户编号信息列表
     */
    public List<ClientNo> getClientNosByCustomerName(String customerName) {
        QueryWrapper<ClientNo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("customer_name", customerName);
        return this.list(queryWrapper);
    }
}