package Util;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Baltzar on 2016-12-14.
 */
public class Logic {

        public static void main(String[] args) throws ParseException {

            BufferedReader loggReader = null;
            try {
                loggReader = new BufferedReader(new FileReader((new File("C:\\Users\\Baltzar\\Wagner\\Temp\\test\\skarptest\\debug.txt"))));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.exit(0);
            }

            HashMap<String, Long> timeLoggs = new HashMap<String, Long>();
            HashMap<String, Date> startTimeForFunction = new HashMap<String, Date>();
            HashMap<String, Integer> numberOfCalls = new HashMap<String, Integer>();

            String readLine, functionName;
            long diff;
            Date timeStamp;

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            try {
                while ((readLine = loggReader.readLine()) != null) {
                    if (readLine.length() > 1) {
                        // Regex is for date (yyyy-MM-dd HH:mm:ss.SSS)
                        if (readLine.matches("[0-9]{4}([- :][0-9]{2}){5}.[0-9]{3}")) {
                            timeStamp = df.parse(readLine);
                            readLine = loggReader.readLine();

                            //Splitting this: index.aspx.cs (2.0)/#1929/getMarkerPositions Start
                            functionName = readLine.split("/")[2].split(" ")[0];

                            if (startTimeForFunction.get(functionName) != null) {
                                diff = timeStamp.getTime() - startTimeForFunction.get(functionName).getTime();
                                if (timeLoggs.get(functionName) != null) {
                                    timeLoggs.put(functionName, timeLoggs.get(functionName) + diff);
                                    startTimeForFunction.put(functionName, null);
                                } else {
                                    timeLoggs.put(functionName, diff);
                                }

                                if (numberOfCalls.get(functionName) != null)
                                    numberOfCalls.put(functionName, numberOfCalls.get(functionName) + 1);
                                else
                                    numberOfCalls.put(functionName, 1);

                            } else {
                                startTimeForFunction.put(functionName, timeStamp);
                            }
                        }
                    }
                    System.out.println(timeLoggs.get("CreateSubMenu"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Funktion\tAntal millisekunder total exekveringstid\tAntal gånger kallad\tHur lång tid varje call tar (tot.exec / antal call)");
            for (Map.Entry<String, Long> map : timeLoggs.entrySet())
                System.out.println(map.getKey() + "\t" +map.getValue() + "\t" + numberOfCalls.get(map.getKey()) + "\t" + (double)map.getValue() / numberOfCalls.get(map.getKey()));

            /**



        }





}
