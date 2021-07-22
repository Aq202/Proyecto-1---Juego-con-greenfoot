import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    private Player1 player1;
    private Player2 player2;
    private boolean initialize = false;
    
    public MyWorld()
    {    
        
        super(800, 600, 1); 
    }
    
    public void act(){
        
        if(!initialize){
            initialize = true;
            startGame(); 
        }
        
    }
    
    public void startGame(){
        
        
        //create players
        player1 = new Player1();
        player2 = new Player2();
        
        addObject(player1, 0,0);
        addObject(player2, 0, 0);
        
        
    }
}
