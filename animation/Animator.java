package animation;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

public class Animator extends ActionInvoker implements ActionListener{
    private static int animatingCounts;
    private static Component window;
    
    public final static int ANIMATOR_EVENT = 0;
    public final static int FPS = 90; //frame per second
    public final static Timer ANIMATION_TIMER = new Timer(1000/FPS, e -> {
        if(animatingCounts != 0 && window != null) window.repaint();
    });
   
    private Component component;//object of animation 
    private List<Animation> animations;
    private Animation currentAnimation;
    private int current;

    public static int getAnimatingCounts(){
        return animatingCounts;
    }

    public static void setWindow(Component component){
        window = component;
    }

    public static Component getWindow(){
        return window;
    }

    public Animator(Component component){
        if(!ANIMATION_TIMER.isRunning()){
            ANIMATION_TIMER.start();
        }
        
        this.component = component;
        this.animations = new ArrayList<Animation>();
    }

    public Animator(Component component, Animation animation){
        this(component);
        this.animations.add(animation);
    }

    public void addAnimation(Animation animation){
        this.animations.add(animation);
    }

    public void removeAnimation(Animation animation){
        this.animations.remove(animation);
    }

    public void startAnimation(int index){
        if(this.currentAnimation != null) return;
        ANIMATION_TIMER.addActionListener(this);
        animatingCounts++;

        this.currentAnimation = animations.get(index);
        this.currentAnimation.initialize(component);
    }

    public void startAnimation(Animation animation){
        this.startAnimation(animations.indexOf(animation));
    }

    public void startAnimation(int index, double length){
        this.animations.get(index).setLength(length);
        this.startAnimation(index);
    }

    public void startAnimation(Animation animation, double length){
        this.startAnimation(this.animations.indexOf(animation), length);
    }

    public void stopAnimation(){
        ANIMATION_TIMER.removeActionListener(this);
        animatingCounts--;

        Animation animation = this.currentAnimation;

        this.current = 0;
        this.currentAnimation = null;

        super.invoke(ANIMATOR_EVENT);
        if(animation != null) animation.end();
    }

    public boolean isAnimating(){
        return this.currentAnimation != null;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        this.currentAnimation.doAnimation(this.component, this.current++);
        if(this.current > this.currentAnimation.getTotalFrame()){
            this.stopAnimation();
        }
    }
}