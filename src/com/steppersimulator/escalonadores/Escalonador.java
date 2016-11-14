package com.steppersimulator.escalonadores;

import java.util.ArrayList;

import com.steppersimulator.model.Processo;
import com.steppersimulator.model.TimeSlice;

public interface Escalonador {
	public ArrayList<TimeSlice> escalonar(ArrayList<Processo> processos,
			int timeSlice, int tempodeTroca);

}
