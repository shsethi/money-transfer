package com.shubham.www.dao;

/**
 * @author shsethi
 */
public class BuildInfo {

    private String status;

    public BuildInfo() {
    }

    private String version;

    public BuildInfo(String status, String version) {
        this.status = status;
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
