package modelos;

<<<<<<< HEAD
import java.time.LocalDate;

public class Informe {
	private int idInforme;
	private LocalDate fechaInforme;
	public Informe(LocalDate fechaInforme) {
		super();
		fechaInforme=this.fechaInforme;
=======
public class Informe {
	private int idInforme;
	
	public Informe() {
		super();
>>>>>>> origin/vicky
	}

	public int getIdInforme() {
		return idInforme;
	}

	public LocalDate getFechaInforme() {
		return fechaInforme;
	}

	public void setFechaInforme(LocalDate fechaInforme) {
		this.fechaInforme = fechaInforme;
	}

	public void setIdInforme(int idInforme) {
		this.idInforme = idInforme;
	}

<<<<<<< HEAD
}
=======
}
>>>>>>> origin/vicky
