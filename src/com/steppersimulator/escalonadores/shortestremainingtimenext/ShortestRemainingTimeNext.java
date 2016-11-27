package com.steppersimulator.escalonadores.shortestremainingtimenext;

import java.util.ArrayList;

import com.steppersimulator.comparators.ComparadorChegadaDePorcessos;
import com.steppersimulator.comparators.ComparadorTempoExecucaoDePorcessos;
import com.steppersimulator.escalonadores.Escalonador;
import com.steppersimulator.model.Processo;
import com.steppersimulator.model.TimeSlice;

public class ShortestRemainingTimeNext implements Escalonador{

	private ArrayList<Processo> processos;
	private ArrayList<Processo> fila;
	private ArrayList<TimeSlice> timeSlicesProcessados;
	private Processo processoDaVez;
	private int tempoTotal;
	private int tempoDeTroca;
	
	public ShortestRemainingTimeNext() {
		this.processos = new ArrayList<>();
		this.fila = new ArrayList<>();
		this.timeSlicesProcessados = new ArrayList<>();
	}
	
	@Override
	public ArrayList<TimeSlice> escalonar(ArrayList<Processo> processos, int timeSlice, int tempodeTroca) {
		this.tempoDeTroca = tempodeTroca;
		clonarProcessos(processos);
		ordenaProcessosChegada();
		
		while(!this.processos.isEmpty()){
			this.processoDaVez = this.processos.remove(0);
			this.tempoTotal = this.processoDaVez.getTempoDeChegada();
			verificarChegadaDeProcessos();
			while(!fila.isEmpty() || processoDaVez.getTempoDeExeculcao() > 0){
				if(processoDaVez.getTempoDeExeculcao() > 0){
					executar();
					verificarChegadaDeProcessos();
				}else{
					trocar();
				}
			}
		}
		return this.timeSlicesProcessados;
	}
	
	private void executar(){
		TimeSlice ultimoExecutado = null;
		if(!timeSlicesProcessados.isEmpty()){
			ultimoExecutado = timeSlicesProcessados.get(timeSlicesProcessados.size() - 1);
		}
		
		if(ultimoExecutado != null && ultimoExecutado.getProcessso().equals(processoDaVez)){
			ultimoExecutado.setTime(ultimoExecutado.getTime() + 1);
			processoDaVez.setTempoDeExeculcao(processoDaVez.getTempoDeExeculcao() - 1);
		}else{
			TimeSlice ts = new TimeSlice();
			ts.setInicioDaExecucao(tempoTotal);
			ts.setProcessso(processoDaVez);
			ts.setTime(1);
			processoDaVez.setTempoDeExeculcao(processoDaVez.getTempoDeExeculcao() - 1);
			this.timeSlicesProcessados.add(ts);
		}
		tempoTotal++;
	}
	
	private void trocar(){
		processoDaVez = fila.remove(0);
		tempoTotal += tempoDeTroca;
	}
	
	private void verificarChegadaDeProcessos(){
		boolean chegoProcesso = false;
		while(!processos.isEmpty() && processos.get(0).getTempoDeChegada() < tempoTotal){
			fila.add(processos.remove(0));
			chegoProcesso = true;
		}
		
		if(!processos.isEmpty() && processos.get(0).getTempoDeChegada() == tempoTotal){
			if(processos.get(0).getTempoDeExeculcao() < processoDaVez.getTempoDeExeculcao()){
				if(processoDaVez.getTempoDeExeculcao() > 0){
					fila.add(processoDaVez);
				}
				processoDaVez = processos.remove(0);
				tempoTotal += tempoDeTroca;
			}else{
				fila.add(processos.remove(0));
			}
			chegoProcesso = true;
		}
		
		if(chegoProcesso){
			ordenaProcessosTempo();
		}
	}
	
	private void ordenaProcessosChegada(){
		this.processos.sort(new ComparadorChegadaDePorcessos());
	}
	
	private void ordenaProcessosTempo(){
		this.fila.sort(new ComparadorTempoExecucaoDePorcessos());
	}
	
	private void clonarProcessos(ArrayList<Processo> processosRecebido){
		for(Processo processo: processosRecebido){
			this.processos.add(processo.clone());
		}
	}
	
}
