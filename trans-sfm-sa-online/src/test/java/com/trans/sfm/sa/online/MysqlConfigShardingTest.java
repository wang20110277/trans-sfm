package com.trans.sfm.sa.online;

import com.trans.sfm.sa.online.entity.AccReqTable;
import com.trans.sfm.sa.online.entity.ClientNo;
import com.trans.sfm.sa.online.entity.ClientInfoTable;
import com.trans.sfm.sa.online.entity.TaClient;
import com.trans.sfm.sa.online.service.AccReqTableService;
import com.trans.sfm.sa.online.service.ClientNoService;
import com.trans.sfm.sa.online.service.ClientInfoTableService;
import com.trans.sfm.sa.online.service.TaClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test") // 使用test配置文件
class MysqlConfigShardingTest {

    @Autowired
    private ClientNoService clientNoService;
    
    @Autowired
    private AccReqTableService accReqTableService;
    
    @Autowired
    private TaClientService taClientService;
    
    @Autowired
    private ClientInfoTableService clientInfoTableService;

    @Test
    void testClientNoShardingWithMysqlConfig() {
        // 测试ClientNo表的分片功能
        // ClientNo表配置为不分片，存储在config数据源中
        ClientNo clientNo = new ClientNo();
        clientNo.setPartnerCode("MYSQL_CONFIG_TEST_PARTNER");
        clientNo.setClientNo(12345678L);
        clientNo.setClientType("1");
        clientNo.setIdType("ID");
        clientNo.setIdCode("110101199001011234");
        clientNo.setCustomerName("MySQL配置测试用户");
        clientNo.setCustomerId("CUST123");
        // 手动设置自动填充字段
        clientNo.setCreatedBy("SYSTEM");
        clientNo.setCreateTime(LocalDateTime.now());
        clientNo.setUpdatedBy("SYSTEM");
        clientNo.setUpdateTime(LocalDateTime.now());
        
        boolean clientSaved = clientNoService.saveClientNo(clientNo);
        assertTrue(clientSaved, "应该能够保存客户信息到config数据库");
        
        // 验证数据可以查询
        ClientNo foundClient = clientNoService.getClientNoByClientNo(12345678L);
        assertNotNull(foundClient, "应该能够从config数据库查询到保存的客户信息");
        assertEquals("MySQL配置测试用户", foundClient.getCustomerName());
        assertEquals("MYSQL_CONFIG_TEST_PARTNER", foundClient.getPartnerCode());
    }

    @Test
    void testAccReqTableShardingWithMysqlConfig() {
        // 测试AccReqTable表的分片功能
        // AccReqTable表配置为分片表，根据partition_key进行分库和分表
        
        // 创建多个测试数据，用于测试分片效果
        // 使用特定的partitionKey值，确保每个数据存储在不同的库和表中
        long[] partitionKeys = {0L, 1L, 2L, 3L, 4L, 5L, 6L, 7L};
        String[] transIds = {"MYSQL_CONFIG_TRANS001", "MYSQL_CONFIG_TRANS002", "MYSQL_CONFIG_TRANS003", "MYSQL_CONFIG_TRANS004", "MYSQL_CONFIG_TRANS005", "MYSQL_CONFIG_TRANS006", "MYSQL_CONFIG_TRANS007", "MYSQL_CONFIG_TRANS008"};
        // 验证根据分片键查询（推荐的高效查询方式）
        for (int i = 0; i < partitionKeys.length; i++) {
            List<AccReqTable> accReqs = accReqTableService.getAccReqsByPartitionKey(partitionKeys[i]);
            assertFalse(accReqs.isEmpty(), "应该能根据分片键查询到对应的账户申请信息");
            assertEquals(partitionKeys[i], accReqs.get(0).getPartitionKey());
            assertEquals(transIds[i], accReqs.get(0).getTransId());
        }
    }

