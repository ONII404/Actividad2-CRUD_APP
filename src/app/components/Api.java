package app.components;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ONII404
 */
public class Api {

    String SQLString;
    Conexion cn;

    /**
     *
     * @param nCtrl ID del Registro a Buscar
     * @return Retorna un vector con los datos del registro requerido
     */
    public String[] search(int nCtrl) {
        SQLString = "SELECT * FROM Estudiante WHERE codeEst='" + nCtrl + "';";
        cn = new Conexion();
        String reg[] = new String[6];

        try {
            cn.open();
            ResultSet rs = cn.getConnection().createStatement().executeQuery(SQLString);

            if (rs.next()) {
                reg[0] = rs.getString("nombre");
                reg[1] = rs.getString("apellidoP");
                reg[2] = rs.getString("apellidoM");
                reg[3] = rs.getString("fechaNaci");
                reg[4] = rs.getString("semestre");
                reg[5] = rs.getString("carrera");

                System.out.println("Registro encontrado: " + reg[0]);
            } else {
                System.out.println("No se encontraron registros para el código " + nCtrl);
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            cn.close();
            return reg;
        }
    }

    /**
     *
     * @param NumCtrl
     * @param name
     * @param ltNameP
     * @param ltNameM
     * @param date
     * @param semestre
     * @param carrera
     */
    public void post(int NumCtrl, String name, String ltNameP, String ltNameM, String date, int semestre, String carrera) {
        SQLString = "INSERT INTO Estudiante (codeEst, nombre, apellidoP, apellidoM, fechaNaci, semestre, carrera) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        cn = new Conexion();
        PreparedStatement pStmt = null;

        try {
            cn.open();
            pStmt = cn.getConnection().prepareStatement(SQLString);
            pStmt.setInt(1, NumCtrl);
            pStmt.setString(2, name);
            pStmt.setString(3, ltNameP);
            pStmt.setString(4, ltNameM);
            pStmt.setString(5, date);
            pStmt.setInt(6, semestre);
            pStmt.setString(7, carrera);

            pStmt.executeUpdate();
            System.out.println("Registro insertado correctamente");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            try {
                if (pStmt != null) {
                    pStmt.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar el PreparedStatement: " + e.getMessage());
            }
            cn.close();
        }
    }

    /**
     * 
     * @param nCtrl
     * @param name
     * @param ltNameP
     * @param ltNameM
     * @param date
     * @param semestre
     * @param carrera 
     */
    public void update(int nCtrl, String name, String ltNameP, String ltNameM, String date, int semestre, String carrera) {

        SQLString = "UPDATE Estudiante SET nombre=?, apellidoP=?, apellidoM=?, fechaNaci=?, semestre=?, carrera=? WHERE codeEst = ?";
        cn = new Conexion();
        PreparedStatement pStmt = null;
        try {
            cn.open();
            pStmt = cn.getConnection().prepareStatement(SQLString);
            pStmt.setString(1, name);
            pStmt.setString(2, ltNameP);
            pStmt.setString(3, ltNameM);
            pStmt.setString(4, date);
            pStmt.setInt(5, semestre);
            pStmt.setString(6, carrera);
            pStmt.setInt(7, nCtrl);

            pStmt.executeUpdate();
            System.out.println("¡Registro Actualizado!");

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            try {
                if (pStmt != null) {
                    pStmt.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar el PreparedStatement: " + e.getMessage());
            }
            cn.close();
        }

    }

    /**
     * 
     * @param nCrtl ID del Registro a Eliminar
     */
    public void remove(int nCrtl) {
        SQLString = "DELETE FROM Estudiante WHERE codeEst = ?;";
        cn = new Conexion();
        PreparedStatement pStmt = null;

        try {
            cn.open();
            pStmt = cn.getConnection().prepareStatement(SQLString);
            pStmt.setInt(1, nCrtl);
            pStmt.executeUpdate();

            System.out.println("Registro Eliminado: " + nCrtl);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());

        } finally {
            try {
                if (pStmt != null) {
                    pStmt.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar el PreparedStatement: " + e.getMessage());
            }
            cn.close();
        }
    }
}
