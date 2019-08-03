package com.fullAutomationStack;

/**
 * Created by lleir on 24/3/18.
 */

import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


public class GenericCommon {

        public static int randomNumberHasta(Integer hasta){
            int res = 0;

            Random randomGenerator = new Random();

            String a = "";
            for (int idx = 1; idx <= hasta; ++idx){
                a += randomGenerator.nextInt(10);
            }
            return Integer.parseInt(a);
        }

        public static int randomNumberSpecificRange(int rangeStart, int rangeEnd){
            int res = 0;
            Random random = new Random();
            for (int idx = 1; idx <= 10; ++idx){
                res = showRandomInteger(rangeStart, rangeEnd, random);
            }
            return res;
        }

        private static int showRandomInteger(int aStart, int aEnd, Random aRandom){
            if (aStart > aEnd) {
                throw new IllegalArgumentException("Start cannot exceed End.");
            }

            long range = (long)aEnd - (long)aStart + 1;						//get the range, casting to long to avoid overflow problems
            long fraction = (long)(range * aRandom.nextDouble());			// compute a fraction of the range, 0 <= frac < range
            int res = ((int)(fraction + aStart));
            return (res== 0 ? showRandomInteger(aStart, aEnd, aRandom) : res);
        }

        /** Devuelve un List de Integers segun una fecha con formato 'dd/MM/yyyy' */
        public static List<Integer> devolverFechaPartida(String fecha){

            String[] fecha_partida = StringUtils.split(fecha, "/");
            List<Integer> list_res = new ArrayList<Integer>();

            list_res.add(Integer.parseInt(fecha_partida[0]));
            list_res.add(Integer.parseInt(fecha_partida[1]));
            list_res.add(Integer.parseInt(fecha_partida[2]));

            return list_res;
        }

        public static String randomStringInitWith(String inistr) {
            return inistr + "_" + randomString();
        }

        public static String randomString(){
            return randomString("");
        }

        public static String randomStringTest() {
            return randomString("test_");
        }

        public static String randomString(String a) {
            char[] chars = "abcde".toCharArray();
            StringBuilder sb = new StringBuilder();
            Random random = new Random();
            for (int i = 0; i < 10; i++) {
                char c = chars[random.nextInt(chars.length)];
                sb.append(c);
            }
            String output = sb.toString();
            return a + output;
        }

        public static String separateIBAN(String IBAN){
            if(!StringUtils.contains(IBAN, " ")){
                char[] chars = IBAN.toCharArray();
                String ibann = "";
                Integer count4 = 0;

                for(int a = 0; a < chars.length; a++){
                    if(count4 < 3){
                        ibann += chars[a];
                        count4++;
                    } else {
                        count4 = 0;
                        ibann += chars[a] + " ";
                    }
                }

                return StringUtils.substring(ibann, 0, ibann.length()-1);
            } else
                return IBAN;
        }

        public static List<String> convertWebElementsInString(List<WebElement> elementos) {
            List<String> listado = new ArrayList<String>();

            for(WebElement elemento : elementos)
                if(!StringUtils.isEmpty(elemento.getText()) && !StringUtils.equals(elemento.getText(), " "))
                    listado.add(elemento.getText());

            return listado;
        }

        public static String convertWebElementsToString(List<WebElement> elementos){
            String res = "";

            for(WebElement elemento : elementos)
                res += elemento.getText() + ", ";

            return res.substring(0, res.length() - 2);
        }

        public static boolean existeStringEnArrayDeWebElements(List<WebElement> elementos, String valorExpected){
            for(WebElement e : elementos)
                if(StringUtils.equals(e.getText(), valorExpected))
                    return true;

            return false;
        }

        public static String recorrer(String[] ae){
            String res = "";
            for(int a = 0; a<ae.length; a++)
                res+=ae[a] + ", ";

            return res;
        }

        public static List<String> convertirArrayStringsInListStrings(String[] params) {
            List<String> res = new ArrayList<String>();
            Integer cont_list_res = 0;


            for(Integer cont_array_params = 0; cont_array_params <= params.length-1; cont_array_params++){

                if(!StringUtils.equals(params[cont_array_params], " ")){
                    res.add(params[cont_array_params]);
                    cont_list_res++;
                }

            }

            return res;
        }

        public static boolean eq(String expected, String compare) {
            return StringUtils.equals(expected, compare);
        }

        public static boolean conts(String expected, String compare) {
            return StringUtils.contains(expected, compare);
        }

