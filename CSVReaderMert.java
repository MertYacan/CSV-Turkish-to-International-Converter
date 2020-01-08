/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csv.reader.mert;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author Mert Yacan
 */
public class CSVReaderMert {
    static int howmanysutun = 6;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<String>();
        String filePath = "C:\\default.csv";
        File file = new File(filePath);
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

		int returnValue = jfc.showOpenDialog(null);
		// int returnValue = jfc.showSaveDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			file = jfc.getSelectedFile();
			filePath = file.getAbsolutePath();
		}else{
                    System.exit(0);
                }
                
        try {
            Scanner inputStream = new Scanner(file).useDelimiter(";\\s*");
            //inputStream.next(); //csvnin ilk satýrýný while loopuna girmeden önce nextleyerek iþleme sokmuyoruz. genelde baþlýklar bulunuyor ilk line'da.
            while (inputStream.hasNext()) {
                String data = inputStream.next();
                list.add(data);
                System.out.println(data);
            }
            inputStream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CSVReaderMert.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("----------------");
        
        //Checking our collected data
        for (String r : list) {
            System.out.println(r);
        }
        
        //Here we create our new file according to the imported file's location and with a new name
        String newFileLoc = filePath.substring(0, filePath.length() - 4) + "new.csv";
        File textFile = new File(newFileLoc);
        if (!textFile.exists()) {
            file.createNewFile();
        }
        
        //Lets write to our new file
        BufferedWriter out = new BufferedWriter(new FileWriter(textFile));
        try {
            for(int i = 0; i<list.size(); i++){
                //if(i%howmanysutun==0){
                //    out.newLine();
                //}
                String tempStrwithproblem;
                tempStrwithproblem = list.get(i);
                String tempStr = tempStrwithproblem.replace(',','.');
                if(tempStr.equals("") || !tempStr.contains(" ")){
                //if(tempStr.equals("") || tempStr.matches("\\S+")){
                    out.append(tempStr + ",");
                }else{
                    if("\"".equals(tempStr.charAt(0))){
                        out.append(tempStr + ",");
                    }else{
                        tempStr = "\"" + tempStr + "\"";
                        out.append(tempStr + ",");
                    }
                }
            }
        } finally {
            out.close();
        }
    }

}
