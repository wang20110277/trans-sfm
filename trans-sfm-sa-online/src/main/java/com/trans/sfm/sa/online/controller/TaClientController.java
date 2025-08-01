package com.trans.sfm.sa.online.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trans.sfm.sa.online.entity.TaClient;
import com.trans.sfm.sa.online.service.TaClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/taClients")
public class TaClientController {
    
    @Autowired
    private TaClientService taClientService;
    
    /**
     * 创建合作方交易账号信息
     * @param taClient 合作方交易账号信息
     * @return 是否创建成功
     */
    @PostMapping
    public boolean createTaClient(@RequestBody TaClient taClient) {
        return taClientService.saveTaClient(taClient);
    }
    
    /**
     * 批量创建合作方交易账号信息
     * @param taClients 合作方交易账号信息列表
     * @return 是否创建成功
     */
    @PostMapping("/batch")
    public boolean createTaClients(@RequestBody List<TaClient> taClients) {
        return taClientService.saveBatchTaClients(taClients);
    }
    
    /**
     * 根据ID获取合作方交易账号信息
     * @param id 合作方交易账号ID
     * @return 合作方交易账号信息
     */
    @GetMapping("/{id}")
    public TaClient getTaClient(@PathVariable Long id) {
        return taClientService.getTaClientById(id);
    }
    
    /**
     * 根据客户编号获取合作方交易账号信息列表
     * @param clientNo 客户编号
     * @return 合作方交易账号信息列表
     */
    @GetMapping("/clientNo/{clientNo}")
    public List<TaClient> getTaClientsByClientNo(@PathVariable Long clientNo) {
        return taClientService.getTaClientsByClientNo(clientNo);
    }
    
    /**
     * 根据合作方代码获取合作方交易账号信息列表
     * @param partnerCode 合作方代码
     * @return 合作方交易账号信息列表
     */
    @GetMapping("/partnerCode/{partnerCode}")
    public List<TaClient> getTaClientsByPartnerCode(@PathVariable String partnerCode) {
        return taClientService.getTaClientsByPartnerCode(partnerCode);
    }
    
    /**
     * 获取所有合作方交易账号信息
     * @return 合作方交易账号信息列表
     */
    @GetMapping
    public List<TaClient> getAllTaClients() {
        return taClientService.getAllTaClients();
    }
    
    /**
     * 分页查询合作方交易账号信息
     * @param currentPage 当前页码
     * @param pageSize 每页大小
     * @return 分页结果
     */
    @GetMapping("/page")
    public Page<TaClient> getTaClientPage(@RequestParam(defaultValue = "1") int currentPage,
                                          @RequestParam(defaultValue = "10") int pageSize) {
        return taClientService.getTaClientPage(currentPage, pageSize);
    }
    
    /**
     * 更新合作方交易账号信息
     * @param taClient 合作方交易账号信息
     * @return 是否更新成功
     */
    @PutMapping
    public boolean updateTaClient(@RequestBody TaClient taClient) {
        return taClientService.updateTaClientByClientNo(taClient);
    }
}