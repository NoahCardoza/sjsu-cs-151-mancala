/**
 * @author Noah Cardoza
 * @version 0.0.1
 * @date 11/10/2022
 * @assignment Mancala
 */

package gui.window;

import gui.model.ModelManager;
import gui.view.MainView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainWindow {
    private final MainView mainView;

    public MainWindow(ModelManager modelManager) {

        mainView = new MainView(modelManager);

        mainView.setSize(900, 300);
        mainView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // keep the aspect ratio of the screen and enforce a minimum size
        mainView.addComponentListener(new ComponentAdapter(){
            @Override
            public void componentResized(ComponentEvent event) {
                int minWidth = 600;
                int minHeight = 350;

                Rectangle b = event.getComponent().getBounds();
                if (b.width < minWidth) {
                    b.width = minWidth;
                }
                if (b.height < minHeight) {
                    b.height = minHeight;
                }
                event.getComponent().setBounds(b.x, b.y, b.width, b.width * minHeight / minWidth);
            }
        });
    }

    public MainView getMainView() {
        return mainView;
    }
}
