package test;

import control.Control;
import entity.Student;
import entity.Studypoint;
import interfaces.ControlFacade;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ControlTester {

    public ControlTester() {
    }

    @BeforeClass
    public static void beforeClass() {
        cleanDB();
    }

    @After
    public void tearDown() {
        cleanDB();
    }

    @Test
    public void testCreateStudent() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
        EntityManager em = emf.createEntityManager();
        ControlFacade ctrl = new Control();
        String firstName = "Tobias";
        String lastName = "Jacobsen";

        Student createStudent = ctrl.createStudent(firstName, lastName);

        Student student = em.find(Student.class, 1);

        assertEquals(firstName, student.getFirstname());
        assertEquals(lastName, student.getLastname());
    }

    @Test
    public void testAddStudypoint() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
        EntityManager em = emf.createEntityManager();
        ControlFacade ctrl = new Control();
        String firstName = "Tobias";
        String lastName = "Jacobsen";

        String description = "Some test description";
        int maxval = 12;
        int score = 12;

        Student createStudent = ctrl.createStudent(firstName, lastName);

        Student student = em.find(Student.class, 1);
        Studypoint p = new Studypoint(description, maxval, score);
        student.addStudypoint(p);

        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            
        } finally {
            em.close();
        }
        
        em = emf.createEntityManager();
        
        Studypoint studypoint = em.find(Studypoint.class, 1);

        assertEquals(firstName, student.getFirstname());
        assertEquals(lastName, student.getLastname());
        assertEquals(description, studypoint.getDescription());
        assertTrue(maxval == studypoint.getMaxval());
        assertTrue(score == studypoint.getScore());
    }

    private static void cleanDB() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.createNativeQuery("SET FOREIGN_KEY_CHECKS=0").executeUpdate();
        em.createNativeQuery("truncate table student").executeUpdate();
        em.createNativeQuery("truncate table studypoint").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
}
