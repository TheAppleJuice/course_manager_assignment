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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(classes = {StudentCollectionRepository.class})
public class StudentCollectionRepositoryTest {
    StudentDao studentTest;

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
        Collection<Student> students = new ArrayList<>();
        Student studentSebbe = new Student("Sebastian", "se.bo@gmail.com", "Vemboö Gamla skola");
        Student studentOla = new Student("Ola", "ola.string@gmail.com", "Stringvägen 6");
        Student studentMicke = new Student("Mikael", "mi.au@gmail.com", "Liggavägen 69");

        students.add(studentSebbe);
        students.add(studentOla);
        students.add(studentMicke);

        //studentTest.createStudent("Ola", "ola@ola.se", "Olavägen");
        //studentTest.createStudent("Micke", "micke@micke.se", "Mickevägen");

        //assertEquals(1,1,"Ola");
        //assertEquals(studentOla,students.findById(1), "Ola");
        //assertEquals(studentOla, students.);



    }


    @AfterEach
    void tearDown() {
        testObject.clear();
        StudentSequencer.setStudentSequencer(0);
    }
}
