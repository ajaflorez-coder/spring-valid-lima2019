package pe.edu.lima2019.models.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "deportistas")
public class Deportista {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "El número de documento no puede estar vacio")
	@Size(min = 8, max = 8, message = "El tamaño del número de documento tiene que estar entre 8 y 8")
	@Column(name = "num_documento", length = 8)	
	private String numDocumento;
	
	@NotEmpty(message = "El apellido no puede estar vacio")
	@Size(min = 2, max = 30, message = "El tamaño del apellido tiene que estar entre 2 y 30")
	@Column(name = "apellidos", length = 30)	
	private String apellidos;
	
	@NotEmpty(message = "El nombre no puede estar vacio")
	@Size(min = 2, max = 30, message = "El tamaño del nombre tiene que estar entre 2 y 30")
	@Column(name = "nombres", length = 30)	
	private String nombres;
		
	@JsonIgnoreProperties(value = "deportistas")
	@NotNull(message = "El pais no puede ser null")
	@ManyToOne
	@JoinColumn(name = "pais_id")
	private Pais pais;
	
	@JsonIgnoreProperties(value = "deportista")
	@OneToMany(mappedBy="deportista", fetch =FetchType.LAZY)
	private List<Medalla> medallas;
	
	public Deportista() {
		this.medallas = new ArrayList<>();
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumDocumento() {
		return numDocumento;
	}

	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNombres() {
		return nombres;
	}
	
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public List<Medalla> getMedallas() {
		return medallas;
	}

	public void setMedallas(List<Medalla> medallas) {
		this.medallas = medallas;
	}
	
	
}
