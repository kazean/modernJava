package modernjavainaction.chap03.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ProcessFile {

    public static void main(String[] args) throws  Exception{
        processFile();
        processBuffered((BufferedReader br)->br.readLine());
        processBuffered((BufferedReader br)->br.readLine()+br.readLine());


    }

    public static String processFile() throws IOException{
        try(BufferedReader br = new BufferedReader(new FileReader("data.txt"))){
            return br.readLine();
        }
    }

    public static String processBuffered(ProcessBuffered p) throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader("data.txt"))){
            return p.process(br);
        }
    }


}

@FunctionalInterface
interface ProcessBuffered{
    public String process(BufferedReader br) throws IOException;
}