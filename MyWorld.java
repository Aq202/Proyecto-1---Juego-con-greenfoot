import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

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
    
    private final int defaultRematchTimer = 300;
    private int rematchTimer = defaultRematchTimer;
    
    //enter options
    private boolean startGameOption = false;
    private boolean rematchOption = false;
    private boolean enterBlocked = false;
    private Rematch rematch = null;
    
    //asteroids timer
    private final int defaultAsteroidsTimer = 2500;
    private int asteroidsTimer = defaultAsteroidsTimer;
    private final int maxAsteroids = 6;
    
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
            asteroidsTimer();
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
        //add asteroids
        Random r = new Random();
        addAsteroids(1 + r.nextInt(maxAsteroids));
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
                gameStarted = false;
            }
            
            if(rematchTimer <= 0){
                removeObjects(getObjects(GameOver.class)); //remover gameOver
                addRematchOption();
                rematchTimer = defaultRematchTimer;
                rematchOption = true;
                
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
        GreenfootSound music = new GreenfootSound("backgroundMusic.mp3");
        music.playLoop();
        music.setVolume(50);
    }
    
    public void addAsteroids(int num){
        
        int border = 100;
        int xMax = getWidth() - 2 * border;
        int yMax = getHeight() - 2 * border;
        Random r = new Random();
        
        for(int i = 0; i < num; i++){
            
            addObject(new Asteroid(), border + r.nextInt(xMax), border + r.nextInt(yMax));
        }
        
    }
    
    public void asteroidsTimer(){
        
        if(gameStarted){
            if(asteroidsTimer <= 0){
                asteroidsTimer = defaultAsteroidsTimer;
                int numOfAsteroids = getObjects(Asteroid.class).size();
                
                if(numOfAsteroids < maxAsteroids){
     
                    Random r = new Random();
                    int numOfAsteroidToAdd = 1 + r.nextInt(maxAsteroids - numOfAsteroids);
                    if(numOfAsteroidToAdd > 0){
                       addAsteroids(numOfAsteroidToAdd); 
                    }
                    
                }
            }else{
                asteroidsTimer--;
            }
        }else{
            if(asteroidsTimer != defaultAsteroidsTimer){
                asteroidsTimer = defaultAsteroidsTimer;
            }
        }
    }
}
