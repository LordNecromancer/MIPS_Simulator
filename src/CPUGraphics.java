import javax.swing.*;
import java.awt.*;

public class CPUGraphics extends JFrame {
    CPUPanel cpuPanel;
    Output output;

    public CPUGraphics(Output output) {
        this.output = output;
        cpuPanel = new CPUPanel( output,this );
        setPreferredSize( new Dimension( 1015, 750 ) );
        setMaximumSize( new Dimension( 1015, 750 ) );
        setMinimumSize( new Dimension( 1015, 750 ) );
        setLocation( 0, 0 );
        add( cpuPanel );
        setVisible( true );
        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
    }
}
