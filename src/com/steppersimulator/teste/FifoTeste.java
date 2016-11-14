package com.steppersimulator.teste;

import java.util.ArrayList;

import com.steppersimulator.escalonadores.fifo.Fifo;
import com.steppersimulator.model.Processo;
import com.steppersimulator.model.TimeSlice;

public class FifoTeste {
	public static void main(String[] args){
		Fifo fifo = new Fifo();
		ArrayList<Processo> processos = new ArrayList<>();
		processos.add(new Processo(0, 0, 8));
		processos.add(new Processo(0, 1, 3));
		processos.add(new Processo(0, 4, 4));
		processos.add(new Processo(0, 4, 6));
		ArrayList<TimeSlice> timeSlice = fifo.escalonar(processos, 3, 3);
		
		for(TimeSlice ts:timeSlice){
			System.out.println(ts);
		}
	}
}
