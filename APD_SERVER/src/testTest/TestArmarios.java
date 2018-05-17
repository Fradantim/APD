package testTest;

import java.util.List;

public class TestArmarios {

	public static void main (String args[]) {
		Armario arm = new Armario(0, "ArmarioA");
		arm.guardar();
		
		Estante estante1 = new Estante(0, "Estante1");
		estante1.guardar();
		arm.getEstantes().add(estante1);
		System.out.println(arm.getId());
		arm.guardar();
		
		System.out.println("FIN");
	}
}
