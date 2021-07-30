import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
import java.util.List;

/**
 * Write a description of class Asteroid here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Asteroid extends Actor
{
    private int degrees = 1;
    private int numOfMunitions = 0;
    
    public Asteroid(){
        Random r = new Random();
        if(r.nextInt(2) == 0){
            degrees *= -1;
        }
    }
    
    public void act()
    {
        turn(degrees);
        checkIfShot();
    }
    
    private void checkIfShot(){
        
        List munitions = getNeighbours(45, true, Munition.class);
        if(munitions.size() > 0){
            int numOfMunitions = 0;
            
            for(int i = 0; i < munitions.size(); i++){
                
                Munition mun = (Munition)munitions.get(i);
                
                    mun.remove();
                    numOfMunitions++;                  
            }
            
            if(numOfMunitions > 0){
                destroyAsteroid();
            }
            
        }
            
        
    }
    
    private void destroyAsteroid(){
        
        
        Random r = new Random();
        
        //20% de encontrar una vida extra
        if(r.nextInt(5) == 0){
            getWorld().addObject(new ExtraLife(), getX(), getY());
        }
        
        getWorld().removeObject(this);
        
    }
}
