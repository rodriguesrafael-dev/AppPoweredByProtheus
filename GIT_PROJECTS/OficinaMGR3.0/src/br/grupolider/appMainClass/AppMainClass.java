package br.grupolider.appMainClass;

import br.grupolider.view.ui.JFDesktop;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 *
 * @author rodrigues raffael
 */
public class AppMainClass {
    
    private static void systemTheme() {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(AppMainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public static void main(String[] args) {  
        new SplashScreenFrota(); //start initialization banner
        systemTheme(); //load theme           
        new JFDesktop().setVisible(true); //start app
    }

}
