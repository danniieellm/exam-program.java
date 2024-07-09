import java.util.Scanner;

public class ExamProgram {
    
    private static final int TOTAL_ASSESSMENTS = 5;
    private static final double COURSEWORK_WEIGHT = 0.5; // 50%
    private static final double EXAM_WEIGHT = 0.5; // 50%
    private static final double REQUIRED_COURSEWORK_RATIO = 2.0 / 3.0; // 2/3 of coursework

    // Scanner for user input
    private static Scanner scanner = new Scanner(System.in);
    
    // Arrays to store scores
    private static double[] courseworkScores = new double[TOTAL_ASSESSMENTS];
    private static double[] examScores = new double[3]; // For system analysis, Java programming, structured cabling

    public static void main(String[] args) {
        int choice;
        // Do-while loop for the menu
        do {
            displayMenu();
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    viewCourseworkResults();
                    break;
                case 2:
                    viewExamResults();
                    break;
                case 3:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);
    }

    //  display the menu
    private static void displayMenu() {
        System.out.println("\nDIT409 Exam Program Menu:");
        System.out.println("1. View coursework results");
        System.out.println("2. View exam results");
        System.out.println("3. Exit the program");
        System.out.print("Enter your choice: ");
    }

    // view coursework results
    private static void viewCourseworkResults() {
        System.out.println("\nCoursework Results for DIT409:");
        inputCourseworkScores();
        int completedAssessments = countCompletedAssessments();
        double courseworkTotal = calculateCourseworkTotal();

        System.out.println("Completed assessments: " + completedAssessments + " out of " + TOTAL_ASSESSMENTS);
        System.out.println("Coursework total: " + courseworkTotal + " / 50");

        //  Decision function
        if (isCourseworkSufficient(completedAssessments)) {
            System.out.println("Student has completed at least 2/3 of coursework.");
        } else {
            System.out.println("Student must repeat the course (less than 2/3 of coursework completed).");
        }
    }

    //  view exam results
    private static void viewExamResults() {
        System.out.println("\nExam Results for DIT409:");
        inputExamScores();
        double courseworkTotal = calculateCourseworkTotal();
        double examTotal = calculateExamTotal();
        double totalScore = calculateTotalScore(courseworkTotal, examTotal);

        System.out.println("Coursework: " + courseworkTotal + " / 50");
        System.out.println("Final Exam: " + examTotal + " / 50");
        System.out.println("Total Score: " + totalScore + " / 100");

        int completedAssessments = countCompletedAssessments();
        if (!isCourseworkSufficient(completedAssessments)) {
            System.out.println("Student must repeat the course (less than 2/3 of coursework completed).");
        }
    }

    //  input coursework scores
    private static void inputCourseworkScores() {
        String[] assessmentNames = {"Assignment 1", "Assignment 2", "Assignment 3", "CAT 1", "CAT 2"};
        for (int i = 0; i < TOTAL_ASSESSMENTS; i++) {
            System.out.print("Enter score for " + assessmentNames[i] + " (0-10): ");
            courseworkScores[i] = scanner.nextDouble();
        }
    }

    //  input exam scores
    private static void inputExamScores() {
        String[] examUnits = {"System Analysis", "Java Programming", "Structured Cabling"};
        for (int i = 0; i < examScores.length; i++) {
            System.out.print("Enter score for " + examUnits[i] + " (0-16.67): ");
            examScores[i] = scanner.nextDouble();
        }
    }

    //  function to use for-loop
    private static int countCompletedAssessments() {
        int count = 0;
        for (double score : courseworkScores) {
            if (score > 0) {
                count++;
            }
        }
        return count;
    }

    // Decision function
    private static boolean isCourseworkSufficient(int completedAssessments) {
        return (double) completedAssessments / TOTAL_ASSESSMENTS >= REQUIRED_COURSEWORK_RATIO;
    }

    // calculate coursework total
    private static double calculateCourseworkTotal() {
        double total = 0;
        for (double score : courseworkScores) {
            total += score;
        }
        return total;
    }

    // calculate exam total
    private static double calculateExamTotal() {
        double total = 0;
        for (double score : examScores) {
            total += score;
        }
        return total;
    }

    //  calculate total score
    private static double calculateTotalScore(double courseworkTotal, double examTotal) {
        return (courseworkTotal * COURSEWORK_WEIGHT) + (examTotal * EXAM_WEIGHT);
    }
}