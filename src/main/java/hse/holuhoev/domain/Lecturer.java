package hse.holuhoev.domain;


import hse.holuhoev.ruz.util.JsonAttribute;

import javax.persistence.*;

/**
 * Преподаватель.
 *
 * @author Evgeny Kholukhoev
 */
@Entity
@Table(name = "LECTURER")
public class Lecturer extends RuzObject {
    @Id
    @JsonAttribute(name = "lecturerOid")
    @Column(name = "ID")
    private Integer Id;

    @JsonAttribute(name = "chairOid")
    @Column(name = "chair_id")
    private Integer chairId;

    @JsonAttribute
    @Column(name = "fio")
    private String fio;

    @JsonAttribute
    @Column(name = "short_fio")
    private String shortFIO;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        this.Id = id;
    }

    public Lecturer() {
    }

    public Integer getChairId() {
        return chairId;
    }

    public void setChairId(Integer chairId) {
        this.chairId = chairId;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getShortFIO() {
        return shortFIO;
    }

    public void setShortFIO(String shortFio) {
        this.shortFIO = shortFio;
    }
}
