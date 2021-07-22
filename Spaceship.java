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
    
    //shield
    private boolean hasShield = false;
    private final int defaultShieldTimer = 230;
    private int shieldTimer = defaultShieldTimer;
    //images
    private String spaceshipImage, astronautImage;
    //control buttons
    private String buttonToShoot, buttonToMoveLeft, buttonToMoveRight;
    //shield animation timer
    private final int defaultAnimationTimer = 25;
    private int animationTimer = defaultAnimationTimer + 10;
    private boolean reduceImage = true;
    
    
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
        
        if(!isDestroyed && !isDead  && !hasShield){
        
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
        
        if(!isDestroyed && !isDead && !hasShield){ //rewrite
            isDestroyed = true;
            setImage(astronautImage);
            turnOnShield(); //activar escudo
        }else if(!isDead && !hasShield){
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
    
    public void turnOnShield(){
        hasShield = true;
        shieldTimer = defaultShieldTimer;
    }
    
    public void activateShield(){
        
        if(hasShield && shieldTimer <= 0){ //desactivar escudo luego de x seg
            
            //reestablecer imagen
            shieldAnimation();        
            hasShield = false;

            
        }else if(hasShield){
            shieldAnimation();
            shieldTimer--;
        }
    }
    
    public void backToSpaceship(){
        
        if(isDestroyed && !isDead){
            if(backToSpaceshipTimer == 0){
                

                setImage(spaceshipImage);                
                turnOnShield(); //activar escudo
                backToSpaceshipTimer = defaultBackToSpaceshipTimer;
                isDestroyed = false;
                
            }
            backToSpaceshipTimer--;
        }
    }
    
    public void shieldAnimation(){
        
        final int blinkDuration = 25;
        final int numberOfBlinks = 6;
        
        //seleccionar imagen
        String image = null;
        if(isDestroyed){
            image = astronautImage;
        }else{
            image = spaceshipImage;
        }
        
        if(defaultShieldTimer == shieldTimer){
            //colocar escudo
            setImage("shield_"+image);
            
        }
        
        if(defaultShieldTimer - (defaultShieldTimer - (blinkDuration * numberOfBlinks)) >= shieldTimer){
            
            if((shieldTimer % blinkDuration) == 0){
                
                if(((shieldTimer / blinkDuration) % 2) == 0){
                    //par, ocultar escudo y permitir disparo
                    setImage(image);
 
                }else{
                    //impar, mostrar escudo
                    setImage("shield_"+image);
 
                }
            }
        }
        
        if(shieldTimer <= 0){
            //reestablecer imagen
            setImage(image);

        }
        
    }
    
    public boolean isDead(){
        return isDead;
    }
    
    public void act(){
        moveShip();
        changeDirection();
        shoot();
        checkIfShotYou();
        backToSpaceship();
        activateShield();

    }
}
