import javax.swing.*;
import java.awt.*;

public class IF_IDPanel extends JPanel {
    Output output;
    private ImageIcon bit0 = new ImageIcon( "bit0.png" );
    private ImageIcon bit1 = new ImageIcon( "bit1.png" );

    public IF_IDPanel(Output output) {
        setLayout( new GridLayout( 5, 1 ) );

        JLabel instruction = new JLabel();
        instruction.setText( "     instruction : " );
        instruction.setFont( new Font( "Serif", Font.BOLD, 14 ) );
        add( instruction );

        JLabel instructionBit = new JLabel();
        JLabel[] instructionBits = new JLabel[32];
        instructionBit.setLayout(  new GridLayout( 1, 32 ) );
        for (int i = 0; i < 32; i++) {
            instructionBits[i] = new JLabel();
            instructionBit.add( instructionBits[i] );
            if (output.IF_ID_instruction.charAt( i  ) == '0') {
                instructionBits[i].setIcon( bit0 );
            }else if(output.IF_ID_instruction.charAt( i  ) == '1'){
                instructionBits[i].setIcon( bit1 );
            }
        }
        add(instructionBit);

        JLabel emptyLabel1=new JLabel(  );
        add(emptyLabel1);

        JLabel pc = new JLabel();
        pc.setText( "     pc : " + output.IF_ID_PC );
        pc.setFont( new Font( "Serif", Font.BOLD, 14 ) );
        add( pc );

        JLabel emptyLabel2=new JLabel(  );
        add(emptyLabel2);

    }
}
