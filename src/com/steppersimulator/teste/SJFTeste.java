package com.steppersimulator.teste;

import java.util.ArrayList;

import com.steppersimulator.escalonadores.shortestjobfirst.ShortestJobFirst;
import com.steppersimulator.model.Processo;
import com.steppersimulator.model.TimeSlice;

public class SJFTeste {
	public static void main (String[] args){
		ShortestJobFirst sjf = new ShortestJobFirst();
		ArrayList<Processo> p = new ArrayList<>();
		
		p.add(new Processo(0, 3, 3));
		p.add(new Processo(0, 6, 9));
		p.add(new Processo(0, 6, 8));
		p.add(new Processo(0, 6, 7));
		p.add(new Processo(0, 6, 6));
		p.add(new Processo(0, 6, 5));
		p.add(new Processo(0, 70, 4));
		
		ArrayList<TimeSlice> timeSlices = sjf.escalonar(p, 2, 2);
		
		for(TimeSlice ts: timeSlices){
			System.out.println(ts);
		}
		System.out.println("Tempo total " + sjf.getTempoTotal());
		
	}
}
