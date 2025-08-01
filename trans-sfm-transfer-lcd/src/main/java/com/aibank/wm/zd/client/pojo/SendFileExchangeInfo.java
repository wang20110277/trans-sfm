package com.aibank.wm.zd.client.pojo;

import lombok.Data;

import java.util.List;

@Data
public class SendFileExchangeInfo {
    private String senderBusiSerialNo;
    private String sendTime;
    private Customer sender;
    private Receiver receiver;

    @Data
    public static class Receiver{
        private String deliveryType;
        private List<Customer> deliveryTo;

    }
    @Data
    public static class Customer {
        private String custId;
        private String subCustId;

        public Customer() {
        }

        public Customer(String custId, String subCustId) {
            this.custId = custId;
            this.subCustId = subCustId;
        }
    }
}
