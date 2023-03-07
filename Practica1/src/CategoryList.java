import java.util.ArrayList;
import java.util.List;

/**
 * Una lista de categorias de la clase {@link Category}.
 * @author Diego Arturo Zamora Cruz.
 */
public class CategoryList {

    /* Lista de categorias. */
    private List<Category> categorias;
    
    /**
     * Constructor vacio.
     */
    public CategoryList () {
        this.categorias = new ArrayList<Category>();
    }

    /**
     * Agrega una nueva categoria a la lista.
     * @param categoria la nueva categoria que se agregara.
     * @return <code>true<\code> si se agrego exitosamente,
     * <code>false</code> en otro caso
     */
    public boolean createCategory (Category categoria) {
        if (getCategory(categoria.getCategoryId()) == null) {
            this.categorias.add(categoria);
            return true;
        }
        return false;
    }

    /**
     * Regresa una lista con todas las categorias.
     * @return una lista con todas las categorias.
     */
    public String getCategories () {
        if (this.categorias.isEmpty()) {
            return null;
        }

        String string = "";
        for (int i = 0; i < this.categorias.size(); i++) {
            string += this.categorias.get(i);
            if (i + 1 < this.categorias.size()) {
                string += "\n";
            }
        }
        return string;
    }

    /**
     * Regresa la categoria con el mismo ID recibido, <code>null<\code> en otro caso. 
     * @param categoryId el ID de la categoria que se desea consultar.
     * @return la categoria con el mismo ID recibido, <code>null<\code> en otro caso.
     */
    public Category getCategory (Integer categoryId) {
        for (int i = 0; i < this.categorias.size(); i++) {
            if (categoryId == this.categorias.get(i).getCategoryId()) {
                return this.categorias.get(i);
            }
        }
        return null;
    }

    /**
     * Elimina ls categoria con el mismo ID recibido.
     * @param categoryId el ID de la categoria que se desea eliminar. 
     */
    public void deleteCategory (Integer categoryId) {
        Category aux = getCategory(categoryId);
        if (aux == null) {
            return;
        }
        this.categorias.remove(aux);
    }
}
