package memory;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Memory {
    int A[][];
    String karta1;
    String karta2;
    JButton knopka1=null;
    JButton knopka2=null;
    boolean k = false;
    int z=0;
Memory() {
 JFrame mainFrame = new JFrame("Память");      
        mainFrame.setSize(600, 600);
        mainFrame.addWindowListener(new WindowAdapter() {   
        @Override
        public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        mainFrame.setLayout(new GridLayout(4, 4));
        mainFrame.getContentPane().setLayout(new GridLayout(4, 4));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //сортировка позиции картинок
        A = new int[16][2];
        int pos = 0;
        boolean f = false;
        int y = 0;
        while (pos != 16) {
            double q = Math.random() * 800;
            int c = (int) q;
            c = c / 100;
            for (int i = 0; i < pos; i = i + 2) {
                if (A[i][0] == c) { f = true; }
            }
            if (f == false) {
                A[pos][0] = c;
                A[pos + 1][0] = c;
                pos = pos + 2;
            }
            f = false;
        }
        f = false;
        pos = 0;
        while (pos != 16) {
            double q = Math.random() * 16;
            int c = (int) q;
            for (int i = 0; i < pos; i++) {
                if (A[i][1] == c) {
                    f = true;
                }
            }
            if (f == false) { A[pos][1] = c; pos++;}
            f = false;
        }
        for (int i = 0; i < 16; i++) { System.out.println(A[i][0] + " " + A[i][1]);  }
        
        JButton A_botton[] = new JButton[16];
        final Map<JButton, ImageIconn> buttonIcons = new HashMap();
        for (int i = 0; i <= 15; i++) {
            ImageIcon icon = createImageIcon("1obloshka.png", "Java");
            A_botton[A[i][1]] = new JButton("", icon);
            ImageIconn iconn = new ImageIconn(createImageIcon("" + A[i][0] + ".png","123"), A[i][0] + "");
            buttonIcons.put(A_botton[A[i][1]], iconn);
            A_botton[A[i][1]].addActionListener(new ActionListener() {
                             
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton button = (JButton) e.getSource();
                    button.setIcon(buttonIcons.get(button).icon);
                    if (!k){
                        k = true;
                        knopka1 = button;
                        karta1 = buttonIcons.get(button).name;
                     //   System.out.println("1-----1");
                    } 
                    else {
                        k = true;
                       if(knopka2==null){
                        knopka2 = button;
                        karta2 = buttonIcons.get(button).name;
                       }
                        if(knopka1!=button && knopka2!=button )
                        if (karta1.equals(karta2) && knopka1 != knopka2) {
                            knopka2.setIcon(null);
                            knopka1.setIcon(null);
                            knopka2.setEnabled(false); //исчезли картинки
                            knopka1.setEnabled(false); //исчезли картинки
                            z++;
                            System.out.println("else1");
                            knopka1 = button;
                            karta1=buttonIcons.get(button).name;
                            k = true;
                            knopka2=null;
                        } 
                        else {
                            System.out.println("else2");
                            ImageIcon icon = createImageIcon("1obloshka.png", "Java");
                            k = true;
                            
                            knopka2.setIcon(icon);
                            knopka1.setIcon(icon);
                            knopka1 = button;
                            karta1 = buttonIcons.get(button).name;
                            knopka2= null;
                            //  System.out.println(" 2-----2");
                        }
                    }
                    if(z==7){
                            knopka2.setIcon(null);
                            knopka1.setIcon(null);
                            knopka2.setEnabled(false); //исчезли картинки
                            knopka1.setEnabled(false); //исчезли картинки
                          }
                }
            });
        }
       
        for (int i = 0; i <= 15; i++) {
            mainFrame.getContentPane().add(A_botton[i]);
        }        
            mainFrame.setVisible(true);  
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Memory Memory = new Memory();
            }
        });
    }
    private static ImageIcon createImageIcon(String path,
            String description) {
        java.net.URL imgURL = Memory.class.getResource(path);
        if (imgURL != null) {return new ImageIcon(imgURL, description);} 
        else {
            System.err.println("Couldn't find file: " + path);
            return null;}
    }
}