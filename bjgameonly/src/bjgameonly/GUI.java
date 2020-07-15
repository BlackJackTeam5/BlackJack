package bjgameonly;

import javax.wring.*;
import java.util.*;
import java.awt.*;

public class GUI extends JFrame {

    int aW = 1300;
    int aH = 800;

    public GUI() {
        this.setSize(aW+6, aH+29);
        this.setTitle("BlackJack");
        this.setVisible(true);
        this.setResizeable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}