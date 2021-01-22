package se.lexicon.course_manager_assignment.data.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager_assignment.data.sequencers.CourseSequencer;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(classes = {CourseCollectionRepository.class})
public class CourseCollectionRepositoryTest {

    @Autowired
    private CourseDao testObject;

    Student studentSebbe = new Student(1,"Sebastian", "se.bo@gmail.com", "Vemboö Gamla skola");

    LocalDate javaStartDate = LocalDate.of(2021, 02,01);

    @Test
    @DisplayName("Test context successfully setup")
    void context_loads() {
        assertFalse(testObject == null);
    }

    //Write your tests here
    @Test
    public void createCourse(){
        Course expectedJavaCourse = new Course(1,"Java Collections", javaStartDate, 5);

        Course actualResult = testObject.createCourse(expectedJavaCourse.getCourseName(), expectedJavaCourse.getStartDate(), expectedJavaCourse.getWeekDuration());
        assertEquals(expectedJavaCourse, actualResult);

    }

    @AfterEach
    void tearDown() {
        testObject.clear();
        CourseSequencer.setCourseSequencer(0);
    }
}
