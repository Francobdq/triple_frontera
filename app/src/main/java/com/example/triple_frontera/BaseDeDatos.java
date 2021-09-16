package com.example.triple_frontera;

public class BaseDeDatos {

    public static int TIPO_PAISES = 0;
    public static int TIPO_AREAS_OPERATIVAS = 1;
    public static int TIPO_PARAJES = 2;


    static String[] paises = {"Argentina", "Uruguay", "Paraguay"};
    static String[][] areas_operativas = {{"a"}, {"b"}, {"c"}};
    static String[][] parajes = {{"8","a"}, {"9","b"}, {"0","c"}};


    // --------------------------- RELACIONADO A PERMISOS --------------------------

    public static boolean Login(String user, String pass){
        return true;
    }


    // --------------------------- RELACIONADO AL AREA -----------------------------

    public static String[] Paises(){
        String[] salida = paises;
        return salida;
    }

    public static String[] AreasOperativas(int id_pais){
        String[][] salida = areas_operativas;

        return salida[id_pais];
    }

    public static String[] Parajes(int id_area_operativa){
        String[][] salida = parajes;
        return salida[id_area_operativa];
    }

    public static String[] Area(int tipo, int id){
        if (tipo == TIPO_PAISES){
            return Paises();
        }
        else if (tipo == TIPO_AREAS_OPERATIVAS){
            return AreasOperativas(id);
        }
        else if (tipo == TIPO_PARAJES){
            return Parajes(id);
        }

        return null;
    }

    public static String[] Area(int tipo){
        return Area(tipo, 0);
    }


    public static int getID_area_operativa(String nombre_area_operativa){
        // BORRAR ESTO ES RE POCO OPTIMO
        for (int i = 0; i < areas_operativas.length; i++){
            for (int j = 0; j < areas_operativas[i].length; j++){
                if (areas_operativas[i][j] == nombre_area_operativa){
                    return i;
                }
            }
        }
        return -1;
    }


    // --------------------------- RELACIONADO A LISTADO PACIENTES -----------------------------

    public static String[] Pacientes(int id_paraje){
        String[] salida = {"Rocio Lopez ", "Agustina Rodriguez","Juliana Thomadoro", "mujer 4", "Ariana Rodriguez" };
        return salida;
    }

    public static String[] pacientesNombreConDNI(int id_paraje){
        String[] salida = {"41223045, Rocio Lopez", "40223456, Agustina Rodriguez", "40223457, Juliana Thomadoro", "42223458, mujer 4", "44223459, Ariana Rodriguez" };
        return salida;
    }

    public static String[] DatosPersonales(int id){
        String[] salida = {"Rocio",
                        " Lopez ","39400332", "07/10/1995 (25 años)"};
        return salida;
    }


    // --------------------------- RELACIONADO A DATOS DE CONTROLES -----------------------------

    public static String[] getDatosControl(int id_control){
        String[] salida = {"Embarazada",
                        "25",
                        "Normal",
                        "21/08/2022",
                        "Normal",
                        "Positivo", // apartir de aca (este inclusive) son los datos de laboratorio
                        "Negativo",
                        "Positivo",
                        "Solicitado",
                        "11",
                        "82",
                        "+O"
                    };
        return salida;
    }

    public static String[] Controles(int id_paciente_seleccionado){
        String[] salida = {"Enero 2021", "junio 2021", "Septiembre 2021"};
        return salida;
    }
}
