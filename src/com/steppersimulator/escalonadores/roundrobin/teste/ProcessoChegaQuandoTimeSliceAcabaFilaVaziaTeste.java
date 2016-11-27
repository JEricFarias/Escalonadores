package com.steppersimulator.escalonadores.roundrobin.teste;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.steppersimulator.escalonadores.roundrobin.RoundRobin;
import com.steppersimulator.model.Processo;
import com.steppersimulator.model.TimeSlice;

public class ProcessoChegaQuandoTimeSliceAcabaFilaVaziaTeste {

	@Test
	public void test() {
RoundRobin rr = new  RoundRobin();
		
		ArrayList<Processo> p = new ArrayList<>();
		p.add(new Processo(0, 2, 6));
		p.add(new Processo(0, 8, 2));
		
		ArrayList<TimeSlice> timeSlices =  rr.escalonar(p, 3, 2);
		
		for(TimeSlice ts:timeSlices){
			System.out.println(ts);
		}
		assertEquals(2, timeSlices.size());
		assertEquals(12, rr.getTempoTotal());
	}

}
