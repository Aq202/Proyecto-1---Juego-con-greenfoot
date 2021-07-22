import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player1 extends Player
{
    private LiveItem[] liveItems = new LiveItem[3];
    private boolean initialized = false;
    private RedSpaceship nave;
    
    public Player1(){

    }
    
    public void act(){
        
        initialize();
        verifyIfIsDead();  
        
    }
    
    
    private void initialize(){
        if(!initialized){
            initialized = true;
            addLifeItems();
            
            createNewSpaceship();
        }
    }
    
    
    public void addLifeItems(){

        liveItems[0] = new LiveItem();
        liveItems[1] = new LiveItem();
        liveItems[2] = new LiveItem();
        
        final int imageWidth = liveItems[0].getImage().getWidth();
        final int initialXPos = 50;
        final int initialYPos = 50;
        final int gap = 15;
        
        getWorld().addObject(liveItems[0], initialXPos, initialYPos);
        getWorld().addObject(liveItems[1], initialXPos + imageWidth + gap, initialYPos);
        getWorld().addObject(liveItems[2], initialXPos + 2 * imageWidth + 2 * gap, initialYPos);
        
    }
    
    private void createNewSpaceship(){
        if(getLives() > 0){
            nave = new RedSpaceship();
            
            int startYPos = (getWorld().getHeight() / 6) * 2;
            int startXPos = nave.getImage().getWidth();
            
            nave.turnOnShield();
            
            getWorld().addObject(nave,  startXPos, startYPos);
        }
    }
    
    private void removeHeart(){
        
        int index = getLives();
        liveItems[index].getImage().setTransparency(120);
    }
    
    private void verifyIfIsDead(){
        if(nave != null){
            if(nave.isDead()){
                System.out.println("MURIO VIDA -1");
                if(kill()){ //si se restó una vida
                    removeHeart();
                }
                
                nave = null;
            }
                
        }else{
            createNewSpaceship();
        }
    }
    
}
