import java.util.Scanner;

public class Edit {
    private Scanner scanner;
    private TeamsController teamsController; 
    private PlayersController playersController;

    public Edit(Scanner scanner, TeamsController teamsController, PlayersController playersController){
        this.scanner = scanner;
        this.teamsController = teamsController;
        this.playersController = playersController;
    }

    // Displays and manages the main Edit sub-menu
    public void editPanel(){
        while(true){
            System.out.println("\nChoose an option:\n 1. Edit a Team\n 2. Edit a Player\n 3. Back");
            String option = scanner.nextLine();

            if(option.equals("1")){
                editTeam();
            }
            else if(option.equals("2")){
                editPlayer();
            }
            else if(option.equals("3")){
                return;
            }
            else{
                System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Prompts for a team name and manages its attribute modifications
    public void editTeam(){
        int ind = AppUtils.idChecker("\nPlease enter team's name to edit the informations: ", "Team not found. Please enter a valid name:", scanner, teamsController.getTeamsIndex());
        
        while(ind!=-1){
            System.out.println("\nChoose an option:\n 1. Edit city's name\n 2. Edit the amount of titles\n 3. Edit the founding year\n 4. Back");
            String option = scanner.nextLine();

            if(option.equals("1")){
                //Περνάω το index της συγκεκριμένης ομάδας και την νέα πόλη και το set γίνεται στην TeamsController
                teamsController.citySetter(editString(ind), ind);
            }
            else if(option.equals("2")){
                //Περνάω το index της συγκεκριμένης ομάδας και τους νέους τίτλους και το set γίνεται στην TeamsController
                teamsController.titlesSetter(editInteger(ind), ind);
            }
            else if(option.equals("3")){
                //Περνάω το index της συγκεκριμένης ομάδας και την νέα χρονιά ίδρυσης και το set γίνεται στην TeamsController
                teamsController.yearFoundedSetter(editInteger(ind), ind);
            }
            else if(option.equals("4")){
                return;
            }
            else{
                System.out.println("Invalid option. Please try again.");
            }    
        }
    }

    // Prompts for a player surname and manages their attribute modifications
    public void editPlayer(){
        int ind = AppUtils.idChecker("\nPlease enter player's surname to edit his informations: ", "Player not found. Please enter a valid surname:", scanner, playersController.getPlayersIndex());
        
        while(ind!=-1) {
            System.out.println("\nChoose an option:\n 1. Edit his age\n 2. Back");
            String option = scanner.nextLine();

            if(option.equals("1")){
                //Περνάω το index του συγκεκριμένου παίκτη και την νέα ηλικία και το set γίνεται στην PlayersController
                playersController.ageSetter(editInteger(ind), ind);
            }
            else if(option.equals("2")){
                return;
            }
            else{
                System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Prompts the user and returns an integer value from the console
    public int editInteger(int index){
        System.out.println("\nPlease enter the new data:");
        return Integer.parseInt(scanner.nextLine());
    }

    // Prompts the user and returns a text string from the console
    public String editString(int index){
        System.out.println("\nPlease enter the new data:");
        return scanner.nextLine();
    }
}