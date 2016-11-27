package com.steppersimulator.escalonadores.roundrobin.teste;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.steppersimulator.escalonadores.roundrobin.RoundRobin;
import com.steppersimulator.model.Processo;
import com.steppersimulator.model.TimeSlice;

public class ExecutaMaisDeUnTimeSliceTeste {

	@Test
	public void test() {
		RoundRobin rr = new RoundRobin();
		ArrayList<Processo> p = new ArrayList<>();
		p.add(new Processo(0, 0, 9));
		p.add(new Processo(0, 9, 9));
		
		ArrayList<TimeSlice> timeSlice = rr.escalonar(p, 3, 2);
		
		for(TimeSlice ts : timeSlice){
			System.out.println(ts);
		}
		
		assertEquals(2, timeSlice.size());
		assertEquals(20, rr.getTempoTotal());
	}

}
