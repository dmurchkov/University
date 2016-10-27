package model;

import javax.persistence.*;

@Entity
@Table(name = "teachers")
public class Teacher extends IdEntity {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int experience;

    @Column(nullable = false)
    private int subjectId;
    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    //--> create subject_id column in teachers table (subject_id is a foreign key)
    private Subject subject;

    public Teacher() {
    }

    public Teacher(String name, int experience, int subjectId) {
        this.name = name;
        this.experience = experience;
        this.subjectId = subjectId;
    }

    public Teacher(String name, int experience, int subjectId, Subject subject) {
        this.name = name;
        this.experience = experience;
        this.subjectId = subjectId;
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Teacher teacher = (Teacher) o;

        return this.getId() == teacher.getId() &&
                this.getName().equals(teacher.getName()) &&
                this.getExperience() == teacher.getExperience();

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + experience;
        result = 31 * result + subjectId;
        return result;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", experience=" + experience +
                ", subjectId=" + subjectId +
                '}';
    }
}
