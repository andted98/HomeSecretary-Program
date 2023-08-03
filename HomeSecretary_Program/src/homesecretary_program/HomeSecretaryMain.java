//Author - Andrew Tedesco

package homesecretary_program;

import javax.swing.JFrame;

public class HomeSecretaryMain {

    public static void main(String[] args) {
            
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                LoginUI login = new LoginUI();
                login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                login.setVisible(true);
            }
        });
    }
    
}
