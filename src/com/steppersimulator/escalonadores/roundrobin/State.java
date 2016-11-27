
package com.steppersimulator.escalonadores.roundrobin;

interface State {
	void troca();
	void executarProcesso();
	void verificarChegadadeProcesso();
}
