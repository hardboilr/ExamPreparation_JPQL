package facade;

import entity.Student;
import java.util.List;

/**
 * @author Tobias Jacobsen
 */
public interface ControlFacade {
    //Find all Students in the system 
    public List<Student> getAllStudents();

    //Find all Students in the System with the first Name Jan
    public List<Student> getStudentsFirstNamedJan();
    
    //Find all Students in the system with the last name Olsen
    public List<Student> getStudentsLastNamedOlsen();
    
    //Find the total sum of study point scores, for a student given the student id.
    public long getTotalStudyPointScore(int id);
    
    //Find the total sum of studypoint scores, given to all students.
    public long getTotalStudyPointScore();
    
    //Find those students that has the greatest total of studypoint scores
    public List<Student> getStudentsMaxStudyPoints();
    
    //Find those students that has the lowest total of studypoint scores 
    public List<Student> getStudentsMinStudyPoints();
}
