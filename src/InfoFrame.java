import javax.swing.*;
import java.awt.*;

public class InfoFrame extends JFrame {
    Output output;
    InfoPanel infoPanel;
    public InfoFrame(Output output) {
        setTitle( "Info" );
        infoPanel=new InfoPanel( output );
        setPreferredSize( new Dimension( 760, 560 )  );
        setMaximumSize( new Dimension( 760, 560 ) );
        setMinimumSize( new Dimension( 760, 560 ) );
        setLocation( 0, 0 );
        setVisible( true );
        this.add(infoPanel);
        infoPanel.repaint(  );
    }

}
