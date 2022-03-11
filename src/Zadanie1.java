import java.io.*;
import java.util.*;

public class Zadanie1 {

    public static void main(String[] args) {

        String[] names = {"Lp.", "Producent", "Przekątna", "Rozdzielczość", "Typ ekranu", "Dotykowy ekran", "CPU", "Rdzenie", "Taktowanie [MHz]", "Ilość RAM",
                          "Pojemność dysku", "Rodzaj dysku", "GPU", "Pamięć GPU", "OS", "Napęd ODD"};
        ArrayList<String> name = new ArrayList<String>(Arrays.asList(names));

        HashMap<String, Integer> qunatity = new HashMap<>();

        FileReader fileReader = null;
        try {
            fileReader = new FileReader("src\\katalog.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Bląd podczas otwarcia pliku");
        }

        if(fileReader == null) { }
        else {
            BufferedReader reader = new BufferedReader(fileReader);
            try {
                for(int i = 0; i<429;i++)
                    System.out.print("-");

                System.out.print("\n|\t"+name.get(0)+"\t");
                for(int i = 1; i<name.size();i++) {
                    show_element(name.get(i));
                }
                System.out.print("|\n");
                for(int i = 0; i<429;i++)
                    System.out.print("-");
                System.out.print("\n");
                String line;
                int rob =1;
                while((line = reader.readLine()) != null) {
                    String [] elements = line.split(";",16);
                    System.out.print("|\t" + rob + "\t");

                        int i = 0;
                        for(String element : elements) {
                            if(element.isEmpty() && i < elements.length -1)
                                show_element("Brak danych");
                            else if(!element.isEmpty())
                                show_element(element);

                            if(i == 0 ) {
                                Integer value = qunatity.get(element);
                                if(value != null) {
                                    value++;
                                    qunatity.replace(element,value);
                                }
                                else
                                {
                                    if(qunatity.containsKey(element)){
                                        qunatity.replace(element,1);
                                    }
                                    else{
                                        qunatity.put(element,1);
                                    }
                                }
                            }
                            i++;
                        }
                        System.out.print("|\n");
                    rob++;
                }
                for(int i = 0; i<429;i++)
                    System.out.print("-");

            } catch (IOException e) {
                System.out.println("Bład odczytu pliku");
            }

            try {
                fileReader.close();
            } catch (IOException e) {
                System.out.println("Błąd podczas zamykania pliku");
            }

            showQuantityLaptop(qunatity);
        }
    }

    private static void showQuantityLaptop(HashMap<String, Integer> qunatity) {
        System.out.println("\nLiczba laptopów każdego z producenta:");
        for(Map.Entry entry : qunatity.entrySet()) {
            System.out.println(entry.getKey() + ": "+entry.getValue());
        }
    }

    public static void show_element(String element) {
        System.out.print("|\t"+element);
        for(int i=0;i<22-element.length();i++){
            System.out.print(" ");
        }
        System.out.print("\t");
    }
}