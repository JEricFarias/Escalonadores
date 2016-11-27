package com.steppersimulator.escalonadores.fifo;

import java.util.ArrayList;
import java.util.LinkedList;

import com.steppersimulator.comparators.ComparadorChegadaDePorcessos;
import com.steppersimulator.escalonadores.Escalonador;
import com.steppersimulator.model.Processo;
import com.steppersimulator.model.TimeSlice;

public class Fifo implements Escalonador{

	private final int ZERAR = 0;
	private ArrayList<TimeSlice> timeSlicesProcessados;
	private LinkedList<Processo> processos;
	private int tempoTotal;
	private int tempodeTorca;
	private Processo processoDaVez;
	
	public Fifo() {
		this.timeSlicesProcessados = new ArrayList<>();
		this.processos = new LinkedList<>();
		this.tempoTotal = 0;
	}
	
	@Override
	public ArrayList<TimeSlice> escalonar(ArrayList<Processo> processos, int timeSlice, int tempodeTroca) {
		clonarProcessos(processos);
		ordena();
		
		this.tempodeTorca = tempodeTroca;
		while(!this.processos.isEmpty()){
			processoDaVez = this.processos.removeFirst();
			if(processoDaVez.getTempoDeChegada() > this.tempoTotal + this.tempodeTorca){
				this.tempoTotal = this.processoDaVez.getTempoDeChegada();
			}
			executar();
			trocar();
			
		}
		
		return this.timeSlicesProcessados;
	}
	
	private void trocar(){
		
		if(!this.processos.isEmpty()){
			this.tempoTotal += this.tempodeTorca;
		}
	}
	
	private void executar(){
		TimeSlice ts = new TimeSlice();
		ts.setInicioDaExecucao(this.tempoTotal);
		this.tempoTotal += processoDaVez.getTempoDeExeculcao();
		ts.setProcessso(processoDaVez);
		ts.setTime(processoDaVez.getTempoDeExeculcao());
		processoDaVez.setTempoDeExeculcao(ZERAR);
		this.timeSlicesProcessados.add(ts);
	}
	
	private void clonarProcessos(ArrayList<Processo> processos){
		for(Processo p: processos){
			this.processos.add(p.clone());
		}
	}
	
	private void ordena(){
		this.processos.sort(new ComparadorChegadaDePorcessos());
	}	

	/**
	 * @return the tempodeTorca
	 */
	public int getTempodeTorca() {
		return tempodeTorca;
	}
	
	public int getTempodeTroca(){
		return this.tempodeTorca;
	}
	
	public int getTempoTotal(){
		return this.tempoTotal;
	}
}
