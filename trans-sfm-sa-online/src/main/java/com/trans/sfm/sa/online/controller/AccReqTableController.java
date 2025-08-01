package com.trans.sfm.sa.online.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trans.sfm.sa.online.entity.AccReqTable;
import com.trans.sfm.sa.online.service.AccReqTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accReqs")
public class AccReqTableController {
    
    @Autowired
    private AccReqTableService accReqTableService;
    
    /**
     * 创建账户申请信息
     * @param accReqTable 账户申请信息
     * @return 是否创建成功
     */
    @PostMapping
    public boolean createAccReq(@RequestBody AccReqTable accReqTable) {
        return accReqTableService.saveAccReq(accReqTable);
    }
    
    /**
     * 批量创建账户申请信息
     * @param accReqTables 账户申请信息列表
     * @return 是否创建成功
     */
    @PostMapping("/batch")
    public boolean createAccReqs(@RequestBody List<AccReqTable> accReqTables) {
        return accReqTableService.saveBatchAccReqs(accReqTables);
    }
    
    /**
     * 根据ID获取账户申请信息
     * @param id 账户申请ID
     * @return 账户申请信息
     */
    @GetMapping("/{id}")
    public AccReqTable getAccReq(@PathVariable Long id) {
        return accReqTableService.getAccReqById(id);
    }
    
    /**
     * 根据全局流水号和子流水号获取账户申请信息
     * @param transId 全局流水号
     * @param subTransSeq 子流水号
     * @return 账户申请信息
     */
    @GetMapping("/transSeq")
    public AccReqTable getAccReqByTransSeq(@RequestParam String transId, @RequestParam String subTransSeq) {
        return accReqTableService.getAccReqByTransSeq(transId, subTransSeq);
    }
    
    /**
     * 根据合作方流水和合作方代码获取账户申请信息
     * @param partnerSerialNo 合作方流水
     * @param partnerCode 合作方代码
     * @return 账户申请信息
     */
    @GetMapping("/serialNo")
    public AccReqTable getAccReqBySerialNo(@RequestParam String partnerSerialNo, @RequestParam String partnerCode) {
        return accReqTableService.getAccReqBySerialNo(partnerSerialNo, partnerCode);
    }
    
    /**
     * 根据客户号获取账户申请信息列表
     * @param clientNo 客户号
     * @return 账户申请信息列表
     */
    @GetMapping("/clientNo/{clientNo}")
    public List<AccReqTable> getAccReqsByClientNo(@PathVariable Long clientNo) {
        return accReqTableService.getAccReqsByClientNo(clientNo);
    }
    
    /**
     * 获取所有账户申请信息
     * @return 账户申请信息列表
     */
    @GetMapping
    public List<AccReqTable> getAllAccReqs() {
        return accReqTableService.getAllAccReqs();
    }
    
    /**
     * 分页查询账户申请信息
     * @param currentPage 当前页码
     * @param pageSize 每页大小
     * @return 分页结果
     */
    @GetMapping("/page")
    public Page<AccReqTable> getAccReqPage(@RequestParam(defaultValue = "1") int currentPage,
                                           @RequestParam(defaultValue = "10") int pageSize) {
        return accReqTableService.getAccReqPage(currentPage, pageSize);
    }
    
    /**
     * 更新账户申请信息
     * @param accReqTable 账户申请信息
     * @return 是否更新成功
     */
    @PutMapping
    public boolean updateAccReq(@RequestBody AccReqTable accReqTable) {
        return accReqTableService.updateAccReqById(accReqTable);
    }
}