package com.example.azaad;

public class DataClassUserChatMessage {


String allmessagesid;
String receiverid;
String senderid;
String messagetext;
String created_date;
String status;
String deletedby;
String isdeleted;

    public DataClassUserChatMessage(String allmessagesid, String receiverid, String senderid, String messagetext, String created_date, String status, String deletedby, String isdeleted) {
        this.allmessagesid = allmessagesid;
        this.receiverid = receiverid;
        this.senderid = senderid;
        this.messagetext = messagetext;
        this.created_date = created_date;
        this.status = status;
        this.deletedby = deletedby;
        this.isdeleted = isdeleted;
    }

    public String getAllmessagesid() {
        return allmessagesid;
    }

    public void setAllmessagesid(String allmessagesid) {
        this.allmessagesid = allmessagesid;
    }

    public String getReceiverid() {
        return receiverid;
    }

    public void setReceiverid(String receiverid) {
        this.receiverid = receiverid;
    }

    public String getSenderid() {
        return senderid;
    }

    public void setSenderid(String senderid) {
        this.senderid = senderid;
    }

    public String getMessagetext() {
        return messagetext;
    }

    public void setMessagetext(String messagetext) {
        this.messagetext = messagetext;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeletedby() {
        return deletedby;
    }

    public void setDeletedby(String deletedby) {
        this.deletedby = deletedby;
    }

    public String getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(String isdeleted) {
        this.isdeleted = isdeleted;
    }




}
