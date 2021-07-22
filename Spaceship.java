import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

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
    
    private int defaultBackToSpaceshipTimer = 600;
    private int backToSpaceshipTimer = defaultBackToSpaceshipTimer;
    
    //images
    private String spaceshipImage, astronautImage;
    //control buttons
    private String buttonToShoot, buttonToMoveLeft, buttonToMoveRight;
    
    
    public Spaceship(String buttonToShoot, String buttonToMoveLeft, String buttonToMoveRight, String spaceshipImage, String astronautImage){
        this.buttonToShoot = buttonToShoot;
        this.buttonToMoveLeft = buttonToMoveLeft;
        this.buttonToMoveRight = buttonToMoveRight;
        this.spaceshipImage = spaceshipImage;
        this.astronautImage = astronautImage;
        
    }
    
        public void moveShip(){
        if(!isDestroyed && !isDead){
          move(4);  
        }
    
    }
    
    public void updateDegree(int degree){
        this.degree += degree;
    }
    
    public void shoot(){
        
        if(!isDestroyed && !isDead ){
        
            if(Greenfoot.isKeyDown(buttonToShoot) && !shotBlocked){
                
                int imageSize = getImage().getWidth();                
                Munition mun = new Munition(getX(), getY(), degree, imageSize);
                getWorld().addObject(mun,mun.getInitialX(), mun.getInitialY());
                shotBlocked = true;
            }else if(!Greenfoot.isKeyDown(buttonToShoot) && shotBlocked){
                shotBlocked = false;
            }
            
        }
        
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
        
        if(isDestroyed){
            astronautControls();  
        }
        
        
    }
    
    public boolean checkIfShotYou(){
        if(!isDead){
            List obstacles = getNeighbours(45, true, Munition.class);
            if(obstacles.size() > 0){
                
                obstacles.forEach((obstacle) ->{
                    
                    getWorld().removeObject((Actor) obstacle);
                });
                destroySpaceship();
                return true;
                
            }
            
        }
        return false;
    }
    
    public void destroySpaceship(){
        
        if(!isDestroyed && !isDead ){
            isDestroyed = true;
            setImage(astronautImage);
     
        }else if(!isDead){
            isDead = true;
            //game over
        }
        
    }
    
    public void astronautControls(){
        if(isDestroyed ){
            
            //mover en circulos al astronauta, incluso si esta muerto
            if((!Greenfoot.isKeyDown(buttonToMoveLeft) && !Greenfoot.isKeyDown(buttonToMoveRight)) || isDead){
                turn(1);
                updateDegree(1); 
                
            }
            if(Greenfoot.isKeyDown(buttonToShoot) && !isDead){ //avanzar
                move(6);
            }
        }
    }
    
    public void backToSpaceship(){
        
        if(isDestroyed && !isDead){
            if(backToSpaceshipTimer == 0){
                

                setImage(spaceshipImage);                
 
                backToSpaceshipTimer = defaultBackToSpaceshipTimer;
                isDestroyed = false;
                
            }
            backToSpaceshipTimer--;
        }
    }
    
    public void act(){
        moveShip();
        changeDirection();
        shoot();
        checkIfShotYou();
        backToSpaceship();

    }
}
