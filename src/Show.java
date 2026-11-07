import java.util.Scanner;

public class Show {
    private Scanner scanner;
    private TeamsController teamsController; 
    private PlayersController playersController;

    public Show(Scanner scanner, TeamsController t, PlayersController p){
        this.scanner = scanner;
        this.teamsController = t;
        this.playersController = p;
    }

    public void showPanel(){
        String option = "0";

        while(true){
            System.out.println("\nChoose an option:\n 1. Show Team\n 2. Show Player\n 3. Back");
            option = scanner.nextLine();

            switch (option) {
                        //Option SHOW TEAM
                        case "1":
                            teamsController.showTeam();
                            break;
                            //Option SHOW PLAYER
                        case "2":
                            playersController.showPlayer();
                            break;
                        //Option BACK
                        case "3":
                            return;
                        default:
                            System.out.println("Invalid option. Please try again.");
                            break;
            }
        }
    }
}
