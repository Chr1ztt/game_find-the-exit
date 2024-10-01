
package games;

import enums.Difficulty;
import asset.Player;
import asset.Point;
import interfaces.GameInterface;
import java.util.Random;
import java.util.Scanner;

public class FindTheExitGame implements GameInterface{
    private final Scanner numberScanner = new Scanner(System.in);
    private final Scanner StringScanner = new Scanner(System.in);
    private final Random random = new Random();
    private int fieldSize;
    private Difficulty difficulty;
    private boolean[][] obstacles;
    private Point startPoint;
    private Point endPoint;
    private Player player;
    private int currentDistance;

    public FindTheExitGame() {
        
    }
    
//    public void init(){
//        Scanner scanner = new Scanner(System.in);
//
//        // Ask for the field size
//        System.out.print("Enter field size: ");
//        int fieldSize = scanner.nextInt();
//        
//        // Ask for difficulty level
//        System.out.println("Select difficulty level:");
//        for (Difficulty diff : Difficulty.values()) {
//            System.out.println(diff.ordinal() + ": " + diff.name());
//        }
//        int difficultyChoice = scanner.nextInt();
//        Difficulty difficulty = Difficulty.values()[difficultyChoice];
//
//        // Initialize the obstacles array
//        boolean[][] obstacles = new boolean[fieldSize * 2 + 1][fieldSize * 2 + 1];
//
//        // Ask for player's name and create a new player
//        System.out.print("Enter player name: ");
//        scanner.nextLine(); // consume leftover newline
//        String playerName = scanner.nextLine();
//        
//        Point startPoint = new Point(0, 0); // Default starting point
//        Player player = new Player(startPoint, difficulty.getLifes());
//    }
    
    
    private Point generateRandomPoint(){
        return new Point(random.nextInt(fieldSize*2+1)-fieldSize, random.nextInt(fieldSize*2+1)-fieldSize);
    }
    private void placeObstacles(){
        int numberOfObstacles = fieldSize * fieldSize * difficulty.getObstacleMultiplier();
        Point obstacle;
        for(int index=0; index<numberOfObstacles; ++index){
                do{
                    obstacle = new Point (random.nextInt(fieldSize*2+1)-fieldSize, random.nextInt(fieldSize*2+1)-fieldSize);
                }while((obstacle.getX() == startPoint.getX() && obstacle.getY()== startPoint.getY()) || (obstacle.getX() == endPoint.getX() && obstacle.getY()== endPoint.getY()));
                obstacles[obstacle.getX()+fieldSize][obstacle.getY()+fieldSize] = true; 
           }
    }
    public boolean isObstacle(Point position){
        return obstacles[position.getX() + fieldSize][position.getY() + fieldSize];
    }
    
    public void giveFeedback(){
        int distance = player.getPosition().distanceTo(endPoint);
        System.out.println(distance == 0 ? "Congrats!! You're finally find the exit at" + endPoint : distance>currentDistance ? "You're getting further away" : "You're getting closer ");
    }
    
    public boolean isEqualEndPoint(Point position){
        return player.getPosition().distanceTo(endPoint) == 0;
    }
    
    public boolean isPlayerOutOfField(){
        return player.getPosition().getX() < fieldSize*-1 || 
                player.getPosition().getX() > fieldSize ||
                player.getPosition().getY() < fieldSize*-1 || 
                player.getPosition().getY() > fieldSize;
    }
    
    public void init(){
        int fieldSize, difficultyChoice;
        System.out.print("insert fieldSize : ");
        do{
            try {
                fieldSize = numberScanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("invalid input, try again");
            }
        }while(true);
        do{
            try {
                for(Difficulty diff : Difficulty.values()){
                    System.out.println(diff.ordinal() + ": " + diff.name());
                }                    
                System.out.print("select difficulty level : ");
                difficultyChoice = numberScanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("invalid input, try again");
            }
        }while(true);
        Difficulty selectedDiff = Difficulty.values()[difficultyChoice];
        
        this.fieldSize = fieldSize;
        this.difficulty = selectedDiff;
        this.obstacles = new boolean[fieldSize*2+1][fieldSize*2+1];
        this.startPoint = generateRandomPoint();
        this.endPoint = generateRandomPoint();
        this.player = new Player(startPoint, difficulty.getLifes());
        this.currentDistance = startPoint.distanceTo(endPoint);
        placeObstacles();
    }

    @Override
    public void play() {
        System.out.println("===================================");
        System.out.println("You are spawned at " + player.getPosition());
        System.out.println("Difficulty: " + difficulty.name());
        System.out.println("Find the exit without hitting obstacles!");
        
        do{
            System.out.println("-------------------------------");
            System.out.println("Remaining Lives: " + player.getLifes());
            System.out.println("You are now at " + player.getPosition());
            System.out.print("Insert your move (WASD): ");
            String moveInput = StringScanner.nextLine().toUpperCase();
            
            switch(moveInput){
                case "W" -> player.moveUp();
                case "A" -> player.moveLeft();
                case "S" -> player.moveDown();
                case "D" -> player.moveRight();
                default -> { 
                    System.out.println("Invalid move. Try again.");
                    continue;
                }
            }
            
            if(isPlayerOutOfField()){
                System.out.println("You are lost");
                break;
            }else if(isObstacle(player.getPosition())){
                System.out.println("You're fell in the darkness");
                player.decreaseLife();
                if(!player.isAlive()){
                    System.out.println("You cannot resist against darkness");   
                }
            }else{
                giveFeedback();
                currentDistance = calculatePlayerDistanceToEndPoint();
            }
            
        }while(player.isAlive() && !player.getPosition().equals(endPoint));
//        if(player.isAlive()){
//            System.out.println("===================================");
//            System.out.println("Congratulations! You found the exit at " + endPoint);
//        }
    }
    
    private int calculatePlayerDistanceToEndPoint(){
        return player.getPosition().distanceTo(endPoint);
    }
    
    
}
