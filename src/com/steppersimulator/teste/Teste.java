package com.steppersimulator.teste;

import java.util.ArrayList;

import com.steppersimulator.escalonadores.roundrobin.RoundRobin;
import com.steppersimulator.model.Processo;
import com.steppersimulator.model.TimeSlice;

public class Teste {
	public static void main(String[] args) throws CloneNotSupportedException{
		ArrayList<Processo> processos = new ArrayList<>();

		RoundRobin rr = new RoundRobin();
		
		/*processos.add(new Processo(0, 0, 5));
		processos.add(new Processo(0, 3, 5));*/
		processos.add(new Processo(0, 0, 5));
		processos.add(new Processo(0, 3, 7));
		processos.add(new Processo(0, 4, 6));
		processos.add(new Processo(0, 20, 8));
		
		ArrayList<TimeSlice> ts = rr.escalonar(processos, 3, 2);
		for(TimeSlice t : ts){
			System.out.println(t);
		}
		System.out.println(rr.getTempoTotal());
		
	}
}
