package kr.iamghost.sugarcube.translationtool;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fc.setDialogTitle("Select Sugar Cube: Bittersweet Factory folder");
		int returnVal = fc.showOpenDialog(null);
	    
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			String path = fc.getSelectedFile().getAbsolutePath();
			if (new File(path + "/SugarCube_BF.exe").isFile()) {
				LuaEngine engine = new LuaEngine();
				engine.doFile("scripts/main.lua", path);
			}
			else {
				JOptionPane.showMessageDialog(null,
						"Cannot find Sugar Cube Installation from: " + path,
						"Oops!",
						JOptionPane.ERROR_MESSAGE);
			}
			JOptionPane.showMessageDialog(null, "Done!");
		}
	}
}
