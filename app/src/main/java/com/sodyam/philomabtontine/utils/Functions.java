package com.sodyam.philomabtontine.utils;

import android.app.Activity;
import android.widget.EditText;

import com.sodyam.philomabtontine.Databasephilomabtontine;
import com.sodyam.philomabtontine.R;
import com.sodyam.philomabtontine.model.T_Agent;

public class Functions {
    public static void initDatabase(Activity activity) {
        Databasephilomabtontine database = Databasephilomabtontine.getInstance(activity);
        if (database.AgentDao().getListeAgents().size() == 0) {
            T_Agent t_agent = new T_Agent();
            t_agent.setAdresse("GODOMEY");
            t_agent.setNomAgent("TATA");
            t_agent.setPrenomAgent("Béatrice");
            t_agent.setDateDeDebut("01-12-2021");
            t_agent.setPoste("SUPERVISEUR");
            t_agent.setTelephone("66562771");
            t_agent.setMot_de_passe("Passw0rd@2022");
            database.AgentDao().insertAgent(t_agent);
        }
    }

    public static boolean checkIsEmpty(Activity activity, EditText editText) {
        String input = editText.getText().toString().trim();
        if (input.isEmpty()) {
            editText.setError(activity.getString(R.string.isRequired));
            return false;
        } else {
            editText.setError(null);
            return true;
        }
    }


    public static boolean verifier_date(String date) {
        String chaine = "0123456789/";
        boolean verifier = true;
        if ((date.charAt(2)) == '/' || ((date.charAt(5)) == '/')) {
            for (int i = 0; i < date.length(); i++) {

                if (((date.charAt(i) == '/') && (i != 2 || i != 5)) || !chaine.contains(date.charAt(i) + "")) {
                    verifier = false;
                    break;
                }
            }
        } else if ((date.charAt(4)) == '/' || ((date.charAt(7)) == '/')) {
            for (int i = 0; i < date.length(); i++){

                if (((date.charAt(i) == '/') && (i != 2 || i != 5)) || !chaine.contains(date.charAt(i) + "")) {
                    verifier = false;
                    break;
                }
            }
        }
        return verifier;
    }
}