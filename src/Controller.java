import java.util.Scanner;

public class Controller {
    private TeamsController teamsController; 
    private PlayersController playersController;
    private Edit edit;
    private Add add;
    private Show show;
    private TransferHistory transferHistory;
    
    public Controller(Scanner scanner){
        this.teamsController = new TeamsController(scanner);
        this.playersController = new PlayersController(scanner);
        this.edit = new Edit(scanner, teamsController, playersController);
        this.add = new Add(scanner, teamsController, playersController);
        this.show = new Show(scanner, teamsController, playersController);
        this.transferHistory = new TransferHistory(scanner, teamsController, playersController);
    }

    public void panel(String option){
        switch (option) {
            //Option ADD
            case "1":
                add.addPanel();
                break;
            //Option SHOW
            case "2":
                show.showPanel();
                break;
            //Option EDIT
            case "3":
                edit.editPanel();
                break;
            //Option TRANSFER HISTORY
            case "4":
                transferHistory.transferHistoryPanel();
                break;
            // Option EXIT
            case "5":
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }
    }
}