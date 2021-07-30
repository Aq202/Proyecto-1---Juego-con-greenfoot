import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class ExtraLife here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ExtraLife extends Actor
{
    /**
     * Act - do whatever the ExtraLife wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        checkIfShot();
    }
    
    private void checkIfShot(){
        
        List munitions = getNeighbours(15, true, Munition.class);
        if(munitions.size() > 0){
            int numOfMunitions = 0;
            
            for(int i = 0; i < munitions.size(); i++){
                
                Munition mun = (Munition)munitions.get(i);
                    mun.remove();
                    numOfMunitions++;                  
            }
            
            getWorld().removeObject(this);
            
        }
            
        
    }
}
