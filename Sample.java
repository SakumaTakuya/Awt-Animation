import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import animation.*;

public class Sample{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            JFrame frame = new JFrame();

            //Since there is a possibility that the screen may become dirty because drawing update is not done, 
            //it is better to set a window to adapt the animation to the animator.
            Animator.setWindow(frame);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.setLayout(null);
            frame.setBounds(100, 100, 800, 500);

            JButton button = new JButton("Click!");
            button.setBounds(350, 300, 100, 100);
            JLabel label = new JLabel("Clicked!!");
            label.setBounds(900, 10, 100, 100);

            //Generate an animation
            MoveAnimation moveAnimation = new MoveAnimation(900, 10, -100, 10, 10000);
            AbsScaleAnimation scaleAnimation = new AbsScaleAnimation(100, 100, 110, 110, 1000);

            //Create an animator and link a component
            Animator labelAnimator = new Animator(label);
            //Add new animation to animator
            labelAnimator.addAnimation(moveAnimation);
            //Create an animator and link a component and an animation
            Animator buttonAnimator = new Animator(button, scaleAnimation);

            button.addMouseListener(new MouseListener(){            
                @Override
                public void mouseExited(MouseEvent e) {
                    //Play backwards
                    scaleAnimation.setPlayBackwards(true);
                    //Forcibly terminate animation
                    buttonAnimator.stopAnimation();
                    //Start animation by index
                    buttonAnimator.startAnimation(0);
                }
            
                @Override
                public void mouseEntered(MouseEvent e) {
                    scaleAnimation.setPlayBackwards(false);
                    buttonAnimator.stopAnimation();
                    buttonAnimator.startAnimation(0);
                }
            
                @Override
                public void mouseClicked(MouseEvent e) {}
                @Override
                public void mouseReleased(MouseEvent e) {}
                @Override
                public void mousePressed(MouseEvent e) {}
            });

            button.addActionListener(e->{
                //Start animation by an animation object
                labelAnimator.startAnimation(moveAnimation);
            });

            //Register action to be executed at the end of the animation
            moveAnimation.addActionListener(e -> {
                moveAnimation.setPlayBackwards(!moveAnimation.getPlayBackwards());
            });

            //Register action to be executed at the end of any animation
            labelAnimator.addActionListener(e ->{
                System.out.println("finish an animation!");
            });

            frame.add(button);
            frame.add(label);
            frame.setVisible(true);
        });
    }
}