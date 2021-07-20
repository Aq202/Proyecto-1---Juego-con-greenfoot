import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    private int livesCount = 3;
    
    
    public void act()
    {
        // Add your action code here.
    }
    
    
    public boolean addLive(){
        if(livesCount < 3){
            livesCount++;
            return true;
        }
        return false;
    }
    
    public boolean kill(){
        if(livesCount > 0){
            livesCount--;
            return true;
        }
        return false;
    }
    
    public int getLives(){
        return livesCount;
    }
}
