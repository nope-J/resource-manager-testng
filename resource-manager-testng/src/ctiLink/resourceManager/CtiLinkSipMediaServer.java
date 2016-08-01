package ctiLink.resourceManager;

import java.util.Date;

public class CtiLinkSipMediaServer {
	private Integer id;

    private String instanceId;

    private String mac;

    private Integer groupId;

    private Integer sipId;

    private String ipAddr;

    private String externalIpAddr;

    private Integer port;

    private String description;

    private Integer status;

    private Integer active;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId == null ? null : instanceId.trim();
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac == null ? null : mac.trim();
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getSipId() {
        return sipId;
    }

    public void setSipId(Integer sipId) {
        this.sipId = sipId;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr == null ? null : ipAddr.trim();
    }

    public String getExternalIpAddr() {
        return externalIpAddr;
    }

    public void setExternalIpAddr(String externalIpAddr) {
        this.externalIpAddr = externalIpAddr == null ? null : externalIpAddr.trim();
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
