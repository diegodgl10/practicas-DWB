package com.product.api.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Clase para representar una categoria.
 * Una categoria posee ID, nombre y acronimo.
 * @author Diego Arturo Zamora Cruz.
 */
@Entity
@Table(name = "category")
public class Category implements Serializable {

    /* El ID de la categoria. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
    private Integer category_id;
    /* El nombre de la categoria. */
	@NotNull
	@Column(name = "category")
    private String category;
    /* El acronimo de la categoria. */
	@NotNull
	@Column(name = "acronym")
    private String acronym;
    /* Status de la categoria. */
    @Column(name = "status")
    @Min(value = 0, message = "status must be 0 or 1")
    @Max(value = 1, message = "status must be 0 or 1")
	@JsonIgnore
    private Integer status;

    /**
     * Constructor vacio.
     */
    public Category () {
    }

    /**
     * Constructor para una categoria que recibe el ID de la categoria, el nombre y su acronimo.
     * @param category_id el ID de la categoria.
     * @param category el nombre de la categoria.
     * @param acronym el acronimo de la categoria.
     */
    public Category (Integer category_id, String category, String acronym) {
        this.category_id = category_id; 
        this.category = category; 
        this.acronym = acronym; 
        this.status = 1;
    }

    /**
     * Constructor para una categoria que recibe el ID de la categoria, el nombre,
     * acronimo y status.
     * @param category_id el ID de la categoria.
     * @param category el nombre de la categoria.
     * @param acronym el acronimo de la categoria.
     * @param status el status de la categoria.
     */
    public Category (Integer category_id, String category,
            String acronym, Integer status) {
        this.category_id = category_id; 
        this.category = category; 
        this.acronym = acronym; 
        this.status = status;
    }

    /**
     * Regresa el ID de la categoria.
     * @return el ID de la categoria.
     */
    public Integer getCategory_id () {
        return this.category_id;
    }

    /**
     * Define el nuevo ID de la categoria.
     * @param category_id el nuevo ID de la categoria.
     */
    public void setCategory_id (Integer category_id) {
        this.category_id = category_id;
    }

    /**
     * Regresa el nombre de la categoria.
     * @return el nombre de la categoria.
     */
    public String getCategory () {
        return this.category;
    }

    /**
     * Define el nuevo nombre de la categoria.
     * @param category el nuevo nombre de la categoria.
     */
    public void setCategory (String category) {
        this.category = category;
    }

    /**
     * Regresa el acronimo de la categoria.
     * @return el acronimo de la categoria.
     */
    public String getAcronym () {
        return this.acronym;
    }

    /**
     * Define el nuevo acronimo de la categoria.
     * @param acronym el nuevo acronimo de la categoria.
     */
    public void setAcronym (String acronym) {
        this.acronym = acronym;
    }

    /**
     * Regresa el status de la categoria.
     * @return el status de la categoria.
     */
    public Integer getStatus () {
        return this.status;
    }

    /**
     * Define el nuevo status de la categoria.
     * @param status el nuevo status de la categoria.
     */
    public void setStatus (Integer status) {
        this.status = status;
    }

    /**
     * Regresa <code>True</code> si el objeto recibido tiene el mismo ID
     * que el que invoco al metodo, <code>False</code> en otro caso.
     * @return <code>True</code> si el objeto recibido tiene el mismo ID
     * que el que invoco al metodo, <code>False</code> en otro caso.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Category)) {
            return false;
        }
        Category aux = (Category) obj;
        if (aux.getCategory_id() != this.category_id) {
            return false;
        }
        return true;
    }

    /**
     * Regresa una representacion en cadena de la categoria.
     * @return una representacion en cadena de la categoria.
     */
    @Override
    public String toString() {
        String string;
        string = String.format("%d, %s, %s", this.category_id,
                                this.category, this.acronym);
        return string;
    }
}