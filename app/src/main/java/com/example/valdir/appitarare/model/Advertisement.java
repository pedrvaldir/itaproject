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
    private String imagem;
    private int wifi;
    private String whatsApp;
    private double longitude;
    private double latitude;

    public Advertisement(String titulo, String descricao, String atendimento,
                         String formasPagamento, String contato, String img,
                         int wifi, String whatsApp, Double latitude, Double longitude) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.horarAtendimento = atendimento;
        this.formasPagamento = formasPagamento;
        this.telContato = contato;
        this.imagem = img;
        this.wifi = wifi;
        this.whatsApp = whatsApp;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    private Advertisement(Parcel in) {
        id = in.readString();
        titulo = in.readString();
        descricao = in.readString();
        horarAtendimento = in.readString();
        formasPagamento = in.readString();
        telContato = in.readString();
        imagem = in.readString();
        wifi = in.readInt();
        whatsApp = in.readString();
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
        parcel.writeString(imagem);
        parcel.writeInt(wifi);
        parcel.writeString(whatsApp);
        parcel.writeDouble(latitude);
        parcel.writeDouble(longitude);
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

    public String getImagem() {
        return imagem;
    }

    public int getWifi() {
        return wifi;
    }

    public String getWhatsApp() {
        return whatsApp;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }
}
