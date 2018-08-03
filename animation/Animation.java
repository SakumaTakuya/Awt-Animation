package animation;

import java.awt.Component;

public abstract class Animation extends ActionInvoker{
    public final static int ANIMATION_EVENT = 0; 
    
    private int totalFrame = 1000;
    private boolean playBackwards;

    public Animation(double length){
        setLength(length);
    }

    //Set the animating time in milliseconds
    public void setLength(double length){
        totalFrame = (int)(length / Animator.FPS);
    } 

    public int getTotalFrame(){
        return totalFrame;
    }

    public void setPlayBackwards(boolean playBack){
        playBackwards = playBack;
    }

    public boolean getPlayBackwards(){
        return playBackwards;
    }

    final void doAnimation(Component component, int frame){
        double rate = (double) frame / totalFrame;
        animate(component, playBackwards ? 1 - rate : rate);
    }

    final void end(){
        invoke(ANIMATION_EVENT);
    }

    public void initialize(Component component){}

    public abstract void animate(Component component, double rate);
}