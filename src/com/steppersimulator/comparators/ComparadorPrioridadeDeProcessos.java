package com.steppersimulator.comparators;

import java.util.Comparator;

import com.steppersimulator.model.Processo;

public class ComparadorPrioridadeDeProcessos implements Comparator<Processo>{

	@Override
	public int compare(Processo arg0, Processo arg1) {
		/*
		 * Retorno invertido, por que o processo com maior prioridade deve ficar na frente.
		 * */
		if(arg0.getPrioridade() < arg1.getPrioridade()){
			return +1;
		}else if(arg0.getPrioridade() > arg1.getPrioridade()){
			return -1;
		}
		return 0;
	}

}
