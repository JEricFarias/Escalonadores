package com.steppersimulator.escalonadores.roundrobin.teste;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.steppersimulator.escalonadores.roundrobin.RoundRobin;
import com.steppersimulator.model.Processo;
import com.steppersimulator.model.TimeSlice;

public class ProcessosMenoresQueTimeSliceTeste {

	@Test
	public void test() {
		RoundRobin rr = new  RoundRobin();
		
		ArrayList<Processo> p = new ArrayList<>();
		p.add(new Processo(0, 0, 1));
		p.add(new Processo(0, 0, 1));
		p.add(new Processo(0, 0, 1));
		
		ArrayList<TimeSlice> timeSlices =  rr.escalonar(p, 3, 2);
		
		for(TimeSlice ts:timeSlices){
			System.out.println(ts);
		}
		assertEquals(3, timeSlices.size());
		assertEquals(7, rr.getTempoTotal());
	}

}
