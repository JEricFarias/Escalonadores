package com.steppersimulator.escalonadores.roundrobin.teste;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ExecutaMaisDeUnTimeSliceTeste.class, ProcessoChegaQuandoTimeSliceAcabaFilaVaziaTeste.class,
		ProcessosMenoresQueTimeSliceTeste.class, RRteste.class, TempoOsiosoEntreProcessosTeste.class,
		VerificaChegadaDepoisDeZeroTeste.class, VerificarChegadaFilaVaziaTeste.class })
public class AllTests {

}
