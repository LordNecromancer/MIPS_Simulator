import javax.swing.*;
import java.awt.*;

public class IF_IDFrame extends JFrame {
    Output output;
    IF_IDPanel if_idPanel;
    public IF_IDFrame(Output output) {
        setTitle( "IF/ID" );
        if_idPanel=new IF_IDPanel( output );
        setPreferredSize( new Dimension( 700, 200 )  );
        setMaximumSize( new Dimension( 700, 200 ) );
        setMinimumSize( new Dimension( 700, 200 ) );
        setLocation( 0, 0 );
        setVisible( true );
        this.add(if_idPanel);
        if_idPanel.repaint(  );
    }

}
