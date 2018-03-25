package hse.holuhoev.domain;

import hse.holuhoev.ruz.RuzField;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BUILDING")
public class Building {
    @Id
    @RuzField(name = "buildingOid")
    @Column(name = "ID")
    private Integer Id;

    @RuzField()
    @Column(name = "address")
    private String address;

    @RuzField()
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

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        this.Id = id;
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
