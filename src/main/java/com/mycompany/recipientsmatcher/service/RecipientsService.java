package com.mycompany.recipientsmatcher.service;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.mycompany.recipientsmatcher.model.Output;
import com.mycompany.recipientsmatcher.model.Pickup;
import com.mycompany.recipientsmatcher.model.Recipient;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

@Service
@PropertySource("classpath:/application.properties")
public class RecipientsService {
    
    List<Pickup> mPickups;
    List<Recipient> mRecipients;
    
    @Value("${input_folder}")
    private String mInputFolder;
    
    @Value("${output_folder}")
    private String mOutputFolder;
    
    @Value("${pickups_file_name}")
    private String mPickupsFileName;
    
    @Value("${recipients_file_name}")
    private String mRecipientsFileName;
    
    @Value("${output_file_name}")
    private String mOutputFileName;
    
    @Value("${recipients_time_zone_id}")
    private String mRecipientsTimeZoneId;
    
    @Autowired
    private com.mycompany.recipientsmatcher.service.LocationService locationService;
    
    @Autowired
    private com.mycompany.recipientsmatcher.service.AcceptCheckService acceptCheckService;
    
    @Autowired
    private com.mycompany.recipientsmatcher.service.TimeCheckService timeCheckService;
    
    private ArrayList<ArrayList<Output>> mMatchedList = new ArrayList<ArrayList<Output>>();

   
   public int getPickupCount(){return mPickups.size();}
   public int getRecipientCount(){return mRecipients.size();}
    
    
	@SuppressWarnings({ "rawtypes", "unchecked" }) 
	// Used @SuppressWarnings to suppress unchecked generic operations, which i am aware of. 
	public void init() throws IOException
    {
        String pickups_path = mInputFolder + "/" + mPickupsFileName;  
        String recipients_path = mInputFolder + "/" + mRecipientsFileName;
       
       
            Reader reader_pickups = Files.newBufferedReader(Paths.get(pickups_path));
            Reader reader_recipients = Files.newBufferedReader(Paths.get(recipients_path));
      
       {
            CsvToBean csvToBean_pickups = new CsvToBeanBuilder(reader_pickups)
                    .withType(Pickup.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            
            CsvToBean csvToBean_recipients = new CsvToBeanBuilder(reader_recipients)
                    .withType(Recipient.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            
            // mPickups and mRecipients are Lists which are declared above
            mPickups = csvToBean_pickups.parse();
            mRecipients = csvToBean_recipients.parse();
       
        }
       
    }
    @SuppressWarnings("unchecked")
	public boolean proc()
    {
        int pickup_count = mPickups.size();
        int recipient_count = mRecipients.size();
        if(pickup_count == 0 || recipient_count == 0)
        {
            System.out.println("input data error!!");
            return false;
        }
        
     // for one pickup we are checking for all recipients who can accept that food
        for(int i = 0; i < pickup_count; i++) 
        {
            Pickup pickup = mPickups.get(i);
            ArrayList<Output> outputArray = new ArrayList<Output>();
            for(int j = 0; j < recipient_count; j++)
            {
                Recipient recipient = mRecipients.get(j);
                // short listing recipients whose food restrictions match with a specific pickup 
                if(!acceptCheckService.accept(pickup, recipient))
                    continue;
                // further short listing recipients based on their open timings
                if(!timeCheckService.openCheck(pickup, recipient))
                    continue;
                // Considering only those short listed recipients who are in the radius of 5 miles.
                double distance = locationService.getDistance(pickup, recipient);
                if(distance > 5)
                    continue;
                outputArray.add(new Output(pickup.getFirstName(), pickup.getLastName(), recipient.getFirstName(), recipient.getLastName(), distance, pickup.getPickupAt()
                ,pickup.getCategories(), recipient.getRestrictions()));
                
            }
            if(outputArray.size() > 0)
                Collections.sort(outputArray);
            mMatchedList.add(outputArray);
        }
        return true;
    }
    public void writeResult() throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException
    {
        String output_path = mOutputFolder + "/" + mOutputFileName;
         try (
            Writer writer = Files.newBufferedWriter(Paths.get(output_path));

            CSVWriter csvWriter = new CSVWriter(writer,
                    CSVWriter.DEFAULT_SEPARATOR,
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
        ) {
                       
            String[] headerRecord = {"PickupFirstName", "PickupLastName", "PickupAt", "Categories", "RecipientFirstName", "RecipientLastName",  "Restrictions", "Distance"};
            csvWriter.writeNext(headerRecord);
            
            for(int i = 0; i < mMatchedList.size(); i++)
            {
                ArrayList<Output> child = mMatchedList.get(i);
                for(int j = 0; j < child.size(); j++)
                {
                    Output entry = child.get(j);
                    /* If it a new pick up entry, we are writing details of pickup.
                      if pickup is already listed with one recipient then directly writing the next qualified recipient */
                    String[] output_entry = {j == 0 ? entry.getPickupFirstName() : "",
                    		                 j == 0 ? entry.getPickupLastName() : "" ,
                                             j == 0 ? entry.getPickupAt() : "",
                                             j == 0 ? Integer.toString(entry.getCategories()) : "", 
                                          entry.getRecipientFirstName(), entry.getRecipientLastName(),
                       Integer.toString(entry.getRestrictions()), Double.toString(entry.getDistance())};
                    csvWriter.writeNext(output_entry);
                }
                
            }

            
        }
    }
    
 }

 