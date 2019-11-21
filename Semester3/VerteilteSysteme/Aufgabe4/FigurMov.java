package Aufgabe4;

public class FigurMov extends Figur{
	
	public FigurMov(char x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void setPosition(char x, int y){
		this.x = x;
		MachMal.eineZehntelSekundeLangGarNichts();
		this.y = y;
	}
	
	public String getPosition(){
		String result = "" + x;
		MachMal.eineZehntelSekundeLangGarNichts();
		result += y;
		return result;
	}

}
