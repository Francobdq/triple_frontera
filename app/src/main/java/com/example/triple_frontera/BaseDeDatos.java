package com.example.triple_frontera;

public class BaseDeDatos {

    public static int TIPO_PAISES = 0;
    public static int TIPO_AREAS_OPERATIVAS = 1;
    public static int TIPO_PARAJES = 2;


    public static boolean Login(String user, String pass){
        return true;
    }


    public static String[] Paises(){
        String[] salida = {"Argentina", "Uruguay", "Paraguay"};
        return salida;
    }

    public static String[] AreasOperativas(int id_pais){
        String[][] salida = {{"2"}, {"4"}, {"5"}};

        return salida[id_pais];
    }

    public static String[] Parajes(int id_area_operativa){
        String[][] salida = {{"8","a"}, {"9","b"}, {"0","c"}};
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
}
