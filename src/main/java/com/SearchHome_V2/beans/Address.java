package com.SearchHome_V2.beans;

public class Address {
    private Long id;
    private Integer provinceCode;
    private String provinceName;
    private Integer districtCode;
    private String districtName;
    private Integer wardsCode;
    private String wardsName;

    public Address( Integer provinceCode, String provinceName, Integer districtCode, String districtName, Integer wardsCode, String wardsName) {
        this.provinceCode = provinceCode;
        this.provinceName = provinceName;
        this.districtCode = districtCode;
        this.districtName = districtName;
        this.wardsCode = wardsCode;
        this.wardsName = wardsName;
    }

    public Address() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(Integer provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public Integer getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(Integer districtCode) {
        this.districtCode = districtCode;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public Integer getWardsCode() {
        return wardsCode;
    }

    public void setWardsCode(Integer wardsCode) {
        this.wardsCode = wardsCode;
    }

    public String getWardsName() {
        return wardsName;
    }

    public void setWardsName(String wardsName) {
        this.wardsName = wardsName;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", provinceCode=" + provinceCode +
                ", provinceName='" + provinceName + '\'' +
                ", districtCode=" + districtCode +
                ", districtName='" + districtName + '\'' +
                ", wardsCode=" + wardsCode +
                ", wardsName='" + wardsName + '\'' +
                '}';
    }
}
