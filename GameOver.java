import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameOver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOver extends Actor
{
    
    public GameOver(){
        GreenfootSound music = new GreenfootSound("gameOver.mp3");
        music.play();
    }
    
}
