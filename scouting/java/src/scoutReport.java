import java.util.*;

class Team implements Comparable<Team> {

    int autoRamps = 0;
    int autoKickstands = 0;
    int autoCenterGoals = 0;
    int autoRollingGoals = 0;
    int numOfMatches = 0;
    int teamNumber;
    int teamScore;

    public Team (int number) {
        teamNumber = number;
    }

    public int compareTo (Team otherTeam) {
        if (this.teamScore == otherTeam.teamScore) {
            return 0;
        } else if (this.teamScore > otherTeam.teamScore) {
            return 1;
        } else {
            return -1;
        }
    }

    public void addMatch (int[] matchArray) {

        numOfMatches++;
        int autoRamp = matchArray[0];
        int autoKickstand = matchArray[1];
        int autoCenterGoal = matchArray[2];
        int autoRollingGoal = matchArray[3];

        if (autoRamp == 1) {
            autoRamps++;
        }
        if (autoKickstand == 1) {
            autoKickstands++;
        }
        if (autoCenterGoal == 1) {
            autoCenterGoal++;
        }
        if (autoRollingGoal == 1) {
            autoRollingGoals++;
        }

    }

    public void getStats () {
        System.out.println("==# Team: " + teamNumber + " #==");
        System.out.println("~ Auto Stats ~");
        System.out.println("Drove off ramps: " + autoRamps + "/" + numOfMatches);
        System.out.println("Kickstands: " + autoKickstands + "/" + numOfMatches);
        System.out.println("Center goals: " + autoCenterGoals + "/" + numOfMatches);
        System.out.println("Rolling goals: " + autoRollingGoals + "/" + numOfMatches);
        System.out.println("~ Team Score ~");
        System.out.println(this.getScore());
    }

    public int getScore () {
        int autoScore = ((autoKickstands * 800) + (autoRamps * 200) + (autoCenterGoals * 1000) + (autoRollingGoals * 350)) / (4 * numOfMatches);
        int teleScore = 0;
        int teamScore = autoScore + teleScore;
        return teamScore;
    }

}

public class scoutReport {

    public static void main (String[] args) {

        System.out.println("Program Started");
        String queue = "*";
        String nextState = "INIT";
        ArrayList<Team> list = new ArrayList<Team>();
        // Scanner in = new Scanner(System.in);

        while (nextState != "quit") {
            switch (nextState) {

                case "INIT":
                    System.out.println("* The INIT case ran");
                    /*Add teams to a list*/
                    int[] teamNums = {4150,4324};
                    int[] matchData = {1,0,0,1};
                    // ArrayList<Team> list = new ArrayList<Team>();
                    for (int i = 0; i < teamNums.length; i++) {
                        Team team = new Team(teamNums[i]);
                        team.addMatch(matchData);
                        team.getScore();
                        list.add(team);
                    }
                    queue += "IDLE*";
                    break;

                case "IDLE":
                    System.out.println("* Idle case ran");
                    System.out.println("--> Please enter command:");
                    // String command = in.nextLine(); /*Needs work*/
                    String command = "rankings";
                    if (command == "rankings") {
                        queue += "RANKS*";
                    } else if (command == "exit") {
                        queue += "EXIT*";
                    } else {
                        System.out.println("--> Command not recognized.");
                        System.out.println("--> Command input: " + command);
                        queue += "IDLE*";
                    }
                    break;

                case "RANKS":
                    System.out.println("* The rankings case ran");
                    Collections.sort(list);
                    // int numOfRanks = in.nextLine(); /*Needs work*/
                    int numOfRanks = 2;
                    for (int i = 0; i < numOfRanks; i++) {
                        System.out.println(i+1 + ") " + list.get(i).teamNumber);
                    }
                    queue += "EXIT*";
                    break;

                case "EXIT":
                    System.out.println("--> The program will exit");
                    break;

                default:
                    // System.out.println("* Default case reached; Error occured in the QSM");
                    queue += "EXIT*";
                    break;
            }

            if (queue.indexOf("*") != -1) {
                /*
                Takes the next string from the queue (from the begining of
                the string to the asterisk)
                */
                nextState = queue.substring(0, queue.indexOf("*"));
                /*Removes the next state from the queue*/
                queue = queue.substring((nextState.length() + 1), queue.length());
            } else {
                nextState = "quit";
            }

        }

    }

}
