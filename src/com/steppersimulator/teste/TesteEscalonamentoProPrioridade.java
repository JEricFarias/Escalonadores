package com.steppersimulator.teste;

import java.util.ArrayList;

import com.steppersimulator.escalonadores.escalonamentoprioridades.EscalonamentoPorPrioridades;
import com.steppersimulator.model.Processo;
import com.steppersimulator.model.TimeSlice;

public class TesteEscalonamentoProPrioridade {
	public static void main(String[] args){
		EscalonamentoPorPrioridades epp = new EscalonamentoPorPrioridades();
		System.out.println("Começa");
		ArrayList<Processo> processos = new ArrayList<>();
		processos.add(new Processo(2, 8, 9));
		processos.add(new Processo(3, 9, 2));
		processos.add(new Processo(5, 10, 3));
		processos.add(new Processo(6, 12, 8));
		
		
		ArrayList<TimeSlice> e = epp.escalonar(processos, 2, 2);
		System.out.println("terminou");
		for(TimeSlice p: e){
			System.out.println(p);
		}
		
		System.out.println("Tempo Total: " + epp.getTempoTotal() + " ut");
	}
}
