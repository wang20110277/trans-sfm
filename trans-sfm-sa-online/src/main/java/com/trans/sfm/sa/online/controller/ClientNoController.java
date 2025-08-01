package com.trans.sfm.sa.online.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trans.sfm.sa.online.entity.ClientNo;
import com.trans.sfm.sa.online.service.ClientNoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientNos")
public class ClientNoController {
    
    @Autowired
    private ClientNoService clientNoService;
    
    /**
     * 创建客户编号
     * @param clientNo 客户编号信息
     * @return 是否创建成功
     */
    @PostMapping
    public boolean createClientNo(@RequestBody ClientNo clientNo) {
        return clientNoService.saveClientNo(clientNo);
    }
    
    /**
     * 批量创建客户编号
     * @param clientNos 客户编号列表
     * @return 是否创建成功
     */
    @PostMapping("/batch")
    public boolean createClientNos(@RequestBody List<ClientNo> clientNos) {
        return clientNoService.saveBatchClientNos(clientNos);
    }
    
    /**
     * 根据ID获取客户编号信息
     * @param id 客户编号ID
     * @return 客户编号信息
     */
    @GetMapping("/{id}")
    public ClientNo getClientNo(@PathVariable Long id) {
        return clientNoService.getClientNoById(id);
    }
    
    /**
     * 根据客户编号获取客户信息
     * @param clientNo 客户编号
     * @return 客户编号信息
     */
    @GetMapping("/clientNo/{clientNo}")
    public ClientNo getClientNoByClientNo(@PathVariable Long clientNo) {
        return clientNoService.getClientNoByClientNo(clientNo);
    }
    
    /**
     * 根据合作方代码获取客户编号列表
     * @param partnerCode 合作方代码
     * @return 客户编号信息列表
     */
    @GetMapping("/partnerCode/{partnerCode}")
    public List<ClientNo> getClientNosByPartnerCode(@PathVariable String partnerCode) {
        return clientNoService.getClientNosByPartnerCode(partnerCode);
    }
    
    /**
     * 获取所有客户编号信息
     * @return 客户编号列表
     */
    @GetMapping
    public List<ClientNo> getAllClientNos() {
        return clientNoService.getAllClientNos();
    }
    
    /**
     * 分页查询客户编号信息
     * @param currentPage 当前页码
     * @param pageSize 每页大小
     * @return 分页结果
     */
    @GetMapping("/page")
    public Page<ClientNo> getClientNoPage(@RequestParam(defaultValue = "1") int currentPage,
                                          @RequestParam(defaultValue = "10") int pageSize) {
        return clientNoService.getClientNoPage(currentPage, pageSize);
    }
    
    /**
     * 根据客户编号更新客户信息
     * @param clientNo 客户编号信息
     * @return 是否更新成功
     */
    @PutMapping
    public boolean updateClientNo(@RequestBody ClientNo clientNo) {
        return clientNoService.updateClientNoByClientNo(clientNo);
    }
}