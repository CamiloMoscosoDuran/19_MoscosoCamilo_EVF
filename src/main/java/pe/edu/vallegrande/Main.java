package pe.edu.vallegrande;

import pe.edu.vallegrande.controller.IncidenciaController;
import pe.edu.vallegrande.model.IncidenciaDAO;
import pe.edu.vallegrande.view.IncidenciaForm;

public class Main {
    public static void main(String[] args) {
        // 1. Crear instancia del Modelo (Acceso a Datos)
        IncidenciaDAO modelo = new IncidenciaDAO();

        // 2. Crear instancia de la Vista (Interfaz Gráfica)
        // Nota: Debes diseñar IncidenciaForm antes de ejecutar.
        IncidenciaForm vista = new IncidenciaForm();

        // 3. Crear instancia del Controlador, pasándole la Vista y el Modelo
        IncidenciaController controlador = new IncidenciaController(vista, modelo);

        // 4. Iniciar la aplicación
        controlador.iniciar();
    }
}