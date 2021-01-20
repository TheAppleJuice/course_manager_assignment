package se.lexicon.course_manager_assignment.data.dao;



import com.sun.xml.internal.xsom.impl.scd.Iterators;
import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;
import se.lexicon.course_manager_assignment.model.Student;

import java.util.*;


public class StudentCollectionRepository implements StudentDao {

    private Collection<Student> students = new HashSet<>();

    public StudentCollectionRepository(Collection<Student> students) {
        StudentSequencer.nextStudentId();
        this.students = students;
    }

    @Override
    public Student createStudent(String name, String email, String address) {
        Student newStudent = new Student(name, email, address);
        if (newStudent == null){
            throw new IllegalArgumentException("object is null");
        }
        if ( email == ){
            throw new IllegalArgumentException("object is null");
        }
        Student checkStudentDuplicate = findByEmailIgnoreCase(students);
        students.add(newStudent);
        return newStudent;
    }
//FEL
    @Override
    public Student findByEmailIgnoreCase(String email) {
        if ()

        return null;
    }
//FEL
    @Override
    public Collection<Student> findByNameContains(String name) {
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            for (String sk : student.getName()) {
                if (sk.equalsIgnoreCase(name)) {
                    result.add(student);
                }
            }
        }

        return result;
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
