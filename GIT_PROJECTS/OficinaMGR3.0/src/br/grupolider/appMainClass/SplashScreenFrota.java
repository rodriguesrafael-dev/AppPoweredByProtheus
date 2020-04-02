package br.grupolider.appMainClass;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

/**
 *
 * @author rodrigues rafael
 */
public class SplashScreenFrota {

    private final int width_img = 711;
    private final int height_img = 293;
    private final int splash_time = 3000;
    private final String img_url = "/br/grupolider/image/SCREEN_SPLASH_OFICINA.png";

    public SplashScreenFrota() {
        JWindow splashWindow = new JWindow();

        splashWindow.getContentPane().add(
                new JLabel(
                        "",
                        new ImageIcon(getClass().getResource(img_url)),
                        SwingConstants.CENTER
                )
        );

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();

        splashWindow.setBounds(
                (dimension.width - width_img) / 2,
                (dimension.height - height_img) / 2,
                width_img,
                height_img
        );
        splashWindow.setVisible(true);

        try {
            Thread.sleep(splash_time);
        } catch (InterruptedException ex) {
            ex.getStackTrace();
        }
        splashWindow.dispose();

    }

}
