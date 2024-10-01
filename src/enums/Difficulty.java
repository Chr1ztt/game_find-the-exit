
package enums;

public enum Difficulty {
    EASY(3, 1, "Easy"),
    MEDIUM(2, 1, "Medium"),
    HARD(2, 2, "Hard"),
    IMPOSSIBLE(1, 3, "yOu CaN't EsCaPe");
    
    private final int lifes;
    private final String difficultyName;
    private final int obstacleMultiplier;
    
    Difficulty(int lifes, int obstacleMultiplier, String difficultyName){
        this.lifes = lifes;
        this.obstacleMultiplier = obstacleMultiplier;
        this.difficultyName = difficultyName;
    }
    
    public int getLifes(){
        return this.lifes;
    }
    public int getObstacleMultiplier(){
        return this.obstacleMultiplier;
    }
    
    
    
    
    
}
