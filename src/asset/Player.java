package asset;

public class Player {
//    private String name;
    private Point position;
    private int lifes;

    public Player(Point position, int lifes) {
        this.position = position;
        this.lifes = lifes;
    }
    
//    public void move(String direction){
//        switch(direction.toLowerCase()){
//            case "w": position.setY(position.getY()+1); break;
//            case "a": position.setY(position.getY()-1); break;
//            case "s": position.setX(position.getX()+1); break;
//            case "d": position.setX(position.getX()-1); break;
//            default: System.out.println("Wrong input, try again");
//        }
//    }
    public void moveLeft(){
        this.position.setX(this.position.getX()-1);
    }
    public void moveRight(){
        this.position.setX(this.position.getX()+1);
    }
    public void moveUp(){
        this.position.setY(this.position.getY()+1);
    }
    public void moveDown(){
        this.position.setY(this.position.getY()-1);
    }
    public void decreaseLife(){
        this.lifes--;
    }

    public Point getPosition() {
        return position;
    }

    public int getLifes() {
        return lifes;
    }
   
    public boolean isAlive(){
        return lifes>0;
    }
    
    
    
}
