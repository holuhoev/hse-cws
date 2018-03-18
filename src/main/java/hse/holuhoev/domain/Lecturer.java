package hse.holuhoev.domain;


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
    @Column(name = "ID")
    private Integer lecturerOid;

    @Column(name = "chair_id")
    private Integer chairOid;

    @Column(name = "fio")
    private String fio;

    @Column(name = "short_fio")
    private String shortFIO;

    @Transient
    private String chair;


    public Integer getLecturerOid() {
        return lecturerOid;
    }

    public void setLecturerOid(Integer lecturerOid) {
        this.lecturerOid = lecturerOid;
    }

    public Lecturer() {
    }

    public Integer getChairOid() {
        return chairOid;
    }

    public void setChairOid(Integer chairOid) {
        this.chairOid = chairOid;
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
