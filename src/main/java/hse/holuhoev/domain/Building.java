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
    private Integer buildingOid;

    @Column(name = "address")
    private String address;

    @Column(name = "building_name")
    private String name;

    @Column(name = "city")
    private CityType city;

    public Building() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getBuildingOid() {
        return buildingOid;
    }

    public void setBuildingOid(Integer buildingOid) {
        this.buildingOid = buildingOid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CityType getCity() {
        return city;
    }

    public void setCity(CityType city) {
        this.city = city;
    }
}