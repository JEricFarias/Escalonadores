package com.steppersimulator.escalonadores.roundrobin;

interface State {
	public void troca();
	void executarProcesso();
	void verificarChegadadeProcesso();
}
