package se.lexicon.course_manager_assignment.data.dao;



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

        students.add(newStudent);
        return newStudent;
    }

//DONE
    @Override
    public Student findByEmailIgnoreCase(String email) {
        /*if (email == null){
            throw new IllegalArgumentException("No match");
        }*/
        Student findStudentByEmail = null;
        for (Student student : students){
            if (student.getEmail().equalsIgnoreCase(email)){
                findStudentByEmail = student;
                break;
            }
        }
        return findStudentByEmail;
    }

//DONE ?
    @Override
    public Collection<Student> findByNameContains(String name) {
        List <Student> result = new ArrayList<>();
        for (Student student : students) {
                if (name.equalsIgnoreCase(student.getName())) {
                    result.add(student);
                }
            }
        return result;
    }

//DONE
    @Override
    public Student findById(int id) {

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
        this.students = new ArrayList<>();
    }
}
