package com.hyjz.hnovel.bean;

public class MaiDianBean2 {
       private String timestamp;//": "记录的时间戳，精确到毫秒，string类型",
       private String memberId;//": "会员标识，string类型",
       private String fromUrl;//": "起点地址，string类型",
       private String toUrl;//": "终点地址，string类型",
       private String dataType;//": "数据类型，（1 有页面跳转 | 2无页面跳转的按钮点击事件），string类型",
       private String stayTime;//": "停留时长，精确到毫秒，string类型",
       private String ipAddr;//": "ip地址，String类型",
       private String network;//": "网络类型，（1 wifi | 2 数据流量 |3 其他），String类型",
       private String browser;//": "浏览器，String类型",
       private String client;//": "客户端，（1 pc|2 android|3 ios|4 其他），string类型",
//       private String articleId;//": "文章id，string类型",
//       private String chapterId;//": "章节序号，string类型",
//       private String chapterTotal;//": "章节总数，string类型",
       private String softwareType;//": "软件形式（1 h5 |2 安卓软件 |3 ios软件|4 其他），string类型",
       private String fromId;//": "起点标识，string类型",
       private String toId;//": "终点标识，string类型

       public String getTimestamp() {
              return timestamp;
       }

       public void setTimestamp(String timestamp) {
              this.timestamp = timestamp;
       }

       public String getMemberId() {
              return memberId;
       }

       public void setMemberId(String memberId) {
              this.memberId = memberId;
       }

       public String getFromUrl() {
              return fromUrl;
       }

       public void setFromUrl(String fromUrl) {
              this.fromUrl = fromUrl;
       }

       public String getToUrl() {
              return toUrl;
       }

       public void setToUrl(String toUrl) {
              this.toUrl = toUrl;
       }

       public String getDataType() {
              return dataType;
       }

       public void setDataType(String dataType) {
              this.dataType = dataType;
       }

       public String getStayTime() {
              return stayTime;
       }

       public void setStayTime(String stayTime) {
              this.stayTime = stayTime;
       }

       public String getIpAddr() {
              return ipAddr;
       }

       public void setIpAddr(String ipAddr) {
              this.ipAddr = ipAddr;
       }

       public String getNetwork() {
              return network;
       }

       public void setNetwork(String network) {
              this.network = network;
       }

       public String getBrowser() {
              return browser;
       }

       public void setBrowser(String browser) {
              this.browser = browser;
       }

       public String getClient() {
              return client;
       }

       public void setClient(String client) {
              this.client = client;
       }

       public String getSoftwareType() {
              return softwareType;
       }

       public void setSoftwareType(String softwareType) {
              this.softwareType = softwareType;
       }

       public String getFromId() {
              return fromId;
       }

       public void setFromId(String fromId) {
              this.fromId = fromId;
       }

       public String getToId() {
              return toId;
       }

       public void setToId(String toId) {
              this.toId = toId;
       }
}
