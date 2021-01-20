package se.lexicon.course_manager_assignment.data.dao;



import com.sun.xml.internal.xsom.impl.scd.Iterators;
import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;
import se.lexicon.course_manager_assignment.model.Student;

import java.util.*;


public class StudentCollectionRepository implements StudentDao {

    private Collection<Student> students;

    public StudentCollectionRepository(Collection<Student> students) {
        this.students = students;
    }

//DONE
    @Override
    public Student createStudent(String name, String email, String address) {
        int id = StudentSequencer.nextStudentId();
        Student newStudent = new Student(id, name, email, address);
        if (newStudent == null){
            throw new IllegalArgumentException("object is null");
        }
        if ( email == null){
            throw new IllegalArgumentException("object is null");
        }
        Student checkStudentDuplicate = findById(newStudent.getId());
        if (checkStudentDuplicate != null){
            throw new IllegalArgumentException("Student exists");

        }
        students.add(newStudent);
        return newStudent;
    }

//DONE
    @Override
    public Student findByEmailIgnoreCase(String email) {
        if (email == null){
            throw new IllegalArgumentException("No match");
        }
        Student findStudentByEmail = null;
        for (Student student : students){
            if (student.getEmail().equalsIgnoreCase(email)){
                findStudentByEmail = student;
                break;
            }
        }
        return findStudentByEmail;
    }

//FIX
    @Override
    public Collection<Student> findByNameContains(String name) {
        List <Student> result = new ArrayList<>();
        /*
        if (name == null){
            throw new IllegalArgumentException("No match");
        }
        Student findStudentByName = null;
        for (Student student : students){
            if (student.getEmail().equalsIgnoreCase(name)){
                findStudentByName = student;
                break;
            }
        }
        return findStudentByName;

         */
        return null;
    }

//DONE
    @Override
    public Student findById(int id) {
        if (id <1){
            throw new IllegalArgumentException("No match");
        }
        Student findStudent = null;
        for (Student student : students){
            if (student.getId()==id){
                findStudent = student;
               break;
            }
        }
        return findStudent;
}
//DONE
    @Override
    public Collection<Student> findAll() {
        return new ArrayList<>(students);
    }


//DONE
    @Override
    public boolean removeStudent(Student student) {

        boolean isRemoved = false;
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student result = iterator.next();
            if (result.equals(student)) {
                iterator.remove();
                isRemoved = true;
            }
        }
        return isRemoved;
    }
//DONE
    @Override
    public void clear() {
        this.students = new HashSet<>();
    }
}
