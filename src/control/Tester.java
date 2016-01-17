package control;

import entity.Student;
import interfaces.ControlFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Tester {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
        EntityManager em = emf.createEntityManager();
        ControlFacade ctrl = new Control();

        //1. Find all Students in the system 
        List<Student> allStudents = ctrl.getAllStudents();
        System.out.println("-------------1. Find all students in the system------------");
        for (Student s : allStudents) {
            System.out.println(s.getId() + ". " + s.getFirstname() + " " + s.getLastname());
        }
        System.out.println("-----------------------------------------------------------");

        //2. Find all Students in the System with the first Name jan
        List<Student> studentsFirstNamedJan = ctrl.getStudentsFirstNamedJan();
        System.out.println("-------------2. Find all students in the system with the first name: Jan------------");
        for (Student s : studentsFirstNamedJan) {
            System.out.println(s.getId() + ". " + s.getFirstname() + " " + s.getLastname());
        }
        System.out.println("-----------------------------------------------------------");

        //3. Find all Students in the system with the last name Olsen 
        List<Student> studentsLastNamedOlsen = ctrl.getStudentsLastNamedOlsen();
        System.out.println("-------------3.  Find all students in the system with the last name: Olsen------------");
        for (Student s : studentsLastNamedOlsen) {
            System.out.println(s.getId() + ". " + s.getFirstname() + " " + s.getLastname());
        }
        System.out.println("-----------------------------------------------------------");

        //4. Find the total sum of study point scores, for a student given the student id.
        int totalStudyPointScoreJan = ctrl.getTotalStudyPointScore(1);
        int totalStudyPointScoreHanne = ctrl.getTotalStudyPointScore(2);
        System.out.println("-------------4. Find the total sum of study point scores, for a student given the student id------------");
        System.out.println("Jan: " + totalStudyPointScoreJan);
        System.out.println("Hanne: " + totalStudyPointScoreHanne);
        System.out.println("-----------------------------------------------------------");

        //5. Find the total sum of studypoint scores, given to all students.
        int totalStudyPointScore = ctrl.getTotalStudyPointScore();
        System.out.println("-------------5. Find the total sum of studypoint scores, given to all students------------");
        System.out.println("Total sum: " + totalStudyPointScore);
        System.out.println("-----------------------------------------------------------");

        //6. Find those students that has the greatest total of studypoint scores
        List<Student> studentsMaxStudyPoints = ctrl.getStudentsMaxStudyPoints();
        System.out.println("-------------6. Find those students that has the greatest total of studypoint scores------------");
        for (Student s : studentsMaxStudyPoints) {
            System.out.println(s.getId() + ". " + s.getFirstname() + " " + s.getLastname());
        }
        System.out.println("-----------------------------------------------------------");

        //7. Find those students that has the lowest total of studypoint scores
        List<Student> studentsMinStudyPoints = ctrl.getStudentsMinStudyPoints();
        System.out.println("-------------7. Find those students that has the lowest total of studypoint scores------------");
        for (Student s : studentsMinStudyPoints) {
            System.out.println(s.getId() + ". " + s.getFirstname() + " " + s.getLastname());
        }
        System.out.println("-----------------------------------------------------------");
    }
}
