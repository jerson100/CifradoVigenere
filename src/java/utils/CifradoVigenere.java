package utils;

/**
 *
 * @author JERSON
 */
public class CifradoVigenere {

    private static final String[] ABC = new String[]{"a", "b", "c", "d", "e", "f",
        "g", "h", "i", "j", "k", "l", "m", "n", "ñ", "o", "p", "q", "r", "s", "t",
        "u", "v", "w", "x", "y", "z"};
    
    public static void main(String[] args) {
        String message = "hola mundo, de. paz";
        String key = "hol";
        System.out.println("Mensaje:\t"+message+"\n"
                           + "clave:\t\t"+key);
        String cifrado = cifrar(message, key);
        System.out.println("cifrado:\t" +cifrado+"\n"
                           + "descifrado:\t"+descifrar(cifrado, key));
    }
    
    public static String cifrar(String message, String key){
        //hola - split
        //new String[]{"h","o","l","a"}
        message = message.toLowerCase();
        key = key.toLowerCase();
        //mensaje = holamundo
        //key =     cielo
        key = fill(key,message, message.length());
        String messageSplit[] = message.split("");
        String keySplit[] = key.split("");
        String response = "";
        int xi,ki,newIndex;
        for(int i = 0; i < messageSplit.length; i++){
            xi = indexOf(messageSplit[i]);
            ki = indexOf(keySplit[i]);
            newIndex = (xi + ki) % ABC.length;
            response += xi == -1 || ki == -1 ? messageSplit[i] : ABC[newIndex];
        }
        return response;
    }
    
    public static String descifrar(String txtCifrado, String key){
        int ci, ki, newIndex;
        txtCifrado = txtCifrado.toLowerCase();
        key = key.toLowerCase();
        key = fill(key,txtCifrado, txtCifrado.length());
//        System.out.println(key);
        String messageSplit[] = txtCifrado.split("");
        String keySplit[] = key.split("");
        String response = "";
        for(int i = 0; i < messageSplit.length; i++){
            ci = indexOf(messageSplit[i]);
            ki = indexOf(keySplit[i]);
            if(ci - ki >= 0){
                newIndex = (ci - ki) % ABC.length;   
            }else{
                newIndex = (ci - ki + ABC.length) % ABC.length;   
            }
            response += ci == -1 || ki == -1 ? messageSplit[i] : ABC[newIndex];
        }
        return response;
    }
    
    public static String fill(String txt, String message, int longitud){
        String newText = "";
        int i = 0;
        int i2 = 0;
        while(longitud > 0){
            if(String.valueOf(message.charAt(i2)).matches("[^a-zñ]")){
                newText += message.charAt(i2);
            }else{
                newText += txt.charAt(i);
                if(i == txt.length() - 1){
                    i = 0;
                }else{
                    i++;
                }
            }
            i2++;
            longitud--;
        }
        return newText;
    }
    
    public static int indexOf(String letter){
        int index =  -1;
        for(int i = 0;i<ABC.length; i++){
            if(ABC[i].equals(letter)){
                return i;
            }
        }
        return index;
    }
    
}
