import javax.swing.*;
import java.awt.*;

public class EX_MEMPanel extends JPanel{
    Output output;
    private ImageIcon bit0 = new ImageIcon( "bit0.png" );
    private ImageIcon bit1 = new ImageIcon( "bit1.png" );
    private ImageIcon nullBit = new ImageIcon( "null bit.png" );

    public EX_MEMPanel(Output output) {
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
            if (output.EX_MEM_instruction.charAt( i-1 ) == '1') {
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
        pc.setText( "     pc : " + output.EX_MEM_PC + "     ALU result : " + output.EX_MEM_ALUResult + "     add ALU : " + output.EX_MEM_addALU + "     readRegister2 : " + output.EX_MEM_readRegister2 );
        pc.setFont( new Font( "Serif", Font.BOLD, 14 ) );
        add( pc );

        JLabel emptyLabel2 = new JLabel();
        add( emptyLabel2 );

        JLabel non_control1 = new JLabel();
        non_control1.setText( "     RegWrite : " + output.EX_MEM_RegWrite + "     PCSrc : " + output.EX_MEM_PCSrc + "     MemRead : " + output.EX_MEM_MemRead );
        non_control1.setFont( new Font( "Serif", Font.BOLD, 14 ) );
        add( non_control1 );

        JLabel non_control2 = new JLabel();
        non_control2.setText(  "     MemWrite : " + output.EX_MEM_MemWrite + "     MemToReg : " + output.EX_MEM_MemToReg + "     branch : " + output.EX_MEM_branch  );
        non_control2.setFont( new Font( "Serif", Font.BOLD, 14 ) );
        add( non_control2 );

    }
}
