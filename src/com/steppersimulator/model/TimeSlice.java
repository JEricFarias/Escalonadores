package com.steppersimulator.model;

public class TimeSlice {
	private Processo processso;
	private int time;
	private int incioDaExecucao;
	
	@Override
	public String toString(){
		return String.format("Processo %d entrou em %d executou %d ut", this.processso.getId(),this.incioDaExecucao, this.time);
	}

	public Processo getProcessso() {
		return processso;
	}

	public void setProcessso(Processo processso) {
		this.processso = processso;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	public void setInicioDaExecucao(int inicioDaExecucao){
		this.incioDaExecucao = inicioDaExecucao;
	}
	
	public int getInicioDaExecucao(){
		return this.incioDaExecucao;
	}

}
