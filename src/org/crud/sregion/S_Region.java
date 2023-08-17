package org.crud.sregion;
/**
 * CRUD: CREATE, READ, UPDATE, DELETE
 * ESTE SERIA EL PRIMER CRUD QUE HACEMOS EN JAVA
 * *************** NOTA IMPORTANTE **********************
 * CRUD QUE TERMINEMOS, CRUD QUE DEBEN HACER CON LA TABLA REPORTE
 * 
 * */

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class S_Region {
	// CONEXION A LA BASE DE DATOS
	
	@SuppressWarnings("unused")
	private static Connection connection = null;
	private static String driver = "oracle.jdbc.driver.OracleDriver";
	private static String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	
	public static void connectDataBaseOracle() throws IOException, SQLException {
		try {
			Class.forName(driver).newInstance();
			System.out.println("CARGO EL DRIVER CORRECTAMENTE: ojdbc6.jar");
		} catch (Exception e) {
			System.out.println("Excepción: "+e.getMessage());
		}
		
		try {
			connection = DriverManager.getConnection(URL,"SYSTEM","marcos");
			System.out.println("CONEXION EXITOSA: Oracle11g");
		} catch (Exception e) {
			System.out.println("Excepción: " + e.getMessage());
		}
	}
	
	public static void insertarS_Region(int id,String name) throws IOException, SQLException{
		try {
			connectDataBaseOracle();
			String queryInsertar = "INSERT INTO S_REGION (ID,NAME) VALUES (?,?)";
			PreparedStatement ps = connection.prepareStatement(queryInsertar);
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.executeQuery();
			System.out.println("INSERTO EL REGISTRO : " + id + "," + name);
		} catch (Exception e) {
			System.out.println("Excepcion: " + e.getMessage());
		}
	}
	
	// ACTUALIZAR
	public static void updateS_Region(String name, int id) throws IOException, SQLException{
		try {
			connectDataBaseOracle();
			String queryUpdate = "UPDATE S_REGION SET NAME = ? WHERE ID = ?";
			PreparedStatement ps = connection.prepareStatement(queryUpdate);
			ps.setString(1, name);
			ps.setInt(2, id);
			ps.executeQuery();
			System.out.println("ACTUALIZO EL REGISTRO: " + id + "," + name);
		} catch (Exception e) {
			System.out.println("Excepcion: " + e.getMessage());
		}
	}
	
	// ELIMINAR
	public static void deleteS_Region(int id) throws IOException, SQLException{
		try {
			connectDataBaseOracle();
			String queryDelete = "DELETE FROM S_REGION WHERE ID = ?";
			PreparedStatement ps = connection.prepareStatement(queryDelete);
			ps.setInt(1, id);
			ps.executeQuery();
			System.out.println("ELIMINO EL REGISTRO: " + id);
		} catch (Exception e) {
			System.out.println("Excepcion: " + e.getMessage());
		}
	}
	
	// SELECCIONAR REGISTRO POR ID
	public static void selectS_Region(int id) throws IOException, SQLException{
		try {
			connectDataBaseOracle();
			String querySeleccionar = "SELECT * FROM S_REGION WHERE ID = ?";
			PreparedStatement ps = connection.prepareStatement(querySeleccionar);
			ps.setInt(1, id);
			ResultSet rSet = ps.executeQuery();
			if (rSet.next()) {
				System.out.println(rSet.getInt("id") + "," + rSet.getString("name"));
			}
		} catch (Exception e) {
			System.out.println("Excepcion: " + e.getMessage());
		}
	}
	
	// SELECCIONAR TODOS LOS REGISTROS
	public static void selectTodoS_Region() throws IOException, SQLException{
		try {
			connectDataBaseOracle();
			String querySeleccionar = "SELECT * FROM S_REGION";
			PreparedStatement ps = connection.prepareStatement(querySeleccionar);
			ResultSet rSet = ps.executeQuery();
			while(rSet.next()) {
				System.out.println(rSet.getInt("id") + "," + rSet.getString("name"));
			}
		} catch (Exception e) {
			System.out.println("Excepcion: " + e.getMessage());
		}
	}
	
	// INVOCAR AL PROCEDIMIENTO ALMACENADO PROC QUE HICIMOS ANTERIORMENTE
	public static void invocarProcedureS_Region(int id, String name) throws IOException, SQLException {
		try {
			connectDataBaseOracle();
			String query = "CALL proc(?,?)";
			CallableStatement cs = connection.prepareCall(query);
			cs.setInt(1, id);
			cs.setString(2, name);
			cs.execute();
			System.out.println("EJECUTO CORRECTAMENTE EL PROCEDIMIENTO");
		} catch (Exception e) {
			System.out.println("Excepcion: " + e.getMessage());
		}
	}
	
	public static void main(String[] args) throws IOException, SQLException {
		//connectDataBaseOracle();
		//insertarS_Region(11, "OAXACA");
		//updateS_Region("CHIAPAS MX", 3);
		//deleteS_Region(2);
		//selectS_Region(12);
		//selectTodoS_Region();
		invocarProcedureS_Region(2, "TLAXCALA");
	}
	
}
