package se.lexicon.course_manager_assignment.data.dao;



import se.lexicon.course_manager_assignment.data.sequencers.CourseSequencer;
import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;

import java.time.LocalDate;
import java.util.*;


public class CourseCollectionRepository implements CourseDao{

    private Collection<Course> courses;


    public CourseCollectionRepository(Collection<Course> courses) {
        this.courses = courses;
    }
//DONE
    @Override
    public Course createCourse(String courseName, LocalDate startDate, int weekDuration) {
        int id = CourseSequencer.nextCourseId();

        Course newCourse = new Course(id, courseName, startDate, weekDuration);

        courses.add(newCourse);
        return newCourse;
    }
//DONE
    @Override
    public Course findById(int id) {
        if (id <1){
            return null;
        }
        Course findCourse = null;
        for (Course course : courses){
            if (course.getId()==id){
                findCourse = course;
                break;
            }
        }
        return findCourse;

    }
//DONE
    @Override
    public Collection<Course> findByNameContains(String name) {
        List<Course> result = new ArrayList<>();
        if (name == null) {
            return null;
        }
        for (Course course : courses) {
                if (course.getCourseName().contains(name)) {
                    result.add(course);

            }
        }
        return result;
    }
//DONE
    @Override
    public Collection<Course> findByDateBefore(LocalDate end) {
        List<Course> result = new ArrayList<>();
        for (Course course : courses) {
             if (course.getStartDate().plusWeeks(course.getWeekDuration()).isBefore(end)) {
                    result.add(course);
                }
        }
        return result;
    }
//DONE
    @Override
    public Collection<Course> findByDateAfter(LocalDate start) {
        List<Course> result = new ArrayList<>();
        for (Course course : courses) {
            if (course.getStartDate().isAfter(start)) {
                    result.add(course);
                }
            }
        return result;
    }

//DONE
    @Override
    public Collection<Course> findAll() {
        return new ArrayList<>(courses);

    }
//DONE
    @Override
    public Collection<Course> findByStudentId(int studentId) {
        if (studentId <1){
            return null;
        }
        List<Course> result = new ArrayList<>();
        for (Course course : courses) {
            for (Student student : course.getStudents()){
                if (student.getId()==studentId){
                    result.add(course);
                }
            }
        }
        return result;
    }
//DONE
    @Override
    public boolean removeCourse(Course course) {
        if (course == null) {
            return false;
        }
        boolean isRemoved = false;
        Iterator<Course> courseIterator = courses.iterator();
        while (courseIterator.hasNext()) {
            Course result = courseIterator.next();
            if (result.equals(course)) {
                courseIterator.remove();
                isRemoved = true;
            }
        }
        return isRemoved;
    }

    @Override
    public void clear() {
        this.courses = new ArrayList<>();
    }

}
