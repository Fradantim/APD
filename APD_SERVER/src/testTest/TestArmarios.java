package testTest;

import java.util.Random;

public class TestArmarios {

	public static void main (String args[]) {
		
		int minArmarios=3;
		int maxArmarios=5;
		
		int minEstantes=2;
		int maxEstantes=10;
		
		System.out.println("--------------");
		System.out.println("Carga en BD");
		
		for(int i1=0; i1<getRand(minArmarios, maxArmarios); i1++) {
			Armario arm = new Armario(0, "A_"+i1);
			arm.guardar();
			System.out.println(i1+">Guardado Armario: ("+arm.getId()+") "+arm.getDesc());
			for(int i2=0; i2<getRand(minEstantes, maxEstantes); i2++) {
				Estante est= new Estante(0, "E_"+i1+"_"+i2);
				arm.agregarEstante(est);
				System.out.println("\t"+i2+">Guardado Estante: ("+est.getId()+") "+est.getDescripcion());
			}
		}
		
		System.out.println("--------------");
		System.out.println("Recupero de BD");
		
		for(Armario arm: ArmarioDao.getInstance().getAll()) {
			System.out.println("Recuperado Armario: ("+arm.getId()+") "+arm.getDesc());
			for(Estante est: arm.getEstantes()) {
				System.out.println("\tRecuperado Estante: ("+est.getId()+") "+est.getDescripcion() +" Estante>Armario>id:"+est.getAmrario().getId());
			}
		}
		
		System.out.println("FIN");
	}
	
	private static int getRand(int min, int max) {
		return (new Random()).nextInt(max-min) + min;
	}
}
