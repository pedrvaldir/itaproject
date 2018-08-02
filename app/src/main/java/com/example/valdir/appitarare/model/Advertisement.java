package com.example.valdir.appitarare.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by VALDIR on 13/07/2018.
 */

public class Advertisement implements Parcelable {

    public Advertisement() {

    }

    private String id;
    private String titulo;
    private String descricao;
    private String horarAtendimento;
    private String formasPagamento;
    private String telContato;
    private int avaliado;
    private int imagem;
    private int wifi;
    private int whatsApp;
    private double longitude;
    private double latitude;

    public Advertisement(String titulo, String descricao, String atendimento,
                         String formasPagamento, String contato, int img, int avaliado,
                         int wifi, int whatsApp, Double latitude, Double longitude) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.horarAtendimento = atendimento;
        this.formasPagamento = formasPagamento;
        this.telContato = contato;
        this.imagem = img;
        this.avaliado = avaliado;
        this.wifi = wifi;
        this.whatsApp = whatsApp;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    private Advertisement(Parcel in) {
        id = in.readString();
        titulo = in.readString();
        descricao = in.readString();
        horarAtendimento = in.readString();
        formasPagamento = in.readString();
        telContato = in.readString();
        avaliado = in.readInt();
        imagem = in.readInt();
        wifi = in.readInt();
        whatsApp = in.readInt();
        latitude = in.readDouble();
        longitude = in.readDouble();
    }

    public static final Creator<Advertisement> CREATOR = new Creator<Advertisement>() {
        @Override
        public Advertisement createFromParcel(Parcel in) {
            return new Advertisement(in);
        }

        @Override
        public Advertisement[] newArray(int size) {
            return new Advertisement[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(titulo);
        parcel.writeString(descricao);
        parcel.writeString(horarAtendimento);
        parcel.writeString(formasPagamento);
        parcel.writeString(telContato);
        parcel.writeInt(avaliado);
        parcel.writeInt(imagem);
        parcel.writeInt(wifi);
        parcel.writeInt(whatsApp);
        parcel.writeDouble(longitude);
        parcel.writeDouble(latitude);
    }


    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getHorarAtendimento() {
        return horarAtendimento;
    }

    public String getFormasPagamento() {
        return formasPagamento;
    }

    public String getTelContato() {
        return telContato;
    }

    public int getAvaliado() {
        return avaliado;
    }

    public int getImagem() {
        return imagem;
    }

    public int getWifi() {
        return wifi;
    }

    public int getWhatsApp() {
        return whatsApp;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }
}
