package hse.holuhoev.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Evgeny Kholukhoev
 */
@Entity
@Table(name = "INSTITUTE")
public class Institute {
    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "name")
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
