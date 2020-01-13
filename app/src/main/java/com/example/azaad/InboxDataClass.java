package com.example.azaad;

public class InboxDataClass {

    public String showuser,messagesid,receiverid,lastmessage,senderid,isdeleted,created_date,user_name,userimage;

    public InboxDataClass(String messagesid, String receiverid, String lastmessage, String senderid, String isdeleted, String created_date, String user_name, String userimage, String showuser) {
        this.messagesid = messagesid;
        this.receiverid = receiverid;
        this.lastmessage = lastmessage;
        this.senderid = senderid;
        this.isdeleted = isdeleted;
        this.created_date = created_date;
        this.user_name = user_name;
        this.userimage = userimage;
        this.showuser = showuser;
    }
    InboxDataClass(){}


    public String getShowuser() {
        return showuser;
    }

    public void setShowuser(String showuser) {
        this.showuser = showuser;
    }

    public String getCreated_date() {
        return created_date;
    }

    public String getMessagesid() {
        return messagesid;
    }

    public String getReceiverid() {
        return receiverid;
    }

    public String getLastmessage() {
        return lastmessage;
    }

    public String getSenderid() {
        return senderid;
    }

    public String getIsdeleted() {
        return isdeleted;
    }

    public String getUser_name() {
        return user_name;
    }



    public String getUserimage() {
        return userimage;
    }

    public void setMessagesid(String messagesid) {
        this.messagesid = messagesid;
    }

    public void setReceiverid(String receiverid) {
        this.receiverid = receiverid;
    }

    public void setLastmessage(String lastmessage) {
        this.lastmessage = lastmessage;
    }

    public void setSenderid(String senderid) {
        this.senderid = senderid;
    }

    public void setIsdeleted(String isdeleted) {
        this.isdeleted = isdeleted;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }



    public void setUserimage(String userimage) {
        this.userimage = userimage;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }
}
