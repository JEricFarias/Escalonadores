package com.steppersimulator.escalonadores.roundrobin;

import java.util.LinkedList;

import com.steppersimulator.model.Processo;
import com.steppersimulator.model.TimeSlice;

class Vazio implements State{

	private RoundRobin roundRobin;
	
	public Vazio(RoundRobin roundRobin) {
		this.roundRobin = roundRobin;
	}
	
	@Override
	public void troca() {
		// faz nada
	}

	@Override
	public void executarProcesso() {
		if(roundRobin.getProcessodaVez().getTempoDeExeculcao() > roundRobin.getTimeSlice()){
			execultarProcesso(roundRobin.getProcessodaVez(), roundRobin.getTimeSlice());
		}else{
			execultarProcesso(roundRobin.getProcessodaVez(), roundRobin.getProcessodaVez().getTempoDeExeculcao());
		}
	}
	
	private void execultarProcesso(Processo processo, int timeSlice){
		// se processo == timeSlice.getProcesso setTime(getTimr() + timeSlice) 
		TimeSlice ts = new TimeSlice();
		processo.setTempoDeExeculcao(processo.getTempoDeExeculcao() - timeSlice);
		roundRobin.setTempoTotal(roundRobin.getTempoTotal() + timeSlice);
		ts.setProcessso(processo);
		ts.setTime(timeSlice);
		roundRobin.getTimeSlicesProcessados().add(ts);
	}

	@Override
	public void verificarChegadadeProcesso() {
		LinkedList<Processo> processos = roundRobin.getPorcessos();
		
		while(!processos.isEmpty() && processos.getFirst().getTempoDeChegada() < roundRobin.getTempoTotal()){
			roundRobin.getFila().add(processos.removeFirst());
		}
		
		if(!roundRobin.getFila().isEmpty()){
			roundRobin.setEstadoAtual(roundRobin.getCheio());
		}else{
			if(!processos.isEmpty() && processos.getFirst().getTempoDeChegada() == roundRobin.getTempoTotal()){
				roundRobin.getFila().add(processos.removeFirst());
				roundRobin.setEstadoAtual(roundRobin.getCheio());
			}
		}
	}
}
