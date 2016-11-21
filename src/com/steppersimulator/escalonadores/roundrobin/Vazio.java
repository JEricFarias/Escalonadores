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
		//  executa o processo
		processo.setTempoDeExeculcao(processo.getTempoDeExeculcao() - timeSlice);
		roundRobin.setTempoTotal(roundRobin.getTempoTotal() + timeSlice);
		
		TimeSlice ultimoProcessado = null;
		// Pega o ultimo timeslicePorcessado
		if(!roundRobin.getTimeSlicesProcessados().isEmpty()){
			ultimoProcessado = roundRobin.getTimeSlicesProcessados().get(roundRobin.getTimeSlicesProcessados().size() - 1);
		}
		
		if(ultimoProcessado != null && processo.equals(ultimoProcessado.getProcessso())){
			ultimoProcessado.setTime(ultimoProcessado.getTime() + timeSlice);
		}else{
			TimeSlice ts = new TimeSlice();
			ts.setProcessso(processo);
			ts.setTime(timeSlice);
			roundRobin.getTimeSlicesProcessados().add(ts);
		}
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
