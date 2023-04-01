package com.product.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Clase para solicitar un Integer por requestbody
 */
public class DtoCategoryId {

    /* Valor del entero. */
    @JsonProperty("category_id")
    private Integer category_id;

    /**
     * Costructor que recibe un entero
     * @param category_id el valor entero.
     */
    public DtoCategoryId(Integer category_id) {
        this.category_id = category_id;
    }

    /**
     * Constructor vacio.
     */
    public DtoCategoryId() {
        
    }

    /**
     * Regresa el valor entero almacenado.
     * @return el valor entero almacenado.
     */
	public Integer getCategory_id() {
		return category_id;
	}

    /**
     * Define el nuevo valor entero a almacenar.
     * @param category_id el nuevo valor entero a almacenar.
     */
	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}
}
