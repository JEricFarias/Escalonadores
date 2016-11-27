package com.steppersimulator.teste;


import java.util.ArrayList;

import com.steppersimulator.escalonadores.shortestremainingtimenext.ShortestRemainingTimeNext;
import com.steppersimulator.model.Processo;
import com.steppersimulator.model.TimeSlice;

public class SRTNTest {

	public static void main(String[] args){
		ShortestRemainingTimeNext srtn = new ShortestRemainingTimeNext();
		ArrayList<Processo> p = new ArrayList<>();
		p.add(new Processo(0, 0, 10));
		p.add(new Processo(0, 3, 5));
		p.add(new Processo(0, 15, 8));
		p.add(new Processo(0, 20, 3));
		
		ArrayList<TimeSlice> timeSlices = srtn.escalonar(p, 2, 2);
		
		for(TimeSlice ts: timeSlices){
			System.out.println(ts);
		}
	}
}
