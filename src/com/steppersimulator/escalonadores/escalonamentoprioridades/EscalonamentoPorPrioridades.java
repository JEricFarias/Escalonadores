package com.steppersimulator.escalonadores.escalonamentoprioridades;

import java.util.ArrayList;

import com.steppersimulator.comparators.ComparadorChegadaDePorcessos;
import com.steppersimulator.comparators.ComparadorPrioridadeDeProcessos;
import com.steppersimulator.escalonadores.Escalonador;
import com.steppersimulator.model.Processo;
import com.steppersimulator.model.TimeSlice;


public class EscalonamentoPorPrioridades implements Escalonador{

	private static int UNIDADE_DE_TEMPO = 1;
	private ArrayList<Processo> processos = new ArrayList<>();
	private ArrayList<Processo> fila = new ArrayList<>();
	private ArrayList<TimeSlice> timeSlices = new ArrayList<>();
	private Processo processoDaVez;
	private int timeSlice;
	private int tempodeTroca;
	private int tempoTotal;
	
	@Override
	public ArrayList<TimeSlice> escalonar(ArrayList<Processo> processos, int timeSlice, int tempodeTroca) {
		clonarProcessos(processos);
		ordenarChegada();
		this.timeSlice = timeSlice;
		this.tempodeTroca = tempodeTroca;
				
		while(!this.processos.isEmpty()){
			this.processoDaVez = this.processos.remove(0);
			this.tempoTotal = this.processoDaVez.getTempoDeChegada();
			verificarChegadaPorcesso();
			while(this.processoDaVez.getTempoDeExeculcao() > 0 || !this.fila.isEmpty()){				
				if(this.processoDaVez.getTempoDeExeculcao() > 0){
					executar();
					verificarChegadaPorcesso();
				}else{
					trocar();
				}
			}
		}
		return this.timeSlices;
	}
	
	private void executar(){
		processoDaVez.setTempoDeExeculcao(processoDaVez.getTempoDeExeculcao() - UNIDADE_DE_TEMPO);
		tempoTotal += UNIDADE_DE_TEMPO;
			
		// Pega o ultimo timeslicePorcessado
		TimeSlice ultimoProcessado = null;
		if(!timeSlices.isEmpty()){
			ultimoProcessado = timeSlices.get(timeSlices.size() - 1);
		}
	
		if(ultimoProcessado != null && processoDaVez.equals(ultimoProcessado.getProcessso())){
			ultimoProcessado.setTime(ultimoProcessado.getTime() + UNIDADE_DE_TEMPO);
		}else{
			TimeSlice ts = new TimeSlice();
			ts.setProcessso(processoDaVez);
			ts.setTime(UNIDADE_DE_TEMPO);
			ts.setInicioDaExecucao(this.tempoTotal);
			this.timeSlices.add(ts);
		}
	}
	
	private void verificarChegadaPorcesso(){
		boolean chegoProcessos = false;
		
		while(!this.processos.isEmpty() && this.processos.get(0).getTempoDeChegada() <= tempoTotal){
			if(this.processos.get(0).getPrioridade() > this.processoDaVez.getPrioridade()){
				this.fila.add(this.processoDaVez);
				this.processoDaVez = this.processos.remove(0);
				this.tempoTotal += this.tempodeTroca;
			}else{
				this.fila.add(this.processos.remove(0));
			}
			chegoProcessos = true; 
		}
		if(chegoProcessos){
			ordenarPrioridades();
		}		
	}
	
	// terminar esse método
	private void trocar(){
		this.processoDaVez = this.fila.remove(0);
		this.tempoTotal += this.tempodeTroca;
		/*boolean teveTroca = false;
		if(!fila.isEmpty() && fila.get(0).getPrioridade() > processoDaVez.getPrioridade()){
			fila.add(processoDaVez);
			processoDaVez = fila.remove(0);
			tempoTotal += tempodeTroca;
			
			teveTroca = true;
		}
		
		if(teveTroca){
			ordenarPrioridades();
		}*/
	}
	
	private void clonarProcessos(ArrayList<Processo> processos){
		for(Processo p: processos){
			this.processos.add(p.clone());
		}
	}
	
	private void ordenarChegada(){
		this.processos.sort(new ComparadorChegadaDePorcessos());
	}
	
	private void ordenarPrioridades(){
		this.fila.sort(new ComparadorPrioridadeDeProcessos());
	}
}
