package com.reaktorlabs.sam.repository.model.asset;

import com.reaktorlabs.sam.repository.model.samuser.SamUser;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@SecondaryTables({
    @SecondaryTable(name = "asset_name"),
    @SecondaryTable(name = "manufacturer"),
    @SecondaryTable(name = "model")
})
public class Asset implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "asset_id")
    private Long id;
    @NotNull
    @Size(min = 3, max = 30)
    @Column(table = "asset_name", name = "asset_name", nullable = false, length = 30)
    private String assetName;
    @NotNull
    @Size(min = 5, max = 60)
    @Column(name = "serial_number", nullable = false, unique = true, length = 60)
    private String serialNumber;
    @NotNull
    @Size(min = 2, max = 30)
    @Column(table = "manufacturer", name = "manufacturer_name", nullable = false, length = 30)
    private String manufacturer;
    @NotNull
    @Size(min = 2, max = 30)
    @Column(table = "model", name = "model_name", nullable = false, length = 30)
    private String model;
    @NotNull
    @Size(min = 3, max = 30)
    @Column(nullable = false, length = 30)
    private String location;
    @NotNull
    @Pattern(regexp = "^\\w{3}-\\d{4}$")
    @Column(name = "asset_tag", nullable = false, unique = true, length = 8)
    private String assetTag;
    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AssetStatusEnum status;
    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "end_of_support")
    private Date supportEndDate;
    @ManyToOne
    @JoinColumn(name = "sam_user_id")
    private SamUser owner;

    public Asset() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAssetTag() {
        return assetTag;
    }

    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
    }

    public AssetStatusEnum getStatus() {
        return status;
    }

    public void setStatus(AssetStatusEnum status) {
        this.status = status;
    }

    public Date getSupportEndDate() {
        return supportEndDate;
    }

    public void setSupportEndDate(Date supportEndDate) {
        this.supportEndDate = supportEndDate;
    }

    public SamUser getOwner() {
        return owner;
    }

    public void setOwner(SamUser user) {
        this.owner = user;
    }
}
