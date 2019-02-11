import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

public class CPUPanel extends JPanel implements MouseListener, MouseMotionListener {
    Output output;
    CPUGraphics cpuGraphics;

    @Override

    public void paintComponent(Graphics g) {

        super.paintComponent( g );
        setLayout( null );
        Image img = null;
        try {
            img = ImageIO.read( new File( "C:/Users/Sun/IdeaProjects/MIPS_Simulator/cpu.png" ) );
            img = img.getScaledInstance( 1015, 687, Image.SCALE_DEFAULT );
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage( img, 0, 0, null );

    }

    public CPUPanel(Output output, CPUGraphics cpuGraphics) {
        repaint();
        this.cpuGraphics=cpuGraphics;
        this.output = output;
        addMouseListener( this );
        addMouseMotionListener( this );
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //        Register File
        if (e.getX() >= 366 && e.getX() <= 479 && e.getY() >= 307 && e.getY() <= 470) {
            RegisterFileGraphics registerFileGraphics = new RegisterFileGraphics( output );
        }
        //        Instruction Memory
        else if (e.getX() >= 140 && e.getX() <= 253 && e.getY() >= 307 && e.getY() <= 472) {
            System.out.println( "Instruction Memory" );
        }
        //        Data Memory
        else if (e.getX() >= 773 && e.getX() <= 886 && e.getY() >= 353 && e.getY() <= 520) {
            System.out.println( "Data Memory" );
        }
        //        IF_ID
        else if (e.getX() >= 297 && e.getX() <= 323 && e.getY() >= 91 && e.getY() <= 637) {
            IF_IDFrame if_idFrame=new IF_IDFrame( output );
        }
        //        ID_Ex
        else if (e.getX() >= 517 && e.getX() <= 542 && e.getY() >= 91 && e.getY() <= 637) {
            ID_EXFrame id_exFrame=new ID_EXFrame( output );
        }
        //        Ex_MEM
        else if (e.getX() >= 718 && e.getX() <= 742 && e.getY() >= 91 && e.getY() <= 637) {
            EX_MEMFrame ex_memFrame=new EX_MEMFrame( output );
        }
        //        MEM_WB
        else if (e.getX() >= 905 && e.getX() <= 929 && e.getY() >= 91 && e.getY() <= 637) {
           MEM_WBFrame mem_wbFrame=new MEM_WBFrame( output );
        }
        //        Info
        else if (e.getX() >= 15 && e.getX() <= 106 && e.getY() >= 493 && e.getY() <= 561) {
            InfoFrame infoFrame=new InfoFrame( output );
        }
        //        Quit
        else if (e.getX() >= 15 && e.getX() <= 106 && e.getY() >= 580 && e.getY() <= 648) {
            System.exit( 0 );
        }
        System.out.println( e.getX() + " , " + e.getY() );
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (e.getX() >= 366 && e.getX() <= 479 && e.getY() >= 307 && e.getY() <= 470) {
            setCursor( new Cursor( Cursor.HAND_CURSOR ) );
        }
        //        Instruction Memory
        else if (e.getX() >= 140 && e.getX() <= 253 && e.getY() >= 307 && e.getY() <= 472) {
            setCursor( new Cursor( Cursor.HAND_CURSOR ) );
        }
        //        Data Memory
        else if (e.getX() >= 773 && e.getX() <= 886 && e.getY() >= 353 && e.getY() <= 520) {
            setCursor( new Cursor( Cursor.HAND_CURSOR ) );
        }
        //        IF_ID
        else if (e.getX() >= 297 && e.getX() <= 323 && e.getY() >= 91 && e.getY() <= 637) {
            setCursor( new Cursor( Cursor.HAND_CURSOR ) );
        }
        //        ID_Ex
        else if (e.getX() >= 517 && e.getX() <= 542 && e.getY() >= 91 && e.getY() <= 637) {
            setCursor( new Cursor( Cursor.HAND_CURSOR ) );
        }
        //        Ex_MEM
        else if (e.getX() >= 718 && e.getX() <= 742 && e.getY() >= 91 && e.getY() <= 637) {
            setCursor( new Cursor( Cursor.HAND_CURSOR ) );
        }
        //        MEM_WB
        else if (e.getX() >= 905 && e.getX() <= 929 && e.getY() >= 91 && e.getY() <= 637) {
            setCursor( new Cursor( Cursor.HAND_CURSOR ) );
        }
        //        Info
        else if (e.getX() >= 15 && e.getX() <= 106 && e.getY() >= 493 && e.getY() <= 561) {
            setCursor( new Cursor( Cursor.HAND_CURSOR ) );
        }
        //        Quit
        else if (e.getX() >= 15 && e.getX() <= 106 && e.getY() >= 580 && e.getY() <= 648) {
            setCursor( new Cursor( Cursor.HAND_CURSOR ) );
        }
        else {
            setCursor( new Cursor( Cursor.DEFAULT_CURSOR ) );
        }
    }
}

