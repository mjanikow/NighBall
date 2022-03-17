import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;

class Launcher extends JFrame {
    private Game gra;
    public Launcher() throws InterruptedException, FileNotFoundException {
        JFrame frame = new JFrame("Nightball run");
        Game game = new Game();
        gra=game;
        frame.add(game);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        run.start();
    }
    Thread run = new Thread(() -> {
        try {
            gra.start();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (true) {
            gra.repaint();
            try {
                gra.update();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });
    }

class Game extends JPanel {

    boolean gameOver;
    private Ball ball = new Ball(this);
    private Plansza plansza = new Plansza(this);
    private Przeszkody przeszkody = new Przeszkody(this,ball);
    private int startTime;
    int realTime;
    private int czySkok=0;
    private String start, start2, start3;
    private int max;
    private String punkty;
    private Scanner odczyt;
    private PrintWriter zapis;
    private int savePoints;
    int szybkosc;
    private int liczczas;
    private Sound audio;
    private int speedLevel;
    List<BufferedImage> vecImages = new ArrayList<BufferedImage>();

    void start() throws FileNotFoundException {
        speedLevel=1;
        audio = new Sound();
        Images images = new Images();
        vecImages = images.images;
        ball.start();
        plansza.start();
        przeszkody.start();
        startTime= (int) System.currentTimeMillis();
        start="Press space to jump. Press space twice and fast to jump higher. Hold space to jump higher automatically. Avoid red objects.  ";
        start2="Try controll ball in air by using space.";
        odczyt= new Scanner(new File("highscore.txt"));
        String text = odczyt.nextLine();
        max = Integer.parseInt(text);
        szybkosc = 6;
        liczczas=300;
        audio.play("sounds/upAudio.aiff");
    }

    void update() throws FileNotFoundException {
        ball.rotate();
        plansza.moveGround();
        plansza.moveStars();
        if (ball.czyDotyka()) czySkok=0;
        przeszkody.przesuwajPrzeszkode();
        realTime= (int) (System.currentTimeMillis()-startTime);
        punkty = "POINTS:" + String.valueOf(realTime/100);
        highscore();
        zwiekszSzybkosc();
    }

    void restart() {
        gamePoints();
        gameOver=true;
        startTime= (int) System.currentTimeMillis();
        szybkosc=0;
        ball.kat=0;
        plansza.starsSPEED=0;
        
    }

    void newGame(){
        savePoints = 0;
        gameOver=false;
        szybkosc=6;
        plansza.starsSPEED=1;
        przeszkody.resetujPrzeszkody();
    }

    void gamePoints(){
        if (savePoints == 0)
              {         audio.play("sounds/endAudio.aiff");
                  savePoints = realTime/100;}
    }

    void highscore() throws FileNotFoundException {
        if (realTime/100>max)
           {
               max=realTime/100;
               zapis = new PrintWriter("highscore.txt");
               zapis.println(max);
               zapis.close();
           }
    }

    void zwiekszSzybkosc(){
        if (realTime/100>liczczas){
            audio.play("sounds/upAudio.aiff");
            ball.szybkoscObrotu++;
            szybkosc++;
            liczczas+=300;
            speedLevel++;
        }
    }

    public Game() {
        addKeyListener(new KeyListener(){
            long moc;
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE && czySkok<=1 ) {
                    {
                        if (ball.getY()==714-80)
                        audio.play("sounds/jumpAudio.aiff");
                        czySkok++;
                        moc= System.currentTimeMillis();
                        ball.jump(300);
                    }
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_X && gameOver ) {
                    {
                        newGame();
                    }
                }
            }
        });
        setFocusable(true);
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        plansza.paint(g2D);
        ball.paint(g2D);
        przeszkody.paint(g2D);
        g2D.setFont(new Font("TimesRoman", Font.PLAIN, 50));
        g2D.drawString(punkty,50,50);
        g2D.setFont(new Font("TimesRoman", Font.PLAIN, 30));

        if (realTime<10000)
            { g2D.drawString(start,100,this.getHeight()-150);
            g2D.drawString(start2,100,this.getHeight()-100);}
        g2D.drawString("HIGHSCORE : " + String.valueOf(max),this.getWidth()-450,50);
        g2D.drawString("speedlevel: : " + String.valueOf(speedLevel),this.getWidth()-1250,50);

        if (gameOver)
           {
               g2D.drawString("GAME OVER",this.getWidth()/3,this.getHeight()/4);
               g2D.drawString("Your points: " +savePoints,this.getWidth()/3,this.getHeight()/4+100);
               g2D.drawString("Press X to restart",this.getWidth()/3,this.getHeight()/4+200);
           }

    }


    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        Launcher gra = new Launcher();
    }
}