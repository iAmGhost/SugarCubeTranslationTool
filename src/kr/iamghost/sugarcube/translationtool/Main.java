package kr.iamghost.sugarcube.translationtool;

import java.io.File;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Sugar Cube Translation Tool by iAmGhost (http://iamghost.kr)");
		if (args.length != 1) {
			System.out.println("Drop SugarCube Bittersweet Factory folder to me!");
		}
		else {
			String path = args[0];
			if (new File(path + "/SugarCube_BF.exe").isFile()) {
				LuaEngine engine = new LuaEngine();
				engine.doFile("scripts/main.lua", path);
			}
			else {
				System.out.println("Cannot find Sugar Cube Installation from: " + path);
			}
		}
	}
}
