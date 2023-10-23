package cat.itacademy.barcelonactiva.abdellaoui.fethi.s05.t01.n01.model.domain;

import java.io.Serializable;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Sucursal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	Integer pk_SucursalID;
	@NotNull
	@Size(min=4,max=15)
	String nomSucursal;
	@NotNull
	@Size(min=4,max=15)
	String paisSucursal;

	public Sucursal() {
	}

	public Sucursal(String nomSucursal, String paisSucursal) {

		this.nomSucursal = nomSucursal;
		this.paisSucursal = paisSucursal;
	}

//	public Sucursal(Integer pk_SucursalID, String nomSucursal, String paisSucursal) {
//
//		this.pk_SucursalID = pk_SucursalID;
//		this.nomSucursal = nomSucursal;
//		this.paisSucursal = paisSucursal;
//	}

	public Integer getPk_SucursalID() {
		return pk_SucursalID;
	}

	public void setPk_SucursalID(Integer pk_SucursalID) {
		this.pk_SucursalID = pk_SucursalID;
	}

	public String getNomSucursal() {
		return nomSucursal;
	}

	public void setNomSucursal(String nomSucursal) {
		this.nomSucursal = nomSucursal;
	}

	public String getPaisSucursal() {
		return paisSucursal;
	}

	public void setPaisSucursal(String paisSucursal) {
		this.paisSucursal = paisSucursal;
	}

	@Override
	public String toString() {
		return "Sucursal [pk_SucursalID=" + pk_SucursalID + ", nomSucursal=" + nomSucursal + ", paisSucursal="
				+ paisSucursal + "]";
	}

}
