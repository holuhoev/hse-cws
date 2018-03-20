package hse.holuhoev.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BUILDING")
public class Building {
    @Id
    @Column(name = "ID")
    private String buildingOid;

    @Column(name = "address")
    private String address;

    @Column(name = "buildingName")
    private String name;

    public Building() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBuildingOid() {
        return buildingOid;
    }

    public void setBuildingOid(String buildingOid) {
        this.buildingOid = buildingOid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
