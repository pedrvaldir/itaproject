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
import com.example.valdir.appitarare.model.News;
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
                    "Title Fake Data Advertisement ",
                    "Description",  //descrição
                    "Hora Atendimento",                // atendimento
                    "Formas de pagamento",    //pagamentos
                    "Tel para contato",                          //café
                    "",               // imagem
                    1,                                                   //wifi
                    "whatsApp",                 //whats
                    -24.108072,                     //latitude
                    -49.326836));


            for (int i = 0; i < 1; i++) {

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

    public static void FakeDataNews(final Context context, boolean isNew,
                                    final ProgressBar progressBar, final RecyclerView recyclerView){
            setLoading(true, progressBar, recyclerView);

            ArrayList<News> arrayAdv = new ArrayList<>();
/*
            arrayAdv.add(new News(
                    "Acessibilidade: Com recursos próprios, gestão Heliton do Valle constrói calçada na Vila São João em Itararé (SP)",
                    "A Prefeitura de Itararé (SP), através da Secretaria Municipal de Serviços Gerais, deu início nessa quarta-feira (03) a construção de uma calçada na Vila São João em Itararé (SP).\n" +
                            "\n" +
                            "A iniciativa liga as ruas Pedro Ferreira dos Santos e 13 de maio e tem por objetivo a melhora do tráfego de pedestres que circulam pelo bairro.\n" +
                            "\n" +
                            "Segundo o prefeito, Heliton do Valle, acessibilidade faz parte das prioridades da gestão. “Além da melhora na circulação de pessoas, a calçada permite a passagem de carrinhos de bebês e até cadeiras de rodas que antes neste local eram intransitáveis. A gestão está empenhada em implantar a acessibilidade, tanto que já tem projetos futuros para isso”, explica.\n" +
                            "\n" +
                            "A obra tem 40 metros de comprimento por 1,5 metro de largura. O prazo para término é neste sábado (06), caso haja chuva a conclusão ficará para próxima segunda (08).\n",
                    "05/10/2018",
                    "http://www.itarare.sp.gov.br/",
                    "https://firebasestorage.googleapis.com/v0/b/itarare-1530419471926.appspot.com/o/noticias%2Fcalcadasaojoaoitarare.jpg?alt=media&token=b0677c38-6880-42d4-9d31-3b32d7e7ca47"));

        arrayAdv.add(new News(
                "Prefeitura de Itararé (SP) realiza trabalho de captação de pneus inservíveis",
                "A Prefeitura de Itararé (SP), por meio da Coordenadoria Municipal de Meio Ambiente, vem realizando, desde 2017, um trabalho de recolhimento e reciclagem de pneus inservíveis. A iniciativa tem como objetivo evitar o descarte inadequado de resíduos sólidos\n" +
                        "\n" +
                        "Segundo com o coordenador da Pasta, Luis Capelassi, o material é recolhido no Ecoponto, localizado à rua São Pedro, 2340, Vila Tonico Adolfo, para então, ser encaminhada para a reciclagem. “Os pneus podem ser utilizados para fazer tapetes, pavimentação ou até mesmo energia”, explica.\n" +
                        "\n" +
                        "De acordo com ele, o projeto é realizado através de uma parceria com a empresa Reciclamp e atende também aos municípios de Riversul (SP) e Bom Sucesso de Itararé (SP). “Esse convênio firmado entre as cidades é de suma importância, uma vez que atende às políticas nacionais de descarte de resíduos sólidos”, destaca.\n" +
                        "\n" +
                        "Saiba Mais - Mais informações podem ser obtidas junto à Coordenadoria Municipal de Meio Ambiente, à rua XV de Novembro, 83, Centro, ou através do telefone (15) 3532-8000. O horário de atendimento é de segunda a sexta-feira, das 8h às 17h.",
                "05/10/2018",
                "http://www.itarare.sp.gov.br/",
                "https://firebasestorage.googleapis.com/v0/b/itarare-1530419471926.appspot.com/o/noticias%2Fpneusitarare.jpeg?alt=media&token=cf30849b-9a45-4117-83c2-9b638404dd61"));

        arrayAdv.add(new News(
                "Prefeito de Itararé (SP) se reúne com gerência da Caixa Econômica Federal",
                "Nesta terça-feira (27), o prefeito de Itararé (SP), Heliton do Valle se reuniu com a Gerência Executiva de Governo (GIGOV) da Caixa Econômica Federal em Sorocaba. Na oportunidade, o prefeito falou sobre programas e projetos para melhorar a infraestrutura de Itararé. “Com a extinção do nome da cidade do Cadip podemos alçar voos mais longínquos. Estamos em busca deles e sabemos que a Caixa pode ser uma excelente parceira”, enfatizou Heliton.\n" +
                        "\n" +
                        "Conforme ele, o objetivo é fazer com que Itararé cresça e ofereça mais oportunidades. “Precisamos fortalecer nossa infraestrutura para angariamos mais benfeitorias e ter uma cidade com mais qualidade e produtividade”, destacou.\n" +
                        "\n" +
                        "O prefeito ainda enalteceu que a reunião foi muito produtiva. “Espero em breve noticiar os resultados deste encontro”, finalizou.",
                "05/10/2018",
                "http://www.itarare.sp.gov.br/",
                "gs://itarare-1530419471926.appspot.com/noticias/instituio__uma_excelente_parceira.jpg"));
*/
            for (int i = 0; i < 1; i++) {

                setLoading(true, progressBar, recyclerView);

                DatabaseReference eventReference = FirebaseDatabase.getInstance().getReference();

                // utilizado para isnerir o Array Adv na lista


                eventReference
                        .child(Constants.CHILD_NAME_NOTICIAS)
                        .child(Constants.CHILD_NAME_PUBLICACAO)
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
