package se.lexicon.course_manager_assignment.data.dao;

import org.apache.catalina.LifecycleState;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager_assignment.data.sequencers.CourseSequencer;
import se.lexicon.course_manager_assignment.data.service.course.CourseManager;
import se.lexicon.course_manager_assignment.dto.forms.CreateCourseForm;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {CourseCollectionRepository.class})
public class CourseCollectionRepositoryTest {

    @Autowired
    private CourseDao testObject;
    private StudentDao testStudentObject;

    /*
    LocalDate javaStartDate = LocalDate.of(2021, 02,01);
    LocalDate cStartDate = LocalDate.of(2021, 03,01);
    */

    // Expected test courses
    Course course1 = new Course(1,"Java", LocalDate.of(2021, 03, 01),5 );
    Course course2 = new Course(2,"C++", LocalDate.of(2021, 03,01), 7);
    Course course3 = new Course("Python", LocalDate.of(2021, 10, 01), 10);

    //Expected test students
    Student student1 = new Student(1,"Sebastian", "se.bo@gmail.com", "Vemboö Gamla skola");
    Student student2 = new Student(2,"Emma", "emma@gmail.com", "Emmavägen 6");



    @Test
    @DisplayName("Test context successfully setup")
    void context_loads() {
        assertFalse(testObject == null);
    }

    //Write your tests here
    @Test
    public void createCourse(){
        Course expectedJavaCourse = new Course(1,"Java", LocalDate.of(2021, 02,01), 5);

        Course actualResult = testObject.createCourse(expectedJavaCourse.getCourseName(), expectedJavaCourse.getStartDate(), expectedJavaCourse.getWeekDuration());

        assertEquals(expectedJavaCourse, actualResult);
    }

    @Test
    //findById(int id)
    public void findById (){

        testObject.createCourse(course1.getCourseName(), course1.getStartDate(), course1.getWeekDuration());

        assertEquals(course1,testObject.findById(course1.getId()));
    }

    @Test
    //findByNameContains(String name)
    public void findByNameContains (){
        List<Course> expected = new ArrayList<>();
        expected.add(course1);
        //expected.add(course2);



        testObject.createCourse(course1.getCourseName(), course1.getStartDate(), course1.getWeekDuration());

        assertEquals(expected, testObject.findByNameContains("Java"));
    }

    @Test
    //findByDateBefore(LocalDate end)
    public void findByDateBefore (){
        List<Course> expected = new ArrayList<>();
        expected.add(course1);
        testObject.createCourse(course1.getCourseName(), course1.getStartDate(), course1.getWeekDuration());
        LocalDate endDate = LocalDate.of(2021, 05,10);
        assertEquals(course1, testObject.findByDateBefore(endDate));

    }

    @Test
    //findByDateAfter(LocalDate start)
    public void findByDateAfter (){
        List<Course> expected = new ArrayList<>();
        expected.add(course1);
        testObject.createCourse(course1.getCourseName(), course1.getStartDate(), course1.getWeekDuration());
        LocalDate startDate = LocalDate.of(2021, 01,01);
        assertEquals(course1, testObject.findByDateBefore(startDate));

    }

    @Test
    //findAll()
    public void findAll() {
        List<Course> expectedCourse = new ArrayList<>();
        expectedCourse.add(course1);
        expectedCourse.add(course2);

        testObject.createCourse(course1.getCourseName(), course1.getStartDate(), course1.getWeekDuration());
        testObject.createCourse(course2.getCourseName(), course2.getStartDate(), course2.getWeekDuration());

        assertEquals(expectedCourse, testObject.findAll());

    }

    @Test
    //findByStudentId(int studentId)
    public void findByStudentId (){



    }

    @Test
    //removeCourse(Course course)
    public void removeCourse (){
        List<Course> expectedRemove = new ArrayList<>();
        expectedRemove.add(course1);
        expectedRemove.add(course2);

        expectedRemove.remove(course1);

        List<Course> actualRemove = new ArrayList<>();
        actualRemove.add(testObject.createCourse(course1.getCourseName(), course1.getStartDate(), course1.getWeekDuration()));
        actualRemove.add(testObject.createCourse(course2.getCourseName(), course2.getStartDate(), course2.getWeekDuration()));

        actualRemove.remove(course1);

        assertEquals(expectedRemove.size(), actualRemove.size());

    }

    @Test
    //clear()
    public void clear(){
        testObject.createCourse(course1.getCourseName(), course1.getStartDate(), course1.getWeekDuration());

        testObject.clear();

        assertNotEquals(course1, testObject.findByNameContains("Java"));

    }

    @AfterEach
    void tearDown() {
        testObject.clear();
        CourseSequencer.setCourseSequencer(0);
    }
}
