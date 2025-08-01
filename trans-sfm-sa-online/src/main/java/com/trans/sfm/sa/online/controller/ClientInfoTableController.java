package com.trans.sfm.sa.online.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trans.sfm.sa.online.entity.ClientInfoTable;
import com.trans.sfm.sa.online.service.ClientInfoTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientInfos")
public class ClientInfoTableController {
    
    @Autowired
    private ClientInfoTableService clientInfoTableService;
    
    /**
     * 创建客户信息
     * @param clientInfoTable 客户信息
     * @return 是否创建成功
     */
    @PostMapping
    public boolean createClientInfo(@RequestBody ClientInfoTable clientInfoTable) {
        return clientInfoTableService.saveClientInfo(clientInfoTable);
    }
    
    /**
     * 批量创建客户信息
     * @param clientInfoTables 客户信息列表
     * @return 是否创建成功
     */
    @PostMapping("/batch")
    public boolean createClientInfos(@RequestBody List<ClientInfoTable> clientInfoTables) {
        return clientInfoTableService.saveBatchClientInfos(clientInfoTables);
    }
    
    /**
     * 根据ID获取客户信息
     * @param id 客户ID
     * @return 客户信息
     */
    @GetMapping("/{id}")
    public ClientInfoTable getClientInfo(@PathVariable Long id) {
        return clientInfoTableService.getClientInfoById(id);
    }
    
    /**
     * 根据客户编号获取客户信息列表
     * @param clientNo 客户编号
     * @return 客户信息列表
     */
    @GetMapping("/clientNo/{clientNo}")
    public List<ClientInfoTable> getClientInfosByClientNo(@PathVariable Long clientNo) {
        return clientInfoTableService.getClientInfosByClientNo(clientNo);
    }
    
    /**
     * 根据客户系统内部ID获取客户信息
     * @param customerId 客户系统内部ID
     * @return 客户信息
     */
    @GetMapping("/customerId/{customerId}")
    public ClientInfoTable getClientInfoByCustomerId(@PathVariable String customerId) {
        return clientInfoTableService.getClientInfoByCustomerId(customerId);
    }
    
    /**
     * 根据分片键获取客户信息列表
     * @param partitionKey 分片键
     * @return 客户信息列表
     */
    @GetMapping("/partitionKey/{partitionKey}")
    public List<ClientInfoTable> getClientInfosByPartitionKey(@PathVariable Long partitionKey) {
        return clientInfoTableService.getClientInfosByPartitionKey(partitionKey);
    }
    
    /**
     * 获取所有客户信息
     * @return 客户信息列表
     */
    @GetMapping
    public List<ClientInfoTable> getAllClientInfos() {
        return clientInfoTableService.getAllClientInfos();
    }
    
    /**
     * 分页查询客户信息
     * @param currentPage 当前页码
     * @param pageSize 每页大小
     * @return 分页结果
     */
    @GetMapping("/page")
    public Page<ClientInfoTable> getClientInfoPage(@RequestParam(defaultValue = "1") int currentPage,
                                                   @RequestParam(defaultValue = "10") int pageSize) {
        return clientInfoTableService.getClientInfoPage(currentPage, pageSize);
    }
    
    /**
     * 根据分片键分页查询客户信息
     * @param partitionKey 分片键
     * @param currentPage 当前页码
     * @param pageSize 每页大小
     * @return 分页结果
     */
    @GetMapping("/page/partitionKey/{partitionKey}")
    public Page<ClientInfoTable> getClientInfoPageByPartitionKey(@PathVariable Long partitionKey,
                                                                 @RequestParam(defaultValue = "1") int currentPage,
                                                                 @RequestParam(defaultValue = "10") int pageSize) {
        return clientInfoTableService.getClientInfoPageByPartitionKey(partitionKey, currentPage, pageSize);
    }
    
    /**
     * 更新客户信息
     * @param clientInfoTable 客户信息
     * @return 是否更新成功
     */
    @PutMapping
    public boolean updateClientInfo(@RequestBody ClientInfoTable clientInfoTable) {
        return clientInfoTableService.updateClientInfoById(clientInfoTable);
    }
}