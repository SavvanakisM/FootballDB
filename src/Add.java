import java.util.Scanner;

public class Add {
    private Scanner scanner;
    private TeamsController teamsController; 
    private PlayersController playersController;

    public Add(Scanner scanner, TeamsController t, PlayersController p){
        this.scanner = scanner;
        this.teamsController = t;
        this.playersController = p;
    }

    public void addPanel(){
        String option = "0";

        while(true){
            System.out.println("\nChoose an option:\n 1. Add Team\n 2. Add Player\n 3. Back");
            option = scanner.nextLine();

            switch (option) {
                //Option ADD TEAM
                case "1":
                    teamsController.addTeam();
                    break;
                //Option ADD PLAYER
                case "2":
                    playersController.addPlayer(teamsController.getTeamsIndex());
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