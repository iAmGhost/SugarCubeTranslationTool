package kr.iamghost.sugarcube.translationtool;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

import org.luaj.vm2.script.LuaScriptEngineFactory;

public class LuaEngine {
	private ScriptEngine engine;
	
	public LuaEngine() {
		engine = new LuaScriptEngineFactory().getScriptEngine();
	}
	
	public void doFile(String filePath, String sugarCubePath) {
		try {
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(new FileInputStream(filePath), "UTF8"));

			StringBuffer buffer = new StringBuffer();
			String line;
			
			while ((line = in.readLine()) != null) {
				buffer.append(line + "\n");
			}

			System.out.println(buffer.toString());
			doString(buffer.toString(), sugarCubePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void doString(String script, String sugarCubePath) {
		try {
			CompiledScript cs = ((Compilable)engine).compile(script);
			Bindings b = engine.createBindings();
			b.put("imageeditor", new ImageEditor());
			b.put("fileutil", new FileUtil());
			b.put("sugarCubePath", sugarCubePath);
			b.put("engine", this);
			cs.eval(b);
		} catch (ScriptException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
