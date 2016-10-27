package model;

import javax.persistence.*;

@Entity
@Table(name = "marks")
public class Mark extends IdEntity {

    @Column(name = "mark", nullable = false)
    private int mark;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private Subject subject;

    public Mark() {
    }

    public Mark(int mark, Student student, Subject subject) {
        this.mark = mark;
        this.student = student;
        this.subject = subject;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
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

        Mark mark1 = (Mark) o;

        return this.getMark() == mark1.getMark();

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + mark;
        return result;
    }
}
