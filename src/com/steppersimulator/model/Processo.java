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
