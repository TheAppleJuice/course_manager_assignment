package se.lexicon.course_manager_assignment.data.service.converter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager_assignment.data.service.course.CourseManager;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {ModelToDto.class})
public class ModelToDtoTest {

    @Autowired
    private Converters testObject;

    LocalDate javaStartDate = LocalDate.of(2021, 02,01);
    LocalDate cStartDate = LocalDate.of(2021, 03,01);

    // Expected test courses
    Course course1 = new Course(1,"Java", javaStartDate,5 );
    Course course2 = new Course(2,"C++", cStartDate, 7);

    //Expected test students
    Student student1 = new Student(1,"Sebastian", "se.bo@gmail.com", "Vemboö Gamla skola");
    Student student2 = new Student(2,"Emma", "emma@gmail.com", "Emmavägen 6");

    //CourseManager test = new CourseManager(course1.enrollStudent(student1),);


    @Test
    @DisplayName("Test context successfully setup")
    void context_loads() {
        assertNotNull(testObject);
    }

    @Test
    //courseToCourseView(Course course)
    public void courseToCourseView (){

    }
    //write your tests here
}
