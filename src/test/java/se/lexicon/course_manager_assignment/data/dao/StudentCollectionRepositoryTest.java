package se.lexicon.course_manager_assignment.data.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;
import se.lexicon.course_manager_assignment.model.Student;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {StudentCollectionRepository.class})
public class StudentCollectionRepositoryTest {

    @Autowired
    private StudentDao testObject;

    @Test
    @DisplayName("Test context successfully setup")
    void context_loads() {
        assertFalse(testObject == null);
    }

    //Write your tests here
    @Test
    public void testCreateStudent (){
        Student studentSebbe = new Student(1,"Sebastian", "se.bo@gmail.com", "Vemboö Gamla skola");
        Student studentEmma = new Student(2,"Emma", "emma@gmail.com", "Emmavägen 6");
        Student studentMatteo = new Student(3,"Matteo", "matteo@gmail.com", "Matteovägen 1");

        //testObject.createStudent(studentSebbe.getName(), studentSebbe.getEmail(), studentSebbe.getAddress());
        testObject.createStudent(studentEmma.getName(), studentEmma.getEmail(), studentEmma.getAddress());
        //testObject.createStudent(studentMatteo.getName(), studentMatteo.getEmail(), studentMatteo.getAddress());



        //assertEqual(Object) !!
        //assertEquals(studentSebbe, testObject.createStudent(studentSebbe.getName(), studentSebbe.getEmail(), studentSebbe.getAddress()));
        assertEquals(studentEmma, testObject.createStudent(studentEmma.getName(), studentEmma.getEmail(), studentEmma.getAddress()));

    }
    @Test
    public void findByEmailIgnoreCase () {
        //Expected Student
        Student studentEmma = new Student(1, "Emma", "emma@gmail.com", "Emmavägen 6");
        Student studentSebbe = new Student(2, "Sebastian", "se.bo@gmail.com", "Vemboö Gamla skola");

        //Actual Student
        testObject.createStudent(studentEmma.getName(), studentEmma.getEmail(), studentEmma.getAddress());
        testObject.createStudent(studentSebbe.getName(), studentSebbe.getEmail(), studentSebbe.getAddress());

        //assertEquals(studentEmma, testObject.findByEmailIgnoreCase(studentEmma.getEmail()));
        assertEquals(studentSebbe, testObject.findByEmailIgnoreCase(studentSebbe.getEmail()));

    }
    @Test
    public void findByNameContains (){
        Student studentMatteo = new Student(1,"Matteo", "matteo@gmail.com", "Matteovägen 1");

        testObject.createStudent(studentMatteo.getName(), studentMatteo.getEmail(), studentMatteo.getAddress());
        assertEquals(studentMatteo, testObject.findByNameContains(studentMatteo.getName()));


    }
    @Test
    public void findById (){
        Student studentMatteo = new Student(1,"Matteo", "matteo@gmail.com", "Matteovägen 1");
        testObject.createStudent(studentMatteo.getName(), studentMatteo.getEmail(), studentMatteo.getAddress());
        assertEquals(studentMatteo, testObject.findById(studentMatteo.getId()));
    }
    @Test
    public void findAll() {
        //Create an array for expected result
        List<Student> findAllStudents = new ArrayList<>();
        Student studentMatteo = new Student(1,"Matteo", "matteo@gmail.com", "Matteovägen 1");
        Student studentSebbe = new Student(2,"Sebastian", "se.bo@gmail.com", "Vemboö Gamla skola");

        findAllStudents.add(studentMatteo);
        findAllStudents.add(studentSebbe);


        //Array for actuall result
        testObject.createStudent(studentMatteo.getName(), studentMatteo.getEmail(), studentMatteo.getAddress());
        testObject.createStudent(studentSebbe.getName(), studentSebbe.getEmail(), studentSebbe.getAddress());

        assertEquals(findAllStudents, testObject.findAll());

    }

    //boolean removeStudent(Student student)
    @Test
    public void removeStudent (){
        List<Student> studentList = new ArrayList<>();
        Student student1 = new Student(1,"Sebastian", "se.bo@gmail.com", "Vemboö Gamla skola");
        Student student2 = new Student(2,"Emma", "emma@gmail.com", "Emmavägen 6");

        studentList.add(student1);
        studentList.add(student2);

        studentList.remove(student1);

        List<Student> actualList = new ArrayList<>();
        actualList.add(testObject.createStudent(student1.getName(), student1.getEmail(), student1.getAddress()));
        actualList.add(testObject.createStudent(student2.getName(), student2.getEmail(), student2.getAddress()));

        actualList.remove(student1);

        assertEquals(studentList.size(), actualList.size());





    }

    @Test
    public void clear() {
        Student studentSebbe = new Student(1,"Sebastian", "se.bo@gmail.com", "Vemboö Gamla skola");
        Student studentEmma = new Student(2,"Emma", "emma@gmail.com", "Emmavägen 6");
        Student studentMatteo = new Student(3,"Matteo", "matteo@gmail.com", "Matteovägen 1");

        testObject.createStudent(studentSebbe.getName(), studentSebbe.getEmail(), studentSebbe.getAddress());
        testObject.createStudent(studentEmma.getName(), studentEmma.getEmail(), studentEmma.getAddress());
        testObject.createStudent(studentMatteo.getName(), studentMatteo.getEmail(), studentMatteo.getAddress());

        testObject.clear();
        assertNotEquals(studentSebbe, testObject.findByNameContains("Sebbe"));
    }

    @AfterEach
    void tearDown() {
        testObject.clear();
        StudentSequencer.setStudentSequencer(0);
    }
}
