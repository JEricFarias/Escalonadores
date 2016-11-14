package com.steppersimulator.escalonadores.fifo;

import java.util.ArrayList;
import java.util.LinkedList;

import com.steppersimulator.escalonadores.Escalonador;
import com.steppersimulator.model.Processo;
import com.steppersimulator.model.TimeSlice;

public class Fifo implements Escalonador{

	private final int ZERAR = 0;
	private ArrayList<TimeSlice> timeSlicesProcessados;
	private LinkedList<Processo> processos;
	private int tempoTotal;
	private int tempodeTorca;
	
	public Fifo() {
		this.timeSlicesProcessados = new ArrayList<>();
		this.processos = new LinkedList<>();
	}
	
	@Override
	public ArrayList<TimeSlice> escalonar(ArrayList<Processo> processos, int timeSlice, int tempodeTroca) {
		clonarProcessos(processos);
		ordenarProcessos();
		this.tempodeTorca = tempodeTroca;
		this.tempoTotal += processos.get(0).getTempoDeChegada();
		while(!this.processos.isEmpty()){
			executar();
			trocar();
		}
		
		return this.timeSlicesProcessados;
	}
	
	private void trocar(){
		if(processos.getFirst().getTempoDeChegada() > tempoTotal + tempodeTorca){
			tempoTotal = processos.getFirst().getTempoDeChegada(); 
		}else{
			this.tempoTotal += this.tempodeTorca;
		}		
	}
	
	private void executar(){
		TimeSlice ts = new TimeSlice();
		Processo processo = this.processos.removeFirst();
		this.tempoTotal += processo.getTempoDeExeculcao();
		ts.setProcessso(processo);
		ts.setTime(processo.getTempoDeExeculcao());
		processo.setTempoDeExeculcao(ZERAR);
		this.timeSlicesProcessados.add(ts);
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
	

	public int getTempodeTroca(){
		return this.tempodeTorca;
	}
	
	public int getTempoTotal(){
		return this.tempoTotal;
	}
}
