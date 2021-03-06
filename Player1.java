import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

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
    private BlueSpaceship nave;
    
    private final int defaultSpaceshipCreationTimer = 50;
    private  int spaceshipCreationTimer = -1;
    
    private MunitionIcon[] munitionItems = new MunitionIcon[3];
    private int currentShotsAvailable = 3;
    
    public void act(){
        
        initialize();
        verifyIfIsDead();  
        updateShotsIcons();
        collectExtraLife();
        
    }
    
    
    private void initialize(){
        if(!initialized){
            initialized = true;
            addLifeItems();
            createNewSpaceship();
            addShotsIcons();
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
            nave = new BlueSpaceship();
            
            int startYPos = (getWorld().getHeight() / 6) * 2;
            int startXPos = nave.getImage().getWidth();
            
            nave.turnOnShield();
            
            getWorld().addObject(nave,  startXPos, startYPos);
        }else{
            gameOverMessage(2);
        }
    }
    
    private void removeHeart(){
        
        int index = getLives();
        liveItems[index].getImage().setTransparency(120);
    }
    
    private void addHeart(){
        if(addLive()){
            int index = getLives() - 1;
            liveItems[index].getImage().setTransparency(255);
        }
    }
    
    private void verifyIfIsDead(){
        if(nave != null){
            if(nave.isDead()){

                if(kill()){ //si se rest?? una vida
                    removeHeart();
                    //habilitar retraso de regeneraci??n
                    spaceshipCreationTimer = defaultSpaceshipCreationTimer;
                }
                
                nave = null;
            }
                
        }else{
            if(spaceshipCreationTimer >= 0){
                if(spaceshipCreationTimer == 0){
                    createNewSpaceship();
                }
                spaceshipCreationTimer--;
            }
            
        }
    }
    
    private void addShotsIcons(){
        
        munitionItems[0] = new MunitionIcon();
        munitionItems[1] = new MunitionIcon();
        munitionItems[2] = new MunitionIcon();
        
        int imageWidth = liveItems[0].getImage().getWidth() / 2;
        int initialYPos = liveItems[0].getY();
        int initialXPos = liveItems[2].getX() + imageWidth + 15;
        int gap = munitionItems[0].getImage().getHeight();
        
        getWorld().addObject(munitionItems[0], initialXPos, initialYPos - gap);
        getWorld().addObject(munitionItems[1], initialXPos, initialYPos);
        getWorld().addObject(munitionItems[2], initialXPos, initialYPos + gap);
 
    }
    
    private void updateShotsIcons(){
        
        if(nave != null){
            if(nave.getShotsAvailable() != currentShotsAvailable){
                
                currentShotsAvailable = nave.getShotsAvailable();
                //agregar iconos
                for(int i = 0; i < nave.getShotsAvailable(); i++){
                    munitionItems[i].getImage().setTransparency(255);
                }
                //quitar iconos
                for(int i = 3; i > nave.getShotsAvailable(); i--){
                    munitionItems[i - 1].getImage().setTransparency(0);
                }
            }
        }
    }
    
    public void endSpaceshipActivity(){
        if(nave != null){
            nave.endSpaceshipActivity();  
        }
        
    }
    
    private void collectExtraLife(){
        if(nave != null){
            if(nave.verifyIfCollectedLife()){
                addHeart();
            }
        }
    
    }
    
}
