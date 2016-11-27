package com.steppersimulator.escalonadores.roundrobin.teste;

import static org.junit.Assert.*;
import java.util.ArrayList;

import org.junit.Test;

import com.steppersimulator.escalonadores.roundrobin.RoundRobin;
import com.steppersimulator.model.Processo;
import com.steppersimulator.model.TimeSlice;

public class RRteste {

	@Test
	public void escalonamentoDeProssoUnico() {
		RoundRobin roundRobin = new RoundRobin();
		
		ArrayList<Processo> processos = new ArrayList<>();
		processos.add(new Processo(0, 8, 9));
		
		ArrayList<TimeSlice> ts = roundRobin.escalonar(processos, 3, 2);
		
		assertEquals(17, roundRobin.getTempoTotal());
		assertEquals(9, ts.get(0).getTime());
		assertEquals(1, ts.size());	
	}

}
