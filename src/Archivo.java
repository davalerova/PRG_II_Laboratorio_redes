/*package Laboratorio_redes;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Archivo {
    private String cadenaArchivo;
    private Scanner input;

    public Archivo() {
        this.cadenaArchivo="";
        try{this.input= new Scanner(new File("./Datos/Archivo.txt"));
        } catch(FileNotFoundException e) {
            e.printStackTrace ();
        }
    }

    public String obtenerArchivo() {
        boolean c=true;
        while(input.hasNext()) {
            this.cadenaArchivo=input.nextLine()+"\n";
            System.out.println(cadenaArchivo);
            if(c)cadenaArchivo=cadenaArchivo.substring(3, cadenaArchivo.length()-1)+"\n";
            for(int i=0;i<cadenaArchivo.length();i++)if(cadenaArchivo.charAt(i)=='0')extraerDatos(this.cadenaArchivo);
            c=false;
        }
        return cadenaArchivo;
    }

    public String toBinary(int octeto){
        String oct="";
        String oct2="";
        while(octeto>0){
            if(octeto%2==1)oct+="1";
            else oct+="0";
            octeto/=2;
        }
        int oct3=oct.length();
        for(int i=0;i<8-oct3;i++){
            oct+="0";
        }
        for(int i=oct.length()-1;i>=0;i--){
            oct2+=oct.charAt(i);
        }
        return oct2;
    }

    public void extraerDatos(String linea) {
        String ip[]=linea.split("\\.");
        System.out.print(toBinary(Integer.parseInt(ip[0]))+".");
        System.out.print(toBinary(Integer.parseInt(ip[1]))+".");
        System.out.print(toBinary(Integer.parseInt(ip[2]))+".");
        System.out.print(toBinary(Integer.parseInt(ip[3].substring(0,ip[3].length()-1)))+"\n");

        /*
        if(Integer.parseInt(ip[0])<128)System.out.println("ip: "+linea+"Clase A");
        else if(Integer.parseInt(ip[0])<192)System.out.println("ip: "+linea+"Clase B");
        else if(Integer.parseInt(ip[0])<224)System.out.println("ip: "+linea+"Clase C");
    }
}*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Archivo {

    public static void obtenerArchivo(String archivo) throws FileNotFoundException, IOException {
        String cadena;
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        int line=1;
        while((cadena = b.readLine())!=null) {
            if(line==1)cadena=cadena.substring(1,cadena.length());
            System.out.print("\nIP archivo: "+cadena+"\t\t\t\t");
            if(cadena.length()==35) {
                extraerDatos(cadena);
            }

            else{
                extraerDatos(cadena);
            }
            line++;
        }
        b.close();
    }



    public static String toBinary(int octeto){
        String oct="";
        String oct2="";
        while(octeto>0){
            if(octeto%2==1)oct+="1";
            else oct+="0";
            octeto/=2;
        }
        int oct3=oct.length();
        for(int i=0;i<8-oct3;i++){
            oct+="0";
        }
        for(int i=oct.length()-1;i>=0;i--){
            oct2+=oct.charAt(i);
        }
        return oct2;
    }


    public static int toDecimal(String octeto){
        int oct=0;
        int pot=0;
        for(int i=0;i<=octeto.length()-1;i++){

            pot=octeto.length()-i-1;
            oct+= ((octeto.charAt(i)-48)*Math.pow(2,pot));
        }

        return oct;
    }


    public static void extraerDatos(String linea) {
        String ip[]=new String[4];
        String oct1;
        String oct2;
        String oct3;
        String oct4;
        if(linea.length()!=35){
            ip=linea.split("\\.");
            oct1=(toBinary(Integer.parseInt(ip[0]))+".");
            oct2=(toBinary(Integer.parseInt(ip[1]))+".");
            oct3=(toBinary(Integer.parseInt(ip[2]))+".");
            oct4=(toBinary(Integer.parseInt(ip[3])));
            System.out.print(oct1);
            System.out.print(oct2);
            System.out.print(oct3);
            System.out.print(oct4);
        }
        else {
            oct1 = (linea.substring(0, 8));
            System.out.print(toDecimal(oct1)+".");
            oct2=(linea.substring(9,17));
            System.out.print(toDecimal(oct2)+".");
            oct3=(linea.substring(18,26));
            System.out.print(toDecimal(oct3)+".");
            oct4=(linea.substring(27,35));
            System.out.print(toDecimal(oct4));
        }

        /**
         * Clase 	        Bits iniciales 	Intervalo (*) 	                N.º de redes 	N.º de direcciones por red 	N.º de hosts por red(‡) 	Máscara de red 	    Dirección de broadcast
         * A 	            0 	            0.0.0.0   - 127.255.255.255 	126 (†) 	    16777216 	                16777214 	                255.0.0.0 	        x.255.255.255
         * B 	            10 	            128.0.0.0 - 191.255.255.255 	16384 	        65536 	                    65534 	                    255.255.0.0 	    x.x.255.255
         * C 	            110 	        192.0.0.0 - 223.255.255.255 	2097152 	    256 	                    254 	                    255.255.255.0 	    x.x.x.255
         * D (Multicast) 	1110 	        224.0.0.0 - 239.255.255.255
         * E (experimental) 1111 	        240.0.0.0 - 255.255.255.
         *
         * Reservadas:
         * x.0.0.0=Identifica la red
         * 0.0.0.0=es reservada por la IANA para identificación local.
         * x.1.1.1=dirección de difusión o broadcast
         * 127.x.x.x se reservan para designar la propia máquina. Se denomina dirección de bucle local o loopback.
         *
         * Privadas
         * Clase A: 10.0.0.0 a 10.255.255.255 (8 bits red, 24 bits hosts).
         * Clase B: 172.16.0.0 a 172.31.255.255 (16 bits red, 16 bits hosts). 16 redes clase B contiguas, uso en universidades y grandes compañías.
         * Clase C: 192.168.0.0 a 192.168.255.255 (24 bits red, 8 bits hosts). 253 redes clase C continuas, uso de compañías medias y pequeñas además de pequeños proveedores de internet (ISP)
         */
        if(oct1.charAt(0)=='0'){
            System.out.print(" - Pertenece a la clase A");
            if(oct1.substring(0,8).equals("00000000")&&oct2.substring(0,8).equals("00000000")&&oct3.substring(0,8).equals("00000000")&&oct4.substring(0,8).equals("00000000"))
                System.out.print(" - Esta dirección está reservada por la IANA para identificación local.");
            else{
                if(oct1.substring(0,8).equals("00001010")) System.out.print(" - Esta red es privada");
                if(oct2.substring(0,8).equals("00000000")&&oct3.substring(0,8).equals("00000000")&&oct4.substring(0,8).equals("00000000")) System.out.print(" - Esta dirección identifica a la red");
                if(oct2.substring(0,8).equals("11111111")&&oct3.substring(0,8).equals("11111111")&&oct4.substring(0,8).equals("11111111")) System.out.print(" - Esta es una dirección de difusión o broadcast");
            }


        }else if(oct1.substring(0,2).equals("10")){
            System.out.print(" - Pertenece a la clase B");
            if(oct3.substring(0,8).equals("00000000")&&oct4.substring(0,8).equals("00000000")) System.out.print(" - Esta dirección identifica a la red");
            if(oct3.substring(0,8).equals("11111111")&&oct4.substring(0,8).equals("11111111")) System.out.print(" - Esta es una dirección de difusión o broadcast");
            if(oct1.substring(0,8).equals("10101100")&&oct2.charAt(3)=='1')System.out.print(" - Esta red es privada");
        }else if(oct1.substring(0,3).equals("110")){
            System.out.print(" - Pertenece a la clase C");
            if(oct4.substring(0,8).equals("00000000")) System.out.print(" - Esta dirección identifica a la red");
            if(oct4.substring(0,8).equals("11111111")) System.out.print(" - Esta es una dirección de difusión o broadcast");
            if(oct1.substring(0,8).equals("11000000")&&oct2.substring(0,8).equals("10101000"))System.out.print(" - Esta red es privada");
        }else if(oct1.substring(0,4).equals("1110")){
            System.out.print(" - Pertenece a la clase D");
        }else System.out.print(" - Pertenece a la clase E");
        //Broadcast - Los mensajes que se dirigen a todas las computadoras en una red se envían como broadcast. Estos mensajes utilizan siempre La dirección IP 255.255.255.255.
    }

    public static void main(String[] args) throws IOException {
        obtenerArchivo("./Datos/Archivo.txt");
    }
}