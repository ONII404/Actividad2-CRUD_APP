package app;

import app.components.FormEstudiante;
import com.formdev.flatlaf.intellijthemes.FlatGruvboxDarkHardIJTheme;

/**
 *
 * @author ONII404
 */
public class main {

    public static void main(String[] args) {

        FlatGruvboxDarkHardIJTheme.setup(); // Theme

        FormEstudiante form = new FormEstudiante(); // Form
        form.setVisible(true);
    }

}
