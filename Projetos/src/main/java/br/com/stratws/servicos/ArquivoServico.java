package br.com.stratws.servicos;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class ArquivoServico {

    public File escolheArquivo() {
    	
    	try {
    	       UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    	     } catch (Exception e) {
    	       e.printStackTrace();
    	     }
    	
        JFileChooser chooser = new JFileChooser();
        File f = null;/*from w  ww .j  a v  a  2  s .  c  o  m*/

        int returnVal = chooser.showOpenDialog(new JFrame(""));
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            f = chooser.getSelectedFile();
            //System.out.println("You chose to open this file: " + f.getAbsolutePath());
        }
        return f;
    }

}