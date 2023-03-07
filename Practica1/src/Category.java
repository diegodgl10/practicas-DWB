/**
 * Clase para representar una categoria.
 * Una categoria posee ID, nombre y acronimo.
 * @author Diego Arturo Zamora Cruz.
 */
public class Category {

    /* El ID de la categoria. */
    private Integer categoryId;
    /* El nombre de la categoria. */
    private String category;
    /* El acronimo de la categoria. */
    private String acronym;

    /**
     * Constructor vacio.
     */
    public Category () {
        throw new UnsupportedOperationException("Constructor no disponible.");
    }

    /**
     * Constructor para una categoria que recibe el ID de la categoria, el nombre y su acronimo.
     * @param categoryId el ID de la categoria.
     * @param category el nombre de la categoria.
     * @param acronym el acronimo de la categoria.
     */
    public Category (Integer categoryId, String category, String acronym) {
        this.categoryId = categoryId; 
        this.category = category; 
        this.acronym = acronym; 
    }

    /**
     * Regresa el ID de la categoria.
     * @return el ID de la categoria.
     */
    public Integer getCategoryId () {
        return this.categoryId;
    }

    /**
     * Define el nuevo ID de la categoria.
     * @param categoryId el nuevo ID de la categoria.
     */
    public void setCategoryId (Integer categoryId) {
        this.categoryId = categoryId;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Category)) {
            return false;
        }
        Category aux = (Category) obj;
        if (aux.getCategoryId() != this.categoryId) {
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
        string = String.format("%d, %s, %s", this.categoryId, this.category, this.acronym);
        return string;
    }
}