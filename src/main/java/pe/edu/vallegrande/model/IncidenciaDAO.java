package pe.edu.vallegrande.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IncidenciaDAO {

    // Método auxiliar para mapear un ResultSet a un objeto Incidencia
    private Incidencia extraerIncidencia(ResultSet rs) throws SQLException {
        Incidencia inc = new Incidencia();
        inc.setId(rs.getInt("id"));
        inc.setTipo(rs.getString("tipo"));
        inc.setAula(rs.getString("aula"));
        inc.setFecha(rs.getDate("fecha"));
        inc.setEstado(rs.getString("estado"));
        inc.setDescripcion(rs.getString("descripcion"));
        return inc;
    }

    // --- 1. REGISTRAR ---
    public boolean registrar(Incidencia inc) {
        String sql = "INSERT INTO incidencia (tipo, aula, fecha, estado, descripcion) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, inc.getTipo());
            ps.setString(2, inc.getAula());
            ps.setDate(3, inc.getFecha());
            ps.setString(4, inc.getEstado());
            ps.setString(5, inc.getDescripcion());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error DAO al registrar: " + e.getMessage());
            return false;
        }
    }

    // --- 2. LISTAR ---
    public List<Incidencia> listarTodas() {
        List<Incidencia> lista = new ArrayList<>();
        String sql = "SELECT * FROM incidencia ORDER BY id DESC";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(extraerIncidencia(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error DAO al listar: " + e.getMessage());
        }
        return lista;
    }

    // --- 3. MODIFICAR ---
    public boolean modificar(Incidencia inc) {
        String sql = "UPDATE incidencia SET tipo=?, aula=?, fecha=?, estado=?, descripcion=? WHERE id=?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, inc.getTipo());
            ps.setString(2, inc.getAula());
            ps.setDate(3, inc.getFecha());
            ps.setString(4, inc.getEstado());
            ps.setString(5, inc.getDescripcion());
            ps.setInt(6, inc.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error DAO al modificar: " + e.getMessage());
            return false;
        }
    }

    // --- 4. ELIMINAR ---
    public boolean eliminar(int id) {
        String sql = "DELETE FROM incidencia WHERE id=?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error DAO al eliminar: " + e.getMessage());
            return false;
        }
    }

    // --- 5. BUSCAR ---
    public List<Incidencia> buscar(String criterio, String valor) {
        List<Incidencia> lista = new ArrayList<>();
        // Inyectamos el criterio (columna) de forma segura ya que viene de un JComboBox
        String sql = "SELECT * FROM incidencia WHERE " + criterio + " LIKE ? ORDER BY id DESC";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + valor + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(extraerIncidencia(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error DAO al buscar: " + e.getMessage());
        }
        return lista;
    }
}