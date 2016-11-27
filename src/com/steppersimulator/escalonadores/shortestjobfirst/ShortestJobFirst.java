package com.steppersimulator.escalonadores.shortestjobfirst;

import java.util.ArrayList;
import java.util.LinkedList;

import com.steppersimulator.comparators.ComparadorChegadaDePorcessos;
import com.steppersimulator.comparators.ComparadorTempoExecucaoDePorcessos;
import com.steppersimulator.escalonadores.Escalonador;
import com.steppersimulator.model.Processo;
import com.steppersimulator.model.TimeSlice;

public class ShortestJobFirst implements Escalonador{
	
	private final int ZERAR = 0;
	private ArrayList<TimeSlice> timeSlicesProcessados;
	private LinkedList<Processo> processos;
	private LinkedList<Processo> fila;
	private Processo processoDaVez;
	private int tempoTotal;
	private int tempodeTorca;
	
	public ShortestJobFirst() {
		this.timeSlicesProcessados = new ArrayList<>();
		this.fila = new LinkedList<>();
		this.processos = new LinkedList<>();
	}
	
	@Override
	public ArrayList<TimeSlice> escalonar(ArrayList<Processo> processos, int timeSlice, int tempodeTroca) {
		clonarProcessos(processos);
		ordenarProcessosChegada();
		this.tempodeTorca = tempodeTroca;
		while(!this.processos.isEmpty()){
			this.processoDaVez = this.processos.removeFirst();
			this.tempoTotal = this.processoDaVez.getTempoDeChegada();
			while(!this.fila.isEmpty() || processoDaVez.getTempoDeExeculcao() > 0){
				executar();
				verificarChegadaDeProcesso();
				trocar();
			}
		}
		
		return this.timeSlicesProcessados;
	}
	
	private void trocar(){
		if(!this.fila.isEmpty()){
			processoDaVez = fila.removeFirst();
			this.tempoTotal += this.tempodeTorca;
		}	
	}
	
	private void verificarChegadaDeProcesso(){
		boolean chegoProcesso = false;
		while(!this.processos.isEmpty() && this.processos.getFirst().getTempoDeChegada() <= this.tempoTotal){
			this.fila.add(this.processos.removeFirst());
			chegoProcesso = true;
		}
		if(chegoProcesso){
			ordenaProcessosTempo();
		}
	}
	
	private void executar(){
		TimeSlice ts = new TimeSlice();
		ts.setInicioDaExecucao(tempoTotal);
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
	
	private void ordenarProcessosChegada(){
		this.processos.sort(new ComparadorChegadaDePorcessos());
	}
	
	private void ordenaProcessosTempo(){
		this.fila.sort(new ComparadorTempoExecucaoDePorcessos());
	}
	

	public int getTempodeTroca(){
		return this.tempodeTorca;
	}
	
	public int getTempoTotal(){
		return this.tempoTotal;
	}
}