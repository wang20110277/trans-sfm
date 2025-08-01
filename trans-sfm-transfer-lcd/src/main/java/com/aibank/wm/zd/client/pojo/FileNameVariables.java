package com.aibank.wm.zd.client.pojo;

import com.google.common.base.MoreObjects;
import lombok.Data;

@Data
public class FileNameVariables {
    private String date;
    private String sendOrganization;
    private String receiveOrganization;
    private String fileType;
    private String reconDate;
    private String fileName;
    private String taCode;
    private String path;

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("date", date)
                .add("sendOrganization", sendOrganization)
                .add("receiveOrganization", receiveOrganization)
                .add("fileType", fileType)
                .add("reconDate", reconDate)
                .add("fileName", fileName)
                .add("taCode", taCode)
                .add("path", path)
                .toString();
    }
}
