package co.edu.unab.tiendaappfc;

import java.io.Serializable;

public class Usuario implements Serializable {

    private int id;
    private String nombre;
    private String correo;
    private String contra;
    private String urlFoto;

    public Usuario(){
    }

    public Usuario(String nombre, String correo, String contra ,String urlFoto) {
        this.nombre = nombre;
        this.correo = correo;
        this.contra = contra;
        this.urlFoto = urlFoto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }
}
