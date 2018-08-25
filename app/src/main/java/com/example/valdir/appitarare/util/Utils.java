package com.example.valdir.appitarare.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.valdir.appitarare.R;
import com.example.valdir.appitarare.model.Advertisement;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Utils {
    public static void FakeDataAdvertisement(final Context context, final String categorieName, boolean isNew,
                                             final ProgressBar progressBar, final RecyclerView recyclerView) {

        if (!categorieName.isEmpty()) {
            setLoading(true, progressBar, recyclerView);

            ArrayList<Advertisement> arrayAdv = new ArrayList<>();

            arrayAdv.add(new Advertisement(
                    "TESTE ",
                    "1º dTESSET" ,  //descrição
                    "ATENDIMENTO",                // atendimento
                    "FORMA DE PAGAMENTO",    //pagamentos
                    "CONTATO",                          //café
                    "",               // imagem
                    1,                                                   //wifi
                    "WHATSAPP",                 //whats
                    -24.108072,                     //latitude
                    -49.326836));



            for (int i=0; i<1; i++) {

                setLoading(true, progressBar, recyclerView);

                DatabaseReference eventReference = FirebaseDatabase.getInstance().getReference();

                // utilizado para isnerir o Array Adv na lista


                eventReference
                        .child(Constants.CHILD_NAME_ANUNCIOS)
                        .child(Constants.CHILD_NAME_CATEGORIES)
                        .child(categorieName)
                        .push()
                        .setValue(arrayAdv.get(i)).onSuccessTask(new SuccessContinuation<Void, Object>() {
                    @NonNull
                    @Override
                    public Task<Object> then(@Nullable Void aVoid) throws Exception {
                        setLoading(false, progressBar, recyclerView);

                        Toast.makeText(context,
                                context.getString(R.string.info_insert_data_success),
                                Toast.LENGTH_LONG).show();

                        return null;
                    }
                });

            }
        }
        }


    private static void setLoading(boolean isLoading, ProgressBar progressBar, RecyclerView recyclerView) {
        if (isLoading) {
            recyclerView.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
    }
}
