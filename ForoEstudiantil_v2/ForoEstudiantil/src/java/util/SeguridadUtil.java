package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utilidades de seguridad: hashing SHA-256 y validaciones.
 * @author Hidalgo Asencio Xavier Andres / Vera Velasquez Gad Michel
 * @version 1.0
 */
public class SeguridadUtil {

    /**
     * Genera hash SHA-256 de una contrasena.
     * @param contrasena texto plano
     * @return hash hexadecimal o null si hay error
     */
    public static String hashearContrasena(String contrasena) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(contrasena.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (Exception e) {
            System.err.println("Error al hashear: " + e.getMessage());
            return null;
        }
    }

    /**
     * Valida que la contrasena tenga al menos 6 caracteres.
     * @param contrasena a validar
     * @return true si es valida
     */
    public static boolean esContrasenaValida(String contrasena) {
        return contrasena != null && contrasena.length() >= 6;
    }

    /**
     * Valida formato basico de correo electronico.
     * @param correo a validar
     * @return true si tiene formato valido
     */
    public static boolean esCorreoValido(String correo) {
        return correo != null && correo.contains("@") && correo.contains(".");
    }
}
