package com.trans.sfm.sa.online.util;

import java.util.Objects;

/**
 * 分片键工具类
 * 提供分片键相关的通用方法
 */
public class PartitionKeyUtils {
    
    /**
     * 根据客户号生成分片键
     * 为了确保数据分布均匀，可以使用一些策略来生成分片键
     * 
     * @param clientNo 客户号
     * @return 分片键
     */
    public static Long generatePartitionKey(Long clientNo) {
        if (clientNo == null) {
            return null;
        }
        // 可以根据业务需求调整分片键生成策略
        // 当前直接使用客户号作为分片键
        return clientNo;
    }
    
    /**
     * 根据多个因素生成分片键
     * 在某些场景下，可能需要根据多个字段来生成分片键以获得更好的数据分布
     * 
     * @param clientNo 客户号
     * @param transDate 交易日期
     * @return 分片键
     */
    public static Long generatePartitionKey(Long clientNo, String transDate) {
        if (clientNo == null) {
            return null;
        }
        
        // 示例：结合客户号和交易日期生成分片键
        // 这里只是一个简单的示例，实际应用中应根据数据分布情况设计算法
        if (transDate != null && transDate.length() >= 8) {
            // 取交易日期的最后4位（日和月）作为因子
            try {
                int dateFactor = Integer.parseInt(transDate.substring(4));
                return clientNo * 10000 + dateFactor;
            } catch (NumberFormatException e) {
                // 如果转换失败，回退到只使用客户号
                return clientNo;
            }
        }
        return clientNo;
    }
    
    /**
     * 验证分片键是否有效
     * 
     * @param partitionKey 分片键
     * @return 是否有效
     */
    public static boolean isValidPartitionKey(Long partitionKey) {
        return partitionKey != null && partitionKey >= 0;
    }
    
    /**
     * 根据客户号生成优化的分片键，使用哈希算法实现更好的数据分布
     * 
     * @param clientNo 客户号
     * @return 分片键
     */
    public static Long generateOptimizedPartitionKey(Long clientNo) {
        if (clientNo == null) {
            return null;
        }
        // 使用 Objects.hash 来生成更均匀分布的分片键
        // 这样可以避免直接使用客户号可能导致的数据倾斜问题
        return (long) Objects.hash(clientNo) & 0x7FFFFFFF;
    }
}