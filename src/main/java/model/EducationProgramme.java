package model;

public class EducationProgramme {
    private int groupId;
    private int subjectId;

    public EducationProgramme(int groupId, int subjectId) {
        this.groupId = groupId;
        this.subjectId = subjectId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public String toString() {
        return "model.EducationProgramme{" +
                "groupId=" + groupId +
                ", subjectId=" + subjectId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EducationProgramme that = (EducationProgramme) o;

        if (groupId != that.groupId) return false;
        return subjectId == that.subjectId;

    }
}