        public static String removeRareChars(String cadena) {
            cadena = StringUtils.replace(cadena, ":", " ");
            cadena = StringUtils.replace(cadena, "--", " ");
            cadena = StringUtils.replace(cadena, "\'", " ");
            cadena = StringUtils.replace(cadena, "\"", " ");
            cadena = StringUtils.replace(cadena, " \" ", " ");
            cadena = StringUtils.replace(cadena, "/", " ");
            cadena = StringUtils.replace(cadena, " / ", " ");
            cadena = StringUtils.replace(cadena, ".", " ");
            cadena = StringUtils.replace(cadena, "*", " ");
            cadena = StringUtils.replace(cadena, ",", " ");
            cadena = StringUtils.replace(cadena, "(", " ");
            cadena = StringUtils.replace(cadena, ")", " ");
            cadena = StringUtils.replace(cadena, "•", " ");
            cadena = StringUtils.replace(cadena, "·", " ");
            return cadena;
        }

        public static String removeRareCharsNoSpace(String cadena) {
            cadena = StringUtils.replace(cadena, ":", "");
            cadena = StringUtils.replace(cadena, "--", "");
            cadena = StringUtils.replace(cadena, "\'", "");
            cadena = StringUtils.replace(cadena, "\"", "");
            cadena = StringUtils.replace(cadena, " \" ", "");
            cadena = StringUtils.replace(cadena, "/", "");
            cadena = StringUtils.replace(cadena, " / ", "");
            cadena = StringUtils.replace(cadena, ".", "");
            cadena = StringUtils.replace(cadena, ",", "");
            cadena = StringUtils.replace(cadena, "*", "");
            cadena = StringUtils.replace(cadena, "(", "");
            cadena = StringUtils.replace(cadena, ")", "");
            cadena = StringUtils.replace(cadena, "•", "");
            cadena = StringUtils.replace(cadena, "·", "");
//		cadena = StringUtils.replace(cadena, "ñ", "n");
            return cadena;
        }

        public static String removeHexaChars(String cadena) {
            cadena = StringUtils.replace(cadena, "\n", "");
            cadena = StringUtils.replace(cadena, "\t", "");
            cadena = StringUtils.replace(cadena, "\b", "");
            cadena = StringUtils.replace(cadena, "\r", "");
            cadena = StringUtils.replace(cadena, "\f", "");
            cadena = StringUtils.replace(cadena, "\\", "");
            cadena = StringUtils.replace(cadena, ".", "");
            cadena = StringUtils.replace(cadena, "º", "");
            cadena = StringUtils.replace(cadena, "°", "");
            cadena = StringUtils.replace(cadena, "?", "");
            cadena = StringUtils.replace(cadena, "¿", "");
            cadena = StringUtils.replace(cadena, "€", "euros");
//		cadena = StringUtils.replace(cadena, "ñ", "n");
            return cadena;
        }

        public static String removeHtmTags(String cadena) {
            cadena = StringUtils.replace(cadena, "<br/>", "");
            cadena = StringUtils.replace(cadena, "</br>", "");
            cadena = StringUtils.replace(cadena, "</ br>", "");
            cadena = StringUtils.replace(cadena, "<br />", "");
            cadena = StringUtils.replace(cadena, "<p>", "");
            cadena = StringUtils.replace(cadena, "</p>", "");
            cadena = StringUtils.replace(cadena, "<p/>", "");
            cadena = StringUtils.replace(cadena, "<strong>", "");
            cadena = StringUtils.replace(cadena, "</strong>", "");
            return cadena;
        }

        public static String replaceComillasVariasConEspaciosPorDobles(String cadena) {
            String res = "";
            res = StringUtils.replace(cadena, "\"\" \"\"", "\"\"");
            res = StringUtils.replace(res, "\"\" \"\" \"\"", "\"\"");
            res = StringUtils.replace(res, "\"\" \"\" \"\" \"\"", "\"\"");
            res = StringUtils.replace(res, "\"\" \"\" \"\" \"\" \"\"", "\"\"");
            res = StringUtils.replace(res, "\"\" \"\" \"\" \"\" \"\" \"\"", "\"\"");
            res = StringUtils.replace(res, "\"\" \"\" \"\" \"\" \"\" \"\" \"\"", "\"\"");
            res = StringUtils.replace(res, "\"\" \"\" \"\" \"\" \"\" \"\"", "\"\"");
            res = StringUtils.replace(res, "\"\" \"\" \"\" \"\" \"\"", "\"\"");
            res = StringUtils.replace(res, "\"\" \"\" \"\" \"\"", "\"\"");
            res = StringUtils.replace(res, "\"\" \"\" \"\"", "\"\"");
            return res;
        }

