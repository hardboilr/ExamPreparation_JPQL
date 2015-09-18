package control;

import entity.Student;
import entity.Studypoint;
import facade.ControlFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * @author Tobias Jacobsen
 */
public class Control implements ControlFacade {

    EntityManagerFactory emf;
    EntityManager em;

    public Control() {
        emf = Persistence.createEntityManagerFactory("PU");
        em = emf.createEntityManager();
    }

    @Override
    public List<Student> getAllStudents() {
        TypedQuery<Student> query = em.createNamedQuery("Student.findAll", Student.class);
        List<Student> list = query.getResultList();
        return list;
    }

    @Override
    public List<Student> getStudentsFirstNamedJan() {
        TypedQuery<Student> query = em.createNamedQuery("Student.findAll", Student.class).setParameter("firstname", "Jan");
        List<Student> list = query.getResultList();
        return list;
    }

    @Override
    public List<Student> getStudentsLastNamedOlsen() {
        TypedQuery<Student> query = em.createNamedQuery("Student.findAll", Student.class).setParameter("lastname", "Olsen");
        List<Student> list = query.getResultList();
        return list;
    }

    @Override
    public long getTotalStudyPointScore(int id) {
        Query query4 = em.createQuery("SELECT SUM(s.score) FROM Studypoint s where s.student_id = :studentid", Studypoint.class);
        Student s = em.find(Student.class, 1);
        query4.setParameter("studentid", s);
        long results4 = (long) query4.getSingleResult();
        return results4;
    }

    @Override
    public long getTotalStudyPointScore() {
        //Find the total sum of studypoint scores, given to all students.
        Query query = em.createQuery("SELECT SUM(s.score) FROM Studypoint s");
        long sum = (long) query.getSingleResult();
        return sum;
    }

    @Override
    public List<Student> getStudentsMaxStudyPoints() {
        //Find those students that has the greatest total of studypoint scores
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Student> getStudentsMinStudyPoints() {
        //Find those students that has the lowest total of studypoint scores 
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
