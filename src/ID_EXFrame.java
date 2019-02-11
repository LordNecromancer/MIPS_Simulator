import javax.swing.*;
import java.awt.*;

public class ID_EXFrame extends JFrame{
    Output output;
    ID_EXPanel id_exPanel;
    public ID_EXFrame(Output output) {
        setTitle( "ID/EX" );
        id_exPanel=new ID_EXPanel( output );
        setPreferredSize( new Dimension( 660, 560 )  );
        setMaximumSize( new Dimension( 660, 560 ) );
        setMinimumSize( new Dimension( 660, 560 ) );
        setLocation( 0, 0 );
        setVisible( true );
        this.add(id_exPanel);
        id_exPanel.repaint(  );
    }

}
