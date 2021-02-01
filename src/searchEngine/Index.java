package searchEngine;

import java.io.*;
import java.util.TreeMap;


public class Index {

   static final String pathDocs= "documents.data";;
   static final String pathVoc = "vocabulary.data";

   static TreeMap<String,Keyword> keywords;
   static TreeMap<Integer,Document> documents;



    public Index(){
        keywords = loadVocabulary();
        documents = loadDocuments();
        //test
    }




    public static Keyword getKeyword(String key){

        return keywords.get(key);
    }

    public void addKeyword(String key, Keyword keyword){

        keywords.put(key, keyword);
    }





    public static Document getDocument(Integer id){

        return documents.get(id);
    }

    public void addDocument(Integer id, Document doc){

        documents.put(id, doc);
    }





    public static void saveVocabulary(){


        try{
            File fileTemp = new File(pathVoc);
            if (fileTemp.exists()){
                fileTemp.delete();
            }
            // Write to disk with FileOutputStream
            FileOutputStream f_out = new FileOutputStream(pathVoc);

            // Write object with ObjectOutputStream
            ObjectOutputStream obj_out = new ObjectOutputStream (f_out);

            // Write object out to disk

            obj_out.writeObject (keywords);

        }catch(IOException e){
            e.printStackTrace();
            System.exit(1);
        }
    }



    public static TreeMap<String,Keyword> loadVocabulary(){
        try{
            System.out.println("Loading keywords..");
            // Read from disk using FileInputStream
            InputStream f_in = Index.class.getClassLoader().getResourceAsStream(pathVoc);

            // Read object using ObjectInputStream
            ObjectInputStream obj_in = new ObjectInputStream (f_in);

            // Read an object
            keywords = (TreeMap<String,Keyword>)obj_in.readObject();
            System.out.println("Loading finished");
            return keywords;

        }catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    return null;
    }



    public static void saveDocuments(){

        try{

            File fileTemp = new File(pathDocs);
            if (fileTemp.exists()){
                fileTemp.delete();
            }
            // Write to disk with FileOutputStream
            FileOutputStream f_out = new FileOutputStream(pathDocs);

            // Write object with ObjectOutputStream
            ObjectOutputStream obj_out = new ObjectOutputStream (f_out);

            // Write object out to disk

            obj_out.writeObject (documents);

        }catch(IOException e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static TreeMap<Integer,Document>loadDocuments(){
        try{
            // Read from disk using FileInputStream
            System.out.println("Loading Documents..");
            System.out.println("Working Directory = " + System.getProperty("user.dir"));
            InputStream f_in = Index.class.getClassLoader().getResourceAsStream(pathDocs);

            // Read object using ObjectInputStream
            ObjectInputStream obj_in = new ObjectInputStream (f_in);

            // Read an object
            documents = (TreeMap<Integer,Document>)obj_in.readObject();
            System.out.println("Loading finished");
            return documents;

        }catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

//    public static void main(String[]args)
//    {
//        File curDir = new File(".");
//        getAllFiles(curDir);
//    }
//    private static void getAllFiles(File curDir) {
//
//        File[] filesList = curDir.listFiles();
//        for(File f : filesList){
//            if(f.isDirectory())
//                System.out.println(f.getName());
//            if(f.isFile()){
//                System.out.println(f.getName());
//            }
//        }
//
//    }



}
