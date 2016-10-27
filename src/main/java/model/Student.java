package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "students")
public class Student extends IdEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int groupId;

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private Group group;

    @OneToMany(mappedBy = "student")
    private List<Mark> marks;

    public Student() {
    }

    public Student(String name, int groupId) {
        this.name = name;
        this.groupId = groupId;
    }

    public Student(String name, int groupId, Group group) {
        this.name = name;
        this.groupId = groupId;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public void setMarks(List<Mark> marks) {
        this.marks = marks;
    }


    @Override
    public String toString() {
        return "Student{" +
                "id= " + getId() +
                ", name='" + name + '\'' +
                ", groupId=" + groupId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Student student = (Student) o;

        return this.getName().equals(student.getName()) && this.getGroupId() == student.getGroupId();

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + groupId;
        result = 31 * result + (marks != null ? marks.hashCode() : 0);
        return result;
    }
}
