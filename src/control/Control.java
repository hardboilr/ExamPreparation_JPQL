package control;

import entity.Student;
import entity.Studypoint;
import interfaces.ControlFacade;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class Control implements ControlFacade {

    private final EntityManagerFactory emf;

    public Control() {
        emf = Persistence.createEntityManagerFactory("PU");
    }

    @Override
    public List<Student> getAllStudents() {
        EntityManager em = getEntityManager();
        TypedQuery<Student> query = em.createNamedQuery("Student.findAll", Student.class);
        List<Student> list = query.getResultList();
        return list;
    }

    @Override
    public List<Student> getStudentsFirstNamedJan() {
        EntityManager em = getEntityManager();
        TypedQuery<Student> query = em.createNamedQuery("Student.findByFirstname", Student.class).setParameter("firstname", "Jan");
        List<Student> list = query.getResultList();
        return list;
    }

    @Override
    public List<Student> getStudentsLastNamedOlsen() {
        EntityManager em = getEntityManager();
        TypedQuery<Student> query = em.createNamedQuery("Student.findByLastname", Student.class).setParameter("lastname", "Olsen");
        List<Student> list = query.getResultList();
        return list;
    }

    @Override
    public int getTotalStudyPointScore(int id) {
        EntityManager em = getEntityManager();
        Query query = em.createNativeQuery("SELECT SUM(s.score) as totalPoints FROM Studypoint s where s.student_id = ?1").setParameter(1, id);
        BigDecimal sum = (BigDecimal) query.getSingleResult();
        return sum.intValue();
    }

    @Override
    public int getTotalStudyPointScore() {
        EntityManager em = getEntityManager();
        Query query = em.createNativeQuery("SELECT SUM(s.score) FROM Studypoint s");
        BigDecimal sum = (BigDecimal) query.getSingleResult();
        return sum.intValue();
    }

    @Override
    public List<Student> getStudentsMaxStudyPoints() {
        EntityManager em = getEntityManager();
        TypedQuery<Student> query = em.createNamedQuery("Student.findAll", Student.class);
        List<Student> results = (List) query.getResultList();
        List<Student> maxList = new ArrayList();
        int maxSize = 0;
        for (Student s : results) {
            int size = sumScores(s.getStudypointCollection());
            if (size == maxSize) {
                maxList.add(s);
                maxSize = size;
            } else if (size > maxSize) {
                maxList.clear();
                maxList.add(s);
                maxSize = size;
            }
        }
        return maxList;
    }

    @Override
    public List<Student> getStudentsMinStudyPoints() {
        EntityManager em = getEntityManager();
        TypedQuery<Student> query = em.createNamedQuery("Student.findAll", Student.class);
        List<Student> results = (List) query.getResultList();
        List<Student> minList = new ArrayList();
        int minSize = sumScores(results.get(0).getStudypointCollection());
        for (Student s : results) {
            int size = sumScores(s.getStudypointCollection());
            if (size == minSize) {
                minList.add(s);
                minSize = size;
            } else if (size < minSize) {
                minList.clear();
                minList.add(s);
                minSize = size;
            }
        }
        return minList;
    }

    private int sumScores(Collection<Studypoint> scoreList) {
        int scoreSum = 0;
        for (Studypoint p : scoreList) {
            scoreSum += p.getScore();
        }
        return scoreSum;
    }

    @Override
    public Student createStudent(String firstName, String lastName) {
        EntityManager em = getEntityManager();
        Student s = new Student(firstName, lastName);
        try {
            em.getTransaction().begin();
            em.persist(s);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return s;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