        public static String replaceComillasVariasPorDobles(String cadena) {
            String cadena_tmp = "";
            if(!StringUtils.contains(cadena, "son igual a los Monedas")){

                cadena_tmp = cadena;

                List<Integer> occurrences = listOccurrencesByString(cadena_tmp, "\"");
                List<Integer> occurrences_out = new ArrayList<Integer>();
                List<Integer> toReplace = new ArrayList<Integer>();

                if(occurrences.size() % 2 == 0){
                    if(ocurrenciasDuplicadas(occurrences)){
                        for(int pos = 0; pos < occurrences.size(); pos++){
                            if(occurrences.size() > 2){
                                int current_pos_comilla_in_cadena = occurrences.get(pos);
                                int next_pos_comilla_in_cadena = occurrences.get(pos+1) != null ? occurrences.get(pos+1) : -1; 		// - ha finalizado el bucle

                                if((current_pos_comilla_in_cadena+1) == next_pos_comilla_in_cadena) {
                                    toReplace.add(current_pos_comilla_in_cadena);
                                    toReplace.add(next_pos_comilla_in_cadena);

                                    pos++;
                                    try {

                                        Integer posc = 0;

                                        if(occurrences.size() != pos+1)
                                            posc = occurrences.get(pos+1);
                                        else
                                            posc = -1;

                                        if(posc != (next_pos_comilla_in_cadena+1)){

                                            int ini = toReplace.get(0);
                                            int fin = toReplace.get(toReplace.size()-1);

                                            String a 	= StringUtils.substring(cadena_tmp, ini, fin+1);
                                            cadena_tmp 		= StringUtils.replace(cadena_tmp, a, "\"\"");
                                            occurrences = listOccurrencesByString(cadena_tmp, "\"");

                                            occurrences = removerEchos(occurrences, occurrences_out);

                                            if(occurrences.size() > 2) {
                                                for(int e = 0; e < toReplace.size(); e++){

                                                    int ocurrence 	= occurrences.get(e);
                                                    int replace  	= toReplace.get(e);

                                                    if(ocurrence == replace){
                                                        occurrences_out.add(toReplace.get(e));
                                                        occurrences.remove(e);
                                                        toReplace.remove(e);
                                                    } else {
                                                        e++;
                                                    }
                                                    e--;
                                                }
                                            }

                                            toReplace = new ArrayList<Integer>();

                                            pos = -1;

                                        }
                                    } catch (IndexOutOfBoundsException e){	}
                                }
                            }
                        }
                    }
                }

                else {
                    cadena_tmp = "El numero de comillas es impar. No se puede tratar.";
                }
            }

            return cadena_tmp;
        }

        private static List<Integer> removerEchos(List<Integer> occurrences, List<Integer> occurrences_out) {

            for(Integer e : occurrences_out){
                occurrences.remove(e);
            }

            return occurrences;


        }

        private static boolean ocurrenciasDuplicadas(List<Integer> posiciones_comillas) {
            // es incorrecto cuando: hay mas de "" juntas (ej: """") == pos 23, 24, 40, 41, 61, 62
            boolean correcto = false;

            for(int i = 0; i < posiciones_comillas.size()-1; i++){

                int current = posiciones_comillas.get(i);
                int next 	= posiciones_comillas.get(i+1) != null ? posiciones_comillas.get(i+1) : -1;

                if(next!=-1){

                    int count = 0;

                    while(current + 1 == next){
                        i++;
                        current = posiciones_comillas.get(i);

                        try{

                            next 	= posiciones_comillas.get(i+1);

                        }catch(IndexOutOfBoundsException e){

                            next 	= -1;

                        }

                        count++;
                    }

                    if(count > 1){
                        correcto = true;
                        break;
                    }

                }


            }

            return correcto;

        }

        public static String replaceAccents(String cadena) {
            cadena = StringUtils.replace(cadena, "á", "a");
            cadena = StringUtils.replace(cadena, "é", "e");
            cadena = StringUtils.replace(cadena, "í", "i");
            cadena = StringUtils.replace(cadena, "ó", "o");
            cadena = StringUtils.replace(cadena, "ú", "u");
            cadena = StringUtils.replace(cadena, "Á", "A");
            cadena = StringUtils.replace(cadena, "É", "E");
            cadena = StringUtils.replace(cadena, "Í", "I");
            cadena = StringUtils.replace(cadena, "Ó", "O");
            cadena = StringUtils.replace(cadena, "Ú", "U");
            return cadena;
        }

        public static int numOcurrencesByString(String cadena, String occurrence) {
            List<Integer> lista = listOccurrencesByString(cadena, occurrence);
            return lista != null ? lista.size() : 0;
        }

        public static Integer listOccurrencesByWord(String cadena, String occurrence) {
            String[] a = StringUtils.split(cadena, " ");
            int count = 0;

            for(int e = 0; e < a.length; e++){
                if(StringUtils.equalsIgnoreCase(occurrence, a[e]))
                    count++;
            }

            return count;
        }

