package model;

public class Teacher {
    private int id;
    private String name;
    private int experience;
    private int subjectId;

    public Teacher(int id, String name, int experience, int subjectId) {
        this.id = id;
        this.name = name;
        this.experience = experience;
        this.subjectId = subjectId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "model.Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", experience=" + experience +
                ", subjectId=" + subjectId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Teacher teacher = (Teacher) o;

        if (id != teacher.id) return false;
        if (experience != teacher.experience) return false;
        if (subjectId != teacher.subjectId) return false;
        return name != null ? name.equals(teacher.name) : teacher.name == null;

    }
}
