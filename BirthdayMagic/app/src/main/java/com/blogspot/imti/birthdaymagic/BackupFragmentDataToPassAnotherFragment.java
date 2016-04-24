package com.blogspot.imti.birthdaymagic;

/**
 * Created by Touhidul_MTI on 25-Feb-16.
 */
public class BackupFragmentDataToPassAnotherFragment {
    static String birthdate;
    public static void setDate(String bd){
        birthdate = bd;
    }
    public static String getDate(){
        return birthdate;
    }
}
