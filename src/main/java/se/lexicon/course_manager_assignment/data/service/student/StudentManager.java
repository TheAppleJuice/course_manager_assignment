package se.lexicon.course_manager_assignment.data.service.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.course_manager_assignment.data.dao.CourseDao;
import se.lexicon.course_manager_assignment.data.dao.StudentDao;
import se.lexicon.course_manager_assignment.data.service.converter.Converters;
import se.lexicon.course_manager_assignment.dto.forms.CreateStudentForm;
import se.lexicon.course_manager_assignment.dto.forms.UpdateStudentForm;
import se.lexicon.course_manager_assignment.dto.views.StudentView;
import se.lexicon.course_manager_assignment.model.Student;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Service
public class StudentManager implements StudentService {

    private final StudentDao studentDao;
    private final CourseDao courseDao;
    private final Converters converters;

    @Autowired
    public StudentManager(StudentDao studentDao, CourseDao courseDao, Converters converters) {
        this.studentDao = studentDao;
        this.courseDao = courseDao;
        this.converters = converters;
    }
//DONE
    @Override
    public StudentView create(CreateStudentForm form) {
        if (form == null){
            return null;
        }

        return converters.studentToStudentView(
                studentDao.createStudent(
                form.getName(),
                form.getEmail(),
                form.getAddress()));
    }

    @Override
    public StudentView update(UpdateStudentForm form) {
        if (form.getId() <1 ){
            return null;
        }

        Student updateStudentView = studentDao.findById(form.getId());
        updateStudentView.setName(form.getName());
        updateStudentView.setAddress(form.getAddress());
        updateStudentView.setEmail(form.getEmail());

        return converters.studentToStudentView(updateStudentView);
    }

    @Override
    public StudentView findById(int id) {

        Student findByIdStudentView = studentDao.findById(id);

        return converters.studentToStudentView(findByIdStudentView);
    }

    @Override
    public StudentView searchByEmail(String email) {
        Student searchByEmailStudentView = studentDao.findByEmailIgnoreCase(email);

        return converters.studentToStudentView(searchByEmailStudentView);
    }

    @Override
    public List<StudentView> searchByName(String name) {
        Collection <Student> searchByNameStudentView = studentDao.findByNameContains(name);

        return converters.studentsToStudentViews(searchByNameStudentView);
    }

    @Override
    public List<StudentView> findAll() {
        Collection<Student> findAllStudentView = studentDao.findAll();
        return converters.studentsToStudentViews(findAllStudentView);
    }

    @Override
    public boolean deleteStudent(int id) {
       boolean deleteStudentBoolean = studentDao.removeStudent(studentDao.findById(id));
        return deleteStudentBoolean;
    }
}
