import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Rematch here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Rematch extends Actor
{
    
    private boolean rematchGame = true;
    
    public Rematch(){
        changeImage();
    }    
    
    public void act()
    {
        changeSelection();        
    }
    
    private void changeSelection(){
        if(Greenfoot.isKeyDown("left") && !rematchGame){            
            rematchGame = true;
            changeImage();
            
        }else if(Greenfoot.isKeyDown("right") && rematchGame){
            rematchGame = false;
            changeImage();
        }
    }
    
    private void changeImage(){
        if(rematchGame){
            setImage("rematchYes.png");
        }else{
            setImage("rematchNo.png");
        }
    }
    
    public boolean rematchGame(){
        return rematchGame;
    }
}
