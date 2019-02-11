import javax.swing.*;
import java.awt.*;

public class RegisterFilePanel extends JPanel {

    private ImageIcon bit0 = new ImageIcon( "bit0.png" );
    private ImageIcon bit1 = new ImageIcon( "bit1.png" );
    private ImageIcon equ = new ImageIcon( "=.png" );

    public void paintComponent(Graphics g) {
        super.paintComponent( g );
    }

    public RegisterFilePanel(Output output) {
        setBackground( new Color( 238, 238, 238 ) );
        setLayout( new GridLayout( 8, 1 ) );

        JLabel t0bit = new JLabel();
        t0bit.setLayout( new GridLayout( 1, 34 ) );
        JLabel[] t0bits;
        t0bits = new JLabel[34];
        for (int i = 0; i < 34; i++) {
            t0bits[i] = new JLabel();
            if (i == 0) {
                t0bits[i].setIcon( new ImageIcon( "t0.png" ) );
            }
            if (i == 1) {
                t0bits[i].setIcon( equ );
            }

            if (i > 1) {
                if (output.$t0.charAt( i - 2 ) == '0') {
                    t0bits[i].setIcon( bit0 );
                } else if (output.$t0.charAt( i - 2 ) == '1') {
                    t0bits[i].setIcon( bit1 );
                }
            }
            t0bit.add( t0bits[i] );
        }
        this.add( t0bit );


        JLabel t1bit = new JLabel();
        t1bit.setLayout( new GridLayout( 1, 34 ) );
        JLabel[] t1bits;
        t1bits = new JLabel[34];
        for (int i = 0; i < 34; i++) {
            t1bits[i] = new JLabel();
            if (i == 0) {
                t1bits[i].setIcon( new ImageIcon( "t1.png" ) );
            }
            if (i == 1) {
                t1bits[i].setIcon( equ );
            }

            if (i > 1) {
                if (output.$t1.charAt( i - 2 ) == '0') {
                    t1bits[i].setIcon( bit0 );
                } else if (output.$t1.charAt( i - 2 ) == '1') {
                    t1bits[i].setIcon( bit1 );
                }
            }
            t1bit.add( t1bits[i] );
        }
        this.add( t1bit );


        JLabel t2bit = new JLabel();
        t2bit.setLayout( new GridLayout( 1, 34 ) );
        JLabel[] t2bits;
        t2bits = new JLabel[34];
        for (int i = 0; i < 34; i++) {
            t2bits[i] = new JLabel();
            if (i == 0) {
                t2bits[i].setIcon( new ImageIcon( "t2.png" ) );
            }
            if (i == 1) {
                t2bits[i].setIcon( equ );
            }

            if (i > 1) {
                if (output.$t2.charAt( i - 2 ) == '0') {
                    t2bits[i].setIcon( bit0 );
                } else if (output.$t2.charAt( i - 2 ) == '1') {
                    t2bits[i].setIcon( bit1 );
                }
            }
            t2bit.add( t2bits[i] );
        }
        this.add( t2bit );


        JLabel t3bit = new JLabel();
        t3bit.setLayout( new GridLayout( 1, 34 ) );
        JLabel[] t3bits;
        t3bits = new JLabel[34];
        for (int i = 0; i < 34; i++) {
            t3bits[i] = new JLabel();
            if (i == 0) {
                t3bits[i].setIcon( new ImageIcon( "t3.png" ) );
            }
            if (i == 1) {
                t3bits[i].setIcon( equ );
            }

            if (i > 1) {
                if (output.$t3.charAt( i - 2 ) == '0') {
                    t3bits[i].setIcon( bit0 );
                } else if (output.$t3.charAt( i - 2 ) == '1') {
                    t3bits[i].setIcon( bit1 );
                }
            }
            t3bit.add( t3bits[i] );
        }
        this.add( t3bit );


        JLabel t4bit = new JLabel();
        t4bit.setLayout( new GridLayout( 1, 34 ) );
        JLabel[] t4bits;
        t4bits = new JLabel[34];
        for (int i = 0; i < 34; i++) {
            t4bits[i] = new JLabel();
            if (i == 0) {
                t4bits[i].setIcon( new ImageIcon( "t4.png" ) );
            }
            if (i == 1) {
                t4bits[i].setIcon( equ );
            }

            if (i > 1) {
                if (output.$t4.charAt( i - 2 ) == '0') {
                    t4bits[i].setIcon( bit0 );
                } else if (output.$t4.charAt( i - 2 ) == '1') {
                    t4bits[i].setIcon( bit1 );
                }
            }
            t4bit.add( t4bits[i] );
        }
        this.add( t4bit );

        JLabel rabit = new JLabel();
        rabit.setLayout( new GridLayout( 1, 34 ) );
        JLabel[] rabits;
        rabits = new JLabel[34];
        for (int i = 0; i < 34; i++) {
            rabits[i] = new JLabel();
            if (i == 0) {
                rabits[i].setIcon( new ImageIcon( "ra.png" ) );
            }
            if (i == 1) {
                rabits[i].setIcon( equ );
            }

            if (i > 1) {
                if (output.$ra.charAt( i - 2 ) == '0') {
                    rabits[i].setIcon( bit0 );
                } else if (output.$ra.charAt( i - 2 ) == '1') {
                    rabits[i].setIcon( bit1 );
                }
            }
            rabit.add( rabits[i] );
        }
        this.add( rabit );

        JLabel spbit = new JLabel();
        spbit.setLayout( new GridLayout( 1, 34 ) );
        JLabel[] spbits;
        spbits = new JLabel[34];
        for (int i = 0; i < 34; i++) {
            spbits[i] = new JLabel();
            if (i == 0) {
                spbits[i].setIcon( new ImageIcon( "sp.png" ) );
            }
            if (i == 1) {
                spbits[i].setIcon( equ );
            }

            if (i > 1) {
                if (output.$sp.charAt( i - 2 ) == '0') {
                    spbits[i].setIcon( bit0 );
                } else if (output.$sp.charAt( i - 2 ) == '1') {
                    spbits[i].setIcon( bit1 );
                }
            }
            spbit.add( spbits[i] );
        }
        this.add( spbit );

        JLabel readRegisters = new JLabel();
        readRegisters.setText( "     ReadData1 : " + output.registerData1 + "     ReadData2 : " + output.registerData2  );
        readRegisters.setFont( new Font( "Serif", Font.BOLD, 14 ) );
        add( readRegisters );


    }
}
