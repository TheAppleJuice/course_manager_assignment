package se.lexicon.course_manager_assignment.data.dao;



import se.lexicon.course_manager_assignment.data.sequencers.CourseSequencer;
import se.lexicon.course_manager_assignment.model.Course;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;


public class CourseCollectionRepository implements CourseDao{

    private Collection<Course> courses;


    public CourseCollectionRepository(Collection<Course> courses) {
        CourseSequencer.nextCourseId();
        this.courses = courses;
    }

    @Override
    public Course createCourse(String courseName, LocalDate startDate, int weekDuration) {
        Course newCource = new Course(courseName, startDate, weekDuration);
        courses.add(newCource);
        // todo: Kan man göra så?
        //  courses.add(new Course(courseName, startDate, weekDuration));
        return newCource;
    }

    @Override
    public Course findById(int id) {
        return null;
    }

    @Override
    public Collection<Course> findByNameContains(String name) {
        return null;
    }

    @Override
    public Collection<Course> findByDateBefore(LocalDate end) {
        return null;
    }

    @Override
    public Collection<Course> findByDateAfter(LocalDate start) {
        return null;
    }

    @Override
    public Collection<Course> findAll() {
        return null;
    }

    @Override
    public Collection<Course> findByStudentId(int studentId) {
        return null;
    }

    @Override
    public boolean removeCourse(Course course) {
        return false;
    }

    @Override
    public void clear() {
        this.courses = new HashSet<>();
    }
}
