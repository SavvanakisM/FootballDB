import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Scanner;

public class TeamsController {
    private Scanner scanner;
    private HashMap<Integer, Team> teams = new HashMap<>();
    private HashMap<String, Integer> teamsIndex = new HashMap<>();
    private int idt = 0;
    
    public TeamsController(Scanner scanner){
        this.scanner = scanner;
        loadTeams();
    }

    //Add Team Method
    public void addTeam() {
        System.out.println("\nPlease enter team's info:\nName: ");
        String s1=scanner.nextLine();
        System.out.println("City: ");
        String s2=scanner.nextLine();
        System.out.println("Titles: ");
        int n1=scanner.nextInt();
        System.out.println("Year Founded: ");
        int n2=scanner.nextInt();
        scanner.nextLine();

        ++idt;
        teams.put(idt, new Team(s1,s2,n1,n2));
        teamsIndex.put(s1, idt);
    }

    //Show Team Method
    public void showTeam() {
        int teamId = -1;
        teamId = AppUtils.idChecker("Please enter the name of the team you want to show: ", "Team not found, please try again: ", scanner, teamsIndex);
        if(teamId!=-1){
            Team team = teams.get(teamId);
            System.out.println("\nTeam Info:\nName: " + team.getName() + "\nCity: " + team.city + "\nTitles: " + team.titles + "\nYear Founded: " + team.yearFounded);
        }
    }

    //Get Teams Method
    public HashMap<Integer, Team> getTeams() {
        return teams;
    }

    //Get Teams Index Method
    public HashMap<String, Integer> getTeamsIndex(){
        return teamsIndex;
    }

    public void citySetter(String name, int ind){
        teams.get(ind).setCity(name);
    }

    public void titlesSetter(int num, int ind){
        teams.get(ind).setTitles(num);
    }

    public void yearFoundedSetter(int num, int ind){
        teams.get(ind).setYearFounded(num);
    }

    public void saveTeams(){
        //Δημιουργία Ροών (Streams)
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("teams.dat"))){
            //Εγγραφή των δεδομένων
            oos.writeObject(teams);
            oos.writeObject(teamsIndex);
        } catch(IOException e){
            //Διαχείριση σφάλματος
            System.out.println("Error saving teams:" + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void loadTeams(){
        File file = new File("teams.dat");
        if(!file.exists()){
            return;
        }

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
            teams = (HashMap<Integer, Team>) ois.readObject();
            teamsIndex = (HashMap<String, Integer>) ois.readObject();
            idt = teams.size();
        } catch(Exception e){
            System.out.println("Error loading teams:" + e.getMessage());
        }
    }
}