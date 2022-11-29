package com.example.hafta7uygulama1;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private ComboBox<String> cmbMeslek;

    @FXML
    private ComboBox<String> cmbMusteriSecimi;

    @FXML
    private ComboBox<String > cmbUrun;

    @FXML
    private DatePicker dtpDogumTarihi;

    @FXML
    private Spinner<Integer> spnAdet;

    @FXML
    private TextField txtAdSoyad;

    @FXML
    private TextField txtAdet;

    @FXML
    private TextField txtDogumTarihi;

    @FXML
    private TextField txtKimlikGoster;

    @FXML
    private TextField txtKimlikNo;

    @FXML
    private TextField txtMeslek;

    @FXML
    private TextField txtUrun;

    private ArrayList<Musteri> musteriler=new ArrayList<Musteri>();

    @FXML
    void musteriGoster(ActionEvent event) {
        int secilen=cmbMusteriSecimi.getSelectionModel().getSelectedIndex();

        if (secilen<0)
            return;

        txtKimlikGoster.setText(String.valueOf(musteriler.get(secilen).getKimlikNo()));
        txtMeslek.setText(musteriler.get(secilen).getMeslek());
        txtDogumTarihi.setText(String.valueOf(musteriler.get(secilen).getDogumTarihi()));
        txtUrun.setText(musteriler.get(secilen).getUrun());
        txtAdet.setText(String.valueOf(musteriler.get(secilen).getUrunAdet()));
    }

    @FXML
    void musteriKayit(ActionEvent event) {
        String adSoyad=txtAdSoyad.getText();
        String meslek=cmbMeslek.getValue();
        LocalDate dogumTarihi=dtpDogumTarihi.getValue();
        String urun=cmbUrun.getValue();

        int urunAdet= spnAdet.getValue();

        if (txtKimlikNo.getText().isEmpty() || txtAdSoyad.getText().isEmpty()){
            Alert musterimesaji=new Alert(Alert.AlertType.ERROR);
            musterimesaji.setTitle("Hata");
            musterimesaji.setHeaderText("Kimlik numarası veya Adsoyad girilmedi...");
            musterimesaji.show();
            return;
        }

        int kimlikNo = Integer.parseInt(txtKimlikNo.getText());

        for (int i = 0; i < musteriler.size(); i++) {
            if (kimlikNo==musteriler.get(i).getKimlikNo()){
                Alert musterimesaji=new Alert(Alert.AlertType.ERROR);
                musterimesaji.setTitle("Hata");
                musterimesaji.setHeaderText(musteriler.get(i).getKimlikNo()+" numaralı müşteri kayıtlı...");
                musterimesaji.show();
                return;
            }
        }

        Musteri musteri=new Musteri(adSoyad, meslek, dogumTarihi, urun, kimlikNo, urunAdet);
        musteriler.add(musteri);

        try {
            FileOutputStream fos=new FileOutputStream("musteribilgileri.dat");
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(musteriler);
            fos.close();
            oos.close();
            Alert bilgilendirmemesaji=new Alert(Alert.AlertType.CONFIRMATION);
            bilgilendirmemesaji.setTitle("Bilgi");
            bilgilendirmemesaji.setHeaderText(musteri.getAdSoyad()+" kişisi kaydedildi...");
            bilgilendirmemesaji.show();

            cmbMusteriSecimi.getItems().add(musteri.getAdSoyad());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void musteriSil(ActionEvent event) {
        try {
            int secilen=cmbMusteriSecimi.getSelectionModel().getSelectedIndex();

            if (secilen < 0 ) {
                Alert bilgilendirmemesaji=new Alert(Alert.AlertType.ERROR);
                bilgilendirmemesaji.setTitle("Hata");
                bilgilendirmemesaji.setHeaderText("Silinecek kayıt bulunamadı...");
                bilgilendirmemesaji.show();
                return;
            }

            String adsoyad=musteriler.get(secilen).getAdSoyad();
            cmbMusteriSecimi.getItems().remove(secilen);
            txtKimlikGoster.clear();
            txtMeslek.clear();
            txtDogumTarihi.clear();
            txtUrun.clear();
            txtAdet.clear();

            musteriler.remove(secilen);

            FileOutputStream fos=new FileOutputStream("musteribilgileri.dat");
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(musteriler);
            fos.close();
            oos.close();
            Alert bilgilendirmemesaji=new Alert(Alert.AlertType.INFORMATION);
            bilgilendirmemesaji.setTitle("Bilgi");
            bilgilendirmemesaji.setHeaderText(adsoyad+" kişisi silindi...");
            bilgilendirmemesaji.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //cmbMeslek.getItems().add("Bilgisayar Programcısı");
        cmbMeslek.setItems(FXCollections.observableArrayList("Bilgisayar Programcısı","Mühendis","Doktor",
                "Esnaf","İşçi","Memur","Polis","Öğretmen"));
        cmbUrun.setItems(FXCollections.observableArrayList("Dolap","Masa","Oturma Odası","Buzdolabı"));
        spnAdet.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,300,1,1));

        try {
            FileInputStream fis=new FileInputStream("musteribilgileri.dat");
            ObjectInputStream ois=new ObjectInputStream(fis);
            musteriler=(ArrayList<Musteri>) ois.readObject();
            ois.close();

            for (int i = 0; i < musteriler.size(); i++) {
                cmbMusteriSecimi.getItems().add(musteriler.get(i).getAdSoyad());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
