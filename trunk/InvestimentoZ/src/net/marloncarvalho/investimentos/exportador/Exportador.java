package net.marloncarvalho.investimentos.exportador;

import net.marloncarvalho.investimentos.entidades.Banco;
import net.marloncarvalho.investimentos.entidades.Fundo;

public interface Exportador {
	public static int XML = 1;
	public static int TXT = 2;

	public void exportar(Banco banco);
	public void exportar(Fundo fundo);

}
