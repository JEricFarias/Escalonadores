package com.steppersimulator.model;

public class Processo implements Cloneable{
	private int id;
	private int prioridade;
	private int tempoDeChegada;
	private int tempoDeExeculcao;
	private static int count;
	
	public Processo (int prioridade, int tempoDeChegada, int tempoDeExeculcao){
		count++;
		this.id = count;
		this.prioridade = prioridade;
		this.tempoDeChegada = tempoDeChegada;
		this.tempoDeExeculcao = tempoDeExeculcao;
	}
	
	@Override
	public Processo clone() throws CloneNotSupportedException{
		return (Processo) super.clone();
	}
	
	@Override
	public String toString(){
		return String.format("Id(%d), Prioridade(%d), TempodeChegada(%d), tempodeExecução(%d)", getId(), getPrioridade(), getTempoDeChegada(), getTempoDeExeculcao());
	}
	
	@Override
	public boolean equals(Object arg0) {
		if(!super.equals(arg0)) return false;
		
		Processo other = (Processo) arg0;
		
		return this.id == other.getId();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPrioridade() {
		return prioridade;
	}
	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}
	public int getTempoDeChegada() {
		return tempoDeChegada;
	}
	public void setTempoDeChegada(int tempoDeChegada) {
		this.tempoDeChegada = tempoDeChegada;
	}
	public int getTempoDeExeculcao() {
		return tempoDeExeculcao;
	}
	public void setTempoDeExeculcao(int tempoDeExeculcao) {
		this.tempoDeExeculcao = tempoDeExeculcao;
	}

}
