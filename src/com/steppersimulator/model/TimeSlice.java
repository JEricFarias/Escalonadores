package com.steppersimulator.model;

public class TimeSlice {
	private Processo processso;
	private int time;
	
	@Override
	public String toString(){
		return String.format("Processo %d executou %d ut", this.processso.getId(), this.time);
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

}
