import java.util.Scanner;

public class TransferHistory {
    private Scanner scanner;
    private TeamsController teamsController; 
    private PlayersController playersController;

    public TransferHistory(Scanner scanner, TeamsController t, PlayersController p){
        this.scanner = scanner;
        this.teamsController = t;
        this.playersController = p;
    }

    public void transferHistoryPanel(){
        int ind = -1;
        ind = AppUtils.idChecker("\nPlease enter player's surname to get his transfer history: ", "Player not found. Please enter a valid surname:", scanner, playersController.getPlayersIndex());
        if (ind == -1) {
            return;
        }

        String option="0";

        while(true){
            System.out.println("\nChoose an option:\n 1. Show Transfer History\n 2. Add Transfer\n 3. Back");
            option = scanner.nextLine();
            switch (option){
                //Option SHOW TRANSFER HISTORY
                case "1":
                    playersController.showTransferHistory(ind);
                    break;
                //Option ADD TRANSFER
                case "2":
                    playersController.addTransfer(teamsController.getTeamsIndex(), ind, teamsController.getTeams());
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