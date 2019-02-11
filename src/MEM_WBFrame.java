import javax.swing.*;
import java.awt.*;

public class MEM_WBFrame extends JFrame {
    Output output;
    MEM_WBPanel mem_wbPanel;
    public MEM_WBFrame(Output output) {
        setTitle( "MEM/WB" );
        mem_wbPanel=new MEM_WBPanel( output );
        setPreferredSize( new Dimension( 660, 260 )  );
        setMaximumSize( new Dimension( 660, 260 ) );
        setMinimumSize( new Dimension( 660, 260 ) );
        setLocation( 0, 0 );
        setVisible( true );
        this.add(mem_wbPanel);
        mem_wbPanel.repaint(  );
    }

}
