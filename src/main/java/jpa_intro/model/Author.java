package jpa_intro.model;

import javax.persistence.*;
import java.util.Date;

// POJO class + ID field
@Entity
@Table(name = "authors")
public class Author extends IdEntity {

    @Column(name = "author_name", length = 20, nullable = false)//"name" by default
    private String name;

    @Column
    private double salary;

    @Temporal(TemporalType.DATE)
    private Date birthday;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @Transient
    private String wontBeAddedToTable;

    public Author() {
    }

    public Author(String name, double salary, Date birthday) {
        this.name = name;
        this.salary = salary;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
