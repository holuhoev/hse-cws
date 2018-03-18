package hse.holuhoev.domain;

import javax.persistence.*;

/**
 * Факультет.
 *
 * @author Evgeny Kholukhoev
 */
@Entity
@Table(name = "INSTITUTE")
public class Institute {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "institute_name")
    private String name;


    public Institute(String name) {
        this.name = name;
    }

    public Institute() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
