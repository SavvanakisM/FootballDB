import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class PlayersController {
    private Scanner scanner;
    private HashMap<Integer, Player> players = new HashMap<>();
    private HashMap<String, Integer> playersIndex = new HashMap<>();
    private HashMap<Integer, List<String>> transfers = new HashMap<>();
    private int idp=0;

    public PlayersController(Scanner scanner){
        this.scanner = scanner;
        loadPlayers();
    }

    //Add Player Method
    public void addPlayer(HashMap<String, Integer> teamsIndex){
        System.out.println("\nPlease enter player's info:\nName: ");
        String s1=scanner.nextLine();
        System.out.println("Surname: ");    
        String s2=scanner.nextLine();
        System.out.println("Age: ");
        int n1=scanner.nextInt();
        scanner.nextLine();
        System.out.println("Team: ");
        String s3;
        while(true){
            s3=scanner.nextLine();
            if(teamsIndex.containsKey(s3)){
                break;
            }
            System.out.println("Team not found. Please enter a valid team name:");
        }

        ++idp;
        players.put(idp, new Player(s1,s2,n1,s3));
        playersIndex.put(s2, idp);

        transfers.put(idp, new ArrayList<>());
        transfers.get(idp).add(s3);
    }

    //Show Player Method
    public void showPlayer(){
        
        int playerId = -1;
        playerId = AppUtils.idChecker("Please enter the surname of the player you want to show: ", "Player not found, please try again: ", scanner, playersIndex);
        if(playerId!=-1){
            Player p = players.get(playerId);
            System.out.println("\nPlayer Info:\nName: "+p.name+"\nSurname: "+p.sur+"\nAge: "+p.age+"\nTeam: "+p.team);
        }
    }

    //-----------------------------------------------------------------------------------------------------------------------------
    //Get Players Index Method
    public HashMap<String, Integer> getPlayersIndex(){
        return playersIndex;
    }

    //Get Players Method
    public HashMap<Integer, Player> getPlayers() {
        return players;
    }
    //-----------------------------------------------------------------------------------------------------------------------------

    //Show Transfer History Method
    public void showTransferHistory(int playerId){
        List<String> transferHistory = transfers.get(playerId);
        System.out.println("\nTransfer History for " + players.get(playerId).getSur() + ":");
        for (String team : transferHistory) {
            System.out.println("-> " + team);
        }
    }

    //Add a Transfer Method
    public void addTransfer(HashMap<String, Integer> teamsIndex, int playerId, HashMap<Integer, Team> teams){
        int teamId = -1;
        teamId = AppUtils.idChecker("Please enter the name of the team to which the player is transferring: ", "Team not found, please try again: ", scanner, teamsIndex);

        if(teamId!=-1){
            String newTeam = teams.get(teamId).getName();
            transfers.get(playerId).add(newTeam);
            players.get(playerId).setTeam(newTeam);
        }
    }   

    public void ageSetter(int num, int ind){
        players.get(ind).setAge(num);
    }

    public void savePlayers(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("players.dat"))){
            oos.writeObject(players);
            oos.writeObject(playersIndex);
            oos.writeObject(transfers);
        } catch(IOException e){
            System.out.println("Error saving players:" + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void loadPlayers(){
        File file = new File("players.dat");
        if(!file.exists()){
            return;
        }

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
            players = (HashMap<Integer, Player>) ois.readObject();
            playersIndex = (HashMap<String, Integer>) ois.readObject();
            transfers = (HashMap<Integer, List<String>>) ois.readObject();
            idp = players.size();
        } catch (Exception e) {
            System.out.println("Error loading players:" + e.getMessage());
        }
    }
}