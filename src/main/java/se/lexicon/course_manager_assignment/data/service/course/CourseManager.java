package se.lexicon.course_manager_assignment.data.service.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.course_manager_assignment.data.dao.CourseDao;
import se.lexicon.course_manager_assignment.data.dao.StudentDao;
import se.lexicon.course_manager_assignment.data.service.converter.Converters;
import se.lexicon.course_manager_assignment.dto.forms.CreateCourseForm;
import se.lexicon.course_manager_assignment.dto.forms.UpdateCourseForm;
import se.lexicon.course_manager_assignment.dto.views.CourseView;
import se.lexicon.course_manager_assignment.dto.views.StudentView;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;


import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Service
public class CourseManager implements CourseService {

    private final CourseDao courseDao;
    private final StudentDao studentDao;
    private final Converters converters;

    @Autowired
    public CourseManager(CourseDao courseDao, StudentDao studentDao, Converters converters) {
        this.courseDao = courseDao;
        this.studentDao = studentDao;
        this.converters = converters;
    }

    @Override
    public CourseView create(CreateCourseForm form) {

       return new CourseView(
               form.getId(),
               form.getCourseName(),
               form.getStartDate(),
               form.getWeekDuration(),
               converters.studentsToStudentViews(studentDao.findAll()));
                    //TODO: Kolla ang findAll... Så det funkar.
    }

    @Override
    public CourseView update(UpdateCourseForm form) {
        Course updateCourseView = courseDao.findById(form.getId());
        updateCourseView.setCourseName(form.getCourseName());
        updateCourseView.setStartDate(form.getStartDate());
        updateCourseView.setWeekDuration(form.getWeekDuration());

        return converters.courseToCourseView(updateCourseView);
    }

    @Override
    public List<CourseView> searchByCourseName(String courseName) {
        Collection <Course> searchByCourseNameCourseView = courseDao.findByNameContains(courseName);

        return converters.coursesToCourseViews(searchByCourseNameCourseView);
    }

    @Override
    public List<CourseView> searchByDateBefore(LocalDate end) {
        Collection <Course> searchByDateBeforeCourseView = courseDao.findByDateBefore(end);
        return converters.coursesToCourseViews(searchByDateBeforeCourseView);
    }

    @Override
    public List<CourseView> searchByDateAfter(LocalDate start) {
        Collection <Course> searchByDateAfterCourseView = courseDao.findByDateAfter(start);
        return converters.coursesToCourseViews(searchByDateAfterCourseView);
    }

    @Override
    public boolean addStudentToCourse(int courseId, int studentId) {
        boolean addStudentToCourseBoolean =
                courseDao.findById(courseId).enrollStudent(studentDao.findById(studentId));
        return addStudentToCourseBoolean;
    }

    @Override
    public boolean removeStudentFromCourse(int courseId, int studentId) {
        boolean removeStudentFromCourseBoolean =
                courseDao.findById(courseId).unenrollStudent(studentDao.findById(studentId));
        return removeStudentFromCourseBoolean;
    }

    @Override
    public CourseView findById(int id) {
        return null;
    }

    @Override
    public List<CourseView> findAll() {
        Collection <Course> findAllCourseView = courseDao.findAll();
        return converters.coursesToCourseViews(findAllCourseView);
    }

    @Override
    public List<CourseView> findByStudentId(int studentId) {
        Collection <Course> findByStudentIdCourseView = courseDao.findByStudentId(studentId);
        return converters.coursesToCourseViews(findByStudentIdCourseView);
    }

    @Override
    public boolean deleteCourse(int id) {
        boolean deleteCourseBoolean = courseDao.removeCourse(courseDao.findById(id));
        return deleteCourseBoolean;
    }
}