        public static List<Integer> listOccurrencesByString(String cadena, String occurrence) {

//		System.out.println("+++" + cadena);



            List<Integer> posiciones = new ArrayList<Integer>();

            for(int pos = 0; pos < cadena.length(); pos++){

                String toCompare = Character.toString(cadena.charAt(pos));

                if(StringUtils.equals(occurrence, toCompare) || StringUtils.contains(occurrence, toCompare))		// cambié la orientacion
                    posiciones.add(pos);

            }

            return posiciones;
        }

        public static List<Integer> listDoubleOccurrencesByString(String cadena, String occurrence1, String occurrence2) {

            List<Integer> posiciones = new ArrayList<Integer>();

//		System.out.println("++" + cadena);

            for(int pos = 0; pos < cadena.length(); pos++){

                String toCompare = Character.toString(cadena.charAt(pos));

                if(StringUtils.equals(toCompare, occurrence1) || StringUtils.equals(toCompare, occurrence2))
                    posiciones.add(pos);

            }

            return posiciones;
        }

        public static List<String> listEntrecomillados(String cadena){
//		System.out.println("+" + cadena);
            List<String> res = new ArrayList<String>();

            List<Integer> comillas = listOccurrencesByString(cadena, "\"");

            for(int poc = 0; poc < comillas.size(); poc++){
                res.add(StringUtils.substring(cadena, comillas.get(poc), comillas.get(poc+1)+1));
                poc++;
            }

            return res;
        }

        public static String getEntrecomillasAfterText(String cadena, String afterText) {

            String tmp = StringUtils.substringAfter(cadena, afterText);

            // aqui he de cojer las primeras dos comillas despues del texto

            return "";
        }

        public static String quitarDoblesEspacios(String cadena) {
            return StringUtils.replace(cadena, "  ", " ");
        }

        public static String quitarEspacios(String cadena) {
            return StringUtils.replace(cadena, " ", "");
        }

        public static String reemplazarEspaciosYCapitalizarCadena(String cadena) {
            List<String> listado = new ArrayList<String>();
            listado.add(cadena);
            return reempleaarEspaciosYCapitalizar(listado).get(0);
        }

        public static List<String> reempleaarEspaciosYCapitalizar(List<String> cadena) {
            for(int a = 0; a < cadena.size(); a++){
                String capitalized = StringUtils.replace(WordUtils.capitalizeFully(cadena.get(a)), " ", "");
                capitalized = replaceAccents(capitalized);
                cadena.set(a, capitalized);
            }
            return cadena;
        }

        public static String reemplazarEspaciosYCapitalizar(String cadena) {

            String tmp = "";

            if(StringUtils.contains(cadena, " ")){
                String[] s = StringUtils.split(cadena, " ");

                for(String str : s)
                    tmp += StringUtils.capitalize(str);

                cadena = tmp;
            }

//		cadena = WordUtils.capitalizeFully(cadena);   	//se quita porque en algunos casos da el identificador "Numerooferta"
            cadena = StringUtils.replace(cadena, "  ", "");
            cadena = StringUtils.replace(cadena, " ", "");
            return cadena;

        }

        public static String separarPorLetrasCapitales(String cadena) {

            if(cadena != null || StringUtils.isNotEmpty(cadena)){
                boolean hasUpperCaseLetters = false;

                String cad = StringUtils.replace(cadena, "-", " ");
                cad = StringUtils.replace(cad, "  ", " ");
                cad = StringUtils.replace(cad, " ", "");


                for(int a = 0; a < cad.length(); a++){

                    String c = Character.toString(cad.charAt(a));

                    if(!StringUtils.equals(c, "\"")){
                        if(StringUtils.equals(c, c.toUpperCase())){
                            hasUpperCaseLetters = true;
                            break;
                        }
                    }

                }
                if(hasUpperCaseLetters){
                    return StringUtils.replaceAll(cadena,
                            String.format("%s|%s|%s",
                                    "(?<=[A-Z])(?=[A-Z][a-z])",
                                    "(?<=[^A-Z])(?=[A-Z])",
                                    "[\\p{M}]",
                                    "(?<=[A-Za-z])(?=[^A-Za-z])"
                            ), " ");
                } else {
                    return cadena;
                }
            } else {
                return cadena;
            }
        }

        public static String getHoraMinutos(){
            DateFormat dateFormat = new SimpleDateFormat("HHmm");
            Date data = new Date();
            return (dateFormat.format(data));
        }

        public static String getHora(){
            DateFormat dateFormat = new SimpleDateFormat("HHmmss");
            Date data = new Date();
            return (dateFormat.format(data));
        }

        public static String getFecha(){
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date data = new Date();
            return (dateFormat.format(data));
        }
    }


