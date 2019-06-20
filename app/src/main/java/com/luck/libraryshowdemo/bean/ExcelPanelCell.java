package com.luck.libraryshowdemo.bean;


public class ExcelPanelCell {

    private int status;// 0无信息，空白显示 ；1表示人数已满；2表示人数不够；3课程关闭
    private String courseName;//课程名称,
    private String teacherName;//讲师姓名

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
