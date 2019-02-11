import javax.swing.*;
import java.awt.*;

public class ID_EXPanel extends JPanel {
    Output output;
    private ImageIcon bit0 = new ImageIcon( "bit0.png" );
    private ImageIcon bit1 = new ImageIcon( "bit1.png" );

    public ID_EXPanel(Output output) {
        setLayout( new GridLayout( 12, 1 ) );

        JLabel instruction0_15 = new JLabel();
        instruction0_15.setText( "     instruction 0-15 : " );
        instruction0_15.setFont( new Font( "Serif", Font.BOLD, 14 ) );
        add( instruction0_15 );
        JLabel instructionBit0_15 = new JLabel();
        JLabel[] instructionBits0_15 = new JLabel[32];
        instructionBit0_15.setLayout( new GridLayout( 1, 16 ) );
        for (int i = 1; i < 17; i++) {
            instructionBits0_15[i] = new JLabel();
            instructionBit0_15.add( instructionBits0_15[i] );
            if (output.ID_EX_instruction0_15.charAt( i-1 ) == '1') {
                instructionBits0_15[i].setIcon( bit1 );
            } else {
                instructionBits0_15[i].setIcon( bit0 );
            }
        }
        for (int i = 17; i < 32; i++) {
            instructionBits0_15[i] = new JLabel();
            instructionBit0_15.add( instructionBits0_15[i] );
        }
        add( instructionBit0_15 );


        JLabel instruction11_15 = new JLabel();
        instruction11_15.setText( "     instruction 11-15 : " );
        instruction11_15.setFont( new Font( "Serif", Font.BOLD, 14 ) );
        add( instruction11_15 );
        JLabel instructionBit11_15 = new JLabel();
        JLabel[] instructionBits11_15 = new JLabel[32];
        instructionBit11_15.setLayout( new GridLayout( 1, 5 ) );
        for (int i = 1; i < 6; i++) {
            instructionBits11_15[i] = new JLabel();
            instructionBit11_15.add( instructionBits11_15[i] );
            if (output.ID_EX_instruction11_15.charAt( i-1 ) == '1') {
                instructionBits11_15[i].setIcon( bit1 );
            } else {
                instructionBits11_15[i].setIcon( bit0 );
            }
        }
        for (int i = 6; i < 32; i++) {
            instructionBits11_15[i] = new JLabel();
            instructionBit11_15.add( instructionBits11_15[i] );
        }
        add( instructionBit11_15 );


        JLabel instruction16_20 = new JLabel();
        instruction16_20.setText( "     instruction 16-20 : " );
        instruction16_20.setFont( new Font( "Serif", Font.BOLD, 14 ) );
        add( instruction16_20 );
        JLabel instructionBit16_20 = new JLabel();
        JLabel[] instructionBits16_20 = new JLabel[32];
        instructionBit16_20.setLayout( new GridLayout( 1, 5 ) );
        for (int i = 1; i < 6; i++) {
            instructionBits16_20[i] = new JLabel();
            instructionBit16_20.add( instructionBits16_20[i] );
            if (output.ID_EX_instruction16_20.charAt( i-1 ) == '1') {
                instructionBits16_20[i].setIcon( bit1 );
            } else {
                instructionBits16_20[i].setIcon( bit0 );
            }
        }
        for (int i = 6; i < 32; i++) {
            instructionBits16_20[i] = new JLabel();
            instructionBit16_20.add( instructionBits16_20[i] );
        }
        add( instructionBit16_20 );


        JLabel emptyLabel1 = new JLabel();
        add( emptyLabel1 );

        JLabel pc = new JLabel();
        pc.setText( "     pc : " + output.ID_EX_PC + "     registerData1 : " + output.ID_EX_registerData1 + "     registerData2 : " + output.ID_EX_registerData2 );
        pc.setFont( new Font( "Serif", Font.BOLD, 14 ) );
        add( pc );

        JLabel emptyLabel2 = new JLabel();
        add( emptyLabel2 );

        JLabel non_control1 = new JLabel();
        non_control1.setText( "     RegDst : " + output.ID_EX_RegDst + "     RegWrite : " + output.ID_EX_RegWrite + "     ALUSrc : " + output.ID_EX_ALUSrc + "     PCSrc : " + output.ID_EX_PCSrc + "     MemRead : " + output.ID_EX_MemRead );
        non_control1.setFont( new Font( "Serif", Font.BOLD, 14 ) );
        add( non_control1 );

        JLabel non_control2 = new JLabel();
        non_control2.setText(  "     MemWrite : " + output.ID_EX_MemWrite + "     MemToReg : " + output.ID_EX_MemToReg + "     ALUOp0 : " + output.ID_EX_ALUOp0 + "     ALUOp1 : " + output.ID_EX_ALUOp1 + "     branch : " + output.ID_EX_branch  );
        non_control2.setFont( new Font( "Serif", Font.BOLD, 14 ) );
        add( non_control2 );

    }
}
