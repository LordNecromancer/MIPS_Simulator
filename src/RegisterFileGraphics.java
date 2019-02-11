import javax.swing.*;
import java.awt.*;

public class RegisterFileGraphics extends JFrame {
    RegisterFilePanel registerFilePanel;
    public RegisterFileGraphics(Output output) {
        setTitle( "Register File" );
        registerFilePanel=new RegisterFilePanel( output );
        setPreferredSize( new Dimension( 760, 450 )  );
        setMaximumSize( new Dimension( 760, 450 ) );
        setMinimumSize( new Dimension( 760, 450 ) );
        setLocation( 0, 0 );
        setVisible( true );
        this.add(registerFilePanel);
        registerFilePanel.repaint(  );
    }
}
