package se.lexicon.course_manager_assignment.data.service.converter;

import org.springframework.stereotype.Component;
import se.lexicon.course_manager_assignment.dto.views.CourseView;
import se.lexicon.course_manager_assignment.dto.views.StudentView;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class ModelToDto implements Converters {

//DONE
    @Override
    public StudentView studentToStudentView(Student student) {
        return new StudentView(student.getId(), student.getName(), student.getEmail(), student.getAddress());
    }
//DONE
    @Override
    public CourseView courseToCourseView(Course course) {
        return new CourseView(
                course.getId(),
                course.getCourseName(),
                course.getStartDate(),
                course.getWeekDuration(),
                studentsToStudentViews(course.getStudents()));
    }
//DONE
    @Override
    public List<CourseView> coursesToCourseViews(Collection<Course> courses) {
        List<Course> result = new ArrayList<>();
        for (Course course : courses) {
            if (course.equals(course.getCourseName())){
                    result.add(course);
                }
            }

        return coursesToCourseViews(result);
    }
//DONE
    @Override
    public List<StudentView> studentsToStudentViews(Collection<Student> students) {

        List<StudentView> result = new ArrayList<>();
        for (Student student : students) {
            result.add(studentToStudentView(student));
        }
        return result;
    }
}
