package com.steppersimulator.escalonadores.escalonamentoprioridades;

import java.util.ArrayList;

import com.steppersimulator.escalonadores.Escalonador;
import com.steppersimulator.model.Processo;
import com.steppersimulator.model.TimeSlice;

public class EscalonamentoPorPrioridades implements Escalonador{

	private ArrayList<Processo> processos = new ArrayList<>();
	private ArrayList<Processo> fila = new ArrayList<>();
	private ArrayList<TimeSlice> timeSlices = new ArrayList<>();
	private Processo processoDaVez;
	private int timeSlice;
	private int tempodeTroca;
	private int tempoTotal;
	
	
	@Override
	public ArrayList<TimeSlice> escalonar(ArrayList<Processo> processos, int timeSlice, int tempodeTroca) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void executar(){
		
	}
	
	private void verificarChegadaPorcesso(){
		
	}
	
	private void trocar(){
		
	}
	
	private void clonarProcessos(ArrayList<Processo> processos){
		for(Processo p: processos){
			try {
				this.processos.add(p.clone());
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void ordenarChegada(){
		for(int i = 0; i < processos.size() - 1; i++){
			for(int j = 1; j < processos.size(); j++ ){
				if(processos.get(i).getTempoDeChegada() > processos.get(j).getTempoDeChegada()){
					Processo aux = processos.get(i);
					processos.add(i, processos.get(j));
					processos.add(j, aux);
				}
			}
		}
	}
	
	private void ordenaPrioridades(){
		if(fila.size() >= 2){
			for(int i = 0; i < fila.size() - 1; i++){
				for(int j = 1; j < fila.size(); j++ ){
					if(fila.get(i).getPrioridade() > fila.get(j).getPrioridade()){
						Processo aux = fila.get(i);
						fila.add(i, fila.get(j));
						fila.add(j, aux);
					}
				}
			}
		}else{
			// a fila sera ordenada apenas quando ha mais de um processo
		}
	}

}
