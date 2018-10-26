package Graficos;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;


public class Animation {

    private int frameCount;                 // Counts ticks for change
    private int frameDelay;                 // frame delay 1-12 (You will have to play around with this)
    private int currentFrame;               // animations current frame
    private int animationDirection;         // animation direction (i.e counting forward or backward)
    private int totalFrames;                // total amount of frames for your animation
    private int x;
    private int vx;
    private int y;
    private int vy;
    
    private boolean stopped;                // has animations stopped

    private List<Frame> frames = new ArrayList<Frame>();    // Arraylist of frames 

    public Animation(BufferedImage[] frames, int frameDelay, int x, int y, int vx, int vy) {
        this.frameDelay = frameDelay;
        this.stopped = true;
        
        for (int i = 0; i < frames.length; i++) {
            addFrame(frames[i], frameDelay);
        }

        
        this.frameCount = 0;
        this.frameDelay = frameDelay;
        this.currentFrame = 0;
        this.animationDirection = 1;
        this.totalFrames = this.frames.size();

        this.x = x;
        this.vx = vx;
        this.y = y;
        this.vy = vy;
        
    }

    public void start() {
        if (!stopped) {
            return;
        }

        if (frames.isEmpty()) {
            return;
        }

        stopped = false;
    }

    public void stop() {
        if (frames.isEmpty()) {
            return;
        }

        stopped = true;
    }

    public void restart() {
        if (frames.isEmpty()) {
            return;
        }

        stopped = false;
        currentFrame = 0;
    }

    public void reset() {
        this.stopped = true;
        this.frameCount = 0;
        this.currentFrame = 0;
    }

    private void addFrame(BufferedImage frame, int duration) {
        if (duration <= 0) {
            System.err.println("Invalid duration: " + duration);
            throw new RuntimeException("Invalid duration: " + duration);
        }

        frames.add(new Frame(frame, duration));
        currentFrame = 0;
    }

    public BufferedImage getSprite() {
        return frames.get(currentFrame).getFrame();
    }

    public void update(long s) {
        
        if (!stopped) {
            frameCount++;

            if (frameCount > frameDelay) {
                frameCount = 0;
                currentFrame += animationDirection;

                if (currentFrame > totalFrames - 1) {
                    currentFrame = 0;
                }
                else if (currentFrame < 0) {
                    currentFrame = totalFrames - 1;
                }
                
                x+=vx;
                y+=vy;
                System.out.println("("+x+","+y+")");
            }
            
        }

    }
    
    public void drawSprite(Graphics g){
        g.drawImage(this.getSprite(), x, y, null);
    }

}