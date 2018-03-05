import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Clase Analizador de accesos a servidor
 */
public class AnalizadorAccesosAServidor
{
    private ArrayList<Acceso> accesos;

    /**
     * Constructor de la clase
     */
    public AnalizadorAccesosAServidor() 
    {
        accesos = new ArrayList<>();
    }

    /**
     * Metodo para leer los archivos a traves de un documento externo
     * @archivoALeer    Un objeto de la clase File donde guardamos los datos del archivo externo
     */
    public void analizarArchivoDeLog(String archivo)
    {
        accesos.clear(); //Limpia la Array antes de introducir los datos nuevos
        File archivoALeer = new File(archivo);
        try {
            Scanner sc = new Scanner(archivoALeer);
            while (sc.hasNextLine()) { //Bucle para leer cada linea del archivo e introducir los datos en la ArrayList
                String lineaLeida = sc.nextLine();               
                Acceso accesoActual = new Acceso(lineaLeida);               
                accesos.add(accesoActual);
            }
            sc.close();
        }
        catch (Exception e) {
            System.out.println("Ocurrio algun error al leer el archivo.");
        }
    }

    /**
     * Metodo para obtener la hora a la que ha habido mas accesos
     */
    public int obtenerHoraMasAccesos() 
    {
        int valorADevolver = -1;

        if (!accesos.isEmpty()) {
            int[] accesosPorHora = new int[24];

            for (Acceso accesoActual : accesos) {
                int horaAccesoActual = accesoActual.getHora();
                accesosPorHora[horaAccesoActual] = accesosPorHora[horaAccesoActual] + 1;
            }

            int numeroDeAccesosMasAlto = accesosPorHora[0];
            int horaDeAccesosMasAlto = 0;
            for (int i = 0; i < accesosPorHora.length; i++) {
                if (accesosPorHora[i] >= numeroDeAccesosMasAlto) {
                    numeroDeAccesosMasAlto = accesosPorHora[i];
                    horaDeAccesosMasAlto = i;
                }
            }

            valorADevolver = horaDeAccesosMasAlto;                      
        }

        return valorADevolver;
    }

    /**
     * Metodo para obtener la pagina web mas visitada 
     */
    public String paginaWebMasSolicitada() 
    {
        ArrayList<Acceso> acceso = new ArrayList<>(); //Una ArrayList nueva donde haremos una copia de la que tenemos
        acceso.addAll(accesos);
        String paginaADevolver = null; //Variable String que utilizaremos para almacenar la web con mas coincidencias a devolver
        int contadorMasCoincidencias = 0; //Contador donde almacenaremos en mayor numero de coincidencias
        if(accesos.size() > 0){  //Condicion para ver si el ArrayList esta vacio o contiene objetos.
            for(int i= 0; i < acceso.size() ; i++){
                int contador = 0; //Contador donde almacenaremos las coincidencias de cada web.
                for(int j = i + 1; j < acceso.size() ; j++){ //Bucle para comprobar las coincidencias
                    if(acceso.get(j).getWeb().equals(acceso.get(i).getWeb())){
                        contador ++;
                        acceso.remove(j);
                        j--;
                    }
                }
                if(contador >= contadorMasCoincidencias){ 
                    contadorMasCoincidencias = contador;
                    paginaADevolver = acceso.get(i).getWeb();
                }
                if(contador != 0){ //Si el contador es distinto de 0, significa que se han eliminado objetos del ArrayList
                    i--;
                }
            }
        }
        return paginaADevolver;
    }

    /**
     * Metodo para obtener la dirección del cliente que ha realizado mayor número de accesos exitosos al servidor. 
     */
    public String clienteConMasAccesosExitosos()
    {
        ArrayList<Acceso> acceso = new ArrayList<>(); //Una ArrayList nueva donde haremos una copia de la que tenemos
        acceso.addAll(accesos);
        String ipADevolver = null; //Variable String que utilizaremos para almacenar la web con mas coincidencias a devolver
        int contadorMasCoincidencias = 0; //Contador donde almacenaremos en mayor numero de coincidencias
        int ipMasCoincidencias = 0;
        if(accesos.size() > 0){  //Condicion para ver si el ArrayList esta vacio o contiene objetos.
            for(int i= 0; i < acceso.size(); i++){ //Bucle para borrar las conexiones que no han sido exitosas
                if (acceso.get(i).getCodigoDeRespuesta() != 200){
                    acceso.remove(i);
                    i--;
                }
            }
            for(int i= 0; i < acceso.size() ; i++){
                int contador = 0; //Contador donde almacenaremos las coincidencias de cada web.
                int ipCoincidencia = 0;
                for(int j = i + 1; j < acceso.size() ; j++){ //Bucle para comprobar las coincidencias
                    if(acceso.get(j).getIp().equals(acceso.get(i).getIp())){
                        contador ++;
                        acceso.remove(j);
                        j--;
                        ipCoincidencia = Integer.parseInt(acceso.get(i).getIp().substring(10,acceso.get(i).getIp().length()));
                    }
                }
                if(contador > contadorMasCoincidencias || contador == contadorMasCoincidencias && ipMasCoincidencias < ipCoincidencia){ 
                    contadorMasCoincidencias = contador;
                    ipADevolver = acceso.get(i).getIp();
                    ipMasCoincidencias = ipCoincidencia;
                }
            }
        }
        return ipADevolver;
    }

}
