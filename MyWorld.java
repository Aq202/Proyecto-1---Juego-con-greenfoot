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
    private boolean gameStarted = false;
    
    private final int defaultRematchTimer = 200;
    private int rematchTimer = defaultRematchTimer;
    
    //enter options
    private boolean startGameOption = false;
    private boolean rematchOption = false;
    private boolean enterBlocked = false;
    private Rematch rematch = null;
    
    public MyWorld()
    {    
        
        super(800, 600, 1); 
    }
    
    public void act(){
        
        if(!initialize){
            initialize = true;
            setMainScreen();
            playBackgroundMusic();
        }else{
            enterActions();
        }
        
        if(gameStarted){
            verifyIfGameEnded();
        }
        
    }
    
    private void setMainScreen(){
        startGameOption = true;
        
        int worldWidth = getWidth() / 2;
        int worldHeight = getHeight() / 2;
        
        addObject(new MainScreen(), worldWidth, worldHeight);
        
    }
    
    
    public void startGame(){
        
        gameStarted = true;
        //create players
        player1 = new Player1();
        player2 = new Player2();
        
        addObject(player1, 0,0);
        addObject(player2, 0, 0);
    
        
    }
    
    private void enterActions(){
        if(Greenfoot.isKeyDown("enter") && !enterBlocked){
            enterBlocked = true;
            
            if(startGameOption){
                startGameOption = false;
                clearWorld();
                startGame();

            }else if(rematchOption){
                rematchOption = false;
                clearWorld();
                if(rematch.rematchGame()){
                    startGame();
                }else{
                    setMainScreen();
                }
            }
        }
        
        if(!Greenfoot.isKeyDown("enter") && enterBlocked){
            enterBlocked = false;
        }
    }
    
    private void clearWorld(){
        
        removeObjects(getObjects(null));
    }
    
    private void verifyIfGameEnded(){
        if((player1.isWinner() || player2.isWinner()) && !rematchOption ){
            
            //al recien perder
            if(rematchTimer == defaultRematchTimer){
                player1.endSpaceshipActivity();
                player2.endSpaceshipActivity();
            }
            
            if(rematchTimer <= 0){
                removeObjects(getObjects(GameOver.class)); //remover gameOver
                addRematchOption();
                rematchTimer = defaultRematchTimer;
                rematchOption = true;
                gameStarted = false;
            }else{
                rematchTimer--;
            }
        }
            
    }
    
    private void addRematchOption(){
        int worldWidth = getWidth() / 2;
        int worldHeight = getHeight() / 2;
        
        rematch = new Rematch();
        addObject(rematch, worldWidth, worldHeight);
    }
    
    private void playBackgroundMusic(){
        GreenfootSound music = new GreenfootSound("temaPrincipal.mp3");
        music.playLoop();
        music.setVolume(50);
    }
}
