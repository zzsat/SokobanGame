package views;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controllers.KeyHandler;
import engine.Controller;
import engine.Paint;
import engine.View;
import models.Model;

public class GameView extends View {
    private JFrame frame;
    private Container c;
    private JPanel backgroundPanel;
    private Paint paint;
    private KeyHandler keyHandler;
    /**
     * constructs the frame, and i
     * @param width frame width
     * @param height frame height
     */
    
    public GameView(String gameName, int width, int height) {
        frame = new JFrame();
        paint = new Paint();
        backgroundPanel = new JPanel();

        keyHandler = new KeyHandler();

        c = frame.getContentPane();
        c.setLayout(new BorderLayout());

        backgroundPanel.setLayout(new BorderLayout());

        c.add(backgroundPanel, BorderLayout.CENTER);
        c.requestFocusInWindow();

        backgroundPanel.add(paint);

        frame.addKeyListener(keyHandler);

        frame.setVisible(true);
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.requestFocusInWindow();
    }

    @Override
    public void attach(Controller controller) {
        super.attach(controller);
        keyHandler.attach(controller);
    }

    @Override
    public void win() {
        JDialog dialog = new JDialog();
        JLabel message = new JLabel("Congratulations, you won the game!", SwingConstants.CENTER);

        // -----STATISTICS-----------//
        JPanel statistics = new JPanel();
        
        JPanel c = new JPanel();
        c.setLayout(new BorderLayout(20, 20));
        c.add(message, BorderLayout.NORTH);
        c.add(statistics, BorderLayout.CENTER);

        c.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        dialog.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        dialog.setTitle("Game Won");
        dialog.add(c);
        dialog.pack();
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }

    @Override
    public void render(Model model) {
        paint.render(model);
    }
}
