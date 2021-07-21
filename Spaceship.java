import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Spaceship here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Spaceship extends Actor
{
    private int degree = 0;
    private boolean shotBlocked = false;
    private boolean isDestroyed = false;
    private boolean isDead = false;
    
    
    //control buttons
    private String buttonToShoot, buttonToMoveLeft, buttonToMoveRight;
    
    
    public Spaceship(String buttonToShoot, String buttonToMoveLeft, String buttonToMoveRight, String spaceshipImage, String astronautImage){
        this.buttonToShoot = buttonToShoot;
        this.buttonToMoveLeft = buttonToMoveLeft;
        this.buttonToMoveRight = buttonToMoveRight;
        
        
    }
    
        public void moveShip(){
        if(!isDestroyed && !isDead){
          move(4);  
        }
    
    }
    
    public void updateDegree(int degree){
        this.degree += degree;
    }
    
    public void changeDirection(){
        
        if(!isDead){
            if(Greenfoot.isKeyDown(buttonToMoveLeft)){
                turn(-3);
                updateDegree(-3);
            }else if(Greenfoot.isKeyDown(buttonToMoveRight)){
                turn(3);
                updateDegree(3);
            }
        }
        
        
        
    }
    
    
        public void act()
        {
            moveShip();
            changeDirection();

        }
}
