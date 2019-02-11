import javax.swing.*;
import java.awt.*;

public class EX_MEMFrame extends JFrame{
    Output output;
    EX_MEMPanel ex_memPanel;
    public EX_MEMFrame(Output output) {
        setTitle( "EX/MEM" );
        ex_memPanel=new EX_MEMPanel( output );
        setPreferredSize( new Dimension( 660, 260 )  );
        setMaximumSize( new Dimension( 660, 260 ) );
        setMinimumSize( new Dimension( 660, 260 ) );
        setLocation( 0, 0 );
        setVisible( true );
        this.add(ex_memPanel);
        ex_memPanel.repaint(  );
    }

}
