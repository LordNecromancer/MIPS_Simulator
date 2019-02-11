import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {
    Output output;
    private ImageIcon bit0 = new ImageIcon( "bit0.png" );
    private ImageIcon bit1 = new ImageIcon( "bit1.png" );
    private ImageIcon equ = new ImageIcon( "=.png" );

    public InfoPanel(Output output) {
        setLayout( new GridLayout( 8, 1 ) );

        JLabel IFbit = new JLabel();
        IFbit.setLayout( new GridLayout( 1, 34 ) );
        JLabel[] IFbits;
        IFbits = new JLabel[34];
        for (int i = 0; i < 34; i++) {
            IFbits[i] = new JLabel();
            if (i == 0) {
                IFbits[i].setIcon( new ImageIcon( "IF.png" ) );
            }
            if (i == 1) {
                IFbits[i].setIcon( equ );
            }
            if (i > 1) {
                if (output.IF_Instruction == null) {
                    IFbits[i].setIcon( bit0 );
                } else {
                    if (output.IF_Instruction.charAt( i - 2 ) == '0') {
                        IFbits[i].setIcon( bit0 );
                    } else if (output.IF_Instruction.charAt( i - 2 ) == '1') {
                        IFbits[i].setIcon( bit1 );
                    }
                }
            }
            IFbit.add( IFbits[i] );
        }
        this.add( IFbit );

        JLabel IDbit = new JLabel();
        IDbit.setLayout( new GridLayout( 1, 34 ) );
        JLabel[] IDbits;
        IDbits = new JLabel[34];
        for (int i = 0; i < 34; i++) {
            IDbits[i] = new JLabel();
            if (i == 0) {
                IDbits[i].setIcon( new ImageIcon( "ID.png" ) );
            }
            if (i == 1) {
                IDbits[i].setIcon( equ );
            }
            if (i > 1) {
                if (output.ID_Instruction == null) {
                    IDbits[i].setIcon( bit0 );
                } else {
                    if (output.ID_Instruction.charAt( i - 2 ) == '0') {
                        IDbits[i].setIcon( bit0 );
                    } else if (output.ID_Instruction.charAt( i - 2 ) == '1') {
                        IDbits[i].setIcon( bit1 );
                    }
                }
            }
            IDbit.add( IDbits[i] );
        }
        this.add( IDbit );

        JLabel EXbit = new JLabel();
        EXbit.setLayout( new GridLayout( 1, 34 ) );
        JLabel[] EXbits;
        EXbits = new JLabel[34];
        for (int i = 0; i < 34; i++) {
            EXbits[i] = new JLabel();
            if (i == 0) {
                EXbits[i].setIcon( new ImageIcon( "EX.png" ) );
            }
            if (i == 1) {
                EXbits[i].setIcon( equ );
            }
            if (i > 1) {
                if (output.EX_Instruction == null) {
                    EXbits[i].setIcon( bit0 );
                } else {
                    if (output.EX_Instruction.charAt( i - 2 ) == '0') {
                        EXbits[i].setIcon( bit0 );
                    } else if (output.EX_Instruction.charAt( i - 2 ) == '1') {
                        EXbits[i].setIcon( bit1 );
                    }
                }
            }
            EXbit.add( EXbits[i] );
        }
        this.add( EXbit );


        JLabel MEMbit = new JLabel();
        MEMbit.setLayout( new GridLayout( 1, 34 ) );
        JLabel[] MEMbits;
        MEMbits = new JLabel[34];
        for (int i = 0; i < 34; i++) {
            MEMbits[i] = new JLabel();
            if (i == 0) {
                MEMbits[i].setIcon( new ImageIcon( "ME.png" ) );
            }
            if (i == 1) {
                MEMbits[i].setIcon( equ );
            }
            if (i > 1) {
                if (output.MEM_Instruction == null) {
                    MEMbits[i].setIcon( bit0 );
                } else {
                    if (output.MEM_Instruction.charAt( i - 2 ) == '0') {
                        MEMbits[i].setIcon( bit0 );
                    } else if (output.MEM_Instruction.charAt( i - 2 ) == '1') {
                        MEMbits[i].setIcon( bit1 );
                    }
                }
            }
            MEMbit.add( MEMbits[i] );
        }
        this.add( MEMbit );

        JLabel WBbit = new JLabel();
        WBbit.setLayout( new GridLayout( 1, 34 ) );
        JLabel[] WBbits;
        WBbits = new JLabel[34];
        for (int i = 0; i < 34; i++) {
            WBbits[i] = new JLabel();
            if (i == 0) {
                WBbits[i].setIcon( new ImageIcon( "WB.png" ) );
            }
            if (i == 1) {
                WBbits[i].setIcon( equ );
            }
            if (i > 1) {
                if (output.WB_Instruction == null) {
                    WBbits[i].setIcon( bit0 );
                } else {
                    if (output.WB_Instruction.charAt( i - 2 ) == '0') {
                        WBbits[i].setIcon( bit0 );
                    } else if (output.WB_Instruction.charAt( i - 2 ) == '1') {
                        WBbits[i].setIcon( bit1 );
                    }
                }
            }
            WBbit.add( WBbits[i] );
        }
        this.add( WBbit );


        JLabel moreInfo = new JLabel();
        moreInfo.setText( "  clock number : " + output.clockNum + "     PC : " + output.PC );
        moreInfo.setFont( new Font( "Serif", Font.BOLD, 14 ) );
        add( moreInfo );

        JLabel aluRsults = new JLabel();
        aluRsults.setText( "  Main ALU output : " + output.mainALUOutput + "     First ALU output : " + output.pcALUOutput + "     Second ALU output : " + output.jumpALUOutput );
        aluRsults.setFont( new Font( "Serif", Font.BOLD, 14 ) );
        add( aluRsults );

        JLabel mainALUinput = new JLabel();
        mainALUinput.setText( "  Main ALU operand 1 : " + output.operand1 + "     Main ALU operand 2 : " + output.operand1 +"     Data Memory output : "+output.MEM_WB_memoryData);
        mainALUinput.setFont( new Font( "Serif", Font.BOLD, 14 ) );
        add( mainALUinput );
    }
}
