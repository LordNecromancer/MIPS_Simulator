import javax.swing.*;
import java.awt.*;

public class MEM_WBPanel extends JPanel {
    Output output;
    private ImageIcon bit0 = new ImageIcon( "bit0.png" );
    private ImageIcon bit1 = new ImageIcon( "bit1.png" );

    public MEM_WBPanel(Output output) {
        setLayout( new GridLayout( 7, 1 ) );

        JLabel instruction = new JLabel();
        instruction.setText( "     instruction : " );
        instruction.setFont( new Font( "Serif", Font.BOLD, 14 ) );
        add( instruction );
        JLabel instructionBit = new JLabel();
        JLabel[] instructionBits = new JLabel[32];
        instructionBit.setLayout( new GridLayout( 1, 5 ) );
        for (int i = 1; i < 6; i++) {
            instructionBits[i] = new JLabel();
            instructionBit.add( instructionBits[i] );
            if (output.MEM_WB_instruction.charAt( i-1 ) == '1') {
                instructionBits[i].setIcon( bit1 );
            } else {
                instructionBits[i].setIcon( bit0 );
            }
        }
        for (int i = 6; i < 32; i++) {
            instructionBits[i] = new JLabel();
            instructionBit.add( instructionBits[i] );
        }
        add( instructionBit );


        JLabel emptyLabel1 = new JLabel();
        add( emptyLabel1 );

        JLabel pc = new JLabel();
        pc.setText( "     memory data : " + output.MEM_WB_memoryData+ "     ALU result : " + output.MEM_WB_ALUResult);
        pc.setFont( new Font( "Serif", Font.BOLD, 14 ) );
        add( pc );

        JLabel emptyLabel2=new JLabel(  );
        add(emptyLabel2);

        JLabel non_control1 = new JLabel();
        non_control1.setText(  "     RegWrite : " + output.MEM_WB_RegWrite +"     MemToReg : " + output.MEM_WB_MemToReg );
        non_control1.setFont( new Font( "Serif", Font.BOLD, 14 ) );
        add( non_control1 );



    }
}
