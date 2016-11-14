package com.steppersimulator.teste;

import java.util.ArrayList;

import com.steppersimulator.escalonadores.roundrobin.RoundRobin;
import com.steppersimulator.model.Processo;
import com.steppersimulator.model.TimeSlice;

public class FinalTeste {
	public static void main(String[] args){
		ArrayList<Processo> processos = new ArrayList<>();
		processos.add(new Processo(0, 8, 7));
		processos.add(new Processo(0, 15, 3));
		processos.add(new Processo(0, 30, 6));
		processos.add(new Processo(0, 32, 4));
		
		RoundRobin rr = new RoundRobin();
		
		ArrayList<TimeSlice> timeSlice = rr.escalonar(processos, 3, 2);
		
		for(TimeSlice ts: timeSlice){
			System.out.println(ts);
		}
		
		System.out.println("Tempo Total: " + rr.getTempoTotal());
	}
}
