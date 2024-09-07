
package game_find.the.exit;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Christian Adi Ananta
 */
public class Game_FindTheExit {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int petak, difficulty, numberOfObstacles, lifes;
        String diff;
        String gerakan;
        char pilihan;
        boolean success;
        boolean[][] obstacles;
        Scanner input = new Scanner(System.in);
        Scanner stringInput = new Scanner(System.in);
        do {
            diff = "";
            pilihan = 'y';
            success = true;
            System.out.print("Insert field size (min. 4) : ");
            petak = input.nextInt();
            obstacles = new boolean[petak*2+1][petak*2+1];

            if(petak <4){
                System.out.println("Input not invalid");
                continue;
            }
            int wrongCounter = 0;
            do{
                System.out.println("1. Easy");
                System.out.println("2. Medium");
                System.out.println("3. Hard");
                System.out.println("4. PLEASE DONT SELECT THIS");
                System.out.print("Choose your life destiny : ");
                difficulty = input.nextInt();
                if(difficulty == 1){
                    numberOfObstacles = petak;
                    diff = "Easy";
                    lifes = 3;
                }
                else if(difficulty == 2){
                    numberOfObstacles = petak*petak*1;
                    diff = "Medium";
                    lifes = 2;
                }
                else if(difficulty == 3){
                    numberOfObstacles = petak*petak*2;
                    diff = "Hard";
                    lifes = 2;
                }
                else if(difficulty == 4){
                    numberOfObstacles = petak*petak*3;
                    diff = "yOu CaN't EsCaPe";
                    lifes = 1;
                }else{
                    numberOfObstacles = 0;
                    lifes =3;
                    wrongCounter++;
                    if(wrongCounter>1){
                        System.out.println("LOOKS LIKE YOU'RE NOT LEARN FROM MISTAKES");
                        numberOfObstacles = petak*petak*3;
                        diff = "yOu CaN't EsCaPe";
                        lifes = 1;
                        break;
                    }
                    System.out.println("please choose correct difficulty");
                }
            }while(difficulty > 4 || difficulty<1);
            
            Random random = new Random();
            int[] start = {random.nextInt(petak*2+1)-petak, random.nextInt(petak*2+1)-petak};
            int[] memoStart = {start[0], start[1]};
            int[] end = {random.nextInt(petak*2+1)-petak, random.nextInt(petak*2+1)-petak};
            int[] jarak = {Math.abs(end[0]-start[0]), Math.abs(end[1]-start[1])};
            int[] obstaclePoint;
            for(int index=0; index<numberOfObstacles; ++index){
                do{
                    obstaclePoint = new int[]{random.nextInt(petak*2+1)-petak, random.nextInt(petak*2+1)-petak};
                }while((obstaclePoint[0] == start[0] && obstaclePoint[1] == start[1]) || (obstaclePoint[0] == end[0] && obstaclePoint[1] == end[1]));
                obstacles[obstaclePoint[0]+petak][obstaclePoint[1]+petak] = true;
            }
            
            int counter = 0;
            System.out.println("===================================");
            System.out.println("You are spawned at " + start[0] +", " + start[1] );
            System.out.println("Difficulty : " + diff );
            System.out.println("There are " + numberOfObstacles + " obstacles");
            System.out.println("Find the exit wihout hitting obstacles!");
            
            //CHEAT
//            System.out.println("obstacles : ");
//            for(int rowIndex=0; rowIndex<obstacles.length; ++rowIndex){
//                for(int colIndex=0; colIndex<obstacles[rowIndex].length; ++colIndex){
//                    if(obstacles[rowIndex][colIndex]){
//                        System.out.println("Obstacles : " + (rowIndex-petak) + ", " + (colIndex-petak));
//                    }
//                }
//            }
//            System.out.println("target : " + Arrays.toString(end));
            
            do {         
                System.out.println("-------------------------------");
                System.out.println("Remaining Lifes : " + lifes);
                System.out.println("-------------------------------");
                System.out.println("W. Go Forward");
                System.out.println("A. Go Backward");
                System.out.println("S. Go Left");
                System.out.println("D. Go Right");
                System.out.print("Insert your choice and press Enter : ");
                gerakan = stringInput.nextLine();
                try {
                    if(gerakan.equalsIgnoreCase("w")){
                        start[1]++;
                    }
                    else if(gerakan.equalsIgnoreCase("a")){
                        start[0]--;
                    }
                    else if(gerakan.equalsIgnoreCase("s")){
                        start[1]--;
                    }
                    else if(gerakan.equalsIgnoreCase("d")){
                        start[0]++;
                    }
                    else if(gerakan.equalsIgnoreCase("allyourbasearebelongtous")){
                        System.out.println("You Win!!!");
                        System.out.println("But... are you really feel win?");
                        success = false;
                        break;
                    }
                    else{
                        System.out.println("Wrong input, try again");
                        continue;
                    }
                    counter++;
                    if(start[0]>petak || start[0]<petak*-1 || start[1]>petak || start[1]<petak*-1){
                        System.out.println("You are lost:(");
                        success = false;
                        break;
                    }else if(obstacles[start[0]+petak][start[1]+petak]){
                        System.out.println("You're fell in the Darkness");
                        lifes--;
                        if(lifes<=0){
                            System.out.println("You cannot resist against Darkness...");
                            success = false;
                            break;
                        }
                    }
                    System.out.println("");
                    System.out.println("The Unknown Says : ");
                    if(Math.abs(end[1]-start[1]) > jarak[1] || Math.abs(end[0]-start[0]) > jarak[0]){
                        System.out.println("You're getting further away");
                    }else{
                        System.out.println("You're getting closer");
                    }

                    jarak[0] = Math.abs(end[0]-start[0]);
                    jarak[1] = Math.abs(end[1]-start[1]);
                    System.out.println("");
                    System.out.println("-------------------------------");
                    System.out.println("You are now at " + start[0] + ", " + start[1]);
                    
                } catch (Exception e) {
                    System.out.println("You're out from game:(");
                }
                
            } while (start[0] != end[0] || start[1] != end[1]);
            if(success){
                System.out.println("====================================");
                System.out.println("Conratulations, You finally made it!");
                System.out.println("You walk " + counter + " times");
                System.out.println("Your Spawn Point: " + memoStart[0] + ", " + memoStart[1]);
                System.out.println("Your Escape Point : " + end[0] + ", " + end[1]);
                System.out.println("====================================");
            }
            System.out.print("Wanna Play Again ?(Y/N)  : ");
            pilihan = input.next().charAt(0);
            
        } while (pilihan=='y');
        System.out.println("Thank you for playing ^_^");
        input.close();
        stringInput.close();
        
    }
    
}
