package control;

import facade.ControlFacade;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Tobias Jacobsen
 */
public class Tester {
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
        EntityManager em = emf.createEntityManager();
        ControlFacade control = new Control();
        
        long sum = control.getTotalStudyPointScore();
        System.out.println("Total score is: " + sum);
        
        long sum1 = control.getTotalStudyPointScore(3);
        System.out.println("Total score is: " + sum1);
        
    }
    
}
