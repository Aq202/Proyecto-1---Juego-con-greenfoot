import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Munition here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Munition extends Actor
{
    private int xPos, yPos, degree, shipImageLarge;
    private final int munitionImageLarge = 50;
    
    
    public Munition(int xPos, int yPos,int degree, int shipImageLarge){
        this.xPos = xPos;
        this.yPos = yPos;
        this.degree = degree;
        this.shipImageLarge = shipImageLarge;
        
        turn(degree);
    }
    
    
    public void act()
    {
        move(10);
        deleteWhenExitsScreen();
    }
    
    public int getInitialX(){
        double distance = (shipImageLarge /2) + (munitionImageLarge / 2);
        double width = Math.cos(Math.toRadians(degree));
        int xFinalPos = xPos + (int)Math.round(distance * width);
        
        return xFinalPos;
    }
    
    public int getInitialY(){
        double distance = (shipImageLarge /2) + (munitionImageLarge / 2);
        double height = Math.sin(Math.toRadians(degree));
        int yFinalPos = yPos + (int)Math.round(distance * height);
        
        return yFinalPos;
    }
    
    private void deleteWhenExitsScreen(){
        if(isAtEdge()){ //si toca el borde del mundo elimianrlo
            
            getWorld().removeObject(this);
        }
    }
}
