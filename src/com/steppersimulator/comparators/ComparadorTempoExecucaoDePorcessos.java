package com.steppersimulator.comparators;

import java.util.Comparator;

import com.steppersimulator.model.Processo;

public class ComparadorTempoExecucaoDePorcessos implements Comparator<Processo>{

	@Override
	public int compare(Processo arg0, Processo arg1) {
		if(arg0.getTempoDeExeculcao() < arg1.getTempoDeExeculcao()){
			return -1;
		}else if(arg0.getTempoDeExeculcao() > arg1.getTempoDeExeculcao()){
			return +1;
		}
		return 0;
	}
	
}
