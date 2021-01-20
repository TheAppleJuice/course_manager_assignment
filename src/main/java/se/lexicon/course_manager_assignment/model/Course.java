package se.lexicon.course_manager_assignment.model;

import se.lexicon.course_manager_assignment.data.sequencers.CourseSequencer;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;

public class Course {
    private Integer id;
    private String courseName;
    private LocalDate StartDate;
    private int weekDuration;
    private Collection <Student> students;

    public Course() {
       id = CourseSequencer.nextCourseId();
    }

    public Course (String courseName, LocalDate startDate, int weekDuration) {
        this.id = CourseSequencer.nextCourseId();
        this.courseName = courseName;
        StartDate = startDate;
        this.weekDuration = weekDuration;
    }

    public Course( String courseName, LocalDate startDate, int weekDuration, Collection<Student> students) {
        this.id = CourseSequencer.nextCourseId();
        this.courseName = courseName;
        StartDate = startDate;
        this.weekDuration = weekDuration;
        this.students = students;
    }



    public int getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public LocalDate getStartDate() {
        return StartDate;
    }

    public void setStartDate(LocalDate startDate) {
        StartDate = startDate;
    }

    public int getWeekDuration() {
        return weekDuration;
    }

    public void setWeekDuration(int weekDuration) {
        this.weekDuration = weekDuration;
    }

    public Collection<Student> getStudents() {
        return students;
    }

    public void setStudents(Collection<Student> students) {
        this.students = students;
    }

    public boolean enrollStudent (Student student){
        return true;
    }

    public boolean unenrollStudent (Student student){
        return true;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id && weekDuration == course.weekDuration && Objects.equals(courseName, course.courseName) && Objects.equals(StartDate, course.StartDate) && Objects.equals(students, course.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseName, StartDate, weekDuration, students);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", StartDate=" + StartDate +
                ", weekDuration=" + weekDuration +
                ", students=" + students +
                '}';
    }
}
