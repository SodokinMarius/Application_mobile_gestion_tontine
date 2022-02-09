package com.sodyam.philomabtontine.Outils;

import static com.sodyam.philomabtontine.utils.AllConstants.TAG;
import static org.apache.commons.lang3.CharSetUtils.count;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sodyam.philomabtontine.model.T_Agent;
import com.sodyam.philomabtontine.model.T_Client;
import com.sodyam.philomabtontine.model.T_Paiement;

import java.lang.reflect.Type;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

public class DateConverter {
 private List<client_par_souscription> clientsParSouscription;
 private  List <pointParTypeLot> mPointParTypeLots;
 private List<T_Client> Clients;
 private  List<T_Agent> Agents;
 private List <T_Paiement> Paiements;
    public DateConverter() {
    }

    //dates
    @TypeConverter
    public static String ConvertFromSourceToString(T_Agent agent)
    {
        return agent.toString();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static Date ConvertStringToDate(String date) {
        String FormatNormal = "YYYY-MM-dd";
        //EEE MMM dd hh:mm:ss 'GMT' yyyy
        SimpleDateFormat Formater = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            Formater = new SimpleDateFormat(FormatNormal);
        }
        try {
            Date dateconvert = (Date) Formater.parse(FormatNormal);
            return dateconvert;
        } catch (Exception error) {
        }
        //Log.d("erreur de convertion ", " Date incorrect ! "+error.toString())   }
        return new Date(0);
    }

    @TypeConverter
    public static String ConvertDateToString(Date dateConvert)
    {
        SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-mm-dd");
        return DateFormat.format(dateConvert);
    }

    /**
     * CONVERTION DES DATES
     * @param value
     * @return
     */
    @TypeConverter
    public Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public Long dateToTimestamp(Date date) {
        if (date == null) {
            return null;
        } else {
            return date.getTime();
        }
    }

