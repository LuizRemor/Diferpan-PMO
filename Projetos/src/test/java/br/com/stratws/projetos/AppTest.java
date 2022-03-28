package br.com.stratws.projetos;

import java.awt.Dimension;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AppTest extends Panel {

	private static final long serialVersionUID = 1L;
	JTextField txtWay = new JTextField(10);;
	JButton importar = new JButton("TESTE");
	
	/**
	 * Método construtor
	 *
	 */
	public AppTest(){
		
            importar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser(System
                        .getProperty("user.dir"));
                fc.setMultiSelectionEnabled(false);
                javax.swing.filechooser.FileFilter filter = new javax.swing.filechooser.FileFilter() {
                    public boolean accept(File f) {
                        return f.isDirectory()
                                || f.getName().toLowerCase().endsWith(
                                        ".txt");
                    }

                    public String getDescription() {
                        return "(*.txt)"; 
                     // return "(*.xls)";
                    }
                };
                fc.addChoosableFileFilter(filter);
                int returnVal = fc.showDialog(null,
                        "Open");
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                	txtWay.setText(fc.getSelectedFile().getAbsolutePath());
          

                } else if (returnVal == JFileChooser.CANCEL_OPTION) {
                    return;

                } else if (returnVal == JFileChooser.UNDEFINED_CONDITION) {
                    return;
                }
            }
        });
		
                JPanel panel = new JPanel();
		txtWay.setEditable(true);
		panel.add(txtWay);
		panel.add(importar);
		JFrame frame = new JFrame();
		frame.getContentPane().add(panel);
		frame.setSize(new Dimension(300, 300));
		frame.setVisible(true);	
		
	}
	/**
	 * @param args
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		AppTest testePanel = new AppTest();
		testePanel.show();	

	}
}