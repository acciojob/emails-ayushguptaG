package com.driver;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)

    private List<Mail> inbox;
    private List<Mail> trash;
        public Gmail(String emailId, int inboxCapacity) {
            super(emailId);
            this.inboxCapacity= inboxCapacity;
            this.inbox= new ArrayList<>();
            this.trash= new ArrayList<>();
        }

    public List<Mail> getInbox() {
        return inbox;
    }

    public List<Mail> getTrash() {
        return trash;
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        Mail newMail= new Mail(date, sender, message);

        if(inbox.size()== inboxCapacity){
            Mail oldest= inbox.get(0);
            inbox.remove(0);
            trash.add(oldest);
            inbox.add(newMail);
        }
        else{
            inbox.add(newMail);
        }
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        for(Mail mail : inbox){

            if(mail.getMessage().equals(message)){
                trash.add(mail);
                inbox.remove(mail);
                break;
            }
        }

    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        if(inbox.isEmpty()) return null;
        else{
            Mail mail= inbox.get(inbox.size()-1);
            String message= mail.getMessage();
            return message;
        }
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(inbox.isEmpty()) return null;
        else{
            Mail mail= inbox.get(0);
            String message= mail.getMessage();
            return message;
        }
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int numberOfMails=0;


        for(int i=0; i<inbox.size(); i++){

            if(inbox.get(i).getDate().compareTo(start) >=0) {
                while (i < inbox.size() && inbox.get(i).getDate().compareTo(start) >= 0 && inbox.get(i).getDate().compareTo(end) <= 0)
                {
                    numberOfMails++;
                    i++;
                }
            }

            if(numberOfMails > 0) break;
        }
        return numberOfMails;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
            return inbox.size();

    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return trash.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
        trash.clear();

    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;
    }
}
