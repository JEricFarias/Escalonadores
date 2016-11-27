package com.steppersimulator.escalonadores.roundrobin.teste;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.steppersimulator.escalonadores.roundrobin.RoundRobin;
import com.steppersimulator.model.Processo;

public class VerificaChegadaDepoisDeZeroTeste {

	@Test
	public void test() {
		RoundRobin rr = new RoundRobin();
		ArrayList<Processo> p = new ArrayList<>();
		p.add(new Processo(0, 25, 5));
		rr.escalonar(p, 3, 2);
		
		assertEquals(30, rr.getTempoTotal());
	}

}
