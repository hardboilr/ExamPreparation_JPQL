package interfaces;

import entity.Student;
import java.util.List;

public interface ControlFacade {

    /**
     * Returns all students in the system
     *
     * @return List with students
     */
    public List<Student> getAllStudents();

    /**
     * Returns all students in the system with the first Name jan
     *
     * @return List with students first-named Jan
     */
    public List<Student> getStudentsFirstNamedJan();

    /**
     * Returns all students in the system with the last name Olsen
     *
     * @return List with students last-named Olsen
     */
    public List<Student> getStudentsLastNamedOlsen();

    /**
     * Returns the total sum of study-point scores, for a student given the
     * student id.
     *
     * @param id for student
     * @return sum of study-points for given student
     */
    public int getTotalStudyPointScore(int id);

    /**
     * Returns the total sum of study-point scores, given to all students.
     *
     * @return sum of study-points for all students
     */
    public int getTotalStudyPointScore();

    /**
     * Returns those students that has the greatest total of study-point scores
     *
     * @return List with students with greatest total of study-point scores
     */
    public List<Student> getStudentsMaxStudyPoints();

    /**
     * Returns those students that has the lowest total of study-point scores
     *
     * @return list with students with lowest total of study-point scores
     */
    public List<Student> getStudentsMinStudyPoints();
    
    /**
     * Create new student in system
     * @param firstName 
     * @param lastName
     * @return Student entity created
     */
    public Student createStudent(String firstName, String lastName);
    
    
}
