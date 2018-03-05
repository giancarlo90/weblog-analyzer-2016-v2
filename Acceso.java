/**
 * Clase Acceso y sus componentes
 */
public class Acceso
{
    //Declaracion de los campos que posee esta clase
    private int ano;
    private int mes;
    private int dia;
    private int hora;
    private int minutos;
    private String ip;
    private String paginaWeb;
    private int codigoDeRespuesta;

    /**
     * Constructor de la clase Acceso
     * @param   elementosLinea es una Array de tipo String donde almacenamos los datos leidos dividos por un espacio en blanco
     */
    public Acceso(String datos)
    {
        String[] elementosLinea = datos.split(" ");
        if(elementosLinea.length == 5){
            ano = Integer.parseInt(elementosLinea[0]);
            mes = Integer.parseInt(elementosLinea[1]);
            dia = Integer.parseInt(elementosLinea[2]);
            hora = Integer.parseInt(elementosLinea[3]);
            minutos = Integer.parseInt(elementosLinea[4]); 
        }
        else{
            ip = elementosLinea[0];
            ano = Integer.parseInt(elementosLinea[1].substring(1,elementosLinea[1].length()));
            mes = Integer.parseInt(elementosLinea[2]);
            dia = Integer.parseInt(elementosLinea[3]);
            hora = Integer.parseInt(elementosLinea[4]);
            minutos = Integer.parseInt(elementosLinea[5].substring(0,(elementosLinea[5].length() - 1)));
            paginaWeb = elementosLinea[6];
            codigoDeRespuesta = Integer.parseInt(elementosLinea[7]);
        }
    }

    /**
     * Metodo que devuelve el año
     */
    public int getAno() 
    {
        return ano;
    }

    /**
     * Metodo que devuelve el mes
     */
    public int getMes()
    {
        return mes;
    }

    /**
     * Metodo que devuelve el dia
     */
    public int getDia()
    {
        return dia;
    }

    /**
     * Metodo que devuelve la hora
     */
    public int getHora()
    {
        return hora;
    }

    /**
     * Metodo que devuelve los minutos
     */
    public int getMinutos()
    {
        return minutos;
    }

    /**
     * Metodo que devuelve la pagina web
     */
    public String getWeb()
    {
        return paginaWeb;
    }

    /**
     * Metodo que devuelve la ip
     */
    public String getIp()
    {
        return ip;
    }

    /**
     * Metodo que devuelve los minutos
     */
    public int getCodigoDeRespuesta()
    {
        return codigoDeRespuesta;
    }
}