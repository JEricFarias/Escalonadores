package com.steppersimulator.teste;

import com.steppersimulator.model.Processo;

public class TesteIgualdadePorcessos {
	public static void main(String [] args){
		Processo p1 = new Processo(1, 2, 5);
		Processo p2 = new Processo(1, 2, 5);
		
		p2 = p1;
		
		p2.setTempoDeExeculcao(p1.getTempoDeExeculcao() - 2);
		System.out.println(p1.equals(p2));
	}

}