    /**
     * ====== CONVERTION DES OBJETS  LISTE DES CLIENTS==============
     * @param data
     * @return
     */
    @TypeConverter
    public static List<T_Client> storedStringToMyObjects(String data) {
        Gson gson = new Gson();
        if (data == null) {
            return Collections.emptyList();
        }
        Type listType = new TypeToken<List<T_Client>>() {}.getType();
        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String myObjectsToStoredString(List<T_Client> myObjects) {
        Gson gson = new Gson();
        return gson.toJson(myObjects);
    }

    /**
     * METODES DE GESTION DES DATES INTEGER ET STRING
     * @param date
     * @return
     */
    public static Integer convertDateStringToInteger(String date)
    {
        //Supression des espaces et du symbolle Slash /
        String dateEnv;
////        String symb="/";
//        if (date.contains(symb) || date.contains(" "))
//        {
            dateEnv=date.trim().replaceAll("/","");
            dateEnv.replaceAll(" ","");
//        }
//
//        else {
            dateEnv=date.trim().replaceAll("-","");
//        }

        /**
         * INVERSER LA DATE EN JJ-MM-AAAA
         */

        return Integer.parseInt(dateEnv);
    }


    public static long ConvertDateStringToIntegerAfterRemovingSpecialsCharacters(Context context,String date)
    {
        String anne,mois,jour;
        long datelong=0;
//        date=dates.replaceAll("/","");
//        date=dates.replaceAll("-","");
//        date=dates.replaceAll(" ","");
//
//        Log.e(TAG,"DATE DEBUT EN FONCTION ========================="+ dates);
        Log.e(TAG,"DATE DEBUT SANS CARACTERE EN FONCTION ========================="+ date);

//        if(date.length()<10)
//        {
//            anne=date.substring(0,4);
//            mois=date.substring(5,7);
//            jour=date.substring(8,9);
//        }
//        else
//        {
//            anne=date.substring(0,4);
//        Log.e(TAG,"ANNE : ==========================="+anne);
//
//
//            mois=date.substring(5,7);
//        Log.e(TAG,"MOIS : ==========================="+mois);
//            jour=date.substring(8,10);
//        Log.e(TAG,"JOUR: ==========================="+jour);
//       // }

//        if(anne.contains("/") || mois.contains("/") || jour.contains("/"))
//        {
//            Toast.makeText(context, "DATE INVALIDE !!", Toast.LENGTH_SHORT).show();
//            datelong=0;
//        }
////        else if(Integer.parseInt(jour) <=0 || Integer.parseInt(jour)>31 || Integer.parseInt(mois)<=0 || Integer.parseInt(mois)>12)
////        {
////           // Toast.makeText(context, "LE JOUR OU LE MOIS DE LA DATE ENTREE EST INVALIDE !!", Toast.LENGTH_SHORT).show();
////        }
//        else
//        {


            datelong=Long.parseLong(date);
//             datelong=Integer.parseInt(anne)+Integer.parseInt(mois)+Integer.parseInt(jour);
       // }

        return  datelong;

    }

    public static  String deleteSpecialCharacters(Context context,String date)
    {
        String anne,mois,jour;
        String datelong="";

        Log.e(TAG,"DATE DEBUT SANS CARACTERE EN FONCTION ========================="+ date);
        try {

            if ((date.charAt(2)) == '/' || ((date.charAt(5)) == '/')) {
                jour = date.substring(0, 2);
                mois = date.substring(3, 5);
                anne = date.substring(6, 10);
            } else {

                anne = date.substring(0, 4);
                mois = date.substring(5, 7);
                jour = date.substring(8, 10);
            }

            if (anne.contains("/") || mois.contains("/") || jour.contains("/")) {
                Toast.makeText(context, "VEUILLEZ RESPECTER LE FORMAT INDIQUE !!", Toast.LENGTH_SHORT).show();
            } else {
                datelong = anne + mois + jour;
            }

        } finally {

            return datelong;
        }


    }


    public static String convertDateIntegerToString(Integer date)
    {
        String newD;
       if (count(Integer.toString(date).trim())<8)
        {
            newD="0"+date.toString();
            return newD.toString();
        }
        return Integer.toString(date);
    }

    public  String AjoutSymbollToStringDate(String date)
    {
        return  date.replaceAll("/","-");
    }
    /**
     * SECTIONNER UNE DATE INT EN JOUR MOIS ANNEE
     */

    public static String ConvertNumericNewDatetoAllLetter()
    {
        java.sql.Date date_actuelle=new java.sql.Date(System.currentTimeMillis());
        int anne= Integer.parseInt(date_actuelle. toString().substring(0,4));
       int mois= Integer.parseInt(date_actuelle.toString().substring(5,7));
       int date=Integer.parseInt(date_actuelle.toString().substring(8));
        int jour=date_actuelle.getDay();
       String dates=date+"-"+mois+"-"+anne;

       String day,month;
        switch (jour)
        {
            case 1:
                day="Lundi";
                break;
            case 2:
                day="Mardi";
                break;
            case 3:
                day="Mercredi";
                break;
            case 4:
                day="Jeudi";
                break;
            case 5:
                day="Vendredi";
                break;
            case 6:
                day="Samedi";
                break;
            case 7:
                day="Dimanche";
                break;
            default:
                day="Dimanche";
                break;
        }

  switch (mois){
      case 01:
          month = "Janvier";
          break;
      case 02:
          month = "Fevrier";
          break;
      case 03:
          month = "Mars";
          break;
      case 04:
          month="Avril";
          break;
      case 05:
          month="Mai";
          break;
      case 06:
          month="Juin";
          break;
      case 07:
          month="Juillet";
          break;
      case 8:
          month="Août";
          break;
      case 9:
          month="Septembre";
          break;
      case 10:
          month="Octobre";
          break;
      case 11:
          month="Novembre";
          break;
      case 12:
          month="Décembre";
          break;
      default:
          month=mois+"";
          break;

  }
   return  day+" "+date+" "+month+" "+anne;

    }

}
