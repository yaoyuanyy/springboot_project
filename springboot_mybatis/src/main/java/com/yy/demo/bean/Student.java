package com.yy.demo.bean;

public class Student {

	private long id;
	private long teacherId;
	private String schoolName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", teacherId=" + teacherId +
                ", schoolName='" + schoolName + '\'' +
                '}';
    }
}