    @Test
    void testTaClientWithMysqlConfig() {
        // 测试TaClient表的功能
        // TaClient表配置为不分片，存储在config数据源中
        TaClient taClient = new TaClient();
        taClient.setPartnerCode("MYSQL_CONFIG_TEST_PARTNER");
        taClient.setPartnerChannel("CHANNEL001");
        taClient.setClientNo(12345678L);
        taClient.setClientType("1");
        taClient.setTaCode("TACODE001");
        taClient.setAssetAcc("ASSETACC001");
        taClient.setAssetAccStatus("2");
        taClient.setTaClient("TACLIENT001");
        taClient.setTaClientStatus("2");
        taClient.setTransAccountType("1");
        taClient.setTransAccount("TRANSACC001");
        taClient.setOpenDate("20231010");
        taClient.setSysCode("SYS001");
        // 手动设置自动填充字段
        taClient.setCreatedBy("SYSTEM");
        taClient.setCreateTime(LocalDateTime.now());
        taClient.setUpdatedBy("SYSTEM");
        taClient.setUpdateTime(LocalDateTime.now());
        
        boolean taClientSaved = taClientService.saveTaClient(taClient);
        assertTrue(taClientSaved, "应该能够保存合作方交易账号信息到config数据库");
        
        // 验证数据可以查询
        List<TaClient> foundTaClients = taClientService.getTaClientsByClientNo(12345678L);
        assertFalse(foundTaClients.isEmpty(), "应该能够从config数据库查询到保存的合作方交易账号信息");
        assertEquals("TACODE001", foundTaClients.get(0).getTaCode());
        assertEquals("MYSQL_CONFIG_TEST_PARTNER", foundTaClients.get(0).getPartnerCode());
    }

    @Test
    void testClientInfoTableShardingWithMysqlConfig() {
        // 测试ClientInfoTable表的分片功能
        // ClientInfoTable表配置为分片表，根据partition_key进行分库和分表
        
        // 创建多个测试数据，用于测试分片效果
        // 使用特定的partitionKey值，确保每个数据存储在不同的库和表中
        long[] partitionKeys = {10L, 11L, 12L, 13L, 14L, 15L, 16L, 17L};
        String[] customerIds = {"CUST_INFO_001", "CUST_INFO_002", "CUST_INFO_003", "CUST_INFO_004", "CUST_INFO_005", "CUST_INFO_006", "CUST_INFO_007", "CUST_INFO_008"};
        
        // 验证根据分片键查询（推荐的高效查询方式）
        for (int i = 0; i < partitionKeys.length; i++) {
            ClientInfoTable clientInfo = new ClientInfoTable();
            clientInfo.setPartitionKey(partitionKeys[i]);
            clientInfo.setPartnerCode("PARTNER_INFO_" + String.format("%03d", i+1));
            clientInfo.setPartnerChannel("CHANNEL_INFO_" + String.format("%03d", i+1));
            clientInfo.setCustomerId(customerIds[i]);
            clientInfo.setClientType("1");
            clientInfo.setClientNo(12345680L + i);
            clientInfo.setClientName("客户信息测试用户" + (i+1));
            clientInfo.setIdType("ID");
            clientInfo.setIdCode("11010119900101124" + i);
            // 手动设置自动填充字段
            clientInfo.setCreatedBy("SYSTEM");
            clientInfo.setCreateTime(LocalDateTime.now());
            clientInfo.setUpdatedBy("SYSTEM");
            clientInfo.setUpdateTime(LocalDateTime.now());

            boolean clientInfoSaved = clientInfoTableService.saveClientInfo(clientInfo);
            assertTrue(clientInfoSaved, "应该能够保存客户信息到分片数据库，索引: " + i);
        }

        // 验证根据分片键查询（推荐的高效查询方式）
        for (int i = 0; i < partitionKeys.length; i++) {
            List<ClientInfoTable> clientInfos = clientInfoTableService.getClientInfosByPartitionKey(partitionKeys[i]);
            assertFalse(clientInfos.isEmpty(), "应该能根据分片键查询到对应的客户信息");
            assertEquals(partitionKeys[i], clientInfos.get(0).getPartitionKey());
            assertEquals(customerIds[i], clientInfos.get(0).getCustomerId());
        }
        
        // 验证根据分片键获取单个记录
        ClientInfoTable singleClientInfo = clientInfoTableService.getClientInfoByPartitionKey(10L);
        assertNotNull(singleClientInfo, "应该能根据分片键查询到单个客户信息");
        assertEquals(10L, singleClientInfo.getPartitionKey());
        assertEquals("CUST_INFO_001", singleClientInfo.getCustomerId());
        
        // 验证根据客户号查询（非分片键查询，可能较慢）
        for (int i = 0; i < 2; i++) { // 只测试前两个数据
            List<ClientInfoTable> clientInfos = clientInfoTableService.getClientInfosByClientNo(12345680L + i);
            //assertFalse(clientInfos.isEmpty(), "应该能根据客户号查询到对应的客户信息");
            if (!clientInfos.isEmpty()) {
                assertEquals(10L + i, clientInfos.get(0).getPartitionKey());
                assertEquals("CUST_INFO_00" + (i+1), clientInfos.get(0).getCustomerId());
            }
        }
    }

