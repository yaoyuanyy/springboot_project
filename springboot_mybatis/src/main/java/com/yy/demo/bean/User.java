package com.yy.demo.bean;

public class User {

	private long id;
    private long studentId;
    private String name;
	private String mobile;
	private int score;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", score=" + score +
                '}';
    }
}
