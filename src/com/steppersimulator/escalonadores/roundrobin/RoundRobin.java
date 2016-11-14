package com.steppersimulator.escalonadores.roundrobin;

import java.util.ArrayList;
import java.util.LinkedList;

import com.steppersimulator.escalonadores.Escalonador;
import com.steppersimulator.model.Processo;
import com.steppersimulator.model.TimeSlice;

public class RoundRobin implements Escalonador{

	// Atributos relacionados ao padrão de projeto State
	private Cheio cheio;
	private Vazio vazio;
	private State estadoAtual;
	
	// Atributos da classe
	private LinkedList<Processo> processos;
	private LinkedList<Processo> fila;
	private ArrayList<TimeSlice> timeSlicesProcessados;
	private Processo processodaVez;
	private int tempodeTroca;
	private int timeSlice;
	private int tempoTotal;
	
	public RoundRobin() {
		// iniciação padão State
		cheio = new Cheio(this);
		vazio = new Vazio(this);
		estadoAtual = vazio;
		
		// iniciação da classe
		this.processos = new LinkedList<>();
		this.fila = new LinkedList<>();
		this.timeSlicesProcessados = new ArrayList<>();
	}
	
	@Override
	public ArrayList<TimeSlice> escalonar(ArrayList<Processo> processos, int timeSlice, int tempodeTroca) {
		clonarProcessos(processos);
		ordenarProcessos();
		this.timeSlice = timeSlice;
		this.tempodeTroca = tempodeTroca;
		
		// no lugar desse while tinha um if
		while(!this.processos.isEmpty()){
			this.processodaVez = this.getPorcessos().removeFirst();
			this.tempoTotal = processodaVez.getTempoDeChegada();
			
			while(processodaVez.getTempoDeExeculcao() > 0 || !fila.isEmpty()){			
				executarProcesso();
				verificarChegadadeProcesso();
				troca();
			}
		}
		return timeSlicesProcessados;
	}
	
	
	private void troca(){
		this.estadoAtual.troca();
	}
	
	private void verificarChegadadeProcesso(){
		this.estadoAtual.verificarChegadadeProcesso();
	}
	
	private void executarProcesso(){
		this.estadoAtual.executarProcesso();
	}
	
	private void clonarProcessos(ArrayList<Processo> processos){
		for(Processo p: processos){
			try {
				this.processos.add(p.clone());
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void ordenarProcessos(){
		for(int i = 1; i < processos.size(); i++){
			int w = i;
			for(int j = i - 1; j >= 0; j--){
				Processo processoTemp = null;
				if(processos.get(w).getTempoDeChegada() < processos.get(j).getTempoDeChegada()){
					processoTemp = processos.get(w);
					processos.set(w, processos.get(j));
					processos.set(j, processoTemp);
					w--;
				}
			}
		}
	}

	
	/*
	 * 
	 * Getters and Setters
	 * 
	 */
	
	/**
	 * @return the cheio
	 */
	public Cheio getCheio() {
		return cheio;
	}

	/**
	 * @param cheio the cheio to set
	 */
	public void setCheio(Cheio cheio) {
		this.cheio = cheio;
	}

	/**
	 * @return the vazio
	 */
	public Vazio getVazio() {
		return vazio;
	}

	/**
	 * @param vazio the vazio to set
	 */
	public void setVazio(Vazio vazio) {
		this.vazio = vazio;
	}

	/**
	 * @return the estadoAtual
	 */
	public State getEstadoAtual() {
		return estadoAtual;
	}

	/**
	 * @param estadoAtual the estadoAtual to set
	 */
	public void setEstadoAtual(State estadoAtual) {
		this.estadoAtual = estadoAtual;
	}

	/**
	 * @return the porcessos
	 */
	public LinkedList<Processo> getPorcessos() {
		return processos;
	}

	/**
	 * @param porcessos the porcessos to set
	 */
	public void setPorcessos(LinkedList<Processo> porcessos) {
		this.processos = porcessos;
	}

	/**
	 * @return the fila
	 */
	public LinkedList<Processo> getFila() {
		return fila;
	}

	/**
	 * @param fila the fila to set
	 */
	public void setFila(LinkedList<Processo> fila) {
		this.fila = fila;
	}

	/**
	 * @return the timeSlicesProcessados
	 */
	public ArrayList<TimeSlice> getTimeSlicesProcessados() {
		return timeSlicesProcessados;
	}

	/**
	 * @param timeSlicesProcessados the timeSlicesProcessados to set
	 */
	public void setTimeSlicesProcessados(ArrayList<TimeSlice> timeSlicesProcessados) {
		this.timeSlicesProcessados = timeSlicesProcessados;
	}

	/**
	 * @return the processodaVez
	 */
	public Processo getProcessodaVez() {
		return processodaVez;
	}

	/**
	 * @param processodaVez the processodaVez to set
	 */
	public void setProcessodaVez(Processo processodaVez) {
		this.processodaVez = processodaVez;
	}

	/**
	 * @return the tempodeTroca
	 */
	public int getTempodeTroca() {
		return tempodeTroca;
	}

	/**
	 * @param tempodeTroca the tempodeTroca to set
	 */
	public void setTempodeTroca(int tempodeTroca) {
		this.tempodeTroca = tempodeTroca;
	}

	/**
	 * @return the timeSlice
	 */
	public int getTimeSlice() {
		return timeSlice;
	}

	/**
	 * @param timeSlice the timeSlice to set
	 */
	public void setTimeSlice(int timeSlice) {
		this.timeSlice = timeSlice;
	}

	/**
	 * @return the tempoTotal
	 */
	public int getTempoTotal() {
		return tempoTotal;
	}

	/**
	 * @param tempoTotal the tempoTotal to set
	 */
	public void setTempoTotal(int tempoTotal) {
		this.tempoTotal = tempoTotal;
	}

}