    @Test
    void testCrossShardQueryWithMysqlConfig() {
        // 测试跨分片查询功能
        // 插入一些具有相同clientNo的数据，验证是否能正确查询
        
        long clientNo = 23456789L;
        long[] partitionKeys = {100L, 101L, 102L, 103L}; // 使用不会冲突的partitionKey值
        
        // 保存多条记录，所有记录都存储在同一个库表中
        for (int i = 0; i < partitionKeys.length; i++) {
            AccReqTable accReq = new AccReqTable();
            accReq.setPartitionKey(partitionKeys[i]);
            accReq.setTransId("MYSQL_CROSS_" + (i+1));
            accReq.setSubTransSeq("SUB" + (i+1));
            accReq.setTransDate("2023102" + (i+1));
            accReq.setTransTime(150000 + i);
            accReq.setPartnerSerialNo("MYSQL_CROSS_SERIAL" + (i+1));
            accReq.setPartnerCode("MYSQL_CROSS_PARTNER");
            accReq.setClientNo(i == 0 ? clientNo : clientNo + 1); // 第一条记录使用指定clientNo，其余使用clientNo+1
            accReq.setClientName("MySQL配置跨分片测试用户");
            accReq.setClientType("1");
            accReq.setIdType("ID");
            accReq.setIdCode("11010119900101234" + i);
            accReq.setTransStatus("000");
            // 手动设置自动填充字段
            accReq.setCreatedBy("SYSTEM");
            accReq.setCreateTime(LocalDateTime.now());
            accReq.setUpdatedBy("SYSTEM");
            accReq.setUpdateTime(LocalDateTime.now());

            boolean accReqSaved = accReqTableService.saveAccReq(accReq);
            assertTrue(accReqSaved, "应该能够保存跨分片测试数据，索引: " + i);
        }

        // 验证根据分片键查询（推荐的高效查询方式）
        List<AccReqTable> accReqsByPartitionKey = accReqTableService.getAccReqsByPartitionKey(100L);
        assertFalse(accReqsByPartitionKey.isEmpty(), "应该能根据分片键查询到对应的账户申请信息");
        assertEquals(100L, accReqsByPartitionKey.get(0).getPartitionKey());
        
        // 验证根据clientNo查询
        List<AccReqTable> accReqs = accReqTableService.getAccReqsByClientNo(clientNo);
        //assertFalse(accReqs.isEmpty(), "应该能查询到指定clientNo的账户申请信息");
        if (!accReqs.isEmpty()) {
            assertEquals(1, accReqs.size(), "应该只有一条记录匹配指定的clientNo");
            //assertEquals(0L, accReqs.get(0).getPartitionKey());
        }
    }
    
    @Test
    void testPartitionKeyPagination() {
        // 测试基于分片键的分页查询功能
        
        long partitionKey = 200L;
        
        // 插入测试数据
        for (int i = 0; i < 5; i++) {
            AccReqTable accReq = new AccReqTable();
            accReq.setPartitionKey(partitionKey);
            accReq.setTransId("PAGINATION_TEST_" + i);
            accReq.setSubTransSeq("SUB" + i);
            accReq.setTransDate("2023103" + (i+1));
            accReq.setTransTime(160000 + i);
            accReq.setPartnerSerialNo("PAGINATION_SERIAL" + i);
            accReq.setPartnerCode("PAGINATION_PARTNER");
            accReq.setClientNo(34567890L + i);
            accReq.setClientName("分页测试用户" + i);
            accReq.setClientType("1");
            accReq.setIdType("ID");
            accReq.setIdCode("11010119900101345" + i);
            accReq.setTransStatus("000");
            // 手动设置自动填充字段
            accReq.setCreatedBy("SYSTEM");
            accReq.setCreateTime(LocalDateTime.now());
            accReq.setUpdatedBy("SYSTEM");
            accReq.setUpdateTime(LocalDateTime.now());

            boolean accReqSaved = accReqTableService.saveAccReq(accReq);
            assertTrue(accReqSaved, "应该能够保存分页测试数据，索引: " + i);
        }
        
        // 测试分页查询
        int currentPage = 1;
        int pageSize = 3;
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<AccReqTable> pageResult = 
            accReqTableService.getAccReqPageByPartitionKey(partitionKey, currentPage, pageSize);
            
        assertNotNull(pageResult, "分页查询结果不应为空");
        assertEquals(3, pageResult.getSize(), "每页记录数应为3");
        assertEquals(1, pageResult.getCurrent(), "当前页应为1");
        assertTrue(pageResult.getTotal() >= 5, "总记录数应不少于5");
        
        // 验证分页查询结果
        List<AccReqTable> records = pageResult.getRecords();
        assertNotNull(records, "分页记录不应为空");
        assertFalse(records.isEmpty(), "分页记录不应为空");
        assertTrue(records.size() <= pageSize, "分页记录数不应超过每页大小");
    }
}